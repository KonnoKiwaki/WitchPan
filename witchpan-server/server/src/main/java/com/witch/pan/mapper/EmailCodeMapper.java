package com.witch.pan.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.witch.pan.pojo.EmailCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  邮箱验证码Mapper 接口
 * </p>
 *
 * @author Yuuki
 * @since 2024-04-23
 */
public interface EmailCodeMapper extends BaseMapper<EmailCode> {

    //用default可以不用重写接口里面这个方法，进行直接调用
    default void disableEmailCode(String email) {
        //update email_code set status = 1 where email = #{email} and status = 0
        UpdateWrapper<EmailCode> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(EmailCode::getStatus, 1)
                .eq(EmailCode::getEmail, email)
                .eq(EmailCode::getStatus, 0);
        this.update(null, wrapper);
    }
}
