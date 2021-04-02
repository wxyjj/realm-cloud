package com.example.ums.config;

import com.example.ums.enums.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author wxy
 * @Date 2021/3/30 15:30
 * @Version 1.0
 */
@Slf4j
@Configuration
public class RabbitMqConfig {
    @Resource
    private CachingConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息成功发送到Exchange");
            } else {
                log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
            }
        });
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
        });
        return rabbitTemplate;
    }

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
