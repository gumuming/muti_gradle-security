package com.liaoin.muti.test.http;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Slf4j
public class Response<T>  {


    private Integer code = 200;

    private String message;

    private T data = null;

    public static <T> Response<T> success() {
        return new Response<>();
    }

    public static <T> Response<T> success(T data) {
        val res = new Response<T>();
        res.setData(data);
        return res;
    }

    public static <T> Response<T> success(T data,String message) {
        val res = new Response<T>();
        res.setData(data);
        res.setMessage(message);
        return res;
    }

    public static <T> Response<T> failed(String message){
        val res = new Response<T>();
        res.setCode(500);
        res.setMessage(message);
        return res;
    }

    /**
     *  返回未获权状态码
     * @param httpCode httpCode
     * @param message message
     * @param <T> T
     * @return response
     */
    public static <T> Response<T> failed(Integer httpCode,String message){
        val res = new Response<T>();
        res.setCode(httpCode);
        res.setMessage(message);
        res.setData((T) "");
        return res;
    }

    public static <T> T handle(Response<T> response) {
        if (response.getCode() == null || !response.getCode().equals(200)) {
            throw new RuntimeException("服务调用失败：" + response.getMessage());
        }
        return response.getData();
    }

    public static void check(Response response) {
        if (response.getCode() == null || !response.getCode().equals(200)) {
            throw new RuntimeException("服务调用失败：" + response.getMessage());
        }
    }

    @Override
    public String toString() {
//        try {
//            return new JsonHelper(new ObjectMapper()).toString(this);
//        } catch (JsonProcessingException e) {
//            log.error("json转换异常参数：{}",this.data,e);
//            return "";
//        }
        return "";
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("message", message);
        result.put("data", data);
        return result;
    }
}
