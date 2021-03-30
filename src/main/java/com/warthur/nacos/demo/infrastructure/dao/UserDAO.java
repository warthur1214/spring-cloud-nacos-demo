package com.warthur.nacos.demo.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.warthur.nacos.demo.infrastructure.po.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author warthur
 * @date 2020/12/25
 */
@Mapper
public interface UserDAO extends BaseMapper<UserEntity> {

    @Select("SELECT user_id, user_name, password, roles, open_id FROM t_user WHERE user_id = #{userId}")
    UserEntity selectById(long userId);

    @Select("SELECT * FROM t_user WHERE user_name = #{userName}")
    UserEntity selectByUserName(String userName);

    @Select("SELECT * FROM t_user WHERE user_id IS NOT NULL")
    List<UserEntity> selectUsers();
}
