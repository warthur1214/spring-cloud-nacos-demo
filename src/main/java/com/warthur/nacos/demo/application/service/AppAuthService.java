package com.warthur.nacos.demo.application.service;

/**
 * @author warthur
 * @date 2021/03/12
 */
public interface AppAuthService {

    /**
     * 校验是否有权限访问
     * @param appId 应用标识
     * @param permissions 权限
     * @return 结果
     */
    boolean hasPermissions(String appId, String... permissions);
}
