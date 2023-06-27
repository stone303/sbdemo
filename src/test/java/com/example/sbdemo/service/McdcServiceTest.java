package com.example.sbdemo.service;

import com.alibaba.fastjson.TypeReference;
import com.example.sbdemo.utils.MapUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class McdcServiceTest {
    @Autowired
    McdcService mcdcService;
    @Test
    public void getMcdcParams() {

        String initialParam = "{\"appkey\":\"3905d343fe72156d\",\"areacode\":\"0516\"}";

        MultiValueMap<String, String> initialMultiMap = MapUtil.jsonStr2MultiMap(initialParam);
        ///List<MultiValueMap<String, String>> mcdcList = mcdcService.getMcdcParams(initialMultiMap);

    }

    @Test
    public void test() {

    }
}