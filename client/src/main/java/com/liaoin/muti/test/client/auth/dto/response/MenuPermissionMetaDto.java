package com.liaoin.muti.test.client.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuPermissionMetaDto {
    private String id;

    private String title;

    private String defaultIcon;

    private String activeIcon;
}
