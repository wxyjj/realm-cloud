package com.example.ums.mq;

import com.example.ums.dto.EmailDto;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author wxy
 * @Date 2021/3/30 16:11
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "email.queue")
public class EmailReceiver {

    @RabbitHandler
    public void handle(EmailDto dto) {
    }
}
