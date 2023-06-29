package com.example.sbdemo.utils;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author guocang.shi
 *
 *
 */

@Slf4j
public class MapUtil {


    public static MultiValueMap<String, String> jsonStr2MultiMap( String str)
    {

        log.info("----"+str);
        JSONObject jsonobject = JSONObject.parseObject(str);

        // convert JSONObject to Map
        Map<String, String> map = new HashMap<>();
        for (String key : jsonobject.keySet()) {
            map.put(key, jsonobject.getString(key));
        }

        // convert Map to MultiValueMap
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            multiValueMap.add(entry.getKey(), entry.getValue());
        }

        return multiValueMap;
    }

    /**
     * 将MultiValueMap转成String类型
     *
     * @param params MultiValueMap参数
     * @return 转换后的字符串
     ***/
    public static String multiValueMapToString(MultiValueMap<String, String> multiValueMap) {
        if (multiValueMap == null || multiValueMap.isEmpty()) {
            return "";
        }


        String queryString = multiValueMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))
                .collect(Collectors.joining("&"));

        return  "?"+queryString;
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
