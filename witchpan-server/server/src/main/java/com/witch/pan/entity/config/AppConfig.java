package com.witch.pan.entity.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Yuuki
 */
@Component("appConfig")
@Data
public class AppConfig {

    @Value("${spring.mail.username}")
    private String sendUsername;

    @Value("${admin.emails}")
    private String emails;

    @Value("${project.folder}")
    private String projectFolder;

    //qq登陆相关
    @Value("${qq.app.id}")
    private String qqAppId;

    @Value("${qq.app.key}")
    private String qqAppKey;

    @Value("${qq.url.authorization}")
    private String qqAuthorizationUrl;

    @Value("${qq.url.access.token}")
    private String qqAccessTokenUrl;

    @Value("${qq.url.openid}")
    private String qqOpenIdUrl;

    @Value("${qq.url.user.info}")
    private String qqUserInfoUrl;

    @Value("${qq.url.redirect}")
    private String qqRedirectUrl;





}
