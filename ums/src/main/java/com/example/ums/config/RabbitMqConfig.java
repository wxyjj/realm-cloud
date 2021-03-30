package com.example.ums.config;

import com.example.ums.enums.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wxy
 * @Date 2021/3/30 15:30
 * @Version 1.0
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 邮件交换机
     */
    @Bean
    public DirectExchange emailDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_EMAIL_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 邮件队列
     */
    @Bean
    public Queue emailQueue() {
        return new Queue(QueueEnum.QUEUE_EMAIL_CANCEL.getName());
    }

    /**
     * 邮件队列绑定到邮件交换机
     */
    @Bean
    public Binding orderBinding(DirectExchange emailDirect, Queue emailQueue) {
        return BindingBuilder
                .bind(emailQueue)
                .to(emailDirect)
                .with(QueueEnum.QUEUE_EMAIL_CANCEL.getRouteKey());
    }

    /**
     * 邮件Ttl交换机
     */
    @Bean
    public DirectExchange emailTtlDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_EMAIL_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 邮件Ttl队列
     */
    @Bean
    public Queue emailTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_EMAIL_CANCEL.getName())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_EMAIL_CANCEL.getExchange())//到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_EMAIL_CANCEL.getRouteKey())//到期后转发的路由键
                .build();
    }

    /**
     * 邮件Ttl队列绑定到邮件Ttl交换机
     */
    @Bean
    public Binding emailTtlBinding(DirectExchange emailTtlDirect, Queue emailTtlQueue) {
        return BindingBuilder
                .bind(emailTtlQueue)
                .to(emailTtlDirect)
                .with(QueueEnum.QUEUE_TTL_EMAIL_CANCEL.getRouteKey());
    }

}
