package com.example.ums.mq;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.example.ums.dto.EmailDto;
import com.example.ums.entity.UmsEmailMsg;
import com.example.ums.mapper.UmsEmailMsgMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * @Author wxy
 * @Date 2021/3/30 16:11
 * @Version 1.0
 */
@Component
public class EmailReceiver {
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private UmsEmailMsgMapper umsEmailMsgMapper;

    @RabbitListener(queues = "email.queue")
    public void handle(Message message, Channel channel) throws IOException {
        String data = new String(message.getBody());
        EmailDto dto = JSONUtil.toBean(data, EmailDto.class);

        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        
        emailSendImpl(dto, tag, channel);
    }

    private void emailSendImpl(EmailDto dto, Long tag, Channel channel) throws IOException {
        UmsEmailMsg umsEmailMsg = umsEmailMsgMapper.findUmsEmailMsgByEmailMsgId(dto.getMsgId());
        if (ObjectUtil.isEmpty(umsEmailMsg)) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(dto.getUserEmail());
            mailMessage.setSubject(dto.getSubject());
            mailMessage.setText(dto.getContent());
            javaMailSender.send(mailMessage);
            umsEmailMsg = new UmsEmailMsg();
            umsEmailMsg.setEmailMsgId(dto.getMsgId());
            umsEmailMsg.setReceiveEmail(dto.getUserEmail());
            umsEmailMsg.setCode(dto.getCode());
            umsEmailMsg.setRetry(0);
            umsEmailMsg.setCreateTime(new Date());
            umsEmailMsgMapper.insert(umsEmailMsg);
            channel.basicAck(tag, false);
        } else {
            Integer retry = umsEmailMsg.getRetry();
            if (retry == 3) {
                channel.basicAck(tag, false);
                return;
            }
            umsEmailMsg.setRetry(retry + 1);
            umsEmailMsgMapper.updateById(umsEmailMsg);
            channel.basicNack(tag, false, true);
        }
    }
}
