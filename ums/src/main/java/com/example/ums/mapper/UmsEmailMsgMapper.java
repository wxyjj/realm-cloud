package com.example.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ums.entity.UmsEmailMsg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author wxy
 * @Date 2021/3/31 15:08
 * @Version 1.0
 */
public interface UmsEmailMsgMapper extends BaseMapper<UmsEmailMsg> {

    @Select("select a.* from ums_email_msg as a where a.email_msg_id = #{emailMsgId}")
    UmsEmailMsg findUmsEmailMsgByEmailMsgId(@Param("emailMsgId") String emailMsgId);
}
