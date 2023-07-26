package com.example.sbdemo.controller;

import com.example.sbdemo.model.MyResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guocang.shi
 *
 *Spring系列的项目里注解的功能非常强大，也是Srping框架的精髓
 *
 * //@RestController 是一个用于定义SpringBoot 控制器的注解。标记在类上，表示该类处理http请求，并且返回json数据。
 *
 * //@RequestMapping 是一个用来处理请求地址映射的注解，可用于类或者方法。用于类，表示类中所有响应请求的方法都是以该地址作为父路径的，
 * 咱们这里用于在Hello()方法上。注解有 value、method等属性，value属性可以默认不写。“/hello”就是 value性的值。 value属性的值就是请求的实
 *
 */
@RestController
public class HelloWorld {
    @RequestMapping(value = "/hello")
    public MyResponse hello() {
        return MyResponse.ok();
    }
}
