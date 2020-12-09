package com.example.pdd.jpa;


import com.example.pdd.entiy.PddData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author wxy
 * @Date 2020/10/27 13:31
 * @Version 1.0
 */
public interface PddDataRepository extends JpaRepository<PddData, String> {
}
