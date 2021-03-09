package com.warthur.nacos.demo.domain.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * @author warthur
 * @date 2020/12/28
 */
public final class ModelMapperUtils {

    public static <S, D> D dto(S source, Class<D> clazz) {
        return dto(source, clazz, null);
    }

    public static <S, D> D dto(S source, Class<D> clazz, PropertyMap<S, D> map) {

        ModelMapper mapper = new ModelMapper();
        if (map != null) {
            mapper.addMappings(map);
            mapper.validate();
        }

        return mapper.map(source, clazz);
    }
}
