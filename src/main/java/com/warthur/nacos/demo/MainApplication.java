package com.warthur.nacos.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


/**
 * @author warthur
 * @date 2020/11/27
 */
@SpringCloudApplication
public class MainApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
    }
}
