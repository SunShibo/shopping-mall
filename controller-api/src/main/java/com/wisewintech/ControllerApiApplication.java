package com.wisewintech;

import org.elasticsearch.action.get.MultiGetRequest;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.wisewintech.dao"})
@EnableTransactionManagement
public class ControllerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControllerApiApplication.class, args);
    }

}
