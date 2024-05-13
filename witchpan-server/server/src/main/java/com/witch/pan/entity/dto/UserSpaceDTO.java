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
public class UserSpaceDTO implements Serializable {
    private Long useSpace;
    private Long totalSpace;

}
