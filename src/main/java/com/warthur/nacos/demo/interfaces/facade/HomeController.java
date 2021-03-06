package com.warthur.nacos.demo.interfaces.facade;

import com.warthur.nacos.demo.infrastructure.config.annotation.SignAuthExclude;
import com.warthur.nacos.dubbo.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warthur
 * @date 2020/12/03
 */
@RestController
@RefreshScope
public class HomeController {

    @Value("${name:无}")
    private String name;

    @Value("${age:0}")
    private Integer age;

    @DubboReference(timeout = 3)
    private TestService testService;

    @GetMapping("/")
    public String home() {

        return "home";
    }

    @GetMapping("/name")
    public String name() {
        return name;
    }

    @GetMapping("/age")
    @SignAuthExclude
    public Integer age() {
        return age;
    }

    @GetMapping("/dubbo/config")
    @SignAuthExclude
    public String dubboConfig() {

        return testService.getConfig();
    }
}
