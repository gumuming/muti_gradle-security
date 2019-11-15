package com.liaoin.muti.test.service.file.file.normal.bean;

import lombok.Getter;

@Getter
public enum FileTips {


    FAIL(500,"失败"),
    SUCCESS(200,"成功"),
    DISABLED_TOEK(401,"token过期"),
    AUTHOR_NO(403,"没有访问权限"),
    TYPE_FALSE("文件类型不支持"),



    ;


    public Integer code;
    public String message;


    FileTips(String msg) {
        this.message = msg;
    }

    FileTips(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }


}
