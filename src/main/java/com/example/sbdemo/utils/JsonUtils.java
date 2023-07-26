package com.example.sbdemo.utils;
import com.alibaba.fastjson.JSON;
import java.util.Map;

/**
 * @author guocang.shi
 */
public class JsonUtils {

    public static String toJson(Map<String, Object> map) {
        return JSON.toJSONString(map);
    }
}
