package com.aidjajd.usercenter.common;

/**
 * 错误码类
 * @author yeguo
 */
public enum ErrorCode {

    NOT_LOGIN(40000,"未登录"),
    PARAMS_ERROR(40001,"请求参数错误"),
    USER_USED_ERROR(40002,"账号已存在"),
    NOT_AUTHORITY(40003,"无权限"),
    NOT_FOUND_ERROR(40004,"未找到"),
    PASSWORD_ERROR(40005,"密码错误"),
    SYSTEM_ERROR(50000,"运行时异常")
    ;


    private int code;

    private String message;



    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;

    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
