package com.liaoin.muti.test.security.config.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Oauth2Properties {

    private Oauth2ClientProperties[] clients = {};
	/**
	 * jwt密钥
	 */
	private String secretKey="surpassm";
	/**
	 * jwt鉴权key
	 */
	private String authoritiesKey="surpassm";
	/**
	 * jwt过期时间是3600秒，既是1个小时
	 */
	private long expiration = 3600L;
	/**
	 * jwt选择了记住我之后的过期时间为7天
	 */
	private long expirationRemember = 604800L;

	private String storeType;
}