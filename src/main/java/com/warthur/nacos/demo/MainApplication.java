package com.warthur.nacos.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author warthur
 * @date 2020/11/27
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.warthur.nacos.demo.infrastructure.dao")
public class MainApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
    }
}
