package com.witch.pan.service;

import com.witch.pan.entity.query.BaseParam;
import com.witch.pan.entity.vo.SessionWebUserVO;
import com.witch.pan.entity.dto.RegisterDTO;
import com.witch.pan.entity.vo.UserInfoVO;
import com.witch.pan.pojo.FileInfo;
import com.witch.pan.pojo.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author Yuuki
 * @since 2024-05-16
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 注册
     *
     * @param registerDto 注册信息
     */
    void register(RegisterDTO registerDto);

    /**
     * 登陆
     *
     * @param email    邮箱
     * @param password 密码
     * @return 部分信息
     */
    SessionWebUserVO login(String email, String password);

    /**
     * 重置密码
     *
     * @param email     邮箱
     * @param password  密码
     * @param emailCode 邮箱验证码
     */
    void resetPwd(String email, String password, String emailCode);

    /**
     * 更新用户使用空间或者总空间
     *
     * @param userId     用户ID
     * @param useSpace   使用空间
     * @param totalSpace 总空间
     * @return 是否更新成功
     */
    Boolean updateUserSpace(String userId, Long useSpace, Long totalSpace);

    SessionWebUserVO qqLogin(String code);

    List<UserInfoVO> loadUserList(BaseParam baseParam);

}
