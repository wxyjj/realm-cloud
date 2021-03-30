package com.example.ums.repository;

import com.example.ums.entity.UmsAdmin;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author wxy
 * @Date 2021/3/30 10:22
 * @Version 1.0
 */
public interface UmsAdminRepository extends JpaRepository<UmsAdmin, Integer>, JpaSpecificationExecutor<UmsAdmin> {

    @EntityGraph(value = "admin-with-roles")
    UmsAdmin findWithUmsRoleByUsername(String userName);
}
