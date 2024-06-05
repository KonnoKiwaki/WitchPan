package com.witch.pan.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Yuuki
 */
@Data
public class SessionShareDTO implements Serializable {
    private String shareId;
    private String shareUserId;
    private LocalDateTime expireTime;
    private String fileId;
}
