package com.hason.rest.annotation;

import java.lang.annotation.*;

/**
 * GET 方法
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Get {

    /**
     * URL
     *
     * @return
     */
    String value() default "";

}
