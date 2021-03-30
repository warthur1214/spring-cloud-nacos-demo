package com.warthur.nacos.demo.domain.repository;

import com.github.pagehelper.PageInfo;
import com.warthur.nacos.demo.infrastructure.po.UserEntity;

/**
 * @author warthur
 * @date 2020/12/25
 */
public interface IUserRepository {

    PageInfo<UserEntity> getUserByPage();

    void save(UserEntity userEntity);

    UserEntity get(long userId);
}
