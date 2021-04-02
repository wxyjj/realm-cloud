package com.example.ums.mq;

import com.example.ums.dto.EmailDto;
import com.example.ums.service.UmsEmailMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * @Author wxy
 * @Date 2021/3/30 16:11
 * @Version 1.0
 */
@Slf4j
@Component
@RabbitListener(queues = "email.queue")
public class EmailReceiver {
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private UmsEmailMsgService umsEmailMsgService;

    @RabbitHandler
    @Retryable(value = Exception.class, backoff = @Backoff(value = 3000, maxDelay = 10000))
    public void handle(EmailDto dto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(dto.getUserEmail());
        mailMessage.setSubject(dto.getSubject());
        mailMessage.setText(dto.getContent());
        javaMailSender.send(mailMessage);

        CompletableFuture.runAsync(() ->
                umsEmailMsgService.addEmailMsg(dto.getMsgId(), dto.getUserEmail(), dto.getCode()));
    }

    @Recover
    public void recover(Exception e) {
        log.error("spring retry after recover => " + e.getMessage());
    }
}
