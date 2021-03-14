package com.warthur.nacos.demo.infrastructure.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author warthur
 * @date 2021/03/13
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus
    public String systemExceptionGet(Exception e) {
        log.error("系统异常", e);

        return e.getMessage();
    }
}
