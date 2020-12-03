package com.example.pdd.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求日志切面
 *
 * @Author wxy
 * @Date 2020/11/5 9:48
 * @Version 1.0
 */
@Slf4j
@Aspect
@Component
public class RequestLogAspect {

    /**
     * 切点
     */
    @Pointcut("execution(* com.example.*.controller..*(..))")
    public void requestServer() {
    }

    /**
     * 环绕通知
     */
    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //开始时间戳
        long start = System.currentTimeMillis();
        //请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        //目标方法执行
        Object result = proceedingJoinPoint.proceed();
        if (!request.getRequestURI().contains("file")) {
            //赋值打印
            RequestInfo requestInfo = new RequestInfo();
            requestInfo.setIp(request.getRemoteAddr());
            requestInfo.setUrl(request.getRequestURL().toString());
            requestInfo.setHttpMethod(request.getMethod());
            requestInfo.setClassMethod(this.getClassMethod(proceedingJoinPoint));
            requestInfo.setRequestParams(this.getRequestParams(proceedingJoinPoint));
            requestInfo.setTimeCost(System.currentTimeMillis() - start);
            log.info("Request Info:{}", JSONObject.toJSONString(requestInfo));
        }
        return result;
    }

    /**
     * 获取类方法
     */
    private String getClassMethod(ProceedingJoinPoint proceedingJoinPoint) {
        String declaringTypeName = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String name = proceedingJoinPoint.getSignature().getName();
        return String.format("%s.%s", declaringTypeName, name);
    }

    /**
     * 获取入参
     */
    private Map<String, Object> getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> requestParams = new HashMap<>();
        //参数名
        String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }

}
