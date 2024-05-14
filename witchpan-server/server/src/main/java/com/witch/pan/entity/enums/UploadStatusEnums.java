package com.witch.pan.entity.enums;

import lombok.Getter;

/**
 * @author Yuuki
 */

@Getter
public enum UploadStatusEnums {
    UPLOAD_SECONDS("upload_seconds", "秒传"),
    UPLOADING("uploading", "上传中"),
    UPLOAD_FINISH("upload_finish", "上传完成");
    private final String code;
    private final String desc;

    UploadStatusEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
