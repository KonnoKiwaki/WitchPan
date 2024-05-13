package com.witch.pan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.witch.pan.entity.vo.FileShareVo;
import com.witch.pan.pojo.FileShare;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yuuki
 * @since 2023-07-14
 */
public interface FileShareService extends IService<FileShare> {

    IPage<FileShareVo> pageInfo(Page<FileShare> pageParam, String userId);

    FileShare saveShare(FileShare fileShare);

    void deleteShareBatch(String shareIds, String userId);
}
