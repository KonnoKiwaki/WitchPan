package com.witch.pan.service;

import com.witch.pan.pojo.EmailCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yuuki
 * @since 2024-04-23
 */
public interface EmailCodeService extends IService<EmailCode> {

    void sendEmailCode(String email, Integer type);

    void checkCode(String email, String code);
}
