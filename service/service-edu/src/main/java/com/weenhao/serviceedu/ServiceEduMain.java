package com.weenhao.serviceedu;

/**
 * @author Wenhao Tong
 * @Description
 * @create 2021-04-30 13:10
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.wenhao"})
public class ServiceEduMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduMain.class,args);
    }
}
