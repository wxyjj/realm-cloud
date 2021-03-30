package com.example.ums.repository;

import com.example.ums.entity.UmsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author wxy
 * @Date 2021/3/30 10:24
 * @Version 1.0
 */
public interface UmsRoleRepository extends JpaRepository<UmsRole, Integer>, JpaSpecificationExecutor<UmsRole> {
}
