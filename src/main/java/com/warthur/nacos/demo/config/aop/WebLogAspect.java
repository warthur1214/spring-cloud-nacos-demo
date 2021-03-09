package com.warthur.nacos.demo.config.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author warthur
 */
@Component
@Aspect
@Slf4j
@Order(100)
public class WebLogAspect {

    @Autowired
    private ObjectMapper objectMapper;

	@Pointcut("execution(@(org.springframework.web.bind.annotation.RequestMapping || " +
            "org.springframework.web.bind.annotation.GetMapping || " +
            "org.springframework.web.bind.annotation.PostMapping || " +
            "org.springframework.web.bind.annotation.PutMapping || " +
            "org.springframework.web.bind.annotation.DeleteMapping) " +
            "public * com.warthur.nacos.*..*(..))")
	public void webLog() {}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		// 接收到请求，记录请求内容
		HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

		// 记录下请求内容
		log.info("{} -> {}", request.getMethod(), request.getRequestURL());
		if (StringUtils.isNotEmpty(request.getQueryString())) {
            log.info("请求参数: {}", request.getQueryString());
        }

		Object arg = Arrays.stream(joinPoint.getArgs())
                .findFirst()
                .orElse(null);
		if (arg == null) {
            arg = Arrays.stream(joinPoint.getArgs())
                    .filter(obj -> obj instanceof String)
                    .findFirst()
                    .orElse(null);
        }
		if (!"GET".equals(request.getMethod()) && arg != null) {
            try {
                String body;
                if (arg instanceof String) {
                    body = String.valueOf(arg);
                } else {
                    body = objectMapper.writeValueAsString(arg);
                }
                int length = Math.min(body.length(), 5120);
                log.info("请求体: {}", body.substring(0, length));
            } catch (JsonProcessingException e) {
                log.error("Json转换失败", e);
            }
        }
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfter(Object ret) throws JsonProcessingException {
		// 处理完请求，返回内容
        String body = objectMapper.writeValueAsString(ret);
        int length = Math.min(body.length(), 5120);
        log.info("RESPONSE : {}", objectMapper.writeValueAsString(ret));
	}
}
