package com.example.pdd.redis;

import com.pdd.pop.sdk.http.api.pop.response.PddOrderInformationGetResponse;

import java.util.List;

/**
 * redis操作 service层
 *
 * @Author wxy
 * @Date 2020/9/22 9:49
 * @Version 1.0
 */
public interface RedisService {

    /**
     * 添加订单脱敏数据
     *
     * @param orderId   订单id
     * @param response  对象
     * @param namespace 命名空间
     */
    void addRedisOrder(String orderId, PddOrderInformationGetResponse response, String namespace);

    /**
     * 获取订单脱敏数据
     *
     * @param namespace 命名空间
     * @return Object
     */
    List<PddOrderInformationGetResponse> getRedisOrder(String namespace);

    /**
     * 删除命名空间对应的keys
     *
     * @param namespace 命名空间
     */
    void deleteByNameSpace(String namespace);
}
