package com.warthur.nacos.demo.infrastructure.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author warthur
 * @date 2021/03/12
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {

        // 模拟普通账号授权
        if ("warthur".equals(loginId) || "wangdong".equals(loginId)) {
            return Arrays.asList("user:select", "user:find", "user:add", "user:edit", "user:delete");
        }

        // 模拟内部服务间 接口授权
        if ("spring-cloud-nacos-demo".equals(loginId)) {
            return Collections.singletonList("com.warthur.nacos.demo.application.service.TestService");
        }

        return new ArrayList<>();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        return Arrays.asList("admin", "user");
    }
}
