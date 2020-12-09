package com.example.pdd.jpa;


import com.example.pdd.entiy.PddLoginMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author wxy
 * @Date 2020/10/27 14:44
 * @Version 1.0
 */
public interface PddLoginMessageRepository extends JpaRepository<PddLoginMessage, String> {

    /**
     * 根据店铺id查询登录信息
     *
     * @param ownerId 店铺id
     * @return LoginMessage
     */
    PddLoginMessage findByOwnerId(String ownerId);
}
