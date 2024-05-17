package com.witch.pan.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yuuki
 */
@Data
public class SessionWebUserVO implements Serializable {
    private String nickname;
    private String id;
    private Boolean isAdmin;
    private String avatar;
//    private String token;
}
