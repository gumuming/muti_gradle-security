package com.liaoin.muti.test.okhttp;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: config-spring-boot-starter
 * @description: 易软返回对象，不是集合
 * @author: JingDe Ran
 * @create: 2019-10-12 14:42
 * @Email: sarakarma49@gmail.com
 */
@Data
public class ResponseNoList<T> {

    private ResponseNoList.Response response;

    private T body;

    @Data
    public class Response implements Serializable {
        private String code;
        private String msg;
    }


}