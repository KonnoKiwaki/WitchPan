package com.witch.pan.entity.enums;

/**
 * @author Yuuki
 */

public enum UserStatusEnum {

    //枚举常量
    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    private Integer status;
    private String desc;

    UserStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer status() {
        return this.status;
    }
    public String desc() {
        return this.desc;
    }
}
