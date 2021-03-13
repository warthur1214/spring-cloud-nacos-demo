package com.warthur.nacos.demo.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * 切面执行顺序如下：
 * 1. 执行切面1的@Around注解方法里joinPoint.proceed()之前的代码；
 * 2. 执行切面1的@Before注解方法体逻辑；
 * 3. 执行切面2的@Around注解方法里joinPoint.proceed()之前的代码；
 * 4. 执行切面2的@Before注解方法体逻辑；
 * 5. 执行被切面方法体逻辑；
 * 6. 执行切面2的@After注解方法体逻辑；
 * 7. 执行切面2的@AfterReturning注解方法体逻辑；
 * 8. 执行切面1的@After注解方法体逻辑；
 * 9. 执行切面1的@AfterReturning注解方法体逻辑；
 * @author warthur
 */
@Slf4j
public abstract class AbstractAspect {

    static  {
        Field field = ReflectionUtils.findField(RequestFacade.class, "request");
        Assert.state(field != null, "Incompatible Tomcat implementation");
        ReflectionUtils.makeAccessible(field);
    }

    protected HttpServletRequest getRequest() {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        return attributes.getRequest();
    }
}
