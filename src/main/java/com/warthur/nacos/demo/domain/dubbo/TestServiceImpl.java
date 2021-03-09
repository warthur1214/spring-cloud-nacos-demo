package com.warthur.nacos.demo.domain.dubbo;

import com.warthur.nacos.demo.application.service.TestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author warthur
 * @date 2021/03/04
 */
@Service
@DubboService
public class TestServiceImpl implements TestService {

    @Value("${spring.application.name: demo-config}")
    private String appName;

    @Override
    public String getConfig() {
        return appName;
    }
}
