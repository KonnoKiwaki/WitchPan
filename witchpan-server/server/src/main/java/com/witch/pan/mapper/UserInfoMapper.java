package com.witch.pan.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.witch.pan.pojo.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author Yuuki
 * @since 2024-05-16
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    Integer updateUserSpace(@Param("userId") String userId, @Param("useSpace") Long useSpace, @Param("totalSpace") Long totalSpace);

    default UserInfo selectByQqOpenId(String openId){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserInfo::getQqOpenId, openId);
        return selectOne(queryWrapper);
    }
}
