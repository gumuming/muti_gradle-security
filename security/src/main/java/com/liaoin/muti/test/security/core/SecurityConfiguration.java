package com.liaoin.muti.test.security.core;


import com.liaoin.muti.test.security.authorize.AuthorizeCofigManager;
import com.liaoin.muti.test.security.handler.SecurityAuthenctiationFailureHandler;
import com.liaoin.muti.test.security.handler.SecurityAuthenticationSuccessHandler;
import com.liaoin.muti.test.security.mobile.MobileCodeAuthenticationSecurityConfig;
import com.liaoin.muti.test.security.properties.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

/**
 * @program: security_demo
 * @description: security 核心配置
 * @author: JingDe Ran
 * @create: 2019-10-25 11:09
 * @Email: sarakarma49@gmail.com
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler;
    @Resource
    private SecurityAuthenctiationFailureHandler securityAuthenctiationFailureHandler;
    @Resource
    AuthorizeCofigManager authorizeCofigManager;
    @Resource
    private MobileCodeAuthenticationSecurityConfig mobileCodeAuthenticationSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //表单登陆
        http.formLogin()
                //当请求需要身份认证时，默认跳转的url
                .loginPage(securityProperties.getDefaultUnauthenticatedUrl())
                //默认的用户名密码登录请求处理url
                .loginProcessingUrl(securityProperties.getDefaultLoginProcessingUrlFrom())
                //自定义登陆成功配置
                .successHandler(securityAuthenticationSuccessHandler)
                //自定义登陆失败配置
                .failureHandler(securityAuthenctiationFailureHandler)
                //登陆的账号属性名称
                .usernameParameter(securityProperties.getUsernameParameter())
                //登陆的密码属性名称
                .passwordParameter(securityProperties.getPasswordParameter())
//                .and()
//                .sessionManagement()
//                //session失效需要处理跳转的地址
//                .invalidSessionStrategy(invalidSessionStrategy)
//                //同一个用户在系统中的最大session数，默认1
//                .maximumSessions(securityProperties.getSession().getMaximumSessions())
//                //达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
//                .maxSessionsPreventsLogin(securityProperties.getSession().isMaxSessionsPreventsLogin())
//                //踢掉前一个session用户，超时策略设置
//                .expiredSessionStrategy(sessionInformationExpiredStrategy)
//                .and()
                .and()
                .logout()
                .logoutUrl(securityProperties.getDefaultLoginOutUrl())
                //自定义退出处理器
            //    .logoutSuccessHandler(logoutSuccessHandler)
                //删除指定的cookies
                .deleteCookies("SESSION_ID")
                //允许携带cookie
                .and()
                .cors()
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .disable();
        //增加手机直接登陆
        http.apply(mobileCodeAuthenticationSecurityConfig);
        //允许自定义头部信息
        http.headers().frameOptions().sameOrigin();
        //不需要验证的URL地址
        authorizeCofigManager.config(http.authorizeRequests());


        //设置manager控制器
//        AuthenticationManager manager = new AuthenticationManager();
//        manager.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
//        http.addFilterAfter(manager, UsernamePasswordAuthenticationFilter.class);

        //携带cookie和自己定义的header。
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry= http.authorizeRequests();
        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
    }
}