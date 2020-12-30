package com.example.pdd.feign;

import com.example.pdd.fallback.XXXFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 拼多多消费
 *
 * @Author wxy
 * @Date 2020/12/7 15:11
 * @Version 1.0
 */
@FeignClient(value = "xxx", fallback = XXXFeignFallBack.class)
public interface XXXFeign {

}
