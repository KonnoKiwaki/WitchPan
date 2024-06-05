package com.witch.pan.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.witch.common.annotation.ResponseResult;
import com.witch.common.pojo.BizException;
import com.witch.common.utils.FileUtil;
import com.witch.common.utils.MD5;
import com.witch.pan.annotation.LoginValidator;
import com.witch.pan.entity.config.AppConfig;
import com.witch.pan.entity.dto.*;
import com.witch.pan.entity.constants.Constants;
import com.witch.pan.entity.vo.SessionWebUserVO;
import com.witch.pan.pojo.UserInfo;
import com.witch.pan.redis.RedisComponent;
import com.witch.pan.redis.RedisUtils;
import com.witch.pan.service.EmailCodeService;
import com.witch.pan.service.UserInfoService;
import com.witch.pan.service.common.UserFileService;
import com.witch.pan.utils.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author Yuuki
 * @since 2024-05-16
 */
@RestController("userInfoController")
@ResponseResult
@Validated
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final EmailCodeService emailCodeService;
    private final UserFileService userFileService;

    @Autowired
    AppConfig appConfig;
    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private RedisUtils redisUtils;

    public UserInfoController(UserInfoService userInfoService, EmailCodeService emailCodeService, UserFileService userFileService) {
        this.userInfoService = userInfoService;
        this.emailCodeService = emailCodeService;
        this.userFileService = userFileService;
    }

    /**
     * 验证码模块
     */
    @GetMapping("/checkCode")
    public void checkCode(HttpServletResponse response, HttpSession session, Integer type) throws IOException {
        CreateImageCode vCode = new CreateImageCode(130, 38, 5, 10);
        //不缓存验证码图片
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //设置响应内容的类型为JPEG格式
        response.setContentType("image/jpeg");

        String code = vCode.getCode();
        if (type == null || type == 0) {
            //普通验证码
            session.setAttribute(Constants.CHECK_CODE_KEY, code);
        } else {
            //邮箱验证码
            session.setAttribute(Constants.CHECK_CODE_KEY_EMAIL, code);
        }
        vCode.write(response.getOutputStream());
    }

    /**
     * 发送邮箱验证码
     */
    @PostMapping("/sendEmailCode")
    public void sendEmailCode(HttpSession session,
                              @NotNull String email,
                              @NotNull String checkCode,
                              @NotNull Integer type) {

        try {
            if(!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY_EMAIL))){
                throw new BizException("图片验证码错误");
            }
            // 0:注册 1：找回
            if(type.equals(Constants.ZERO)) {
                UserInfo userInfo = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getEmail, email));
                if (null != userInfo) {
                    throw new BizException("邮箱已存在");
                }
            }
            emailCodeService.sendEmailCode(email, type);
        }
        finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY_EMAIL);
        }
    }

    @PostMapping("/register")
    public void register(HttpSession session, @Valid @RequestBody RegisterDTO registerDto) {
        try {
            if(!registerDto.getCheckCode().equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BizException("图片验证码错误");
            }
            userInfoService.register(registerDto);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

    @PostMapping("/login")
    public SessionWebUserVO login(HttpSession session,
                                  @Valid @RequestBody LoginDTO loginDto) {
        try {
            if(!loginDto.getCheckCode().equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BizException("图片验证码错误");
            }
            //存放登陆效验所需要的信息
            SessionWebUserVO sessionWebUserVO = userInfoService.login(loginDto.getEmail(), loginDto.getPassword());
            session.setAttribute(Constants.SESSION_KEY, sessionWebUserVO);

            return sessionWebUserVO;

        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

    @PostMapping("/resetPwd")
    public void resetPwd(HttpSession session,
                         @Valid @RequestBody ResetPwdDTO resetPwdDTO) {
        try {
            if(!resetPwdDTO.getCheckCode().equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BizException("图片验证码错误");
            }
            userInfoService.resetPwd(resetPwdDTO.getEmail(), resetPwdDTO.getPassword(), resetPwdDTO.getEmailCode());
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY);
        }
    }

    @GetMapping("/getAvatar/{userId}")
    public void getAvatar(HttpServletResponse response, @PathVariable @NotNull String userId) {
        String avatarFolderName = Constants.FILE_FOLDER_FILE + Constants.FILE_FOLDER_AVATAR_NAME;
        File folder = new File(appConfig.getProjectFolder() + avatarFolderName);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String avatarPath = appConfig.getProjectFolder() + avatarFolderName + userId + Constants.AVATAR_SUFFIX;
        File file = new File(avatarPath);
        if (!file.exists()) {
            // 默认头像
            String defaultAvatarPath = appConfig.getProjectFolder() + avatarFolderName + Constants.AVATAR_DEFAULT;
            File defaultAvatar = new File(defaultAvatarPath);
            if (!defaultAvatar.exists()) {
                printNoDefaultImage(response);
                return;
            }
            avatarPath = defaultAvatarPath;
        }
        response.setContentType("image/jpg");
        FileUtil.readFile(response, avatarPath);
    }

    @GetMapping("/getUserInfo")
    @LoginValidator
    public SessionWebUserVO getUserInfo(HttpSession session) {
        return (SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY);
    }

    @GetMapping("/getUseSpace")
    @LoginValidator
    public UserSpaceDTO getUseSpace(HttpSession session) {
        SessionWebUserVO userVo = (SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY);
        return userFileService.getUseSpace(userVo.getId());
    }

    @PostMapping("/logout")
    @LoginValidator
    public void logout(HttpSession session) {
        SessionWebUserVO userVo = (SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY);
        UserInfo userInfo = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getId, userVo.getId()));
        userInfo.setLastLoginTime(LocalDateTime.now());
        userInfoService.updateById(userInfo);
        session.invalidate();
    }

    @PostMapping("/updateUserAvatar")
    @LoginValidator
    public void updateUserAvatar(HttpSession session, MultipartFile avatar) {
        SessionWebUserVO userVo = (SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY);
        String baseFolder = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE;
        File targetFileFolder = new File(baseFolder + Constants.FILE_FOLDER_AVATAR_NAME);
        if (!targetFileFolder.exists()) {
            targetFileFolder.mkdirs();
        }
        File targetFile = new File(targetFileFolder.getPath() + "/" + userVo.getId() + Constants.AVATAR_SUFFIX);
        try {
            avatar.transferTo(targetFile);
        } catch (IOException e) {
            throw new BizException("头像更新失败");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userVo.getId());
        userInfo.setQqAvatar("");
        userInfoService.updateById(userInfo);
        userVo.setAvatar(null);
        session.setAttribute(Constants.SESSION_KEY, userVo);
    }

    @PostMapping("/updatePassword")
    @LoginValidator
    public void updatePassword(HttpSession session,
                               @Size(min = 8, max = 18, message = "密码长度8-18") String password) {
        SessionWebUserVO userVo = (SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userVo.getId());
        userInfo.setPassword(MD5.encrypt(password));
        userInfoService.updateById(userInfo);
    }

    @PostMapping("/qqLogin")
    public String qqLoginLogin(HttpSession session, String callbackUrl) throws UnsupportedEncodingException {
        String state = StringTools.getRandomNumber(Constants.LENGTH_20);
        //callbackUrl 是如果登陆时间过期，重新登录后，回到原来的页面
        if(!StringTools.isEmpty(callbackUrl)) {
            session.setAttribute(state, callbackUrl);
        }
        String url = String.format(appConfig.getQqAuthorizationUrl(), appConfig.getQqAppId(), URLEncoder.encode(appConfig.getQqRedirectUrl(), "UTF-8"), state);
        return url;
    }

    @RequestMapping("/qqLogin/callback")
    public Map<String, Object> qqLoginCallback(HttpSession session, @NotNull String code, @NotNull String state) {
       Map<String, Object> result = new HashMap<>();
       SessionWebUserVO sessionWebUserVo = userInfoService.qqLogin(code);
       session.setAttribute(Constants.SESSION_KEY, sessionWebUserVo);
       result.put("callbackUrl", session.getAttribute(state));
       result.put("userInfo", sessionWebUserVo);
       return result;
    }

    private void printNoDefaultImage(HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println("请在头像目录下放置默认头像,起名为default_avatar.jpg");
            writer.close();
        } catch (IOException e) {
            throw new BizException("输出默认头像失败");
        }
    }
}

