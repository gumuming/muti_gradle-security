package com.liaoin.muti.test.security.mobile;

import org.springframework.web.context.request.ServletWebRequest;


public interface MobileProcessorService {
	/**
	 * 校验手机号
	 * @param servletWebRequest servletWebRequest
	 */
	void validatePhone(ServletWebRequest servletWebRequest);
}
