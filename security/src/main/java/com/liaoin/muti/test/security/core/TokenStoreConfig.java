package com.liaoin.muti.test.security.core;

import com.liaoin.muti.test.security.jwt.JwtEnhancer;
import com.liaoin.muti.test.security.properties.SecurityProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * @author mc
 * @version 1.0v
 * 改变令牌存储，这里采用redis存储
 */
@Configuration
public class TokenStoreConfig {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "liaoin.security.oAuth2",name = "storeType",havingValue = "redis",matchIfMissing = true)
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 定义jwt
     */
    @Configuration
    @ConditionalOnProperty(prefix = "liaoin.security.oAuth2",name = "storeType",havingValue = "jwt")
    public static class JwtTokenConfig{
        @Resource
        private SecurityProperties securityProperties;
        @Bean
        public TokenStore jwtTokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            accessTokenConverter.setSigningKey(securityProperties.getOAuth2().getSecretKey());
            return accessTokenConverter;
        }

        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer(){
            return new JwtEnhancer();
        }

    }
}
