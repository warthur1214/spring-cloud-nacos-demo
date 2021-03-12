package com.warthur.nacos.demo.interfaces.facade;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warthur
 * @date 2021/03/12
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public SaTokenInfo login(String userId) {

        StpUtil.setLoginId(userId);

        return StpUtil.getTokenInfo();
    }

    @GetMapping("/test")
    @SaCheckLogin
    public String test() {

        return "已登录校验";
    }
}
