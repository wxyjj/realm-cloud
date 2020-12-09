package com.example.pdd.redis;

/**
 * redis命名空间
 *
 * @Author wxy
 * @Date 2020/9/22 9:44
 * @Version 1.0
 */
public interface RedisNamespace {

    /**
     * 万鑫药房的订单脱敏数据
     */
    String PDD_WX_ORDER_DATA = "{pdd_wx_order_data}:";

    /**
     * 和平药房的订单脱敏数据
     */
    String PDD_HP_ORDER_DATA = "{pdd_hp_order_data}:";
}
