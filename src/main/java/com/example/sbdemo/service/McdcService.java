package com.example.sbdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author guocang.shi
 */
@Slf4j
@Component
public class McdcService {

    public static List<MultiValueMap<String, String>> getMcdcParams(MultiValueMap<String, String> sourceMap) {

// MCDCList 为拼接后的list
        List<MultiValueMap<String, String>> MCDCList= new ArrayList<>();

// 首先将正常的请求body放入MCDCList
        MCDCList.add(sourceMap);

        /*  嵌套循环：
         *      第一层循环（根据key遍历sourceMap，生成对应key的异常value）：
         *          获取当前key值，命名为 sourceKey
         *          声明异常value，命名为 nullValue
         *          声明 MultiValueMap类型的变量用来拼接异常请求体，命名为 destMapNull
         *
         *          第二层循环（再次根据key遍历sourceMap，并将key-value重新组装成map）：
         *              获取当前key值，命名为 destKey
         *              比对destKey与sourceKey
         *              如果destKey == sourceKey
         *                  将destKey对应的value置为异常值nullValue，并将 destKey->nullValue 放入destMapNull
         *              其余的
         *                  destKey 对应的 value 为原来正确的 value，也将 destKey->value 放入destMapNull
         *
         *          第二层循环结束会获得destMapNull，并且拥有与sourceMap一样的key，但是其中有且只有一个key对应的value是异常的nullValue
         *          将destMapNull放入MCDCList
         *
         *      第一层循环结束后，会获得一个完整的符合MCDC规则的请求体列表 MCDCList
         *
         */
        for(Iterator<String> iterator = sourceMap.keySet().iterator(); iterator.hasNext();) {

            String sourceKey = iterator.next();

            String nullValue = "";

            MultiValueMap<String, String> nullDestMap= new LinkedMultiValueMap<>();

            for(Iterator<String> iterator1 = sourceMap.keySet().iterator(); iterator1.hasNext();) {

                String destKey = iterator1.next();

                if(destKey.equals(sourceKey)) {

                    nullDestMap.add(destKey, nullValue);
                } else{

                    nullDestMap.add(destKey, sourceMap.getFirst(destKey));
                }
            }

            MCDCList.add(nullDestMap);
        }
        return MCDCList;
    }
}
