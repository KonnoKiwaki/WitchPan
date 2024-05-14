package com.witch.pan.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewFolderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String filePid;
    @NotEmpty
    private String filename;

}
