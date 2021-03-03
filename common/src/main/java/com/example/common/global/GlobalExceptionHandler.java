package com.example.common.global;

import cn.hutool.crypto.CryptoException;
import com.example.common.support.ApiException;
import com.example.common.support.IErrorCode;
import com.example.common.support.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常拦截处理
 *
 * @Author wxy
 * @Date 2020/9/22 13:08
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public Result<Object> handleOne(ApiException exception) {
        log.error(errInfo(exception));
        IErrorCode errorCode = exception.getErrorCode();
        return Result.failed(errorCode, errorCode.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = CryptoException.class)
    public Result<Object> handleTwo(CryptoException exception) {
        log.error(errInfo(exception));
        return Result.failed("数据加密|解密失败");
    }

    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Result<Object> handleThree(HttpMessageNotReadableException exception) {
        log.error(errInfo(exception));
        return Result.failed("请求体JSON格式错误");
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<Object> handle(Exception exception) {
        log.error(errInfo(exception));
        return Result.failed(exception.getMessage());
    }

    public String errInfo(Exception e) {
        String str = "";
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            str = sw.toString();
        } catch (IOException ie) {
            log.error(ie.getMessage());
        }
        return str;
    }
}

