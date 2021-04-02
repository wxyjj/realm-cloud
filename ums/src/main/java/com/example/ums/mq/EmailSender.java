package com.example.ums.mq;

import com.alibaba.fastjson.JSONObject;
import com.example.ums.dto.EmailDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;

import static com.example.ums.enums.QueueEnum.QUEUE_TTL_EMAIL_CANCEL;
import static org.springframework.amqp.core.MessageDeliveryMode.PERSISTENT;
import static org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_JSON;

/**
 * @Author wxy
 * @Date 2021/3/30 16:00
 * @Version 1.0
 */
@Component
public class EmailSender {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(EmailDto dto, Duration duration) {
        String exchange = QUEUE_TTL_EMAIL_CANCEL.getExchange();
        String routeKey = QUEUE_TTL_EMAIL_CANCEL.getRouteKey();

        String msgId = dto.getMsgId();
        String delayTime = Long.toString(duration.toMillis());
        byte[] body = JSONObject.toJSONString(dto).getBytes();

        Message message = MessageBuilder.withBody(body).build();
        MessageProperties messageProperties = message.getMessageProperties();
        messageProperties.setDeliveryMode(PERSISTENT);
        messageProperties.setContentType(CONTENT_TYPE_JSON);
        messageProperties.setExpiration(delayTime);

        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(exchange, routeKey, message, correlationData);
    }
}
