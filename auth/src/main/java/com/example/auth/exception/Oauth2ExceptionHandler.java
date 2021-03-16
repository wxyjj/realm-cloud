package com.example.auth.exception;

import com.example.common.support.Result;
import com.example.common.utils.LogPrintUtils;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局处理Oauth2抛出的异常
 *
 * @Author wxy
 * @Date 2021/2/8 11:05
 * @Version 1.0
 */
@Order(0)
@RestControllerAdvice
public class Oauth2ExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = AuthenticationException.class)
    public Result<Object> handleOauth(AuthenticationException exception) {
        LogPrintUtils.errInfo(exception);
        return Result.failed(exception.getMessage());
    }
}
