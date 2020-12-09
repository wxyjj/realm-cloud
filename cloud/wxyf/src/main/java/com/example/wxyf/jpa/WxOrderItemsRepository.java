package com.example.wxyf.jpa;


import com.example.wxyf.entity.WxOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author wxy
 * @Date 2020/11/30 10:40
 * @Version 1.0
 */
public interface WxOrderItemsRepository extends JpaRepository<WxOrderItems, String> {
    /**
     * 根据订单id查询订单明细
     *
     * @param orderId 订单id
     */
    WxOrderItems findByOrderId(String orderId);
}
