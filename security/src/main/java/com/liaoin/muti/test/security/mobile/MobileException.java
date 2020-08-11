package com.liaoin.muti.test.security.mobile;

import org.springframework.security.core.AuthenticationException;

import java.io.Serializable;


public class MobileException extends AuthenticationException implements Serializable {
	public MobileException(String msg) {
		super(msg);
	}
}
