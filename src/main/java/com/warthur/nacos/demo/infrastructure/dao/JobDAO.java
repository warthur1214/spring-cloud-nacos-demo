package com.warthur.nacos.demo.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.warthur.nacos.demo.infrastructure.po.JobEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author warthur
 * @date 2021/03/18
 */
@Mapper
public interface JobDAO extends BaseMapper<JobEntity> {

    @Select("SELECT * FROM quartz.QRTZ_JOB_DETAILS LIMIT 1")
    JobEntity findOne();
}
