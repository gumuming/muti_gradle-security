package com.liaoin.muti.test.project.mapper;

import com.liaoin.muti.test.project.entity.PropertyOrder;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;
/**
* @author: Sara Karma
* @date: 2019/11/7
*/
@Mapper
public interface PropertyOrderMapper extends BaseMapper<PropertyOrder> {
    PropertyOrder testXml(String name);
}
