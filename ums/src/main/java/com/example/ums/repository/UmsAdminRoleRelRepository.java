package com.example.ums.repository;

import com.example.ums.entity.UmsAdminRoleRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author wxy
 * @Date 2021/3/30 10:23
 * @Version 1.0
 */
public interface UmsAdminRoleRelRepository extends JpaRepository<UmsAdminRoleRel, Integer>, JpaSpecificationExecutor<UmsAdminRoleRel> {
}
