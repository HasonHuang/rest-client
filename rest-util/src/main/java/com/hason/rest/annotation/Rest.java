package com.hason.rest.annotation;

import java.lang.annotation.*;

/**
 * 表示一个 Rest 接口
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Rest {

    /**
     * 服务器信息
     *
     * @return
     */
    String value() default "";

}
