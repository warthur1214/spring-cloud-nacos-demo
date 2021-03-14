package com.warthur.nacos.demo.infrastructure.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.warthur.nacos.demo.infrastructure.config.satoken.SpringApp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

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

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(10000);
        httpRequestFactory.setConnectionRequestTimeout(15000);
        httpRequestFactory.setReadTimeout(90000);
        return httpRequestFactory;
    }

    @Bean(name = "fastJsonRestTemplate")
    public RestTemplate fastJsonRestTemplate(@Qualifier("clientHttpRequestFactory") ClientHttpRequestFactory httpRequestFactory) {

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setFastJsonConfig(new FastJsonConfig());

        RestTemplate fastJsonRestTemplate = new RestTemplate(httpRequestFactory);
        fastJsonRestTemplate.getMessageConverters().add(0, fastJsonHttpMessageConverter);

        return fastJsonRestTemplate;
    }

    @Bean
    public RestTemplate restTemplate(@Qualifier("clientHttpRequestFactory") ClientHttpRequestFactory httpRequestFactory) {

        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        restTemplate.getMessageConverters().add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.getMessageConverters().add(2, new FormHttpMessageConverter());

        return restTemplate;
    }
}
