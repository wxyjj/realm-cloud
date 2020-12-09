package com.example.wxyf.jpa;


import com.example.wxyf.entity.WxStore;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author wxy
 * @Date 2020/11/30 10:41
 * @Version 1.0
 */
public interface WxStoreRepository extends JpaRepository<WxStore, String> {
}
