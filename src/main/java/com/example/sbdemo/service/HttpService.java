package com.example.sbdemo.service;

import com.example.sbdemo.utils.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class HttpService {


    @Autowired
    private RestTemplate restTemplate;
    //RestTemplate restTemplate = new RestTemplate(new SSL());

    /*** 实现restTemplate的post请求* */
    public ResponseEntity post(String serverUrl, MultiValueMap<String, String> requestMap)
    {
        RestTemplateUtil.trustAllHosts();
        HttpHeaders headers = new HttpHeaders( ) ;
        headers.add( "Content-Type", "application/x-www-form-urlencoded");

        //将设置的header、body参数加入http请求
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestMap, headers);

        // post请求: ResponseEntity标识整个http相应: 状态码、头部信息以及相应体内容
        ResponseEntity<String> responseEntity= restTemplate.postForEntity(serverUrl, entity, String.class);

        // Log输出请求body & 返回 body
        log.info("\n\n" +"requestBody"+ requestMap.toSingleValueMap());
        log.info("requestBody"+responseEntity.getBody()+"\n\n");

        return responseEntity;
    }

    public ResponseEntity get(String serverUrl, String requestParam)
    {
        log.info("---requestParam---"+requestParam);
        log.info("---serverUrl---"+serverUrl);
        RestTemplateUtil.trustAllHosts();
        String requestUrl = serverUrl + requestParam;
        log.info(requestUrl);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestUrl, String.class);

        log.info("\n\n" +"--requestUrl-- "+ requestUrl);
        log.info("--requestUrl-- "+ responseEntity.getBody() + "\n\n");

        return responseEntity;

    }


}
