package com.liaoin.muti.test.client.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
    private String id;

    private String name;

    private String url;

    private String parentId;

    private List<PermissionDto> children;
}
