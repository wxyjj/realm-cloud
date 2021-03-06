package com.example.common.utils;

import com.example.common.support.ApiException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @Author wxy
 * @Date 2020/9/25 10:27
 * @Version 1.0
 */
public final class CheckUtils {

    /**
     * 空值校验
     *
     * @param object    对象
     * @param exception 自定义异常
     * @see Optional#isPresent()
     * @see ObjectUtils#isEmpty(Object[])
     * @see StringUtils#hasLength(CharSequence)
     * @see StringUtils#isEmpty(Object)
     * @see CollectionUtils#isEmpty(java.util.Collection)
     * @see CollectionUtils#isEmpty(java.util.Map)
     */
    public static void checkNull(Object object, ApiException exception) {
        if (ObjectUtils.isEmpty(object)) {
            throw exception;
        }
    }

    /**
     * 空值校验并返回
     *
     * @param t         泛指
     * @param exception 自定义异常
     * @see Optional#isPresent()
     * @see ObjectUtils#isEmpty(Object[])
     * @see StringUtils#hasLength(CharSequence)
     * @see StringUtils#isEmpty(Object)
     * @see CollectionUtils#isEmpty(java.util.Collection)
     * @see CollectionUtils#isEmpty(java.util.Map)
     */
    public static <T> T checkNullReturn(T t, ApiException exception) {
        if (ObjectUtils.isEmpty(t)) {
            throw exception;
        }
        return t;
    }

}
