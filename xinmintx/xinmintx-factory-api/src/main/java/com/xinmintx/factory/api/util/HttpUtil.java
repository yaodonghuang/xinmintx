package com.xinmintx.factory.api.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 *
 **/
@Slf4j
public class HttpUtil {

    private HttpUtil() {
    }

    private final static String UTF8 = StandardCharsets.UTF_8.displayName();

    private static CloseableHttpClient httpClient;

    static {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(1000).setSocketTimeout(4000).setExpectContinueEnabled(true).build();
        PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager();
        pool.setMaxTotal(300);
        pool.setDefaultMaxPerRoute(50);
        HttpRequestRetryHandler retryHandler = (IOException exception, int executionCount, HttpContext context) -> {
            if (executionCount > 1) {
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                return true;
            } else if (exception instanceof SocketException) {
                return true;
            }
            return false;
        };
        httpClient = HttpClients.custom().setConnectionManager(pool).setDefaultRequestConfig(requestConfig).setRetryHandler(retryHandler).build();
    }

    /**
     * @param certPath
     * @param password
     * @return
     * @throws Exception
     */
    public static CloseableHttpClient sslHttpsClient(String certPath, String password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream inputStream = new FileInputStream(new File(certPath))) {
            keyStore.load(inputStream, password.toCharArray());
        }
        SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
    }

    /**
     * @param certPath
     * @param password
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String certPath, String password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream inputStream = new FileInputStream(new File(certPath))) {
            keyStore.load(inputStream, password.toCharArray());
        }
        Enumeration enumas = keyStore.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements())// we are readin just one certificate.
        {
            keyAlias = (String)enumas.nextElement();
            System.out.println("alias=[" + keyAlias + "]");
        }
        // Now once we know the alias, we could get the keys.
        System.out.println("is key entry=" + keyStore.isKeyEntry(keyAlias));
        PrivateKey prikey = (PrivateKey) keyStore.getKey(keyAlias, password.toCharArray());
        Certificate cert = keyStore.getCertificate(keyAlias);
        PublicKey pubkey = cert.getPublicKey();
        return pubkey;
    }

    /**
     * 设置请求头信息
     *
     * @param headers
     * @param request
     * @return
     */
    private static void setHeaders(Map<String, Object> headers, HttpRequest request) {
        if (null != headers && headers.size() > 0) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }
    }

    /**
     * 发送post请求请求体为xml
     *
     * @param url
     * @param xml
     * @param headers
     * @return
     */
    public static String sendPostXml(String url, String xml, Map<String, Object> headers) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            setHeaders(headers, httpPost);
            StringEntity entity = new StringEntity(xml, StandardCharsets.UTF_8);
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送json请求
     *
     * @param url
     * @param json
     * @return
     */
    public static String sendPostJson(String url, String json, Map<String, Object> headers) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            setHeaders(headers, httpPost);
            StringEntity stringEntity = new StringEntity(json, StandardCharsets.UTF_8);
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送get请求
     *
     * @param url
     * @param params
     * @param header
     * @return
     */
    public static String sendGet(String url, Map<String, Object> params, Map<String, Object> header) {
        String result = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (params != null && params.size() > 0) {
                List<NameValuePair> pairs = new ArrayList<>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
                builder.setParameters(pairs);
            }
            HttpGet httpGet = new HttpGet(builder.build());
            setHeaders(header, httpGet);
            HttpResponse response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 发送get请求
     *
     * @param url
     * @param xml
     * @param headers
     * @return
     */
    public static String sendSslXmlPost(String url, String xml, Map<String, Object> headers, CloseableHttpClient httpClient) {
        String result = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            setHeaders(headers, httpPost);
            StringEntity entity = new StringEntity(xml, StandardCharsets.UTF_8);
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseData = response.getEntity();
            result = EntityUtils.toString(responseData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
