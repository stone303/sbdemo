package com.example.sbdemo.utils;


import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * @author guocang.shi
 *
 * 原理：
 * 默认情况下，Java使用一个叫做TrustManager的接口来验证服务器端的SSL证书。
 * TrustManager通常会验证SSL证书是否由受信任的证书颁发机构（CA）签发，以确保证书的可信度。
 * 绕过SSL证书验证的原理是通过自定义和注册一个TrustManager实现类，覆盖掉默认的证书验证过程，从而实现绕过SSL证书的目的。
 *
 *实践步骤：
 * 创建一个类，实现X509TrustManager接口，该接口用于验证服务器端的SSL证书。
 * 在实现类中，覆盖接口方法，例如 checkServerTrusted() 和 checkClientTrusted()，使其不做任何验证。
 * 在Java代码中，通过调用SSLContext类的静态方法 getInstance()，获取SSLContext实例。
 * 调用SSLContext的 init() 方法，传入自定义的TrustManager数组，以及一个SecureRandom对象来初始化SSLContext实例。
 * 通过HttpsURLConnection或HttpClient等工具类，打开HTTPS连接，并将SSLContext实例中的SSLSocketFactory和HostnameVerifier设置给该连接对象，从而绕过SSL证书验证。
 * 可选地，通过设置系统属性 javax.net.ssl.trustStore 和 javax.net.ssl.trustStorePassword，指定信任的证书库和密码，以替换默认的信任库。
 *
 */
public class RestTemplateUtil {

    /**
     * 绕过SSL证书验证
     *
     */
    public static void trustAllHosts() {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                //getAcceptedIssuers()方法返回一个X509Certificate数组，表示信任的证书颁发机构。在这里，返回null表示不信任任何证书颁发机构。
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
            }
        };

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}