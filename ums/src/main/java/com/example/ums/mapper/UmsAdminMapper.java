package com.example.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ums.entity.UmsAdmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author wxy
 * @Date 2021/3/30 13:45
 * @Version 1.0
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    @Select("select a.* from ums_admin as a where a.username = #{userName}")
    UmsAdmin findUmsAdminByUserName(@Param("userName") String userName);

    @Select("select a.email from ums_admin as a where a.username = #{userName}")
    String findEmailByUserName(@Param("userName") String userName);
}
