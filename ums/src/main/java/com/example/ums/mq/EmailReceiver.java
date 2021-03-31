package com.example.ums.mq;

import com.example.ums.dto.EmailDto;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author wxy
 * @Date 2021/3/30 16:11
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "email.queue")
public class EmailReceiver {
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Resource
    private JavaMailSender javaMailSender;

    @RabbitHandler
    public void handle(EmailDto dto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(dto.getUserEmail());
        mailMessage.setSubject(dto.getSubject());
        mailMessage.setText(dto.getContent());
        javaMailSender.send(mailMessage);
    }
}
