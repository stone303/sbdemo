package com.example.sbdemo.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class HttpClientUtilsTest {

    HttpClientUtils httpClientUtils = new HttpClientUtils();
    @Test
    public void testGetSendGetRequest()
    {
        String URL="http://api.binstd.com/areacode/query?appkey=3905d343fe72156d&areacode=0571";
        try {
            String result= httpClientUtils.sendGetRequest(URL);
            JSONObject jsonObject =JSONObject.parseObject(result);

            log.info(result);
            Assert.assertEquals(jsonObject.get("status"),0);
            Assert.assertEquals(jsonObject.get("msg"),"ok");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSendGetRequest()
    {
        String URL="https://api.binstd.com/areacode/query?";
        Map<String,String> map=new HashMap<>();
        map.put("appkey","3905d343fe72156d");
        map.put("areacode","0571");
        try {
            JSONObject result= httpClientUtils.sendGetRequest(URL,MapUtil.mapToString(map));
            log.info("---参数---："+MapUtil.mapToString(map));
            Assert.assertEquals( result.get("status"),0);
            Assert.assertEquals( result.get("msg"),"ok");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}