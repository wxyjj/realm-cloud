package com.example.common.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * 基本工具类
 *
 * @Author wxy
 * @Date 2020/9/25 10:27
 * @Version 1.0
 */
@Slf4j
@Component
public class RsaUtils {

    /**
     * 公匙RSA加密
     */
    public <T> String pubEnc(T t, RSA rsa) {
        //JSON序列化
        String json = JSONObject.toJSONString(t, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        //数据加密
        byte[] value = StrUtil.bytes(json, CharsetUtil.CHARSET_UTF_8);
        byte[] bytes = rsa.encrypt(value, KeyType.PublicKey);
        //BASE64编码
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 公匙RSA解密
     */
    public <T> T pubDec(String string, Class<T> cla, RSA rsa) {
        //BASE64解码
        byte[] bytes = Base64.getDecoder().decode(string);
        //数据解密
        byte[] decrypt = rsa.decrypt(bytes, KeyType.PublicKey);
        String value = StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
        //JSON序列化
        return JSONObject.parseObject(value, cla);
    }

    /**
     * 私匙RSA加密
     */
    public <T> String priEnc(T t, RSA rsa) {
        //JSON序列化
        String json = JSONObject.toJSONString(t, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        //数据加密
        byte[] value = StrUtil.bytes(json, CharsetUtil.CHARSET_UTF_8);
        byte[] bytes = rsa.encrypt(value, KeyType.PrivateKey);
        //BASE64编码
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 私匙RSA解密
     */
    public <T> T priDec(String string, Class<T> cla, RSA rsa) {
        //BASE64解码
        byte[] bytes = Base64.getDecoder().decode(string);
        //数据解密
        byte[] decrypt = rsa.decrypt(bytes, KeyType.PrivateKey);
        String value = StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
        //JSON序列化
        return JSONObject.parseObject(value, cla);
    }
}
