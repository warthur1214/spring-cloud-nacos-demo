package com.warthur.nacos.demo.domain.model.vo;

import com.warthur.nacos.demo.infrastructure.po.UserEntity;
import lombok.Data;
import org.modelmapper.PropertyMap;

/**
 * @author warthur
 * @date 2020/12/25
 */
@Data
public class UserInfo {

    private String userName;
    private String uniqueId;

    public static PropertyMap<UserEntity, UserInfo> getPropertyMap() {
        return new PropertyMap<UserEntity, UserInfo>() {
            @Override
            protected void configure() {
                map(source.getUserName(), destination.getUserName());
                map(source.getOpenId(), destination.getUniqueId());
            }
        };
    }
}
