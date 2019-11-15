package com.liaoin.muti.test.file.fileBase64;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 富文本配置
 */
@Component
@Order(value = 0)
@Slf4j
public class PropertiesCommandLineRunner implements CommandLineRunner {

    @Value("${project.properties.files}")
    private String files;
    
    @Autowired
    private ConfigManager configManager;

    @Value("${project.properties.ueditorConfig}")
    private String config;

    @Value("${udeitor.config}")
    private String configPath;

    @Override
    public void run(String... args) throws Exception {
        log.info("开始读取富文本配置文件");
        configManager.initEnv(configPath);
        try {
            if (StringUtils.isNoneBlank(files)) {
                Properties prop = new Properties();
                files = files.trim();
                String[] f = files.split(",");
                for (String fileName : f) {
                    InputStreamReader sr = new InputStreamReader(PropertiesCommandLineRunner.class.getClassLoader().getResourceAsStream(fileName),"UTF-8");
                    prop.load(sr);
                }
                PropertiesUtil.init(prop);
                log.info("读取富文本配置文件结束");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}