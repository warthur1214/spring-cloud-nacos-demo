package com.warthur.nacos.demo.config.filter;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author warthur
 * @date 2021/03/12
 */
@Data
@Accessors(chain = true)
public class SpringApp {

    private String appName;
}
