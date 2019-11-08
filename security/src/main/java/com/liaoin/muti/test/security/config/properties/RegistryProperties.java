package com.liaoin.muti.test.security.config.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "config.registry")
public class RegistryProperties {

    /**
     * 不需要验证的url
     */
    private String[] noVerify ={};
	/**
	 * 数据加密在key 长度必须16位
	 */
	private String encryptKey="d7b85f6e214abcda";

}