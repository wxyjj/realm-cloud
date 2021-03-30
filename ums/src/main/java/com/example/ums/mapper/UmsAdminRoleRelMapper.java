package com.example.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ums.dto.resp.RoleResp1;
import com.example.ums.entity.UmsAdminRoleRel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author wxy
 * @Date 2021/3/30 13:45
 * @Version 1.0
 */
public interface UmsAdminRoleRelMapper extends BaseMapper<UmsAdminRoleRel> {

    @Select("select b.role_id,b.name from ums_admin_role_rel as a " +
            "left join ums_role as b on a.role_id = b.role_id " +
            "where a.admin_id = #{adminId}")
    List<RoleResp1> findRoleResp1ByAdminId(@Param("adminId") String adminId);
}
