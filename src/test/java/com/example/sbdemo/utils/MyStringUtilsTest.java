package com.example.sbdemo.utils;


import org.junit.Test;

public class MyStringUtilsTest {
    MyStringUtils myStringUtils =new MyStringUtils();
    @Test
    public void testGetStringText() {

        System.out.println(myStringUtils.getStringText(50));
    }
}