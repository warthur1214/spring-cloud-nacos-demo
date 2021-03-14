package com.warthur.nacos.demo.infrastructure.config.filter;

import com.warthur.nacos.demo.domain.utils.StpDubboUtils;
import lombok.NoArgsConstructor;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author warthur
 * @date 2021/03/12
 */
@Activate(group = CommonConstants.PROVIDER)
@NoArgsConstructor
public class AuthorizationFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String appId = invocation.getAttachment("appId");
        String clazz = invocation.getServiceName();
        String method = invocation.getServiceName().concat(".").concat(invocation.getMethodName());

        if (!StpDubboUtils.hasPermission(appId, clazz) && !StpDubboUtils.hasPermission(appId, method)) {
            throw new RuntimeException("无权限访问");
        }

        return invoker.invoke(invocation);
    }
}
