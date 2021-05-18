package com.warthur.nacos.demo.infrastructure.config.filter;

import com.google.common.collect.Iterables;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author warthur
 * @date 2021/03/12
 */
@Activate(group = CommonConstants.PROVIDER)
@NoArgsConstructor
@Slf4j
public class AuthorizationFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String appId = invocation.getAttachment("appId");
        String clazz = invocation.getAttachment("interface");
        String method = clazz.concat(".").concat(invocation.getMethodName());

        // 判断appId是否有请求 class/method 权限，无则返回异常
        log.info("服务名：{}", method);

        return invoker.invoke(invocation);
    }
}
