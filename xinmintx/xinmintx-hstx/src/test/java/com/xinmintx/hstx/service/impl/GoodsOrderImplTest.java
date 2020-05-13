package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.UserMapper;
import com.xinmintx.hstx.pojo.po.User;
import com.xinmintx.hstx.service.GoodsOrderService;
import com.xinmintx.hstx.util.httpclient.HttpClientUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
class GoodsOrderImplTest {

    @Autowired
    private GoodsOrderService service;

    @Autowired
    private UserMapper userMapper;

//    @Test
//    void cancelOrderDetail() {
//        service.cancelOrderDetail(341);
//    }

    @Test
    void cancelOrderDetails() {
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.lambda().eq(User::getName,"孟庆军");
//        List<User> users = userMapper.selectList(userQueryWrapper);
//        for (User user : users) {
//            System.out.println(user);
//        }

//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        LambdaQueryWrapper<User> boss = queryWrapper.eq(User::getName, "BOSS");
//        List<User> users = userMapper.selectList(boss);
//        for (User user : users) {
//            System.out.println(user);
//        }

//        List<User> list = new LambdaQueryChainWrapper<>(userMapper).eq(User::getName, "孟庆军").list();
//        for (User user : list) {
//            System.out.println(user);
//        }
        User user = new LambdaQueryChainWrapper<>(userMapper).eq(User::getName, "孟").one();
        System.out.println(user);
    }

    @Value("${gift.url}")
    private String giftUrl;

    @Test
    public void test1(){
        //领取礼包
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("giftId", "57");
        Map<String, String> headers = new HashMap<>();
        headers.put("token", "4df66883-fe4d-48a2-be55-5f6b6aeb3957");
        String s = HttpClientUtil.doPost(giftUrl, headers, paramMap,"");
        System.out.println(s);
    }
}