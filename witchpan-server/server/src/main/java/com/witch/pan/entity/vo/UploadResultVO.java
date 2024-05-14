package com.witch.pan.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Yuuki
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadResultVO  implements Serializable {

    private String id;
    private String status;

}
