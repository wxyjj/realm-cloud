package com.example.hpyf.jpa;


import com.example.hpyf.entity.HpOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author wxy
 * @Date 2020/10/27 14:02
 * @Version 1.0
 */
public interface HpOrderItemsRepository extends JpaRepository<HpOrderItems, String> {

    /**
     * 根据订单id查询订单明细
     *
     * @param orderId 订单id
     */
    HpOrderItems findByOrderId(String orderId);
}
