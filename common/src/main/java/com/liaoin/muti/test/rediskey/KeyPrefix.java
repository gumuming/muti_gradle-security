package com.liaoin.muti.test.rediskey;
/**
*redis key 管理
*@author: Sara Karma
*@date: 2019/11/11
*/
public interface KeyPrefix {

    /**
     * 获取前缀
     * @return String
     */
    String getPrefixKey();

    /**
     * 过期时间 /s
     * @return int
     */
    int  getExpireSeconds();


}
