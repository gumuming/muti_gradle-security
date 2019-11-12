package com.liaoin.muti.test.client.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleEditParam {
    private String id;

    private String name;

    private List<String> menuPermissionIds;

    private List<String> permissionIds;
}
