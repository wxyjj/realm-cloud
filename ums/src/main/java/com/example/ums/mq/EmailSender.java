package com.example.ums.mq;

import com.example.ums.dto.EmailDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.example.ums.enums.QueueEnum.QUEUE_TTL_EMAIL_CANCEL;

/**
 * @Author wxy
 * @Date 2021/3/30 16:00
 * @Version 1.0
 */
@Component
public class EmailSender {
    @Resource
    private AmqpTemplate amqpTemplate;

    public void sendMessage(EmailDto dto, Long delayTime) {
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(QUEUE_TTL_EMAIL_CANCEL.getExchange(), QUEUE_TTL_EMAIL_CANCEL.getRouteKey(), dto, message -> {
            //给消息设置延迟毫秒值
            message.getMessageProperties().setExpiration(String.valueOf(delayTime));
            return message;
        });
    }
}
