package com.witch.pan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.witch.pan.entity.query.FileInfoQuery;
import com.witch.pan.pojo.FileInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文件信息表 Mapper 接口
 * </p>
 *
 * @author Yuuki
 * @since 2023-05-19
 */
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    IPage<FileInfo> selectPageInfo(Page<FileInfo> pageParam, @Param("query") FileInfoQuery query);

    Long selectUseSpace(@Param("userId") String userId);

    Integer updateFileDelFlagBatch(@Param("bean") FileInfo fileInfo,
                                   @Param("userId") String userId,
                                   @Param("pidList") List<String> delFilePidList,
                                   @Param("idList") List<String> idList,
                                   @Param("oldDelFlag") Integer oldDelFlag);

    Integer delFileBatch(@Param("userId") String userId,
                         @Param("pidList") List<String> delFilePidList,
                         @Param("idList") List<String> idList,
                         @Param("oldDelFlag") Integer oldDelFlag);
}
