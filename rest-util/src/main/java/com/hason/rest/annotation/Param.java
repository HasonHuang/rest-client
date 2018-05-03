package com.hason.rest.annotation;

import java.lang.annotation.*;

/**
 * 请求参数
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {

    /**
     * 参数名称
     *
     * @return
     */
    String value() default "";

}
