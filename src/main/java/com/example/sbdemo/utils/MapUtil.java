package com.example.sbdemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author guocang.shi
 *
 *
 */
public class MapUtil {

    public static MultiValueMap<String, String> json2MultiMap(String jsonStr)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MultiValueMap<String, String> multiMap = objectMapper.readValue(jsonStr, LinkedMultiValueMap.class);
            return multiMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return   null;
    }
    public static MultiValueMap<String, String> jsonStr2MultiMap(String jsonstr)
    {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        // 将JSON字符串解析为JSONObject
        JSONObject jsonObj = new JSONObject(Boolean.parseBoolean(jsonstr));

        // 使用Spring的MultiValueMap存放键值对
        for (String key : jsonObj.keySet()) {
            String value = jsonObj.getString(key);
            map.add(key, value);
        }

        return map;
    }


    public static String  map2UrlStr(Map<String, String> map)
    {
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }
    /**
     * 将MultiValueMap转成String类型
     *
     * @param params MultiValueMap参数
     * @return 转换后的字符串
     ***/
    public static String multiValueMapToString(MultiValueMap<String, String> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }

        Map<String, String> map = params.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                    String value = StringUtils.collectionToDelimitedString(entry.getValue(), ",");
                    return value != null ? value : "";
                }));

        return map.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }


    public static  Map<String, String>  multiMap2Map( MultiValueMap<String, String> multiValueMap)
    {
        Map<String, String> map = multiValueMap.toSingleValueMap();
        return map;
    }

    /**
     * 将MultiValueMap转为普通的Map（单值）
     *
     * @param multiValueMap MultiValueMap参数
     * @return Map类型
     */
    public static <K, V> Map<K, V> multiValueMap2Map(MultiValueMap<K, V> multiValueMap) {
        return multiValueMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(0)));
    }

}
