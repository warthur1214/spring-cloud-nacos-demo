package com.warthur.nacos.demo.infrastructure.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.warthur.nacos.demo.domain.repository.IUserRepository;
import com.warthur.nacos.demo.infrastructure.dao.UserDAO;
import com.warthur.nacos.demo.infrastructure.po.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author warthur
 * @date 2020/12/25
 */
@Service
public class UserRepository implements IUserRepository {

    @Autowired
    private UserDAO userDAO;

    @Override
    public IPage<UserEntity> getUserByPage() {
        Page<UserEntity> page = new Page<>(1, 10);
        return userDAO.selectUsers(page);
    }

    @Override
    public void save(UserEntity userEntity) {


        userDAO.insert(userEntity);
    }

    @Override
    public UserEntity get(long userId) {

        return userDAO.selectById(userId);
    }
}
