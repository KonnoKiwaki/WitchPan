package com.witch.pan.entity.enums;

import lombok.Getter;

/**
 * @author Yuuki
 */

@Getter
public enum DateTimePatternEnum {

    YYYY_MM_DD_HH_MM_DD("yyyy-MM-dd HH:mm:ss"),
    YYYY_MM_DD("yyyy-MM-dd"),
    YYYYMM("yyyyMM");

    private String pattern;

    DateTimePatternEnum(String pattern) {
        this.pattern = pattern;
    }
}
