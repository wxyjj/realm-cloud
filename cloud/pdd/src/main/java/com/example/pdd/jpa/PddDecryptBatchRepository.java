package com.example.pdd.jpa;


import com.example.pdd.entiy.PddDecryptBatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author wxy
 * @Date 2020/12/1 15:45
 * @Version 1.0
 */
public interface PddDecryptBatchRepository extends JpaRepository<PddDecryptBatch, String> {

    /**
     * 根据订单id查询解密后数据
     *
     * @param dataTag 订单id
     * @return Object
     */
    List<PddDecryptBatch> findByDataTagOrderByDataTypeAsc(String dataTag);
}
