package com.liaoin.muti.test.service.file.file.normal.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix ="config.file" )
public class Config {

    private String fileName;

    /**端口号*/
    private Integer filePort;
    /**本地文件地址*/
    private String  filePath;

    /**本地文件地址*/
    private String  filePath1;
    /**IP地址*/
    private String  fileHost;
    /**请求地址*/
    private String  fileUrl;
    /**设置不能上传的文件类型*/
    private String  fileType;











}
