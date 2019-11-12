package com.liaoin.muti.test.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author mc
 * version 1.0v
 * date 2019/2/10 17:45
 * description TODO
 */
public class SecurityAuthenticationException extends AuthenticationException {

    public SecurityAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public SecurityAuthenticationException(String msg) {
        super(msg);
    }
}
