package com.liaoin.muti.test.rediskey.user;

import com.liaoin.muti.test.rediskey.BasePrefixKey;

/**
 * @author: RanJingde
 * program: test
 * description: token key
 * create: 2019-11-12 11:03
 * Email: sarakarma49@gmail.com
 */
public class TokenKey  extends BasePrefixKey {
    public TokenKey(String prefix) {
        super(prefix);
    }

    public TokenKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    @Override
    public String getPrefixKey() {
        return "access:";
    }
}