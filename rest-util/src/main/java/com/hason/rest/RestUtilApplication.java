package com.hason.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 程序入口，实现类似 RestFeign 框架的功能
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
@SpringBootApplication
public class RestUtilApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestUtilApplication.class, args);
    }

}
