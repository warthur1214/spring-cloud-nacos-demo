package com.warthur.nacos.demo.config.filter;

import cn.dev33.satoken.stp.StpUtil;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author warthur
 * @date 2021/03/12
 */
@Activate
public class ConsumerContextFilter implements Filter {

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

        StpUtil.setLoginId(springApp.getAppName());

        RpcContext.getContext().setAttachment("appId", springApp.getAppName());

        return invoker.invoke(invocation);
    }
}
