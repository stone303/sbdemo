package com.example.sbdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author guocang.shi
 *
 *
 *传统情况下在java代码里访问restful服务，一般使用Apache的HttpClient，不过此种方法使用起来太过繁琐。
 * Spring这么强大的框架当然会提供一种简单便捷的模板类来进行操作，这就是RestTemplate。
 *
 *RestTemplate方法的名称遵循命名约定，第一部分指出正在调用什么HTTP方法，第二部分指示返回的内容。
 * 本节会介绍restTemplate.getForEntity/postForEntit、getForObject/postForObject方法，
 * get/post指调用了HTTP的get/post方法，Entity指将HTTP响应转换为您选择的对象类型。
 * 还有其他很多类似的方法，有兴趣的同学可以参考官方api。
 *这里 @Configuration作用在类上就是表示这个类是用来配置工程需要的工具的
 * @Bean就是具体配置进来的工具，在项目启动时会被自动初始化并加载到容器中可供使用
 *
 */

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate ;
    }

}
