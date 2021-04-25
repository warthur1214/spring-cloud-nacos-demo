package com.warthur.nacos.demo.infrastructure.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.Master;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 根据name查询user
     * @param userName name
     * @return user
     */
    List<UserEntity> selectByUserName(String userName);

    @Select("SELECT * FROM t_user WHERE user_id IS NOT NULL")
    IPage<UserEntity> selectUsers(Page<UserEntity> page);
}
