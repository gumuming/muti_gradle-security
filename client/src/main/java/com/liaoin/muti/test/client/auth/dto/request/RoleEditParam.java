package com.liaoin.muti.test.client.auth.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("角色参数")
public class RoleEditParam {

    @ApiModelProperty(value = "id",example = "")
    private String id;

    @ApiModelProperty(value = "名称",example = "管理员")
    private String name;

    @ApiModelProperty(value = "菜单Id",example = "")
    private List<String> menuPermissionIds;

    @ApiModelProperty(value = "权限Id",example = "")
    private List<String> permissionIds;
}
