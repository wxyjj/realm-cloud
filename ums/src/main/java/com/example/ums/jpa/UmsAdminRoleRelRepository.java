package com.example.ums.jpa;

import com.example.ums.entity.UmsAdminRoleRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author wxy
 * @Date 2021/3/16 16:49
 * @Version 1.0
 */
public interface UmsAdminRoleRelRepository extends JpaRepository<UmsAdminRoleRel, String>, JpaSpecificationExecutor<UmsAdminRoleRel> {

    List<UmsAdminRoleRel> findAllByAdminId(String adminId);
}
