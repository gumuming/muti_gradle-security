package com.liaoin.muti.test.security.constant;

/**
 * @author mc
 * version 1.0v
 * date 2019/3/7 0:01
 * description TODO
 */
public interface Constant {

    /**
     * redis token key prefix
     */
    String TOKEN_KEY_PREFIX ="access:";
    String USER_INFO_KEY ="userInfo:";
    int SUCCESS_CODE = 200;
    String SUCCESS_MSG = "成功";
    int FAIL_CODE = 400;
    String FAIL_MSG = "失败";
    int FAIL_SESSION_CODE = 401;
    String FAIL_SESSION_MSG = "会话已过期，请重新登陆";
}
