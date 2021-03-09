package com.warthur.nacos.demo.application.service;

import com.warthur.nacos.demo.domain.model.aggregates.UserRichInfo;

/**
 * @author warthur
 * @date 2020/12/25
 */
public interface UserService {

    UserRichInfo getUserById(long userId);

}
