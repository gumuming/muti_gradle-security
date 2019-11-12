package com.liaoin.muti.test.controller;

import com.liaoin.muti.test.project.entity.PropertyOrder;
import com.liaoin.muti.test.http.Response;
import com.liaoin.muti.test.security.annotation.AuthorizationToken;
import com.liaoin.muti.test.service.PropertyOrderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author: RanJingde
 * program: test
 * description: 测试
 * create: 2019-11-07 11:33
 * Email: sarakarma49@gmail.com
 */
@RequestMapping
@RestController
@ApiModel("测试--api")
public class TestController {
    @Resource
    PropertyOrderService propertyOrderService;

    @PostMapping("test")
    @ApiOperation("测试")
    @ApiImplicitParam(name = "Authorization", value = "授权码 以 Bearer  带一个空格", required = true, dataType = "string", paramType = "header")
    public Response test( @ApiParam(hidden = true) @AuthorizationToken String accessToken){
        return Response.success("hello 秦可卿");
    }
    @PostMapping("save")
    @ApiOperation("save")
    @ApiImplicitParam(name = "Authorization", value = "授权码 以 Bearer  带一个空格", required = true, dataType = "string", paramType = "header")
    public Response save(@RequestBody PropertyOrder propertyOrder){
        return propertyOrderService.save(propertyOrder);
    }

    @ApiOperation("查看详情")
    @GetMapping("findById")
    @ApiImplicitParam(name = "Authorization", value = "授权码 以 Bearer  带一个空格", required = true, dataType = "string", paramType = "header")
    public Response findById(@RequestParam String id){
        return propertyOrderService.findById(id);
    }

    @ApiOperation("testXml")
    @GetMapping("testXml")
    @ApiImplicitParam(name = "Authorization", value = "授权码 以 Bearer  带一个空格", required = true, dataType = "string", paramType = "header")
    public Response testXml(@RequestParam String name){
        return propertyOrderService.testXml(name);
    }

}