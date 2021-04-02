package com.example.ums.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.ums.entity.UmsEmailMsg;
import com.example.ums.mapper.UmsEmailMsgMapper;
import com.example.ums.service.UmsEmailMsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author wxy
 * @Date 2021/4/2 10:34
 * @Version 1.0
 */
@Service
public class UmsEmailMsgServiceImpl implements UmsEmailMsgService {
    @Resource
    private UmsEmailMsgMapper umsEmailMsgMapper;

    @Override
    public void addEmailMsg(String emailMsgId, String receiveEmail, Integer code) {
        UmsEmailMsg umsEmailMsg = umsEmailMsgMapper.findUmsEmailMsgByEmailMsgId(emailMsgId);
        if (ObjectUtil.isEmpty(umsEmailMsg)) {
            umsEmailMsg = new UmsEmailMsg();
            umsEmailMsg.setEmailMsgId(emailMsgId);
            umsEmailMsg.setReceiveEmail(receiveEmail);
            umsEmailMsg.setCode(code);
            umsEmailMsg.setRetry(0);
            umsEmailMsg.setCreateTime(new Date());
            umsEmailMsgMapper.insert(umsEmailMsg);
        } else {
            umsEmailMsg.setRetry(umsEmailMsg.getRetry());
            umsEmailMsgMapper.updateById(umsEmailMsg);
        }
    }
}
