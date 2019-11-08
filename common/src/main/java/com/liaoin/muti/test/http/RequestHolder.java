//package com.liaoin.muti.test.http;
//import com.liaoin.shareepc.entity.sourceone.user.User;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 通过threadLocal　获取用户信息和请求信息
// */
//public class RequestHolder {
//
//    private static final ThreadLocal<User> userHolder = new ThreadLocal<User>();
//
//    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();
//
//    public static void add(User user) {
//        userHolder.set(user);
//    }
//
//    public static void add(HttpServletRequest request) {
//        requestHolder.set(request);
//    }
//
//    public static User getCurrentUser() {
//        return userHolder.get();
//    }
//
//    public static HttpServletRequest getCurrentRequest() {
//        return requestHolder.get();
//    }
//
//    public static void remove() {
//        userHolder.remove();
//        requestHolder.remove();
//    }
//}
