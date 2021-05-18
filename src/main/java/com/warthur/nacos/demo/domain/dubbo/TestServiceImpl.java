package com.warthur.nacos.demo.domain.dubbo;

import com.warthur.nacos.dubbo.TestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author warthur
 * @date 2021/03/04
 */
// @Service
@DubboService
public class TestServiceImpl implements TestService {

    @Value("${spring.application.name: demo-config}")
    private String appName;

    @Value("${server.port}")
    private String port;

    @Override
    public String getConfig() {

        // try {
        //     TimeUnit.SECONDS.sleep(10);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        return MessageFormat.format("appName: {0}, port: {1}", appName, port);
    }
}
