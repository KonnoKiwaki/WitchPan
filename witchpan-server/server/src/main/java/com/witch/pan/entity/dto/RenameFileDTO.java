package com.witch.pan.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Yuuki
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RenameFileDTO implements Serializable {

    @NotEmpty
    private String id;
    @NotEmpty
    @Pattern(regexp = "[^/]")
    private String filename;

}
