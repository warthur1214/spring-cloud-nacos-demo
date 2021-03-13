package com.warthur.nacos.demo.config.aop;

import com.warthur.nacos.demo.config.redis.StringRedisCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author warthur
 * @date 2021/03/13
 */
@Component
@Aspect
@Slf4j
@Order(250)
public class RequestParamAspect extends AbstractAspect {

    @Autowired
    private StringRedisCache stringRedisCache;

    @Pointcut("execution(@(org.springframework.web.bind.annotation.RequestMapping " +
            "|| org.springframework.web.bind.annotation.GetMapping " +
            "|| org.springframework.web.bind.annotation.PostMapping " +
            "|| org.springframework.web.bind.annotation.PutMapping " +
            "|| org.springframework.web.bind.annotation.DeleteMapping)" +
            "public * com.warthur.nacos.demo.interfaces.facade..*(..)) ")
    public void signAuth() {}

    /**
     * 排除被@SignAuthExclude、@GlobalAuthExclude标记过的方法
     */
    @Pointcut("!execution(@(com.warthur.nacos.demo.application.annotation.SignAuthExclude )" +
            "public * com.warthur.nacos.demo.interfaces.facade..*(..)) ")
    public void excludeMethod() {}

    /**
     * 排除被@SignAuthExclude、@GlobalAuthExclude标记过的类
     */
    @Pointcut("!within(@(com.warthur.nacos.demo.application.annotation.SignAuthExclude)" +
            " com.warthur.nacos.demo.interfaces.facade.*) ")
    public void excludeClass() {}

    @Around("signAuth() && excludeClass() && excludeMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        // 处理时间戳和签名的逻辑
        HttpServletRequest request = getRequest();
        String signature = request.getParameter("signature");
        String timestamp = request.getHeader("timestamp");
        String nonce = request.getHeader("nonce");

        String nonceCache = stringRedisCache.get("nonce:" + nonce);

        // 校验随机字符串
        if (StringUtils.isEmpty(nonce) || nonce.length() != 32 || StringUtils.isNotEmpty(nonceCache)) {
            throw new RuntimeException("随机字符串非法");
        }

        // timestamp合法性校验 误差300s
        if (StringUtils.isEmpty(timestamp) || !validTimestamp(timestamp)) {
            throw new RuntimeException("时间戳非法");
        }

        String md5Signature = DigestUtils.md5Hex(timestamp + nonce);

        if (StringUtils.isEmpty(signature) || !StringUtils.equals(signature, md5Signature)) {
            throw new RuntimeException("签名非法");
        }

        Object result;
        try {
            stringRedisCache.set("nonce:" + nonce, nonce);
            result =  joinPoint.proceed();
        } finally {
            stringRedisCache.delete("nonce:" + nonce);
        }

        return result;
    }
}

