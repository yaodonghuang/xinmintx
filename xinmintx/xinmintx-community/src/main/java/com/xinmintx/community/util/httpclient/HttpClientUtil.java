package com.xinmintx.community.util.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/23 0023
 * @time: 下午 21:03
 * @Description:
 */
@Slf4j
public class HttpClientUtil {

    /**
     * get请求获取json
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * get请求获取json
     *
     * @param url
     * @return
     */
    public static String doGet(String url, Map<String, String> paramMap) {
        try {
            CloseableHttpClient client = HttpClientPool.getConnection();
            StringBuilder builderUrl = new StringBuilder();
            builderUrl.append(url);
            if (paramMap != null) {
                builderUrl.append(buildUrlQuerys(paramMap));
            }
            log.info("httpClient.doGet.Url=" + builderUrl.toString());
            HttpGet request = new HttpGet(builderUrl.toString());
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Post请求获取json
     *
     * @param url 请求地址
     * @return
     */
    public static String doPost(String url) {
        return doPost(url, null);
    }

    /**
     * Post请求获取json
     *
     * @param url      请求地址
     * @param paramMap url参数
     * @return
     */
    public static String doPost(String url, Map<String, String> paramMap) {
        return doPost(url, null, paramMap);
    }

    /**
     * Post请求获取json
     *
     * @param url      请求地址
     * @param headers  请求头
     * @param paramMap url参数
     * @return
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> paramMap) {
        return doPost(url, headers, paramMap, "");
    }

    /**
     * Post 提交json 请求获取json
     *
     * @param url      请求地址
     * @param headers  请求头
     * @param paramMap url参数
     * @param body     json参数
     * @return
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> paramMap, String body) {
        try {
            CloseableHttpClient client = HttpClientPool.getConnection();
            StringBuilder builderUrl = new StringBuilder();
            builderUrl.append(url);
            if (paramMap != null) {
                builderUrl.append(buildUrlQuerys(paramMap));
            }
            log.info("httpClient.doPost.Url=" + builderUrl.toString());
            HttpPost request = new HttpPost(builderUrl.toString());
            if (StringUtils.isNotBlank(body)) {
                request.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
            }
            if (headers != null) {
                headers.remove(HttpHeaders.CONTENT_TYPE);
                headers.forEach(request::addHeader);
            }
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Post 提交表单 请求获取json
     *
     * @param url      请求地址
     * @param headers  请求头
     * @param paramMap url参数
     * @param body     表单参数
     * @return
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> paramMap, Map<String, String> body) {
        try {
            CloseableHttpClient client = HttpClientPool.getConnection();
            StringBuilder builderUrl = new StringBuilder();
            builderUrl.append(url);
            if (paramMap != null) {
                builderUrl.append(buildUrlQuerys(paramMap));
            }
            log.info("httpClient.doPost.Url=" + builderUrl.toString());
            HttpPost request = new HttpPost(builderUrl.toString());
            if (body != null) {
                List<NameValuePair> nameValuePairList = new ArrayList<>();
                body.forEach((key, value) -> {
                    nameValuePairList.add(new BasicNameValuePair(key, value));
                });
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
                formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
                request.setEntity(formEntity);
            }
            if (headers != null) {
                headers.remove(HttpHeaders.CONTENT_TYPE);
                headers.forEach(request::addHeader);
            }
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取post方法的参数
     *
     * @param request
     * @return String
     */
    public static String getPostParam(HttpServletRequest request) {
        InputStream is = null;
        String contentStr = "";
        try {
            is = request.getInputStream();
            contentStr = IOUtils.toString(is, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return contentStr;
    }

    private static String buildUrlQuerys(Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }

    private static String buildUrl(String url, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }
}
