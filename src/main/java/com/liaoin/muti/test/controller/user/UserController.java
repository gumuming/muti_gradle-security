package com.liaoin.muti.test.controller.user;

import com.liaoin.muti.test.http.Response;
import com.liaoin.muti.test.okhttp.JsonFromUrlHelper;
import com.liaoin.muti.test.okhttp.OkhttpUtils;
import com.liaoin.muti.test.security.UserInfo;
import com.liaoin.muti.test.service.user.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author: RanJingde
 * program: test
 * description: 用户基本信息模块
 * create: 2019-11-12 14:08
 * Email: sarakarma49@gmail.com
 */
@RequestMapping("/user")
@RestController
@ApiModel("用户--api")
public class UserController {
    @Value("${server.port}")
    private String port;

    @Value("${liaoin.security.default-login-processing-url-from}")
    private String loginUrl;

    @Value("${liaoin.security.default-login-processing-url-phone}")
    private String noPassWordUrl;


    @Resource
    private UserService userService;


    @Resource
    JsonFromUrlHelper jsonFromUrlHelper;

    @ApiOperation(value = "登陆接口 用户名和密码")
    @PostMapping("login/authentication/form")
    @ApiImplicitParam(name = "Login", value = "Basic dXNlcl8xOjEyMzQ1Ng==", required = true, dataType = "string", paramType = "header")
    public Response login(HttpServletRequest httpServletRequest, @ApiParam("名称") @RequestParam("username") String username,
                          @ApiParam("密码") @RequestParam("password") String password){
        HashMap<String, String> stringStringHashMap = new HashMap<>(2);
        stringStringHashMap.put("username",username);
        stringStringHashMap.put("password",password);
        String login = httpServletRequest.getHeader("Login");
        String url = "http://localhost:" + port + loginUrl;
        return jsonFromUrlHelper.login(url,stringStringHashMap,"Login",login);
    }

    @ApiOperation(value = "登陆接口 用户名登陆")
    @PostMapping("login/authentication/mobile")
    @ApiImplicitParam(name = "Login", value = "Basic dXNlcl8xOjEyMzQ1Ng==", required = true, dataType = "string", paramType = "header")
    public Response loginNoPassWord(HttpServletRequest httpServletRequest, @ApiParam("名称") @RequestParam("username") String username){
        //todo 做小程序登陆校验
        HashMap<String, String> stringStringHashMap = new HashMap<>(1);
        stringStringHashMap.put("mobile",username);
        String login = httpServletRequest.getHeader("Login");
        String url = "http://localhost:" + port + noPassWordUrl;
        return jsonFromUrlHelper.login(url,stringStringHashMap,"Login",login);
    }

    @ApiOperation(value = "测试添加用户")
    @PostMapping("login/authentication/register")
    @ApiImplicitParam(name = "Login", value = "Basic dXNlcl8xOjEyMzQ1Ng==", defaultValue = "Basic dXNlcl8xOjEyMzQ1Ng==",required = true, dataType = "string", paramType = "header")
    public Response register(
                             @ApiParam(name = "username",defaultValue = "admin",value = "名称") @RequestParam(name = "username") String username,
                             @ApiParam(name = "password",defaultValue = "123456",value = "密码") @RequestParam(name = "password") String password){
        return userService.register(username,password).ifPresent(userInfo -> Response.success(),()->Response.failed("error"));
    }

    @ApiOperation(value = "查询用户信息")
    @PostMapping("login/authentication/info")
    @ApiImplicitParam(name = "Authorization", value = "授权码 以 Bearer  带一个空格", required = true, dataType = "string", paramType = "header")
//    @ApiImplicitParam(name = "Login", value = "Basic dXNlcl8xOjEyMzQ1Ng==", defaultValue = "Basic dXNlcl8xOjEyMzQ1Ng==",required = true, dataType = "string", paramType = "header")
    public Response info(
            @ApiParam(name = "id",defaultValue = "1",value = "名称") @RequestParam(name = "id") Integer id){
        Optional<UserInfo> byId = userService.findById(id);
        boolean present = byId.isPresent();
        if(present)
            return Response.success(byId.get());
        else
            return Response.failed("error");
    }
}