package com.liaoin.muti.test.security.config.properties;

import lombok.Data;

/**
 * @program: security_demo
 * @description: 浏览器配置
 * @author: JingDe Ran
 * @create: 2019-10-25 09:32
 * @Email: sarakarma49@gmail.com
 */
@Data
public class BrowserProperties {

    private SessionProperties session = new SessionProperties();

    private String signUpUrl = "/imooc-signUp.html";

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginResponseType loginType = LoginResponseType.JSON;

    private int rememberMeSeconds = 3600;
}