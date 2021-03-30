package com.warthur.nacos.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author warthur
 * @date 2020/11/27
 */
@SpringCloudApplication
@ComponentScan("io.shardingsphere.transaction.aspect")
public class MainApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
    }
}
