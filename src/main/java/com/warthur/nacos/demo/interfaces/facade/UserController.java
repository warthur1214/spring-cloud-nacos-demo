package com.warthur.nacos.demo.interfaces.facade;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.warthur.nacos.demo.application.service.UserService;
import com.warthur.nacos.demo.domain.model.aggregates.UserRichInfo;
import com.warthur.nacos.demo.domain.repository.IUserRepository;
import com.warthur.nacos.demo.infrastructure.po.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warthur
 * @date 2020/12/25
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private IUserRepository iUserRepository;

    @GetMapping("/user")
    public IPage<UserEntity> addUserInfo() {

        return iUserRepository.getUserByPage();
    }

    @GetMapping("/user/{id}")
    public UserRichInfo getUser(@PathVariable long id) {

        return userService.getUserById(id);
    }
}
