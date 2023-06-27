package com.example.sbdemo.config;

import com.example.sbdemo.service.McdcService;
import com.example.sbdemo.utils.SSL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateConfigTest {
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void restTemplate_httpGet()
    {
        String url = "http://localhost:8080/hello";
        // string.class 表示请求的返回值是string类型
        String response = restTemplate.getForObject(url, String.class);
        // 控制台打印返回值
        System.out.println(response);
        //校验返回值是否为 Hello World
        assertTrue(response.equals("Hello World"));
    }

    @Test
    public void restTemplate_httpPost() {
        //测试url
        String url = "http://localhost:8080/hello";
        // String.class 表示请求的返回值是String类型，/hello api没有请求参数，所以请求的参数为null
        String response = restTemplate.postForObject(url, null, String.class);
        // 控制台打印返回值
        System.out.println(response) ;
        // 校验返回值是否为 Hello World
        assertTrue(response.equals("Hello World"));
    }

    @Test
    public void restTemplate_httpsGet() {
        // 感谢binstd.com提供的免费API服务，下面是固话区号查询的API，参数放在url中
        String url = "https://api.binstd.com/areacode/query?appkey=3905d343fe72156d&areacode=0571";
        // get请求: ResponseEntity标识整个http相应: 状态码、头部信息以及相应体内容。
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        //输出返回内容中的body
        System.out.println(responseEntity.getBody());// 输出整个返回内容
        System.out.println(responseEntity);
    }
    @Test
    public void restTemplate_httpsPost()
    {
        //感谢binstd.com提供的免费API服务下面是固话区号查询的API，参数放在body 中
        String url ="https://api.binstd.com/areacode/query";
        // 设置 http header 参数
         HttpHeaders headers = new HttpHeaders( );
         headers.add( "Content-Type", "application/x-www-form-urlencoded");
        // 设置 http 请求 body 参数，只能用 MutivalueMap，HashMap会报错
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add(  "appkey",  "3905d343fe72156d");
        requestMap.add( "areacode" , "21");
        // 将设置的header、body参数加入http请求
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestMap, headers);
        // post请求: ResponseEntity标识整个http相应: 状态码、头部信息以及相应体内容
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        System.out.println( responseEntity.getBody());
    }

    @Test
    public void restTemplate_httpnosGet() {
        // 创建自定义的 RestTemplate
        RestTemplate restTemplate = new RestTemplate(new SSL());

        // 感谢binstd.com提供的免费API服务，下面是固话区号查询的API，参数放在url中
        String url = "https://api.binstd.com/areacode/query?appkey=3905d343fe72156d&areacode=0571";
        // get请求: ResponseEntity标识整个http相应: 状态码、头部信息以及相应体内容。
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        //输出返回内容中的body
        System.out.println(responseEntity.getBody());// 输出整个返回内容
        System.out.println(responseEntity);
    }

    @Test
    public void restTemplate_httpnosPost()
    {
        RestTemplate restTemplate = new RestTemplate(new SSL());

        //感谢binstd.com提供的免费API服务下面是固话区号查询的API，参数放在body 中
        String url ="https://api.binstd.com/areacode/query";
        // 设置 http header 参数
        HttpHeaders headers = new HttpHeaders( );
        headers.add( "Content-Type", "application/x-www-form-urlencoded");
        // 设置 http 请求 body 参数，只能用 MutivalueMap，HashMap会报错
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add(  "appkey",  "3905d343fe72156d");
        requestMap.add( "areacode" , "21");
        // 将设置的header、body参数加入http请求
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestMap, headers);
        // post请求: ResponseEntity标识整个http相应: 状态码、头部信息以及相应体内容
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        System.out.println( responseEntity.getBody());

    }

    @Test
    public void restTemplate_httpnosPost_MCDC()
    {
        RestTemplate restTemplate = new RestTemplate(new SSL());

        //感谢binstd.com提供的免费API服务下面是固话区号查询的API，参数放在body 中
        String url ="https://api.binstd.com/areacode/query";
        // 设置 http header 参数
        HttpHeaders headers = new HttpHeaders( );
        headers.add( "Content-Type", "application/x-www-form-urlencoded");
        // 设置 http 请求 body 参数，只能用 MutivalueMap，HashMap会报错
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add(  "appkey",  "3905d343fe72156d");
        requestMap.add( "areacode" , "0516");

        List<MultiValueMap<String, String>> requestBodyList = McdcService.getMcdcParams (requestMap);

        for (MultiValueMap requestBody : requestBodyList) {
            // 将设置的header、body参数加入http请求
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);
            // post请求: ResponseEntity标识整个http相应: 状态码、头部信息以及相应体内容
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
           // System.out.println(responseEntity.getBody());
            System.out.println("\n\n" + "-----requestBody----"+ requestBody.toSingleValueMap());
            System.out.println("-----requestBody----"+ responseEntity.getBody() + " \n\n");
        }
    }

}