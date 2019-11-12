package com.liaoin.muti.test.client.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEditParam {
    private List<String> userIds;

    private List<String> roleIds;
}
