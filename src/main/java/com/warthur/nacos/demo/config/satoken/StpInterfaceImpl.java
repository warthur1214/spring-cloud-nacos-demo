package com.warthur.nacos.demo.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author warthur
 * @date 2021/03/12
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        return Arrays.asList("user:select", "user:find", "user:add", "user:edit", "user:delete");
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        return Arrays.asList("admin", "user");
    }
}
