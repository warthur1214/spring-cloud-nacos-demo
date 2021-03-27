package com.warthur.nacos.demo.interfaces.facade;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.warthur.nacos.demo.application.service.UserService;
import com.warthur.nacos.demo.domain.model.aggregates.UserRichInfo;
import com.warthur.nacos.demo.domain.repository.IUserRepository;
import com.warthur.nacos.demo.infrastructure.po.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping("/users")
    @SaCheckRole(value = {"admin", "user"}, mode = SaMode.OR)
    @SaCheckPermission("user:list")
    public List<String> getUsers() {

        return Arrays.asList("user1", "user2");
    }

    @GetMapping("/user/{userId}")
    @SaCheckRole(value = {"admin", "user"}, mode = SaMode.OR)
    @SaCheckPermission("user:find")
    public UserRichInfo getUser(@PathVariable long userId) {

        return userService.getUserById(userId);
    }

    @PostMapping("/users")
    @SaCheckRole(value = {"admin", "user"}, mode = SaMode.OR)
    @SaCheckPermission("user:add")
    public IPage<UserEntity> addUserInfo() {

        return iUserRepository.getUserByPage();
    }

    @PutMapping("/users/{userId}")
    @SaCheckRole(value = {"admin", "user"}, mode = SaMode.OR)
    @SaCheckPermission("user:edit")
    public String editUser(@PathVariable String userId) {

        return "编辑用户";
    }

    @DeleteMapping("/users/{userId}")
    @SaCheckRole(value = {"admin", "user"}, mode = SaMode.OR)
    @SaCheckPermission("user:delete")
    public String deleteUser(@PathVariable String userId) {

        System.out.println(StpUtil.getLoginId());

        return "删除用户";
    }
}
