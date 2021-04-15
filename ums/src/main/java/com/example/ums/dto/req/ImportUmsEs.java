package com.example.ums.dto.req;

import com.example.ums.entity.UmsAdmin;
import com.example.ums.entity.UmsRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author wxy
 * @Date 2021/4/15 10:34
 * @Version 1.0
 */
@Data
public class ImportUmsEs implements Serializable {
    private static final long serialVersionUID = 5974151102577404405L;

    @ApiModelProperty(value = "用户")
    private UmsAdmin umsAdmin;
    @ApiModelProperty(value = "用户拥有的权限")
    private List<UmsRole> umsRoleList;
}
