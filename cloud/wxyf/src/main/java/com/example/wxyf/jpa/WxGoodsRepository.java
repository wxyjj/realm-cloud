package com.example.wxyf.jpa;


import com.example.wxyf.entity.WxGoods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author wxy
 * @Date 2020/11/30 10:38
 * @Version 1.0
 */
public interface WxGoodsRepository extends JpaRepository<WxGoods, String> {
    /**
     * 通过拼多多商品id查询数据
     *
     * @param pddGoodsId 拼多多商品id
     * @return Object
     */
    WxGoods findByPddGoodsId(String pddGoodsId);
}
