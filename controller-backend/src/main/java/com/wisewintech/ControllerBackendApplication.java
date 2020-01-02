package com.wisewintech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.wisewintech.dao"})
@EnableTransactionManagement
public class ControllerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControllerBackendApplication.class, args);
    }

}
