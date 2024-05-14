package com.witch.pan.entity.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Yuuki
 */
@Data
public class ResetPwdDTO {
    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 18, message = "密码长度在8-18之间")
    private String password;

    @NotNull
    private String checkCode;

    @NotNull
    private String emailCode;
}
