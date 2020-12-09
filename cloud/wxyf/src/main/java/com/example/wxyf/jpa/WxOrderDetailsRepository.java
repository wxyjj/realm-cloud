package com.example.wxyf.jpa;

import com.example.wxyf.entity.WxOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author wxy
 * @Date 2020/11/30 10:40
 * @Version 1.0
 */
public interface WxOrderDetailsRepository extends JpaRepository<WxOrderDetails, String> {
    /**
     * 根据订单号查询OMS订单详情
     *
     * @param orderId 订单号
     * @return OMSOrderDetails
     */
    WxOrderDetails findByOrderId(String orderId);
}
