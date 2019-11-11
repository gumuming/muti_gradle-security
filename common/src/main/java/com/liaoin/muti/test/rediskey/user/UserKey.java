package com.liaoin.muti.test.rediskey.user;

import com.liaoin.muti.test.rediskey.BasePrefixKey;

/**
 * @author: RanJingde
 * program: test
 * description: 用户模块key
 * create: 2019-11-11 09:46
 * Email: sarakarma49@gmail.com
 */
public class UserKey extends BasePrefixKey {

    private UserKey(String prefix){
        super(prefix);
    }

    private UserKey(String prefix,int expireSeconds){
        super(expireSeconds,prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");

    public static UserKey getByIdWithTime = new UserKey("id",300);

    public static void main(String[] args) {
        System.out.println(UserKey.getByIdWithTime.getPrefixKey()+">>"+UserKey.getByIdWithTime.getExpireSeconds());
    }

}