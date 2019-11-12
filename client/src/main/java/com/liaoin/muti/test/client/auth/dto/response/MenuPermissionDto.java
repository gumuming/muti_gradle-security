package com.liaoin.muti.test.client.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuPermissionDto {
    private String id;

    private String parentId;

    private String name;

    private String path;

    private String component;

    private String redirect;

    private Boolean alwaysShow;

    private Boolean leaf;

    private Boolean hidden;

    private Boolean status;

    private Integer order;

    private MenuPermissionMetaDto meta;

    private List<MenuPermissionDto> children;
}
