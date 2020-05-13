package com.xinmintx.system.service;


import com.alibaba.fastjson.JSON;
import com.xinmintx.system.domain.CheckInUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/29 0029
 * @time: 下午 18:29
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class ISysCardServiceTest {

    @Autowired
    ISysCardService sysCardService;

    @Test
    public void test(){
        List<CheckInUser> users = sysCardService.selectCheckInById(185);
        System.out.println(JSON.toJSONString(users));
    }

}