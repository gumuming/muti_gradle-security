package com.liaoin.muti.test.security.mobile;


import com.liaoin.muti.test.security.properties.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;


@Component
public class MobileProcessorServiceDefaultImpl implements MobileProcessorService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void validatePhone(ServletWebRequest servletWebRequest) {
		String parameter = servletWebRequest.getParameter(SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE);
		logger.info("登陆请求验证手机号："+parameter);

	}
}
