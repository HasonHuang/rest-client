package com.hason.rest.client;


import com.hason.rest.annotation.Get;
import com.hason.rest.annotation.Param;
import com.hason.rest.annotation.Rest;
import com.hason.rest.bean.HttpResult;

/**
 * 请求示例
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
@Rest("http://localhost:8000")
public interface RequestDemo {

    @Get
    HttpResult get1();

    @Get("/get2")
    HttpResult getById(@Param("id") String id);

}
