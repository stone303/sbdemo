package com.example.sbdemo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guocang.shi
 *
 *  总结起来，Java类的底层执行过程包括类加载（JVM）通过类加载器加载类的字节码文件（.class文件）、
 *  链接阶段（验证、准备和解析）、初始化阶段、对象创建、方法调用和垃圾回收。这一系列步骤确保了Java类的正确加载、处理和执行。
 *
 *  问题 ：不能解决单独运行的测试的情况，如运行 test1 再单独运行test2，此时获取池中信息就为null了
 *
 *  解决方案：
 *  配置文件：将需要共享的数据存储在配置文件中，单独运行的时候读取配置文件获取数据。
 *  可以使用属性文件（.properties）或其他格式的配置文件（如.json或.yaml）来存储数据。
 *  数据库：将需要共享的数据存储在数据库中，在每个单独运行的实例中通过数据库连接获取数据。
 *  可以使用关系型数据库（如MySQL、PostgreSQL）或者NoSQL数据库（如MongoDB、Redis）来存储数据。
 *
 */
public class DataPool {
    private static Map<String, String> dataPool = new HashMap<>();

    // 添加数据到数据池
    public static synchronized void addData(String key, String value) {
        dataPool.put(key, value);
    }

    // 从数据池获取数据
    public static synchronized String getData(String key) {
        return dataPool.get(key);
    }

    // 更新数据池中的数据
    public static synchronized void updateData(String key, String value) {
        // 判断数据池中是否存在该key，如果存在则更新，如果不存在则添加
        if (dataPool.containsKey(key)) {
            dataPool.put(key, value);
        } else {
            dataPool.put(key, value);
        }
    }

    /* 销毁数据池*/
    public static synchronized void destroyDataPool() {
        dataPool.clear();
    }

}
