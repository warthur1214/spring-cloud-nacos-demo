package com.warthur.nacos.demo.domain.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author warthur
 * @date 2020/12/25
 */
@Data
@Accessors(chain = true)
public class UserSchool {

    private String schoolName;
}
