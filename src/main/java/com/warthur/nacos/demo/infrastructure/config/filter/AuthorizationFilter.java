package com.warthur.nacos.demo.infrastructure.config.filter;

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

        // 判断appId是否有请求 class/method 权限，无则返回异常

        return invoker.invoke(invocation);
    }
}
