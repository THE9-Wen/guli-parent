package com.wenhao.servicecms;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-06 22:10
 */
@SpringBootApplication
@ComponentScan("com.wenhao")
@MapperScan("com.wenhao.servicecms.mapper")
public class CmsApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplicaiton.class,args);
    }
}
