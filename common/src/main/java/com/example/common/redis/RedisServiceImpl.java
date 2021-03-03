package com.example.common.redis;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @Author wxy
 * @Date 2021/3/3 13:47
 * @Version 1.0
 */
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
}
