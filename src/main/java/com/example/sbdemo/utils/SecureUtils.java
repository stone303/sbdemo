package com.example.sbdemo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author guocang.shi
 *
 * 它是一个信息摘要算法，而摘要和加密是有区别的
 */
public class SecureUtils {

    /***
     * MD5
     * 信息摘要算法
     * 它是一个信息摘要算法，而摘要和加密是有区别的
     * @param input
     * @return
     */
    public static String encrypt(String input) {
        try {
            // 创建一个MD5摘要算法实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将输入字符串转换为字节数组
            byte[] inputBytes = input.getBytes();

            // 计算MD5摘要
            byte[] digest = md.digest(inputBytes);

            // 将摘要转换为16进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    /***
     * base64
     * Base64是一种编码方式而不是加密方式
     * 将待编码的数据（例如，字节数组）分割成每 3 个字节一组。每个字节包含 8 位二进制数据。
     * 将每组 3 个字节的数据划分成 4 个 6 位的小组。
     * 对每个 6 位的小组进行映射，将其转换为一个可打印字符。Base64使用了 64 个可打印字符，
     * 通常是 A-Z、a-z、0-9 和两个额外的符号。这个映射表保证了每个 6 位的小组都能被准确地表示为一个可打印字符。
     * 如果原始数据的长度不是 3 的倍数，则在末尾添加一个或两个填充字符（通常是’='），以确保编码结果的长度为 4 的倍数。
     * 解码过程与编码过程相反：
     *
     * 将编码后的字符串解析为每个字符对应的 6 位二进制数据。
     * 将每个字符的 6 位二进制数据组合成一个 24 位的字节序列。
     * 将 24 位字节序列划分为每个 8 位一组，即 3 个字节。
     * 将每个字节转换为对应的二进制数据。
     * @param input
     * @return
     */
    public static String encode64(String input) {
        byte[] inputBytes = input.getBytes();
        byte[] encodedBytes = Base64.getEncoder().encode(inputBytes);
        return new String(encodedBytes);
    }

    public static String decode64(String input) {
        byte[] inputBytes = input.getBytes();
        byte[] decodedBytes = Base64.getDecoder().decode(inputBytes);
        return new String(decodedBytes);
    }


}
