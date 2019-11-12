package com.liaoin.muti.test.controller.user;

import com.liaoin.muti.test.http.Response;
import com.liaoin.muti.test.okhttp.JsonFromUrlHelper;
import com.liaoin.muti.test.okhttp.OkhttpUtils;
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
    @Resource
    JsonFromUrlHelper jsonFromUrlHelper;

    @ApiOperation(value = "登陆接口")
    @PostMapping("login")
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
}