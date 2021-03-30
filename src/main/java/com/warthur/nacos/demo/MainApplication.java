package com.warthur.nacos.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author warthur
 * @date 2020/11/27
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("io.shardingsphere.transaction.aspect")
public class MainApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
    }
}
