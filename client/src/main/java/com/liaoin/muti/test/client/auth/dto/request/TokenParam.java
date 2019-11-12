package com.liaoin.muti.test.client.auth.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 获取token参数
 */
@Getter
@Setter
public class TokenParam {

    /**
     * 用户ID
     */
    private String userId;
}
