package com.example.ums;

import com.example.ums.entity.UmsEmailMsg;
import com.example.ums.mapper.UmsEmailMsgMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class UmsApplicationTests {
    @Resource
    private UmsEmailMsgMapper umsEmailMsgMapper;

    @Test
    void contextLoads() {
        UmsEmailMsg umsEmailMsg = new UmsEmailMsg();
        umsEmailMsg.setEmailMsgId("1");
        umsEmailMsg.setReceiveEmail("1083762642@qq.com");
        umsEmailMsg.setCode(1);
        umsEmailMsg.setCreateTime(new Date());
        umsEmailMsgMapper.insert(umsEmailMsg);
    }

}
