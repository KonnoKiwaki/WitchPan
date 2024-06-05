package com.witch.pan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.witch.common.pojo.BizException;
import com.witch.common.utils.MD5;
import com.witch.pan.entity.dto.*;
import com.witch.pan.entity.query.BaseParam;
import com.witch.pan.entity.vo.UserInfoVO;
import com.witch.pan.pojo.FileInfo;
import com.witch.pan.redis.RedisComponent;
import com.witch.pan.entity.enums.UserStatusEnum;
import com.witch.pan.entity.config.AppConfig;
import com.witch.pan.entity.constants.Constants;
import com.witch.pan.entity.vo.SessionWebUserVO;
import com.witch.pan.pojo.UserInfo;
import com.witch.pan.mapper.UserInfoMapper;
import com.witch.pan.service.EmailCodeService;
import com.witch.pan.service.FileInfoService;
import com.witch.pan.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.witch.pan.utils.JsonUtils;
import com.witch.pan.utils.OKHttpUtils;
import com.witch.pan.utils.StringTools;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author Yuuki
 * @since 2024-05-16
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    private final EmailCodeService emailCodeService;
    private final RedisComponent redisComponent;
    private final FileInfoService fileInfoService;

    @Autowired
    private AppConfig appConfig;


    public UserInfoServiceImpl(EmailCodeService emailCodeService, RedisComponent redisComponent, FileInfoService fileInfoService) {
        this.emailCodeService = emailCodeService;
        this.redisComponent = redisComponent;
        this.fileInfoService = fileInfoService;
    }

    /**
     * 注册
     *
     * @param registerDto 注册信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterDTO registerDto) {
        //验证邮箱是否存在
        UserInfo emailUser = getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, registerDto.getEmail()));
        if(emailUser != null){
            throw new BizException("邮箱账号已经存在");
        }
        //验证这个邮箱的名称是否存在
        UserInfo nickNameUser = getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getNickname, registerDto.getNickname()));
        if(nickNameUser != null){
            throw new BizException("此邮箱已经存在此昵称");
        }
        //验证邮箱验证码
        emailCodeService.checkCode(registerDto.getEmail(), registerDto.getEmailCode());

        //存放用户信息
        String userId = StringTools.getRandomNumber(Constants.LENGTH_10);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setNickname(registerDto.getNickname());
        userInfo.setEmail(registerDto.getEmail());
        userInfo.setPassword(MD5.encrypt(registerDto.getPassword()));
        userInfo.setUseSpace(0L);
        SysSettingsDTO sysSettingDto = redisComponent.getSysSettingDto();
        userInfo.setTotalSpace(sysSettingDto.getUserInitSpace() * Constants.GB);
        this.save(userInfo);
    }

    @Override
    public SessionWebUserVO login(String email, String password) {
        UserInfo userInfo = getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, email));
        if(userInfo == null || !userInfo.getPassword().equals((MD5.encrypt(password)))){
            throw new BizException("账号或密码错误");
        }
        if(UserStatusEnum.DISABLE.status().equals(userInfo.getStatus())) {
            throw new BizException("账号已被禁用");
        }
        SessionWebUserVO sessionWebUserVO = new SessionWebUserVO();
        sessionWebUserVO.setNickname(userInfo.getNickname());
        sessionWebUserVO.setId(userInfo.getId());
        // 判断登陆用户的email是否是管理员email
        sessionWebUserVO.setIsAdmin(ArrayUtils.contains(appConfig.getEmails().split(","), email));
        sessionWebUserVO.setAvatar(userInfo.getQqAvatar());

        //用户空间
        UserSpaceDTO userSpace = new UserSpaceDTO();
        // 查询当前用户已经上传文件大小总和
        Long useSpace = fileInfoService.getUseSpace(userInfo.getId());
        userSpace.setUseSpace(useSpace);
        userSpace.setTotalSpace(userInfo.getTotalSpace());
        //使用空间信息存redis里面
        redisComponent.saveUserSpaceUse(userInfo.getId(), userSpace);

        return sessionWebUserVO;
    }

    /**
     * 重置密码
     *
     * @param email     邮箱
     * @param password  密码
     * @param emailCode 邮箱验证码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPwd(String email, String password, String emailCode) {
        UserInfo emailInfo = getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, email));
        if(emailInfo == null) {
            throw new BizException("邮箱账号不存在");
        }
        emailCodeService.checkCode(email, emailCode);
        if(MD5.encrypt(password).equals(emailInfo.getPassword())) {
            throw new BizException("新密码不能与旧密码相同");
        }
        emailInfo.setPassword(MD5.encrypt(password));
        updateById(emailInfo);
    }

    @Override
    public Boolean updateUserSpace(String userId, Long useSpace, Long totalSpace) {
        Integer update = baseMapper.updateUserSpace(userId, useSpace, totalSpace);
        return update != null && update >= 1;
    }

    @Override
    public SessionWebUserVO qqLogin(String code) {
        //通过回调code获取accessToken
        String accessToken = getQqAccessToken(code);

        //获取qqOpenId
        String openId = getQqOpenId(accessToken);
        String avatar = null;
        UserInfo user = getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getQqOpenId, openId));
        QQInfoDTO qqUserInfo = getQqUserInfo(accessToken, openId);

        //没有用户，注册用户
        if(user == null) {

            //获取用户的qq基本信息
            user = new UserInfo();
            String nickName = qqUserInfo.getNickname();
            nickName = nickName.length() > Constants.LENGTH_20 ? nickName.substring(0, 20) : nickName;
            avatar = StringTools.isEmpty(qqUserInfo.getFigureurl_qq_2()) ? qqUserInfo.getFigureurl_qq_1() : qqUserInfo.getFigureurl_qq_2();

            user.setQqOpenId(openId);
            user.setQqAvatar(avatar);
            user.setCreateTime(LocalDateTime.now());
            user.setLastLoginTime(LocalDateTime.now());
            user.setId(StringTools.getRandomNumber(Constants.LENGTH_10));
            user.setStatus(UserStatusEnum.ENABLE.status());
            user.setUseSpace(0L);
            user.setTotalSpace(redisComponent.getSysSettingDto().getUserInitSpace() * Constants.MB);
            user.setNickname(nickName);
            baseMapper.insert(user);
        } else {
            user = baseMapper.selectByQqOpenId(openId);
            //再次登录用户qq头像可能更换
            avatar = StringTools.isEmpty(qqUserInfo.getFigureurl_qq_2()) ? qqUserInfo.getFigureurl_qq_1() : qqUserInfo.getFigureurl_qq_2();
            //再次登录用户qq昵称可能更换
            String nickname = qqUserInfo.getNickname();
            nickname = nickname.length() > Constants.LENGTH_20 ? nickname.substring(0, 20) : nickname;

            user.setNickname(nickname);
            user.setQqAvatar(avatar);
            user.setLastLoginTime(LocalDateTime.now());
            baseMapper.updateById(user);
        }

        //返回前端显示的数据
        SessionWebUserVO sessionWebUserVO = new SessionWebUserVO();
        sessionWebUserVO.setAvatar(avatar);
        sessionWebUserVO.setId(user.getId());
        sessionWebUserVO.setNickname(user.getNickname());
        sessionWebUserVO.setIsAdmin(ArrayUtils.contains(appConfig.getEmails().split(","), user.getEmail() == null ? "" : user.getEmail()));

        //用户空间
        UserSpaceDTO userSpace = new UserSpaceDTO();
        // 查询当前用户已经上传文件大小总和
        Long useSpace = fileInfoService.getUseSpace(user.getId());
        userSpace.setUseSpace(useSpace);
        userSpace.setTotalSpace(user.getTotalSpace());
        redisComponent.saveUserSpaceUse(user.getId(), userSpace);

        return sessionWebUserVO;
    }


    @Override
    public List<UserInfoVO> loadUserList(BaseParam userInfoQuery) {
        Page<UserInfo> page = new Page<>(userInfoQuery.getPageNo(), userInfoQuery.getPageSize());
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(UserInfo::getCreateTime);
        IPage<UserInfo> userInfoIPage = baseMapper.selectPage(page, queryWrapper);
        List<UserInfo> userList = userInfoIPage.getRecords();
        List<UserInfoVO> users = userList.stream().map(item -> {
            UserInfoVO userVO = new UserInfoVO();
            BeanUtils.copyProperties(item, userVO);
            return userVO;
        }).collect(Collectors.toList());

        return users;
    }

    private String getQqAccessToken(String code) {
        String accessToken = null;
        String url = null;
        try {
            url = String.format(appConfig.getQqAccessTokenUrl(), appConfig.getQqAppId(), appConfig.getQqAppKey(), code, URLEncoder.encode(appConfig.getQqRedirectUrl(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String tokenResult = OKHttpUtils.getRequest(url);
        if(tokenResult == null || tokenResult.indexOf(Constants.VIEW_OBJ_RESULT_KEY) != -1) {
            throw new BizException("获取qqToken失败");
        }
        String[] params = tokenResult.split("&");
        if(params != null && params.length > 0) {
            for (String param : params) {
                if(param.indexOf("access_token") != -1) {
                    accessToken = param.split("=")[1];
                    break;
                }
            }
        }
        return accessToken;
    }

    private String getQqOpenId(String accessToken) {
        String url = String.format(appConfig.getQqOpenIdUrl(), accessToken);
        String openIdResult = OKHttpUtils.getRequest(url);
        String tmpJson = this.getQqResp(openIdResult);
        if(tmpJson == null) {
            throw new BizException("调用qq接口获取qqOpenId失败");
        }
        Map jsonData = JsonUtils.convertJson2Obj(tmpJson, Map.class);
        if(jsonData == null || jsonData.containsKey(Constants.VIEW_OBJ_RESULT_KEY)) {
            throw new BizException("调用qq接口获取qqOpenId失败");
        }
        return String.valueOf(jsonData.get("openid"));
    }

    private String getQqResp(String result) {
        if(StringUtils.isNotBlank(result)) {
            int pos = result.indexOf("callback");
            if(pos != -1) {
                int start = result.indexOf("(");
                int end = result.lastIndexOf(")");
                String jsonStr = result.substring(start + 1, end - 1);
                return jsonStr;
            }
        }
        return null;
    }

    private QQInfoDTO getQqUserInfo(String accessToken, String qqOpenId) {
        String url = String.format(appConfig.getQqUserInfoUrl(), accessToken, appConfig.getQqAppId(), qqOpenId);
        String response = OKHttpUtils.getRequest(url);
        if(StringUtils.isNotBlank(response)) {
            QQInfoDTO qqInfo = JsonUtils.convertJson2Obj(response, QQInfoDTO.class);
            if(qqInfo.getRet() != 0) {
                throw new BizException("调用qq接口获取用户信息异常");
            }
            return qqInfo;
        }
        throw  new BizException("调用qq接口获取用户信息异常");
    }
}
