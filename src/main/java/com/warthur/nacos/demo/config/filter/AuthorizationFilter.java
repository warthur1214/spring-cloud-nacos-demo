package com.warthur.nacos.demo.config.filter;

import com.warthur.nacos.demo.application.service.AppAuthService;
import lombok.NoArgsConstructor;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.*;

/**
 * @author warthur
 * @date 2021/03/12
 */
@Activate(group = CommonConstants.PROVIDER)
@NoArgsConstructor
public class AuthorizationFilter implements Filter {

    @DubboReference
    private AppAuthService authService;

    public AuthorizationFilter(AppAuthService authService) {
        this.authService = authService;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String appId = invocation.getAttachment("appId");
        String clazz = invocation.getServiceName();
        String method = invocation.getServiceName().concat(".").concat(invocation.getMethodName());

        // 自己实现服务类，验证权限
        if (!invocation.getServiceName().equals(AppAuthService.class.getName())
                && authService.hasPermissions(appId, clazz, method)) {
            throw new RuntimeException("无权限访问");
        }

        return invoker.invoke(invocation);
    }
}
