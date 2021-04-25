package com.warthur.nacos.demo.infrastructure.config.quartz;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author warthur
 * @date 2021/03/18
 */
@Configuration
public class QuartzConfig {

    // /**
    //  * QuartzDataSource 注解则是配置Quartz独立数据源的配置
    //  */
    // @QuartzDataSource
    // @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.quartz")
    // public DataSource quartzDataSource(){
    //     return new HikariDataSource();
    // }

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Order(1)
    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
        DataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().build();
        return schedulerFactoryBean -> {
            schedulerFactoryBean.setDataSource(dataSource);
            schedulerFactoryBean.setTransactionManager(new DataSourceTransactionManager(dataSource));
        };
    }
}
