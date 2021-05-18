package com.warthur.nacos.demo.infrastructure.config.filter;

import com.warthur.nacos.demo.infrastructure.config.satoken.SpringApp;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author warthur
 * @date 2021/03/12
 */
@Activate(group = CommonConstants.PROVIDER)
@NoArgsConstructor
@Slf4j
public class AuthorizationFilter implements Filter {

    /**
     * 变量名与Bean名称一致
     */
    private SpringApp springApp;

    /**
     * dubbo自动注入bean，注意变量名
     * @param app bean object
     */
    public void setSpringApp(SpringApp app) {
        this.springApp = app;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String appId = invocation.getAttachment("remote.application");
        String clazz = invocation.getServiceName();
        if (StringUtils.isEmpty(clazz)) {
            clazz = invocation.getAttachment("interface");
        }
        String method = clazz.concat(".").concat(invocation.getMethodName());

        // 判断appId是否有请求 class/method 权限，无则返回异常
        log.info("调用方：{}，服务名：{}", appId, method);
        // if ("spring-cloud-nacos-consumer".equals(appId)) {
        //     throw new RpcException("无权限");
        // }

        return invoker.invoke(invocation);
    }
}
