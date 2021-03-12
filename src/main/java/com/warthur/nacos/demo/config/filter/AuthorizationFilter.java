package com.warthur.nacos.demo.config.filter;

import cn.dev33.satoken.stp.StpUtil;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author warthur
 * @date 2021/03/12
 */
@Activate
public class AuthorizationFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String appId = invocation.getAttachment("appId");

        String clazz = invocation.getServiceName();
        String method = invocation.getServiceName().concat(".").concat(invocation.getMethodName());

        if (!StpUtil.hasPermission(appId, clazz) && !StpUtil.hasPermission(appId, method)) {
            throw new RuntimeException("无权限访问");
        }

        return invoker.invoke(invocation);
    }
}
