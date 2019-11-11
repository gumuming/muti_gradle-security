package com.liaoin.muti.test.security.config.token;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.liaoin.muti.test.http.Response;
import com.liaoin.muti.test.http.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("token 拦截生效");
		log.info("requestUrl:{}",request.getRequestURI());
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			request.setAttribute("Authorization", token);
			return true;
		}
		response(response);
		return true;
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
