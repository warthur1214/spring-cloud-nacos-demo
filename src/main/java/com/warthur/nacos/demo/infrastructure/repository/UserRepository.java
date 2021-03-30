package com.warthur.nacos.demo.infrastructure.repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<UserEntity> getUserByPage() {
        PageHelper.startPage(1, 10);
        return new PageInfo<>(userDAO.selectUsers());
    }

    @Override
    public void save(UserEntity userEntity) {


    }

    @Override
    public UserEntity get(long userId) {

        return userDAO.selectById(userId);
    }
}
