package com.warthur.nacos.demo.infrastructure.po;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * @author warthur
 * @date 2020/12/28
 */
public class BaseEntity {

    public <S, D> D dto(Class<D> clazz) {
        return dto(clazz, null);
    }

    public <S, D> D dto(Class<D> clazz, PropertyMap<S, D> map) {

        ModelMapper mapper = new ModelMapper();
        if (map != null) {
            mapper.addMappings(map);
            mapper.validate();
        }

        return mapper.map(this, clazz);
    }
}
