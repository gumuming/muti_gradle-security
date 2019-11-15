package com.liaoin.muti.test.client.auth.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户角色")
public class UserRoleEditParam {
    @ApiModelProperty(value = "用户id",example = "")
    private List<String> userIds;

    @ApiModelProperty(value = "角色id",example = "")
    private List<String> roleIds;
}
