package com.aidjajd.usercenter.exception;


import com.aidjajd.usercenter.common.ErrorCode;
import com.aidjajd.usercenter.common.Result;
import com.aidjajd.usercenter.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * @author yeguo
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BusinessException.class)
    public Result businessExceptionHandler(BusinessException e) {
        log.error("businessException:",e.getDescription());
        return ResultUtils.err(e.getCode(),e.getMessage(),e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException:",e);
        return ResultUtils.err(ErrorCode.SYSTEM_ERROR,"运行时异常");
    }
}
