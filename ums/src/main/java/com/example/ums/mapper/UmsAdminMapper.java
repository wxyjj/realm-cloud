package com.example.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ums.entity.UmsAdmin;
import org.apache.ibatis.annotations.Param;

/**
 * @Author wxy
 * @Date 2021/3/30 13:45
 * @Version 1.0
 */
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {

    UmsAdmin findUmsAdminByUserName(@Param("userName") String userName);

    String findEmailByUserName(@Param("userName") String userName);
}
