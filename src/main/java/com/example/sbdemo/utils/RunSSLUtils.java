package com.example.sbdemo.utils;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * @author guocang.shi
 */
public class RunSSLUtils {

    // 创建一个 HttpClient 实例并绕过 SSL 验证
    //public static CloseableHttpClient createHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    public static  SSLConnectionSocketFactory createHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        // 创建一个自定义的 TrustManager，绕过证书验证
        TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                // 不做任何操作
            }
            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                // 不做任何操作
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }};

        // 创建一个使用自定义 TrustManager 的 SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagers, new SecureRandom());

        // 创建一个 SSLConnectionSocketFactory，使用自定义的 SSLContext，同时关闭主机名验证
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        // 创建 HttpClient 实例，使用自定义的 SSLConnectionSocketFactory 来绕过 SSL 验证
     /***   return HttpClients.custom()
                .setSSLSocketFactory(sslSocketFactory)
                .build();***/
        return sslSocketFactory;
    }

}
