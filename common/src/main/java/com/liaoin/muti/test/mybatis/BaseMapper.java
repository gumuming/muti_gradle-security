package com.liaoin.muti.test.mybatis;


import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;


public interface BaseMapper<T> extends Mapper<T>, InsertListMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}