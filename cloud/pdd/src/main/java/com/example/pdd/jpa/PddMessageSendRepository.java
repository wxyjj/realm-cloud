package com.example.pdd.jpa;


import com.example.pdd.entiy.PddMessageSend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author wxy
 * @Date 2020/10/27 13:27
 * @Version 1.0
 */
public interface PddMessageSendRepository extends JpaRepository<PddMessageSend, String> {
}
