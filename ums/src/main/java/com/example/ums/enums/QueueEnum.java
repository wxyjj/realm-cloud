package com.example.ums.enums;

/**
 * @Author wxy
 * @Date 2021/3/30 15:30
 * @Version 1.0
 */
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    QUEUE_EMAIL_CANCEL("email", "email.queue", "email.routeKey"),
    /**
     * 消息通知ttl队列
     */
    QUEUE_TTL_EMAIL_CANCEL("email.ttl", "email.queue.ttl", "email.routeKey.ttl"),
    ;

    /**
     * 交换名称
     */
    private final String exchange;
    /**
     * 队列名称
     */
    private final String name;
    /**
     * 路由键
     */
    private final String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }

    public String getExchange() {
        return exchange;
    }

    public String getName() {
        return name;
    }

    public String getRouteKey() {
        return routeKey;
    }
}
