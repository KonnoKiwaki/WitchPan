package com.witch.pan.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Yuuki
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadFileDTO implements Serializable {

    private String code;
    private String filename;
    private String filePath;

}
