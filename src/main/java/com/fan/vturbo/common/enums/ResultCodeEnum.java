package com.fan.vturbo.common.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(true, "200", "操作成功"),
    FAIL(false, "400", "操作失败"),
    ERROR(false, "500", "系统异常");

    private Boolean flag;       //是否成功
    private String code;        //状态码
    private String message;     //返回信息

    ResultCodeEnum(Boolean flag, String code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

}
