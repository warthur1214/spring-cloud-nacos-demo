package com.warthur.nacos.demo.infrastructure.config.quartz;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author warthur
 * @date 2021/03/18
 */
@Configuration
@Setter
@ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.quartz")
public class QuartzConfig {

    private String driverClassName;
    private String url;
    private String userName;
    private String password;

    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer() {
        return bean -> bean.setDataSource(getDataSource());
    }

    /**
     * Quartz 的DataSource不再声明成bean使用
     */
    public HikariDataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }
}
