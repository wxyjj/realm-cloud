package com.example.ums.jpa;

import com.example.ums.entity.UmsAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author wxy
 * @Date 2021/3/16 16:20
 * @Version 1.0
 */
public interface UmsAdminRepository extends JpaRepository<UmsAdmin, String>, JpaSpecificationExecutor<UmsAdmin> {

    UmsAdmin findFirstByUsername(String username);
}
