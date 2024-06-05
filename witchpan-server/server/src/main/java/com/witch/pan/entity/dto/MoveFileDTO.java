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


    // 目标目录ID
    @NotEmpty
    private String filePid;

    // 要移动的文件IDs
    @NotEmpty
    private String ids;

}
