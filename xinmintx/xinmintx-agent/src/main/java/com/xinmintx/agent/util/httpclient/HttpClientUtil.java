package com.xinmintx.agent.util.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
     * @param url
     * @return
     */
    public static String doGet(String url) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
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
     * @param url
     * @return
     */
    public static String doPost(String url) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost request = new HttpPost(url);
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
     * @param url
     * @return
     */
    public static String doPost(String url, Map<String, String> paramMap) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            String buildUrl = buildUrl(url, paramMap);
            log.info("httpClient.doPost.Url=" + buildUrl);
            HttpPost request = new HttpPost(buildUrl);
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String buildUrl(String url, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(url);
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
