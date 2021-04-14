package com.example.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ums.entity.UmsEmailMsg;
import org.apache.ibatis.annotations.Param;

/**
 * @Author wxy
 * @Date 2021/3/31 15:08
 * @Version 1.0
 */
public interface UmsEmailMsgMapper extends BaseMapper<UmsEmailMsg> {

    UmsEmailMsg findUmsEmailMsgByEmailMsgId(@Param("emailMsgId") String emailMsgId);
}
