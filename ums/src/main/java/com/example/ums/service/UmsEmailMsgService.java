package com.example.ums.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Author wxy
 * @Date 2021/4/2 10:33
 * @Version 1.0
 */
public interface UmsEmailMsgService {
    /**
     * 保存邮箱发送记录
     */
    @Transactional
    void addEmailMsg(String emailMsgId, String receiveEmail, Integer code);
}
