package com.witch.pan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.witch.common.pojo.BizException;
import com.witch.common.utils.FileUtil;
import com.witch.pan.annotation.LoginValidator;
import com.witch.pan.entity.config.AppConfig;
import com.witch.pan.entity.constants.Constants;
import com.witch.pan.entity.dto.DownloadFileDTO;
import com.witch.pan.entity.dto.MoveFileDTO;
import com.witch.pan.entity.dto.SessionShareDTO;
import com.witch.pan.entity.enums.FileCategoryEnums;
import com.witch.pan.entity.enums.FileDelFlagEnums;
import com.witch.pan.entity.enums.FileFolderTypeEnums;
import com.witch.pan.entity.query.FileInfoQuery;
import com.witch.pan.entity.vo.*;
import com.witch.pan.pojo.FileInfo;
import com.witch.pan.pojo.FileShare;
import com.witch.pan.pojo.UserInfo;
import com.witch.pan.redis.RedisComponent;
import com.witch.pan.service.FileInfoService;
import com.witch.pan.service.FileShareService;
import com.witch.pan.service.UserInfoService;
import com.witch.pan.utils.FileUtils;
import com.witch.pan.utils.StringTools;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Yuuki
 */
@RestController
@RequestMapping("/showShare")
@Valid
public class WebShareController {
    @Autowired
    private FileShareService fileShareService;

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private AppConfig appConfig;

    @LoginValidator(validated = false)
    @RequestMapping("/getShareInfo")
    public ShareInfoVO getShareInfo(String shareId) {
        return getShareInfoCommon(shareId);
    }

    private ShareInfoVO getShareInfoCommon(String shareId) {
        //先看分享表有没有
        FileShare share = fileShareService.getById(shareId);
//        if(share == null || (share.getExpireTime() != null && LocalDateTime.now().isAfter(share.getExpireTime()))) {
//            throw new BizException("分享文件已失效或不存在");
//        }

        FileInfo fileInfo = fileInfoService.getOne(new LambdaQueryWrapper<FileInfo>()
                .eq(FileInfo::getId, share.getFileId())
                .eq(FileInfo::getUserId, share.getUserId()));
//        //分享表有看实际上文件库里面有没有
//        if(fileInfo == null || !FileDelFlagEnums.USING.getFlag().equals(fileInfo.getDeleted())) {
//            throw new BizException("分享文件已失效或不存在");
//        }
        ShareInfoVO shareVo = new ShareInfoVO();
        UserInfo userInfo = userInfoService.getById(share.getUserId());
        shareVo.setNickName(userInfo.getNickname());
        shareVo.setAvatar(userInfo.getQqAvatar());
        if(fileInfo.getFilename().length() > 20){
            shareVo.setFileName(fileInfo.getFilename().substring(0, 20) + StringTools.getFileSuffix(fileInfo.getFilename()));
        } else {
            shareVo.setFileName(fileInfo.getFilename());
        }
        shareVo.setUserId(userInfo.getId());
        shareVo.setShareTime(share.getCreateTime());
        return shareVo;
    }

    @RequestMapping("/getShareLoginInfo")
    @LoginValidator(validated = false)
    public ShareInfoVO getShareLoginInfo(HttpSession session, @NotNull String shareId) {
        SessionShareDTO sessionShareDTO = getSessionShareFromSession(session, shareId);
        if (sessionShareDTO == null) {
            throw new BizException("系统错误");
        }
        //判断是否是当前用户分享的文件
        ShareInfoVO shareInfoVO = getShareInfoCommon(shareId);
        SessionWebUserVO userDto = getUserInfoFromSession(session);
        if (userDto != null && userDto.getId().equals(sessionShareDTO.getShareUserId())) {
            shareInfoVO.setCurrentUser(true);
        } else {
            shareInfoVO.setCurrentUser(false);
        }
        return shareInfoVO;
    }

    private SessionWebUserVO getUserInfoFromSession(HttpSession session) {
        SessionWebUserVO sessionWebUserVO = (SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY);
        return sessionWebUserVO;
    }

    private SessionShareDTO getSessionShareFromSession(HttpSession session, String shareId) {
        SessionShareDTO sessionShareDTO = (SessionShareDTO) session.getAttribute(Constants.SESSION_SHARE_KEY + shareId);
        return sessionShareDTO;
    }


    @RequestMapping("/checkShareCode")
    @LoginValidator(validated = false)
    public String checkShareCode(HttpSession session, @RequestBody @NotNull Map<String, String> data) {
        String shareId = data.get("shareId");
        String code = data.get("code");
        SessionShareDTO sessionShareDTO = fileShareService.checkShareCode(shareId, code);
        session.setAttribute(Constants.SESSION_SHARE_KEY + shareId, sessionShareDTO);
        return "success";
    }


/*    @GetMapping("/loadFileList")
    @LoginValidator(validated = false)
    public Map<String, Object> loadFileList(HttpSession session,
                                            @NotNull String shareId,
                                            FileInfoQuery query,
                                            String filePid) {
        // 验证分享会话信息
        SessionShareDTO shareSessionDto = checkShare(session, shareId);
        if(!StringTools.isEmpty(filePid) && !Constants.ZERO_STR.equals(filePid)) {
            // 防止恶意调用接口
            fileInfoService.checkRootFilePid(shareSessionDto.getFileId(), shareSessionDto.getShareUserId(), filePid);
            query.setFilePid(filePid);
        } else {
            query.setFileId(shareSessionDto.getFileId());
        }

        // 分页
        Page<FileInfo> pageParam = new Page<>(query.getPage(), query.getLimit());
        SessionShareDTO sessionShareDTO = getSessionShareFromSession(session, shareId);
        query.setUserId(sessionShareDTO.getShareUserId());
        query.setDeleted(FileDelFlagEnums.USING.getFlag());

        IPage<FileInfoVO> fileInfoVOIPage = fileInfoService.pageInfo(pageParam, query);
        List<FileInfoVO> records = fileInfoVOIPage.getRecords();

        // 构造返回对象
        Map<String, Object> result = new HashMap<>();
        result.put("list", records);
        result.put("totalCount", fileInfoVOIPage.getTotal());
        result.put("pageSize", query.getLimit());
        result.put("pageNo", query.getPage());

        return result;
    }*/

    @GetMapping("/loadFileList")
    @LoginValidator(validated = false)
    public List<FileInfoVO> loadFileList(HttpSession session,
                                         @NotNull String shareId,
                                         FileInfoQuery query,
                                         String filePid) {
        SessionShareDTO shareSessionDto = checkShare(session, shareId);
        if(!StringTools.isEmpty(filePid) && !Constants.ZERO_STR.equals(filePid)) {
            fileInfoService.checkRootFilePid(shareSessionDto.getFileId(), shareSessionDto.getShareUserId(), filePid);
            query.setFilePid(shareSessionDto.getFileId());
        } else {
            query.setFileId(shareSessionDto.getFileId());
        }
        Page<FileInfo> pageParam = new Page<>(query.getPage(), query.getLimit());
        SessionShareDTO sessionShareDTO = getSessionShareFromSession(session, shareId);
        query.setUserId(sessionShareDTO.getShareUserId());
        query.setDeleted(FileDelFlagEnums.USING.getFlag());
        IPage<FileInfoVO> fileInfoVOIPage = fileInfoService.pageInfo(pageParam, query);
        return fileInfoVOIPage.getRecords();
    }

    //实时校验分享信息
    private SessionShareDTO checkShare(HttpSession session, String shareId) {
        SessionShareDTO shareSessionDto = getSessionShareFromSession(session, shareId);
        if(shareSessionDto == null) {
            throw new BizException("分享验证失效，请重新验证");
        }
        if(shareSessionDto.getExpireTime() != null && LocalDateTime.now().isAfter(shareSessionDto.getExpireTime())) {
            throw new BizException("分享验证失效，请重新验证");
        }
        return shareSessionDto;
    }

    @GetMapping("/getFolderInfo")
    @LoginValidator(validated = false)
    public List<FileInfoVO> getFolderInfo(HttpSession session,
                                @RequestParam @NotNull Map<String, String> data) {
        String shareId = data.get("shareId");
        String path = data.get("path");
        SessionShareDTO sessionShareDTO = checkShare(session, shareId);
        String[] ids = path.split("/");
        return fileInfoService.listFolderByIds(ids);
    }

    @GetMapping("/getFile/{shareId}/{fileId}")
    @LoginValidator(validated = false)
    public void getFile(HttpSession session,
                        HttpServletResponse response,
                        @PathVariable("shareId") @NotBlank String shareId,
                        @PathVariable("fileId") @NotBlank String fileId) {
        SessionShareDTO sessionShareDTO = checkShare(session, shareId);
        String filePath = fileInfoService.getFilePath(fileId, sessionShareDTO.getShareUserId());
        FileUtil.readFile(response, filePath);
    }

    // 获取视频文件
    @LoginValidator(validated = false)
    @GetMapping("/ts/getVideoInfo/{shareId}/{fileId}")
    public void getVideoInfo(HttpSession session,
                             HttpServletResponse response,
                             @PathVariable("shareId") @NotBlank String shareId,
                             @PathVariable("fileId") @NotBlank String fileId) {
        SessionShareDTO sessionShareDTO = checkShare(session, shareId);
        String filePath = fileInfoService.getFilePath(fileId, sessionShareDTO.getShareUserId());
        FileUtil.readFile(response, filePath);
    }


    // 创建下载链接
    @LoginValidator(validated = false)
    @GetMapping("/createDownloadUrl/{shareId}/{fileId}")
    public Map<String, String> createDownloadUrl(HttpSession session,
                                                 @PathVariable("shareId") @NotBlank String shareId,
                                                 @PathVariable("fileId") @NotBlank String fileId) {
        SessionShareDTO sessionShareDTO = checkShare(session, shareId);
        // 需要校验登陆状态
        String code = fileInfoService.createDownloadUrl(sessionShareDTO.getShareUserId(), fileId);
        HashMap<String, String> data = new HashMap<>();
        data.put("code", code);
        return data;
    }

    // 下载文件，无需登陆
    @LoginValidator(validated = false)
    @GetMapping("/download/{code}")
    public void download(HttpServletRequest request,
                         HttpServletResponse response,
                         @PathVariable @NotEmpty String code) throws UnsupportedEncodingException {
        DownloadFileDTO fileDTO = redisComponent.getDownloadCode(code);
        if (null == fileDTO) {
            return;
        }
        String filePath = appConfig.getProjectFolder() + Constants.FILE_FOLDER_FILE + fileDTO.getFilePath();
        String filename = fileDTO.getFilename();
        FileUtils.writeDownloadFile(response, request, filename, filePath);
    }

    @LoginValidator(validated = false)
    @RequestMapping("/saveShare")
    public void saveShare(HttpSession session,
                          @NotNull String shareId,
                          @NotNull String shareFileIds,
                          @NotNull String myFolderId) {
        SessionShareDTO shareSessionDTO = checkShare(session, shareId);
        SessionWebUserVO webUserVO = getUserInfoFromSession(session);
        //自己保存自己
        if(shareSessionDTO.getShareUserId().equals(webUserVO.getId())) {
            throw new BizException("不能保存自己的分享");
        }
        fileInfoService.saveShare(shareSessionDTO.getFileId(), shareFileIds, myFolderId, shareSessionDTO.getShareUserId(),webUserVO.getId());
    }

    @LoginValidator(validated = false)
    @GetMapping("/loadAllFolder")
    public List<FileInfoVO> loadAllFolder(HttpSession session,
                                          @NotEmpty String filePid) {
        SessionWebUserVO user = (SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY);
        LambdaQueryWrapper<FileInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileInfo::getFilePid, filePid)
                .eq(FileInfo::getUserId, user.getId())
                .eq(FileInfo::getFolderType, FileFolderTypeEnums.FOLDER.getType());
        List<FileInfo> list = fileInfoService.list(wrapper);
        return list.stream().map(item -> {
            FileInfoVO fileInfoVO = new FileInfoVO();
            BeanUtils.copyProperties(item, fileInfoVO);
            return fileInfoVO;
        }).collect(Collectors.toList());
    }

    @LoginValidator(validated = false)
    @PostMapping("/cancelShare")
    public void cancelShare(HttpSession session,@RequestBody @NotEmpty Map<String, String> shareIds) {
        String shareId = shareIds.get("shareIds");
        String userId = ((SessionWebUserVO) session.getAttribute(Constants.SESSION_KEY)).getId();
        fileShareService.deleteShareBatch(shareId, userId);
    }


}
