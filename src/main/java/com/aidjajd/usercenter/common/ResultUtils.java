package com.aidjajd.usercenter.common;

/**
 * 返回工具类
 * @author yeguo
 */
public class ResultUtils {
    // 成功
    public static <T> Result<T> success(T data) {
        return new Result<>(20000,data);
    }

    // 失败
    public static <T> Result<T> err(ErrorCode errorCode) {
        return new Result<>(errorCode);
    }

    public static <T> Result<T> err(ErrorCode errorCode,String description) {
        return new Result<>(errorCode,description);
    }

    public static <T> Result<T> err(int code,String message,String description) {
        return new Result<>(code,null,message,description);
    }
}
