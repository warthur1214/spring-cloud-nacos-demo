package com.warthur.nacos.demo.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author warthur
 * @date 2020/12/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "t_user", schema = "test")
public class UserEntity extends BaseEntity {

    @TableId
    private Long userId;
    private String userName;
    private String password;
    private String roles;
    private String openId;
}
