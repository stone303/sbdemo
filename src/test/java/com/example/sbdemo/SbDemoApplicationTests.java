package com.example.sbdemo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/***
 * @RunWith是Junit4提供的注解，将Spring和Junit链接了起来
 * @SpringBootTest 作用是Spring将加载所有被管理的bean，基本等同于启动了整个服务，此时便可以开始功能测试
 * @Test作用在方法上，表明这是一个测试方法
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SbDemoApplicationTests {

    @Test
    void contextLoads() {
    }

}
