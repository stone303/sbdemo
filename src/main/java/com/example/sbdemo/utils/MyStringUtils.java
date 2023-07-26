package com.example.sbdemo.utils;


/**
 * @author guocang.shi
 */
public class MyStringUtils {

    public static  String getStringText(int length)
    {
        String str="qwertyuiopasdfghjklzxcvbnm12345wertyuiopasdfghjklzxcvbnm1234567890qwertyuiopasdfghjklzxcv67890qwertyuiopasdfghjklzxcvbnm1234567890";

        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();

        if(length <= charArray.length) {
            for (int i = 0; i < length; i++) {
                sb.append(charArray[i]);
            }
        }
        return sb.toString();
    }
}
