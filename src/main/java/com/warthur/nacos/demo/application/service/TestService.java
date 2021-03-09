package com.warthur.nacos.demo.application.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author warthur
 * @date 2020/12/03
 */
public interface TestService {

    String getConfig();
}
