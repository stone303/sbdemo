package com.example.sbdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.Iterator;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class McdcServiceTest {
    @Autowired
    McdcService mcdcService;
    @Test
    public void getMcdcParams() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        // 添加键值对
        map.add("key1", "value1");
        map.add("key1", "value2");
        map.add("key2", "value3");

        List<MultiValueMap<String, String>> list =mcdcService.getMcdcParams(map);
/***
 * 获取迭代器：要使用迭代器遍历集合，首先需要通过调用集合的iterator()方法来获取迭代器对象。
 * 例如：Iterator<T> iterator = collection.iterator();
 * 遍历元素：使用迭代器对象的hasNext()方法来检查集合中是否还有更多的元素，使用next()方法来获取下一个元素。例如：
 */
        Iterator<MultiValueMap<String, String>> iterator = list.iterator();
       while(iterator.hasNext())
       {
           System.out.println(iterator.next());
       }

    }
}