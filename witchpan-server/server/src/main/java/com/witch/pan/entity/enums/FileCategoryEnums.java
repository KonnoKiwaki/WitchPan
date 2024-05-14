package com.witch.pan.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

;

/**
 * @author Yuuki
 */


@Getter
public enum FileCategoryEnums {

    VIDEO(1, "video", "视频"),
    MUSIC(2, "music", "音乐"),
    IMAGE(3, "image", "图片"),
    DOC(4, "doc", "文档"),
    OTHER(5, "other", "其他");

    private final Integer category;
    private final String code;
    private final String desc;

    FileCategoryEnums(Integer category, String code, String desc) {
        this.category = category;
        this.code = code;
        this.desc = desc;
    }

    public static FileCategoryEnums getByCode(String code) {
        for (FileCategoryEnums item : FileCategoryEnums.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

}
