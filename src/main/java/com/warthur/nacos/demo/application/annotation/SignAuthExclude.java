package com.warthur.nacos.demo.application.annotation;

import java.lang.annotation.*;

/**
 * @author warthur
 * @date 2018/11/06
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SignAuthExclude {
}
