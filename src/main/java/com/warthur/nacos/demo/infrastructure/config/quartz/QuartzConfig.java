package com.warthur.nacos.demo.infrastructure.config.quartz;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

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

    @Bean(name = "SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {

        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        // 默认的自动执行调度,这里设置为不自动执行调度，
        // 为了方便扩展集群分布式调度任务，这个服务只配置调度，另外启动一个或多个服务执行调度
        factory.setAutoStartup(false);
        return factory;
    }
}
