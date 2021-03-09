package com.warthur.nacos.demo.domain.service;

import com.warthur.nacos.demo.application.service.UserService;
import com.warthur.nacos.demo.domain.model.aggregates.UserRichInfo;
import com.warthur.nacos.demo.domain.model.vo.UserInfo;
import com.warthur.nacos.demo.domain.model.vo.UserSchool;
import com.warthur.nacos.demo.domain.repository.IUserRepository;
import com.warthur.nacos.demo.infrastructure.po.UserEntity;
import com.warthur.nacos.demo.infrastructure.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author warthur
 * @date 2020/12/25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public UserRichInfo getUserById(long userId) {

        UserEntity userEntity = userRepository.get(userId);

        UserInfo userInfo = userEntity.dto(UserInfo.class, UserInfo.getPropertyMap());

        UserRichInfo richInfo = new UserRichInfo();
        richInfo.setUser(userInfo);

        // 模拟从第三方平台拉取学校信息
        List<UserSchool> schools = schoolRepository.get(userEntity.getUserName()).stream()
                .map(s -> new UserSchool().setSchoolName(s.getSchoolName()))
                .collect(Collectors.toList());
        richInfo.setSchools(schools);

        return richInfo;
    }
}
