package com.warthur.nacos.demo.infrastructure.config.filter;

import com.warthur.nacos.demo.infrastructure.config.satoken.SpringApp;
import com.warthur.nacos.demo.domain.utils.StpDubboUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author warthur
 * @date 2021/03/12
 */
@Activate(group = CommonConstants.CONSUMER)
public class ConsumerAuthFilter implements Filter {

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

        RpcContext.getContext().setAttachment("appId", springApp.getAppName());
        return invoker.invoke(invocation);
    }
}
