package com.witch.pan.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.witch.common.pojo.BizException;
import com.witch.common.pojo.ResultCode;
import com.witch.pan.entity.config.AppConfig;
import com.witch.pan.entity.constants.Constants;
import com.witch.pan.entity.dto.FileUploadDTO;
import com.witch.pan.entity.dto.UserSpaceDTO;
import com.witch.pan.entity.enums.*;
import com.witch.pan.entity.vo.UploadResultVO;
import com.witch.pan.pojo.FileInfo;
import com.witch.pan.pojo.UserInfo;
import com.witch.pan.redis.RedisComponent;
import com.witch.pan.service.FileInfoService;
import com.witch.pan.service.UserInfoService;
import com.witch.pan.service.impl.FileInfoServiceImpl;
import com.witch.pan.utils.StringTools;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yuuki
 */
@Service
public class UserFileServiceImpl implements UserFileService {

    private final RedisComponent redisComponent;
    private final FileInfoService fileInfoService;
    private final UserInfoService userInfoService;
    private final AppConfig appConfig;
    private final FileInfoServiceImpl fileInfoServiceImpl;


    public UserFileServiceImpl(RedisComponent redisComponent, FileInfoService fileInfoService, UserInfoService userInfoService, AppConfig appConfig, FileInfoServiceImpl fileInfoServiceImpl) {
        this.redisComponent = redisComponent;
        this.fileInfoService = fileInfoService;
        this.userInfoService = userInfoService;
        this.appConfig = appConfig;
        this.fileInfoServiceImpl = fileInfoServiceImpl;
    }

    /**
     * 获取用户使用空间
     *
     * @param id 用户ID
     */
    @Override
    public UserSpaceDTO getUseSpace(String id) {
        UserSpaceDTO spaceDTO = redisComponent.getUserSpaceUse(id);
        if (null == spaceDTO) {
            spaceDTO = new UserSpaceDTO();
            // 查询当前用户已经上传文件大小总和
            Long useSpace = fileInfoService.getUseSpace(id);
            UserInfo userInfo = userInfoService.getById(id);
            spaceDTO.setUseSpace(useSpace);
            spaceDTO.setTotalSpace(userInfo.getTotalSpace());
            redisComponent.saveUserSpaceUse(id, spaceDTO);
        }
        return spaceDTO;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UploadResultVO uploadFile(String userId, MultipartFile file, FileUploadDTO fileDTO) throws IOException {
        UploadResultVO resultVO = new UploadResultVO();
        File tempFileFolder = null;
        try {
            //生成文件id
            String fileId = fileDTO.getId();
            if(!StringUtils.hasText(fileId)){
                fileId = StringTools.getRandomNumber(Constants.LENGTH_10);
            }
            resultVO.setId(fileId);

            // 查询用户已使用空间
            UserSpaceDTO userSpaceUse = getUseSpace(userId);
            if(userSpaceUse.getUseSpace() + file.getSize() > userSpaceUse.getTotalSpace()){
                throw new BizException(ResultCode.OUT_OF_SPACE);
            }

            //第一个分片，第一个文件
            if(fileDTO.getChunkIndex() == 0) {
                List<FileInfo> dbFileList = fileInfoService.list(new
                        LambdaQueryWrapper<FileInfo>()
                        .eq(FileInfo::getFileMd5, fileDTO.getFileMd5())
                        .eq(FileInfo::getStatus, FileStatusEnums.USING.getStatus())
                );
                if(!dbFileList.isEmpty()) {
                    // 服务器存在该文件，直接秒传
                    FileInfo dbFile = dbFileList.get(0);
                    // 判断文件大小
                    if(dbFile.getFileSize() + userSpaceUse.getUseSpace() > userSpaceUse.getTotalSpace()) {
                        throw new BizException(ResultCode.OUT_OF_SPACE);
                    }
                    boolean save = fileInfoService.saveFileInfoFromFile(userId, fileId, fileDTO, dbFile);
                    if(!save) {
                        throw new BizException("文件保存失败");
                    }
                    resultVO.setStatus(UploadStatusEnums.UPLOAD_SECONDS.getCode());
                    //更新空间使用情况
                    updateUserSpace(userId, dbFile.getFileSize(), userSpaceUse);
                    return resultVO;
                }
            }

            // 判断磁盘空间
            Long currentTempSize = redisComponent.getFileTempSize(userId, fileId);
            if (file.getSize() + currentTempSize + userSpaceUse.getUseSpace() > userSpaceUse.getTotalSpace()) {
                throw new BizException(ResultCode.OUT_OF_SPACE);
            }
            // 暂存临时目录
            String tempFolderName = appConfig.getProjectFolder() + Constants.FILE_FOLDER_TEMP;
            String currentUserFolderName = userId + fileId;

            tempFileFolder = new File(tempFolderName + currentUserFolderName);
            if (!tempFileFolder.exists()) {
                tempFileFolder.mkdirs();
            }
            File newFile = new File(tempFileFolder.getPath() + "/" + fileDTO.getChunkIndex());
            //文件上传到目录中
            file.transferTo(newFile);
            if (fileDTO.getChunkIndex() < fileDTO.getChunks() - 1) {
                resultVO.setStatus(UploadStatusEnums.UPLOADING.getCode());
                // 保存临时大小 也就是分片上传的大小
                redisComponent.saveFileTempSize(userId, fileId, file.getSize());
                return resultVO;
            }
            //只有一个分片, 或者最后一个分片走这里
            redisComponent.saveFileTempSize(userId, fileId, file.getSize());
            // 最后一个分片上传完成，记录是数据库，异步合并
            // 写入数据库
            fileInfoService.saveFileInfo(userId, fileId, fileDTO);
            // 更新用户使用空间
            Long totalSize = redisComponent.getFileTempSize(userId, fileId);
            updateUserSpace(userId, totalSize, userSpaceUse);
            // 上传完成
            resultVO.setStatus(UploadStatusEnums.UPLOAD_FINISH.getCode());

            String finalFileId = fileId;
            // 异步合并
            //注册了一个事务同步回调，这个回调会在当前事务提交后被执行。
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    fileInfoServiceImpl.transferFile(finalFileId, userId);
                }
            });
            return resultVO;
        } catch (Exception e) {
            if (tempFileFolder != null) {
                FileUtils.deleteDirectory(tempFileFolder);
            }
            throw new BizException("文件上传失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delFileBatch(String userId, String ids, Boolean isAdmin) {
        List<String> idList = Arrays.asList(ids.split(","));
        LambdaQueryWrapper<FileInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileInfo::getUserId, userId)
                .eq(FileInfo::getDeleted, FileDelFlagEnums.RECYCLE.getFlag())
                .in(!idList.isEmpty(), FileInfo::getId, idList);
        List<FileInfo> fileInfoList = fileInfoService.list(wrapper);

        List<String> delFileSubFolderFileList = new ArrayList<>();
        // 找到所选文件子目录文件ID
        for (FileInfo fileInfo : fileInfoList) {
            if (FileFolderTypeEnums.FOLDER.getType().equals(fileInfo.getFolderType())) {
                fileInfoServiceImpl.findAllSubFolderList(delFileSubFolderFileList, userId, fileInfo.getId(), FileDelFlagEnums.DEL.getFlag());
            }
        }
        // 删除所选文件子目录中的文件
        if (!delFileSubFolderFileList.isEmpty()) {
            fileInfoService.delFileBatch(userId, delFileSubFolderFileList, null, isAdmin ? null : FileDelFlagEnums.DEL.getFlag());
        }
        // 删除所选文件
        fileInfoService.delFileBatch(userId, null, idList, isAdmin ? null : FileDelFlagEnums.RECYCLE.getFlag());

        // 更新数据库
        Long useSpace = fileInfoService.getUseSpace(userId);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setUseSpace(useSpace);
        userInfoService.updateById(userInfo);

        // 更新Redis
        UserSpaceDTO userSpaceUse = redisComponent.getUserSpaceUse(userId);
        userSpaceUse.setUseSpace(useSpace);
        redisComponent.saveUserSpaceUse(userId, userSpaceUse);
    }

    /**
     * 更新用户空间，累加更新
     *
     * @param userId       用户ID
     * @param fileSize     文件大小
     * @param userSpaceDTO Redis
     */
    private void updateUserSpace(String userId, Long fileSize, UserSpaceDTO userSpaceDTO) {
        Boolean update = userInfoService.updateUserSpace(userId, fileSize, null);
        if (!update) {
            throw new BizException(ResultCode.OUT_OF_SPACE);
        }
        userSpaceDTO.setUseSpace(userSpaceDTO.getUseSpace() + fileSize);
        redisComponent.saveUserSpaceUse(userId, userSpaceDTO);
    }

}
