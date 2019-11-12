package com.liaoin.muti.test.security;


import com.liaoin.muti.test.security.properties.RegistryProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: JingDe Ran
 * program: security_demo
 * description:
 * create: 2019-10-25 16:43
 * Email: sarakarma49@gmail.com
 */
@Configuration
@EnableConfigurationProperties(RegistryProperties.class)
public class RegistryConfig {
}