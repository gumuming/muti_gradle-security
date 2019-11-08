package com.liaoin.muti.test.security.config;


import com.liaoin.muti.test.security.config.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: security_demo
 * @description: security 配置
 * @author: JingDe Ran
 * @create: 2019-10-25 09:31
 * @Email: sarakarma49@gmail.com
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}