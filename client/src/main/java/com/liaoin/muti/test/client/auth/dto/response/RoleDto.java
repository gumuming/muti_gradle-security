package com.liaoin.muti.test.client.auth.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleDto {
    private List<String> names;

    private List<MenuPermissionDto> menus;

    private List<String> apis;

    private List<String> routes;
}
