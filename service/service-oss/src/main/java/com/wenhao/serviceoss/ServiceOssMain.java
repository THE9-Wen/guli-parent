package com.wenhao.serviceoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-05-02 13:27
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.wenhao"})
@EnableDiscoveryClient
public class ServiceOssMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOssMain.class,args);
    }
}
