package com.example.sbdemo.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author guocang.shi
 *
 * 解决 DataPool ,ResponsePool在单独运行的情况下，数据共享的池化思想可能无法解决的问题
 * 未解决问题： 多线程调用问题
 *
 */
public class ProDataPool {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH="D:\\workspace\\github\\sbdemo\\src\\main\\resources\\params.properties";

    /***
     * 获取参数值
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties(); //loadProperties()方法是一个私有方法，用于加载配置文件并将数据存储在Properties对象中
        }
        return properties.getProperty(key);
    }

    /***
     * 添加参数值
     * @param key
     * @param value
     */
    public static void addProperty(String key,String value) {
        if (properties == null) {
            loadProperties();
           }
        /*** 添加至对象中*/
        properties.setProperty(key,value);
        saveProperties();
    }

    /***
     * 清除 参数值
     * @param key
     */
    public static void clearProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        /*** 只从对象中移除*/
        properties.remove(key);
        saveProperties();
    }
    /***
     * 读取 值
     *在方法中，首先创建一个新的Properties对象。然后，使用FileInputStream来打开配置文件并将其传递给properties.load()方法。
     * properties.load()方法会将配置文件中的键值对读取到Properties对象中。
     */
    private static void loadProperties() {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 保存 值至 文件
     * FileOutputStream来创建一个文件输出流，这样可以将更改后的Properties对象写入到配置文件中。
     * 此外，store()方法将属性写回文件，并添加了一条注释，说明这次更新的属性。
     */
    private static void saveProperties() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(CONFIG_FILE_PATH)) {
            properties.store(fileOutputStream, "Updated Properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
