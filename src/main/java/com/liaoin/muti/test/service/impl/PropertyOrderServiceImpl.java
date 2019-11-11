package com.liaoin.muti.test.service.impl;

import com.liaoin.muti.test.project.entity.PropertyOrder;
import com.liaoin.muti.test.http.Response;
import com.liaoin.muti.test.project.mapper.PropertyOrderMapper;
import com.liaoin.muti.test.service.PropertyOrderService;
import com.liaoin.muti.test.util.java8.OptionalExt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author: RanJingde
 * program: test
 * description: 测试
 * create: 2019-11-07 11:44
 * Email: sarakarma49@gmail.com
 */
@Service
public class PropertyOrderServiceImpl implements PropertyOrderService {
    @Resource
    private PropertyOrderMapper propertyOrderMapper;

    @Override
    public Response findById(String id) {
        return OptionalExt.ofNullable(propertyOrderMapper.selectByPrimaryKey(id)).ifPresent(propertyOrder -> Response.success(propertyOrder),
                ()->Response.failed("未找到记录"));
    }

    @Override
    public Response save(PropertyOrder propertyOrder) {
        if(Objects.isNull(propertyOrder.getId())){
            propertyOrderMapper.insert(propertyOrder);
        }else{
            propertyOrderMapper.updateByPrimaryKeySelective(propertyOrder);
        }
        return Response.success();
    }

    @Override
    public Response testXml(String name) {
        return Response.success(propertyOrderMapper.testXml(name));
    }
}