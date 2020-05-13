package com.xinmintx.hstx.util;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/3/17 0017
 * @time: 上午 11:42
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OpenIdUtilsTest {

    @Autowired
    private OpenIdUtils openIdUtils;

    @Test
    public void test() {
        openIdUtils.sendMessage("osGY-w6uGse-E1RkOBx-jRYDvTm4", "message");
    }

}
