package com.example.pdd.aop;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求消息封装
 *
 * @Author wxy
 * @Date 2020/11/5 9:55
 * @Version 1.0
 */
@Data
public class RequestInfo implements Serializable {
    private static final long serialVersionUID = 4947781971511245564L;
    /**
     * 请求的ip
     */
    private String ip;
    /**
     * 请求的url
     */
    private String url;
    /**
     * 请求的方法
     */
    private String httpMethod;
    /**
     * 请求的类方法
     */
    private String classMethod;
    /**
     * 请求的参数
     */
    private Object requestParams;
    /**
     * 耗时
     */
    private Long timeCost;
}
