package com.wenhao.serviceedu;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-04-30 13:10
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.wenhao"})
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceEduMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduMain.class,args);
    }
}
