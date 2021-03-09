package com.warthur.nacos.demo.infrastructure.repository;

import com.warthur.nacos.demo.infrastructure.po.SchoolEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author warthur
 * @date 2020/12/25
 */
@Service
public class SchoolRepository {

    public List<SchoolEntity> get(String userName) {

        return new ArrayList<>();
    }
}
