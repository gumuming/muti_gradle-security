package com.liaoin.muti.test.security.config.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.liaoin.muti.test.security.config.constant.Constant;
import lombok.Data;

/**
 * 前端JSON返回格式,自定义响应格式
 *
 * @author mc
 * version 1.0
 * date 2018/10/30 12:52
 * description
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T>{
	/**
	 * 响应业务状态
	 */
	private Integer code;

	/**
	 * 响应消息
	 */
	private String message;

	/**
	 * 响应中的数据
	 */
	private T data;

	public Result(T data) {
		this.code = Constant.SUCCESS_CODE;
		this.message = Constant.SUCCESS_MSG;
		this.data = data;
	}

	public Result(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Result(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}




}
