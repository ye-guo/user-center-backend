package com.aidjajd.usercenter.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回类
 * @author yeguo
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    private String description;

    public Result(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Result(int code,T data) {
        this(code,data,"成功");
    }
    // 失败不返回数据
    public Result(ErrorCode errorCode){
        this(errorCode.getCode(),null, errorCode.getMessage());
    }
    public Result(ErrorCode errorCode,String description) {
        this.code = errorCode.getCode();
        this.data = null;
        this.message = errorCode.getMessage();
        this.description = description;
    }
    public Result(int code,T data,String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }


}
