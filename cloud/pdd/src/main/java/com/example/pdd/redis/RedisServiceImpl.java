package com.example.pdd.redis;

import com.pdd.pop.sdk.http.api.pop.response.PddOrderInformationGetResponse;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author wxy
 * @Date 2020/9/22 9:57
 * @Version 1.0
 */
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 添加订单脱敏数据
     *
     * @param orderId   订单id
     * @param response  对象
     * @param namespace 命名空间
     */
    @Override
    public void addRedisOrder(String orderId, PddOrderInformationGetResponse response, String namespace) {
        String key = namespace + orderId;
        redisTemplate.opsForValue().setIfAbsent(key, response);
    }

    /**
     * 获取订单脱敏数据
     *
     * @param namespace 命名空间
     * @return Object
     */
    @Override
    public List<PddOrderInformationGetResponse> getRedisOrder(String namespace) {
        List<PddOrderInformationGetResponse> dataList = new ArrayList<>();
        Set<String> strings = redisTemplate.keys(namespace + "*");
        if (null != strings) {
            strings.forEach(c -> {
                PddOrderInformationGetResponse data = (PddOrderInformationGetResponse) redisTemplate.opsForValue().get(c);
                dataList.add(data);
            });
        }
        return dataList;
    }

    /**
     * 删除命名空间对应的keys
     *
     * @param namespace 命名空间
     */
    @Override
    public void deleteByNameSpace(String namespace) {
        Set<String> strings = redisTemplate.keys(namespace + "*");
        assert strings != null;
        redisTemplate.delete(strings);
    }
}
