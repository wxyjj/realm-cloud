package com.example.hpyf.jpa;


import com.example.hpyf.entity.HpCancelOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author wxy
 * @Date 2020/10/27 14:08
 * @Version 1.0
 */
public interface HpCancelOrderRepository extends JpaRepository<HpCancelOrder, String> {
}
