package com.witch.pan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.witch.common.pojo.BizException;
import com.witch.pan.redis.RedisComponent;
import com.witch.pan.entity.config.AppConfig;
import com.witch.pan.entity.constants.Constants;
import com.witch.pan.entity.dto.SysSettingsDTO;
import com.witch.pan.pojo.EmailCode;
import com.witch.pan.mapper.EmailCodeMapper;
import com.witch.pan.service.EmailCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.witch.pan.utils.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 * 邮箱验证码服务实现类
 * </p>
 *
 * @author Yuuki
 * @since 2024-04-23
 */
@Service
public class EmailCodeServiceImpl extends ServiceImpl<EmailCodeMapper, EmailCode> implements EmailCodeService {

    private final JavaMailSender javaMailSender;

    private final RedisComponent redisComponent;

    //信息类
    @Autowired
    private AppConfig appConfig;

    public EmailCodeServiceImpl(JavaMailSender javaMailSender, RedisComponent redisComponent) {
        this.javaMailSender = javaMailSender;
        this.redisComponent = redisComponent;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendEmailCode(String email, Integer type) {
        String code = StringTools.getRandomNumber(Constants.LENGTH_5);
        // 发送验证码
        sendMailCode(email, code);

        // 将之前这个邮箱的验证码置为无效
        baseMapper.disableEmailCode(email);

        EmailCode emailCode = new EmailCode();
        emailCode.setCode(code);
        emailCode.setEmail(email);
        emailCode.setStatus(Constants.ZERO);
        this.save(emailCode);
    }

    @Override
    public void checkCode(String email, String code) {
        //判断验证码是否输入正确
        EmailCode emailCode = getOne(new LambdaQueryWrapper<EmailCode>().eq(EmailCode::getEmail, email).eq(EmailCode::getCode, code));
        if(emailCode == null) {
            throw new BizException("邮箱验证码错误");
        }

        //判断验证码是否失效
        //获取系统默认时间
        ZoneId zoneId = ZoneId.systemDefault();
        long time =  System.currentTimeMillis() - emailCode.getCreateTime().atZone(zoneId).toInstant().toEpochMilli();
        //验证码15分钟有效
        if(emailCode.getStatus() == 1 || time > Constants.LENGTH_15 * 60 * 1000) {
            throw new BizException("邮箱验证码已失效");
        }

        //这次注册成功，此次验证码标记为使用过
        baseMapper.disableEmailCode(email);

    }

    private void sendMailCode(String toEmail, String code) {
        try {
            //消息封装
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(appConfig.getSendUsername());
            helper.setTo(toEmail);

            //redis拿取固定邮件信息
            SysSettingsDTO sysSettingDto = redisComponent.getSysSettingDto();
            helper.setSubject(sysSettingDto.getRegisterEmailTitle());
            helper.setText(String.format(sysSettingDto.getRegisterEmailContent(), code));
            helper.setSentDate(new Date());

            //发送邮件
            javaMailSender.send(message);

        } catch (Exception e) {
            throw new BizException("邮件发送失败");
        }
    }
}
