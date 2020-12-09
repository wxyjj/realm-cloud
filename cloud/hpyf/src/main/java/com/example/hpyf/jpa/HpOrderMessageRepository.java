package com.example.hpyf.jpa;


import com.example.hpyf.entity.HpOrderMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author wxy
 * @Date 2020/10/28 10:49
 * @Version 1.0
 */
public interface HpOrderMessageRepository extends JpaRepository<HpOrderMessage, String> {

    /**
     * 根据订单id查询OMS订单消息数据
     *
     * @param orderId 订单id
     */
    HpOrderMessage findByOrderId(String orderId);
}
