package com.example.ums;

import com.example.ums.dto.EmailDto;
import com.example.ums.mq.EmailSender;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UmsApplicationTests {
    @Resource
    private EmailSender emailSender;

    @Test
    void contextLoads() {
        EmailDto dto = new EmailDto();
        dto.setMsgId("123");
        dto.setUserEmail("1573932891@qq.com");
        dto.setSubject("Please verify your device");
        dto.setContent("Hey gg/mm!\n" +
                "\n" +
                "A sign in attempt requires further verification because we did not recognize your device. To complete the sign in, enter the verification code on the unrecognized device.\n" +
                "\n" +
                "Device: Chrome on Windows\n" +
                "Verification code: 680070\n" +
                "\n" +
                "If you did not attempt to sign in to your account, your password may be compromised. Visit https://github.com/settings/security to create a new, strong password for your GitHub account.\n" +
                "\n" +
                "If you'd like to automatically verify devices in the future, consider enabling two-factor authentication on your account. Visit https://docs.github.com/articles/configuring-two-factor-authentication to learn about two-factor authentication.\n" +
                "\n" +
                "If you decide to enable two-factor authentication, ensure you retain access to one or more account recovery methods. See https://docs.github.com/articles/configuring-two-factor-authentication-recovery-methods in the GitHub Help.\n" +
                "\n" +
                "Thanks,\n" +
                "The GitHub Team");
        Long delayTime = 60000L;
        emailSender.sendMessage(dto, delayTime);
    }

}
