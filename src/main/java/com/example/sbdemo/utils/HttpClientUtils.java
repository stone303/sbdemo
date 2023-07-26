package com.example.sbdemo.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.config.RequestConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


/**
 * @author guocang.shi
 */
@Slf4j
@Component
public class HttpClientUtils {
    private static final int HTTP_TIMEOUT_MS = 3000;
    private static final int MAX_TOTAL_CONNECTIONS = 100;
    private static final int MAX_CONNECTIONS_PER_ROUTE = 10;

    private static PoolingHttpClientConnectionManager connectionManager;
    private static CloseableHttpClient httpClient;
    private static String  Authorization="bearer sf-e3f7af19-aee8-485e-ba5d-0e7cc7d2ecd2";

    static {
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(MAX_CONNECTIONS_PER_ROUTE);
        final RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(HTTP_TIMEOUT_MS).setSocketTimeout(HTTP_TIMEOUT_MS)
                .build();
        try {
            httpClient = HttpClients.custom().setConnectionManager(connectionManager).setSSLSocketFactory(RunSSLUtils.createHttpClient())
                    .setDefaultRequestConfig(requestConfig).build();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        /***
        * 方法一： 直接忽略 SSL认证    setSSLSocketFactory(sslSocketFactory)
        *  SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial((chain, authType) -> true).build();
        *  SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        *  HttpClient httpClient = HttpClients.custom()
        *          .setSSLSocketFactory(sslSocketFactory)
        *           .build();
        ***/
    }

    /***
     * 如果您遇到了"Incompatible types. Found: 'org.apache.http.HttpResponse',
     * required: 'java.lang.AutoCloseable'"错误，这通常是因为您使用了错误的类型或方法。
     * 在HttpClient中，HttpResponse接口并不是AutoCloseable接口的子接口，因此不能直接将HttpResponse对象赋值给AutoCloseable类型的变量。
     * 要解决这个问题，您可以将HttpResponse对象转换为CloseableHttpResponse对象，因为CloseableHttpResponse是AutoCloseable接口的子接口。
     * 这样，您就可以将CloseableHttpResponse对象赋值给AutoCloseable类型的变量。
     * @param url
     * @return
     * @throws IOException
     */

    public static String sendGetRequest(String url) throws IOException {
        final HttpGet getMethod = new HttpGet(url);
        try (final CloseableHttpResponse response = httpClient.execute(getMethod)) {
            HttpEntity entity = response.getEntity();
            String responseData = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
            response.close(); // 关闭CloseableHttpResponse对象
            return responseData;
        }
        catch (IOException ex) {
            getMethod.abort();
            throw ex;
        }
    }

    /***
     *在Apache HttpClient中，EntityUtils.consume方法用于消费响应实体的内容并释放相关资源。让我们更详细地理解这个过程：
     * 当我们发送HTTP请求并获取到响应时，响应实体（Response Entity）是一个包含响应的内容的实体对象。在处理完响应内容后，
     * 我们需要确保及时释放相关资源，以避免资源泄漏和内存泄漏。
     * EntityUtils.consume方法的作用就是帮助我们消费和释放响应实体的内容。具体而言，它会将实体的内容读取完毕，
     * 并关闭底层的输入流。这将释放底层的网络连接，并确保相关资源被及时关闭和释放。
     * @param url
     * @param param
     * @return
     * @throws IOException
     */
    public static JSONObject sendGetRequest(String url,String param) throws IOException {
       // log.info("---URL---："+url);
      //  log.info("---参数---："+param);
        final HttpGet getMethod = new HttpGet(url+param);
        try (final CloseableHttpResponse response = httpClient.execute(getMethod)) {
           // 从响应对象中获取响应实体
            HttpEntity entity = response.getEntity();

            // 将响应实体转换为字符串
            String responseData = EntityUtils.toString(entity, "UTF-8");

            //消费响应实体内容，释放资源
            EntityUtils.consume(entity);
            // 关闭CloseableHttpResponse对象
            response.close();

            JSONObject jsonObject =JSONObject.parseObject(responseData);

            return jsonObject;
        }
        catch (IOException ex) {
            getMethod.abort();
            throw ex;
        }
    }


    /***
     *入参数是表单格式
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
    ***/
    public static String sendPostRequest(String url, Map<String, String> paramMap) throws IOException {

        final HttpPost postMethod = new HttpPost(url);

        // 设置请求头
        postMethod.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType());

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(),
                    entry.getValue()));
        }
        postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

        try (final CloseableHttpResponse response = httpClient.execute(postMethod)) {
            HttpEntity entity = response.getEntity();
            String responseData = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
            response.close(); // 关闭CloseableHttpResponse对象
            return responseData;
        }
        catch (IOException ex) {
            postMethod.abort();
            throw ex;
        }
    }

    /***
     *入参数格式 josn
     * @param url
     * @param paramJson
     * @return
     * @throws IOException
     */
    public static JSONObject sendPostRequest(String url, JSONObject paramJson) throws IOException {
        final HttpPost postMethod = new HttpPost(url);

        // 设置请求头
        postMethod.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        postMethod.setHeader("Authorization",Authorization);
        // 设置请求体
        StringEntity requestEntity = new StringEntity(paramJson.toString(), ContentType.APPLICATION_JSON);
        postMethod.setEntity(requestEntity);

        log.info("---请求url---"+url);
        log.info("---请求入参---"+paramJson.toString());

        // 发送请求并获取响应
        HttpResponse httpResponse = httpClient.execute(postMethod);
        HttpEntity responseEntity = httpResponse.getEntity();
        // 解析响应
        String response = EntityUtils.toString(responseEntity);
        JSONObject responsejson= JSONObject.parseObject(response);
        //消费响应实体内容，释放资源
        EntityUtils.consume(responseEntity);
        // 关闭连接
        httpClient.close();
        return responsejson;
    }
}

