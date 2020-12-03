package com.example.pdd.global;

import cn.hutool.crypto.CryptoException;
import com.example.pdd.support.ApiException;
import com.example.pdd.support.IErrorCode;
import com.example.pdd.support.Result;
import lombok.extern.slf4j.Slf4j;
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
    @ExceptionHandler(value = Exception.class)
    public Result<Object> handle(Exception exception) {
        log.error(this.errInfo(exception));
        if (exception instanceof ApiException) {
            ApiException apiException = (ApiException) exception;
            IErrorCode errorCode = apiException.getErrorCode();
            if (null != errorCode) {
                return Result.failed(errorCode, errorCode.getMessage());
            } else {
                return Result.failed();
            }
        } else if (exception instanceof CryptoException) {
            return Result.failed("数据加密|解密失败");
        }
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

