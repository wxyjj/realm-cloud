package com.example.hpyf.jpa;


import com.example.hpyf.entity.HpGoodsCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HpGoodCodeRepository extends JpaRepository<HpGoodsCode, String> {

    /**
     * 通过erp的skuId查询对应的拼多多skuId和goodsId
     *
     * @param outSkuId erp的skuId
     * @return Object
     */
    HpGoodsCode findByOutSkuId(String outSkuId);

    /**
     * 通过拼多多商品id查询数据
     *
     * @param pddGoodsId 拼多多商品id
     * @return Object
     */
    HpGoodsCode findByPddGoodsId(String pddGoodsId);
}
