package com.witch.pan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.witch.common.pojo.BizException;
import com.witch.common.pojo.ResultCode;
import com.witch.pan.entity.constants.Constants;
import com.witch.pan.entity.enums.ShareValidTypeEnums;
import com.witch.pan.entity.vo.FileShareVo;
import com.witch.pan.pojo.FileShare;
import com.witch.pan.mapper.FileShareMapper;
import com.witch.pan.service.FileShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.witch.pan.utils.StringTools;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yuuki
 * @since 2023-07-14
 */
@Service
public class FileShareServiceImpl extends ServiceImpl<FileShareMapper, FileShare> implements FileShareService {

    @Override
    public IPage<FileShareVo> pageInfo(Page<FileShare> pageParam, String userId) {
        LambdaQueryWrapper<FileShare> wrapper = new LambdaQueryWrapper();
        wrapper.eq(FileShare::getUserId, userId).orderByDesc(FileShare::getCreateTime);
        IPage<FileShare> iPage = this.page(pageParam, wrapper);
        List<FileShare> records = iPage.getRecords();

        List<FileShareVo> fileShareVos = records.stream().map(item -> {
            FileShareVo fileShareVo = new FileShareVo();
            BeanUtils.copyProperties(item, fileShareVo);
            return fileShareVo;
        }).collect(Collectors.toList());

        IPage<FileShareVo> page = new Page(pageParam.getCurrent(), pageParam.getSize(), iPage.getTotal());
        page.setRecords(fileShareVos);
        return page;
    }

    @Override
    public FileShare saveShare(FileShare fileShare) {
        ShareValidTypeEnums typeEnums = ShareValidTypeEnums.getByType(fileShare.getValidType());
        if (null == typeEnums) {
            throw new BizException(ResultCode.PARAM_IS_INVALID);
        }
        if (ShareValidTypeEnums.FOREVER != typeEnums) {
            fileShare.setExpireTime(LocalDateTime.now().plusDays(typeEnums.getDays()));
        }
        if (!StringUtils.hasText(fileShare.getCode())) {
            fileShare.setCode(StringTools.getRandomString(Constants.LENGTH_5));
        }
        fileShare.setId(StringTools.getRandomString(Constants.LENGTH_20));
        save(fileShare);
        return fileShare;
    }

    @Override
    public void deleteShareBatch(String shareIds, String userId) {
        LambdaQueryWrapper<FileShare> wrapper = new LambdaQueryWrapper<>();
        List<String> ids = Arrays.asList(shareIds.split(","));
        wrapper.in(FileShare::getId, ids);
        remove(wrapper);
    }
}
