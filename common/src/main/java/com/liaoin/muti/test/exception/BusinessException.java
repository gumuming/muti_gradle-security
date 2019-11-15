package com.liaoin.muti.test.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 自定义异常
 */
@Getter
@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private Integer code = 500;

    /**
     * Constructs a new runtime com.liaoin.service.core.com.liaoin.service.controller.exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime com.liaoin.service.core.com.liaoin.service.controller.exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     * @param code 代码
     */
    public BusinessException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
