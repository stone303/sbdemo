package com.example.sbdemo.config;

/**
 * @author guocang.shi
 * 存储参数
 */
public class GlobalParams {

    public static String returnValue;




    // 私有构造函数，防止实例化
    private GlobalParams() {}

    // 设置返回字段的方法
    public static void setReturnValue(String value) {
        returnValue = value;
    }

    // 获取返回字段的方法
    public static String getReturnValue() {
        return returnValue;
    }


}
