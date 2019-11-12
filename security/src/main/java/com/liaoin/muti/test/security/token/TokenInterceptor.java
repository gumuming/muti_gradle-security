package com.liaoin.muti.test.security.token;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.liaoin.muti.test.helper.commone.RedisHelper;
import com.liaoin.muti.test.http.Response;
import com.liaoin.muti.test.http.ResultCode;
import com.liaoin.muti.test.rediskey.user.TokenKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.liaoin.muti.test.security.constant.Constant.TOKEN_KEY_PREFIX;
import static com.liaoin.muti.test.security.constant.Constant.USER_INFO_KEY;

/**
 * @author mc
 * Create date 2019/3/1 9:12
 * Version 1.0
 * Description token拦截器
 */
@Component
@Slf4j
public class TokenInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RedisHelper redisHelper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("token 拦截生效 requestUrl:{}",request.getRequestURI());
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			String redisToken = redisHelper.get(USER_INFO_KEY + token);
			if(StringUtils.isEmpty(redisToken)){
				response(response);
				return false;
			}
			request.setAttribute("Authorization", token);
			return true;
		}
		response(response);
		return false;
	}
	/**
	 * 返回错误信息
	 */
	private void response(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try (PrintWriter out = response.getWriter()) {
			out.write(objectMapper.writeValueAsString(Response.failed(HttpStatus.FORBIDDEN.value(), ResultCode.PERMISSION_NO_ACCESS.getMsg())));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
