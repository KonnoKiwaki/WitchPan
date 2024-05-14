package com.witch.pan.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Yuuki
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareDTO implements Serializable {

    @NotNull
    private String fileId;
    private Integer validType;
    private String code;

}
