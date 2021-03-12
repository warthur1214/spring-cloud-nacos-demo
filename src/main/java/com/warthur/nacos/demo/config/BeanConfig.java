package com.warthur.nacos.demo.config;

import com.warthur.nacos.demo.config.satoken.SpringApp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author warthur
 * @date 2020/12/12
 */
@Configuration
public class BeanConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public SpringApp springApp() {
        return new SpringApp().setAppName(appName);
    }
}
