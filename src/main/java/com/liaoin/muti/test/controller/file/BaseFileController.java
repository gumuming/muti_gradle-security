package com.liaoin.muti.test.controller.file;

import com.alibaba.fastjson.JSONException;
import com.liaoin.muti.test.file.fileBase64.ActionEnter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(description = "富文本件上传--")
@RestController
@RequestMapping(value = "/base")
public class BaseFileController {

    @Autowired
    private ActionEnter actionEnter;

    @ApiOperation("base64上传")
    @RequestMapping(value = "/uploadForUeditor")
    @ResponseBody
    public String uploadForUeditor(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        MultipartFile file=null;
        if(request instanceof MultipartHttpServletRequest) {
            file = ((MultipartHttpServletRequest)request).getFile("upfile");
            String result = actionEnter.exec(file);
            System.out.println(result);
            return result;
        }
        return actionEnter.exec(file);
    }

















}
