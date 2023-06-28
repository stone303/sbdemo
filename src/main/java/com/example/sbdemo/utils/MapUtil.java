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
 */

@Slf4j
public class MapUtil {

    public static MultiValueMap<String, String> jsonStr2MultiMap( String str)
    {
        log.info("----"+str);
        //string 转化为json对象
        JSONObject jsonobject = JSONObject.parseObject(str);

        // convert JSONObject to Map
        Map<String, String> map = new HashMap<>();
        for (String key : jsonobject.keySet()) {
            map.put(key, jsonobject.getString(key));
        }

        /*** convert Map to MultiValueMap
         * MultiValueMap 同一个key可以对应多个值
         * Map 同一个key可以对应1个值
         *Map.Entry<String, String>：这是一个泛型类型声明，指定了 Map 集合中键值对的类型。
         * 在这个例子中，键的类型是 String，值的类型也是 String。因此，Map.Entry<String, String> 表示一个键值对的类型。
         * entry：这是一个临时变量，用于在每次迭代中保存当前的键值对。
         * map.entrySet()：这是 Map 接口中的一个方法，它返回一个包含键值对的 Set 集合。
         * 每个键值对都表示为一个 Map.Entry 对象。通过调用 entrySet() 方法，我们可以获得一个包含所有键值对的集合。
         * :：这是增强的 for 循环的语法，它用于遍历集合中的元素。
         * map.entrySet() 后面的部分是要遍历的集合对象。
         * 因此，整个语句的意思是，对于 map 集合中的每个键值对，将当前键值对赋值给 entry，然后执行循环体中的代码。
         * 在循环体中，你可以使用 entry.getKey() 获取当前键值对的键，使用 entry.getValue() 获取当前键值对的值
        */
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            multiValueMap.add(entry.getKey(), entry.getValue());
        }
        return multiValueMap;
    }

    /**
     * 将MultiValueMap转成String类型
     * @param params MultiValueMap参数
     * @return 转换后的字符串
     ***/
    public static String multiValueMapToString(MultiValueMap<String, String> multiValueMap) {
        if (multiValueMap == null || multiValueMap.isEmpty()) {
            return "";
        }

        /***
         * multiValueMap.entrySet().stream()：这是将 MultiValueMap 对象转换为一个流（Stream）的操作。entrySet() 方法返回一个包含键值对的 Set 集合，
         * 然后我们通过调用 stream() 方法将其转换为一个流。
         * .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))：这是对流中的每个元素进行映射操作的部分。
         * 对于流中的每个键值对，我们使用 map() 方法将其转换为一个字符串，格式为 "key=value"。entry.getKey() 返回键，entry.getValue() 返回值的集合，
         * 我们使用 String.join(",", entry.getValue()) 将值的集合转换为一个以逗号分隔的字符串。
         * .collect(Collectors.joining("&"))：这是将流中的元素收集并连接起来的操作。
         * 我们使用 Collectors.joining("&") 将每个键值对字符串连接起来，以 "&" 符号作为连接符，形成最终的查询字符串。
         * 因此，整个代码的作用是将 MultiValueMap 中的键值对转换为一个查询字符串，其中键和值之间用 "=" 符号连接，不同的键值对之间用 "&" 符号连接。
         */
        String queryString = multiValueMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + String.join(",", entry.getValue()))
                .collect(Collectors.joining("&"));

        return  "?"+queryString;
    }

    /**
     * 将MultiValueMap转为普通的Map（单值）
     * @param multiValueMap MultiValueMap参数
     * @return Map类型
     */
    public static  Map<String, String>  multiMap2Map( MultiValueMap<String, String> multiValueMap)
    {
        Map<String,String> map = multiValueMap.toSingleValueMap();
        return map;
    }

    /**
     * 将MultiValueMap转为普通的Map（单值）
     * @param multiValueMap MultiValueMap参数
     * @return Map类型
     *
     * public static <K, V> Map<K, V> multiValueMap2Map(MultiValueMap<K, V> multiValueMap)：
     * 这是一个公共静态方法，它接受一个 MultiValueMap 对象作为参数，并返回一个普通的 Map 对象。方法使用了泛型，<K, V> 表示键和值的类型，可以根据实际情况进行替换。
     * multiValueMap.entrySet().stream()：这是将 MultiValueMap 对象转换为一个流（Stream）的操作。
     * entrySet() 方法返回一个包含键值对的 Set 集合，然后我们通过调用 stream() 方法将其转换为一个流。
     * .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(0)))：
     * 这是将流中的元素收集并转换为一个普通的 Map 对象的操作。我们使用 Collectors.toMap()
     * 方法将流中的每个键值对转换为一个 Map.Entry 对象，并将其收集到一个新的 Map 对象中。Map.Entry::getKey 表示将每个键值对的键作为新 Map 的键，
     * e -> e.getValue().get(0) 表示将每个键值对的值集合中的第一个值作为新 Map 的值。
     */
    public static <K, V> Map<K, V> multiValueMap2Map(MultiValueMap<K, V> multiValueMap) {
        return multiValueMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(0)));
    }

}
