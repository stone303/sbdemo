package com.example.sbdemo.utils;

import org.junit.Test;

public class SecureUtilsTest {

    @Test
    public void encrypt() {
        String test = "teting2023";
        System.out.println(SecureUtils.encrypt(test));
    }

    @Test
    public void encode64()
    {
        String test = "TESTING2023";
        String  stringEncode =SecureUtils.encode64(test);
        System.out.println(stringEncode);

        System.out.println(SecureUtils.decode64(stringEncode));
    }
}