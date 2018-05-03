package com.hason.rest.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 展示两个 Rest 接口，被 RequestDemo 调用
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
@RestController
public class DemoController {

    @GetMapping("/get1")
    public HttpResult<?> get1() {
        return new HttpResult<>();
    }

    @GetMapping("/get2")
    public HttpResult<?> get2(@RequestParam Long id) {
        HttpResult<String> result = new HttpResult<>();
        result.setData("Your id is " + id);
        return result;
    }

}
