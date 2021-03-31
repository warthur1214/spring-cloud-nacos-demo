package com.warthur.nacos.demo.infrastructure.config.quartz;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author warthur
 * @date 2021/03/31
 */
@Configuration
@Setter
@ConfigurationProperties(prefix = "spring.quartz.datasource")
public class QuartzConfig {

    private String driverClassName;
    private String jdbcUrl;
    private String userName;
    private String password;

    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
        return bean -> bean.setDataSource(getDataSource());
    }

    /**
     * QuartzDataSource 注解则是配置Quartz独立数据源的配置
     */
    public HikariDataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }
}
