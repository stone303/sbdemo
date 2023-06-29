package com.example.sbdemo.controller;

import com.example.sbdemo.model.McdcResponse;
import com.example.sbdemo.model.TestResponse;
import com.example.sbdemo.service.HttpService;
import com.example.sbdemo.utils.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author guocang.shi
 */
@Slf4j
@RestController
public class TestPostController {

    @Autowired
    HttpService httpService;

    @RequestMapping(value = "/areacode/query" ,method = RequestMethod.POST)
    public TestResponse mcdcResponse(
            @RequestParam(value = "url", required = true) String url,
            @RequestParam(value = "initialParam",required = true) String initialParam){

        ResponseEntity responseEntity;
        TestResponse testResponse = new TestResponse();

        //入参请求
        responseEntity=httpService.post(url, MapUtil.jsonStr2MultiMap(initialParam));

        //响应内容定义
        testResponse.setCode("0");
        testResponse.setMsg("success");
        testResponse.setData(responseEntity.getBody().toString());
        return testResponse;
    }
}
