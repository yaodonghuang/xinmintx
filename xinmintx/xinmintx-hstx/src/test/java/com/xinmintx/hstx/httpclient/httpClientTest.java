package com.xinmintx.hstx.httpclient;

import com.alibaba.fastjson.JSON;
import com.xinmintx.hstx.util.httpclient.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/15 0015
 * @time: 下午 20:43
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class httpClientTest {

    @Test
    public void test1() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        Map<String, String> body = new HashMap<>();
        body.put("username", "张三");
        body.put("address", "平湖");
        body.put("sex", "男");
        String json = HttpClientUtil.doPostByForm("http://localhost:8080/user/test1", headers, null, body);
        System.out.println(json);
    }

    @Test
    public void test2() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, Object> body = new HashMap<>();
        body.put("username", "张三");
        body.put("address", "平湖");
        body.put("sex", "男");
        String querys = JSON.toJSONString(body);
        String json = HttpClientUtil.doPost("http://localhost:8080/user/test2", headers, null, querys);
        System.out.println(json);
    }
}
