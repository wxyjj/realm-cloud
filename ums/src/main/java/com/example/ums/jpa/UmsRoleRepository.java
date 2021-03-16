package com.example.ums.jpa;

import com.example.ums.entity.UmsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author wxy
 * @Date 2021/3/16 16:31
 * @Version 1.0
 */
public interface UmsRoleRepository extends JpaRepository<UmsRole, String>, JpaSpecificationExecutor<UmsRole> {

    List<UmsRole> findAllByIdIn(List<String> roleIds);
}
