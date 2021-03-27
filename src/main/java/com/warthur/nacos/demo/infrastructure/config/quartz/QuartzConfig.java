package com.warthur.nacos.demo.infrastructure.config.quartz;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author warthur
 * @date 2021/03/18
 */
@Configuration
public class QuartzConfig {

    /**
     * QuartzDataSource 注解则是配置Quartz独立数据源的配置
     */
    @Bean
    @org.springframework.boot.autoconfigure.quartz.QuartzDataSource
    @ConfigurationProperties(prefix = "spring.quartz.datasource")
    public DataSource quartzDataSource(){
        return new HikariDataSource();
    }

}
