package com.witch.pan.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Yuuki
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysSettingsDTO implements Serializable {

    private String registerEmailTitle = "邮箱验证码";

    private String registerEmailContent = "您好，欢迎加入魔女云盘这个大家庭，您的邮箱验证码是：%s, 15分钟有效";

    private Integer userInitSpace = 1;

}

