package com.liaoin.muti.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
/**
*@author: Sara Karma
*@date: 2019/11/7
*/
@SpringBootApplication
@MapperScan("com.liaoin.muti.test.project.mapper")
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
