package com.warthur.nacos.demo.domain.dubbo;

import com.warthur.nacos.demo.application.service.AppAuthService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author warthur
 * @date 2021/03/12
 */
@DubboService
public class AppAuthServiceImpl implements AppAuthService {

    @Override
    public boolean hasPermissions(String appId, String... permissions) {
        // todo 查询数据库，或者redis

        return true;
    }
}
