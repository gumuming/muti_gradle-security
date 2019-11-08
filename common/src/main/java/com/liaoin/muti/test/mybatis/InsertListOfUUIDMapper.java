package com.liaoin.muti.test.mybatis;

import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;


@RegisterMapper
public interface InsertListOfUUIDMapper<T> {

    @InsertProvider(type = InsertListOfUUIDProvider.class, method = "dynamicSQL")
    int insertList(List<? extends T> recordList);
}
