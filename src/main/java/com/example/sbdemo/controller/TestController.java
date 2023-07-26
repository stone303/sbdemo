package com.example.sbdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.sbdemo.Entity.Test;
import com.example.sbdemo.model.McdcResponse;
import com.example.sbdemo.model.MyResponse;
import com.example.sbdemo.model.TestResponse;
import com.example.sbdemo.utils.HttpClientUtils;
import com.example.sbdemo.utils.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author guocang.shi
 */
@Slf4j
@RestController
public class TestController {

    @RequestMapping(value = "/test")
    @ResponseBody
    public MyResponse hello() {
        try {
            String value= HttpClientUtils.sendGetRequest("http://api.binstd.com/areacode/query?appkey=3905d343fe72156d&areacode=0571");
            log.info(value);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return MyResponse.ok();
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public TestResponse test(@RequestBody Test test) throws IOException {
        TestResponse testResponse = new TestResponse();

        String value= HttpClientUtils.sendPostRequest("http://api.binstd.com/areacode/query", MapUtil.object2Map(test));
       log.info(value);
        JSONObject jsonObject = JSON.parseObject(value);
        testResponse.setStatus(jsonObject.getInteger("status"));
        testResponse.setMsg(jsonObject.getString("msg"));
        /*** 将string 转化为list***/
        List<String> list = JSON.parseArray(jsonObject.getString("result"),String.class);
        testResponse.setResult(list);
        return testResponse;
    }

}
