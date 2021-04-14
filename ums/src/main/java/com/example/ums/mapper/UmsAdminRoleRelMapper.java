package com.example.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ums.dto.resp.RoleResp;
import com.example.ums.entity.UmsAdminRoleRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wxy
 * @Date 2021/3/30 13:45
 * @Version 1.0
 */
public interface UmsAdminRoleRelMapper extends BaseMapper<UmsAdminRoleRel> {

    List<RoleResp> findRoleRespByAdminId(@Param("adminId") String adminId);
}
