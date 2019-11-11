package com.liaoin.muti.test.rediskey;

/**
 * @author: RanJingde
 * program: test
 * description: 抽象基类
 * create: 2019-11-11 09:42
 * Email: sarakarma49@gmail.com
 */
public abstract class BasePrefixKey implements KeyPrefix{
    private int expireSeconds;

    private String prefix;

    public BasePrefixKey(String prefix) {
        //0代表永不过期
        this(0, prefix);
    }

    public BasePrefixKey(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int  getExpireSeconds() {
        //默认0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefixKey() {
        String className = getClass().getSimpleName();
        return className+":" + prefix+":";
    }
}