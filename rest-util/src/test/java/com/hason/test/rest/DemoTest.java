package com.hason.test.rest;

import com.hason.rest.bean.HttpResult;
import com.hason.rest.client.RequestDemo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试 {@link RequestDemo}
 *
 * 先运行 rest-server，在运行测试用例
 *
 * @author Huanghs
 * @since 2.0
 * @date 2018/5/3
 */
public class DemoTest extends BaseTest {

    @Autowired
    private RequestDemo demo;

    @Test
    public void test() {
        HttpResult result = demo.get1();
        System.out.println(result);
    }

    @Test
    public void testGet2() {
        HttpResult result = demo.getById("100");
        System.out.println(result);
    }

}
