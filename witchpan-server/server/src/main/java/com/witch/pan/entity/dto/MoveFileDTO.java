package com.witch.pan.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author Yuuki
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveFileDTO implements Serializable {


    @NotEmpty
    private String filePid; // 目标目录ID
    @NotEmpty
    private String ids; // 要移动的文件IDs

}
