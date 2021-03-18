package com.warthur.nacos.demo.infrastructure.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.warthur.nacos.demo.infrastructure.po.CronTriggerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author warthur
 * @date 2021/03/18
 */
@Mapper
public interface CronTriggerDAO extends BaseMapper<CronTriggerEntity> {
}
