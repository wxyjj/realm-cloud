package com.example.hpyf.jpa;


import com.example.hpyf.entity.HpOrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author wxy
 * @Date 2020/10/27 13:57
 * @Version 1.0
 */
public interface HpOrderDetailsRepository extends JpaRepository<HpOrderDetails, String> {

    /**
     * 根据订单号查询OMS订单详情
     *
     * @param orderId 订单号
     * @return OMSOrderDetails
     */
    HpOrderDetails findByOrderId(String orderId);
}
