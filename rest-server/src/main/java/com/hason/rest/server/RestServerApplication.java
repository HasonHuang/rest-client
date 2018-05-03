package com.hason.rest.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 程序入口，提供 Rest 接口作演示
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
@SpringBootApplication
public class RestServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServerApplication.class, args);
    }

}
