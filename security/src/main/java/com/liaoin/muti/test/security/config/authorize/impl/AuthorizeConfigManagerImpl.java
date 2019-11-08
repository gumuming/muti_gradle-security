package com.liaoin.muti.test.security.config.authorize.impl;



import com.liaoin.muti.test.security.config.authorize.AuthorizeCofigManager;
import com.liaoin.muti.test.security.config.authorize.AuthorizeConfigProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mc
 * version 1.0v
 * date 2018/9/24 21:16
 * description TODO
 */
@Component
public class AuthorizeConfigManagerImpl implements AuthorizeCofigManager {
    @Resource
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders){
            authorizeConfigProvider.config(config);
        }
//        config.anyRequest().authenticated();
    }
}
