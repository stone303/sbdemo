package com.example.sbdemo.utils;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class MapUtilTest {

    @Test
    public void testJsonStr2MultiMap() {
        // JSON字符串
       // String json = "{\"appkey\":\"3905d343fe72156d\",\"areacode\":\"021\"}";
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("appkey","3905d343fe72156d");
        jsonObject.put("areacode","021");


        System.out.println(jsonObject);

        // convert JSONObject to Map
        Map<String, String> map = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            map.put(key, jsonObject.getString(key));
        }

        // convert Map to MultiValueMap
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            multiValueMap.add(entry.getKey(), entry.getValue());
        }

// output multivalue map key-values
        multiValueMap.forEach((key, values) -> {
            System.out.println("Key: " + key);
            System.out.println("Values:");
            values.forEach(System.out::println);
        });
    }


    @Test
    public void testMultiValueMapToString() {


        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("appkey", "3905d343fe72156d");
        map.add("areacode", "021");

        String queryString = map.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))
                .collect(Collectors.joining("&"));

        System.out.println(queryString);

    }

}