package com.witch.pan.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author Yuuki
 * 插入和更新数据时，自动填充实体类中的创建时间和更新时间字段的配置类
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean createTime = metaObject.hasSetter("createTime");
        boolean updateTime = metaObject.hasSetter("updateTime");
        if (updateTime) {
            strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        }
        if (createTime) {
            strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        boolean updateTime = metaObject.hasSetter("updateTime");
        if (updateTime) {
            strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        }
    }
}
