package com.xinmintx.hstx.service.impl;

import com.alibaba.fastjson.JSON;
import com.xinmintx.hstx.pojo.vo.GoodsTypeVo;
import com.xinmintx.hstx.service.GoodsService;
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
 * @date: 2020/1/10 0010
 * @time: 下午 14:06
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsServiceImplTest {
    @Autowired
    private GoodsService service;

    @Test
    public void getGoodsTypes() {
        List<GoodsTypeVo> goodsTypes = service.getGoodsTypes();
        System.out.println(JSON.toJSONString(goodsTypes));
    }
}
