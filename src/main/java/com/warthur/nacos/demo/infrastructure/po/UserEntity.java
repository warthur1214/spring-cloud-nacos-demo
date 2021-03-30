package com.warthur.nacos.demo.infrastructure.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author warthur
 * @date 2020/12/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserEntity extends BaseEntity {

    private Long userId;
    private String userName;
    private String password;
    private String roles;
    private String openId;
}
