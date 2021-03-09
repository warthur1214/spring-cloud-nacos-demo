package com.warthur.nacos.demo.domain.model.aggregates;

import com.warthur.nacos.demo.domain.model.vo.UserInfo;
import com.warthur.nacos.demo.domain.model.vo.UserSchool;
import lombok.Data;

import java.util.List;

/**
 * @author warthur
 * @date 2020/12/25
 */
@Data
public class UserRichInfo {

    private UserInfo user;
    private List<UserSchool> schools;
}
