package com.witch.pan.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.witch.pan.entity.enums.FileDelFlagEnums;
import com.witch.pan.entity.query.FileInfoQuery;
import com.witch.pan.pojo.FileInfo;
import com.witch.pan.pojo.UserInfo;
import com.witch.pan.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FileCleanTask {
    @Autowired
    private FileInfoService fileInfoService;

    @Scheduled(fixedDelay = 1000 * 60 * 3)
    public void execute() {
        List<FileInfo> fileInfoList = fileInfoService.list(new LambdaQueryWrapper<FileInfo>()
                .eq(FileInfo::getDeleted, FileDelFlagEnums.RECYCLE.getFlag()));

        // 找到创建时间超过13天的文件并删除
        long thirteenDaysInMillis = 13L * 24 * 60 * 60 * 1000; // 13 days
        LocalDateTime currentTime = LocalDateTime.now();

        fileInfoList.forEach(fileInfo -> {
            LocalDateTime createTime = fileInfo.getCreateTime();
            Instant createInstant = createTime.atZone(ZoneId.systemDefault()).toInstant();
            long createTimestamp = createInstant.toEpochMilli();

            if (createTimestamp + thirteenDaysInMillis < System.currentTimeMillis()) {
                fileInfoService.removeById(fileInfo.getId());
            }
        });

        Map<String, List<FileInfo>> fileInfoMap = fileInfoList.stream().collect(Collectors.groupingBy(FileInfo::getUserId));
        for(Map.Entry<String, List<FileInfo>> entry : fileInfoMap.entrySet()) {
            List<String> fileIds = entry.getValue().stream().map(p -> p.getId()).collect(Collectors.toList());
            fileInfoService.delFileBatch(entry.getKey(), null, fileIds, 1);
        }
    }
}
