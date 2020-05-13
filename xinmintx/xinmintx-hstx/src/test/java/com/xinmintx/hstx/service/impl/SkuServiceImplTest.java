package com.xinmintx.hstx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.xinmintx.hstx.mapper.GoodsSkuMapper;
import com.xinmintx.hstx.mapper.ShoppingCartDetailMapper;
import com.xinmintx.hstx.pojo.po.GoodsSku;
import com.xinmintx.hstx.pojo.po.ShoppingCartDetail;
import com.xinmintx.hstx.util.ListUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/10 0010
 * @time: 下午 13:38
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SkuServiceImplTest {

    @Autowired
    private GoodsSkuMapper mapper;
    @Autowired
    private ShoppingCartDetailMapper shoppingCartDetailMapper;

    @Test
    public void test() {
        List<GoodsSku> goodsSkus = mapper.selectByMap(null);
        Map<String, List<GoodsSku>> goodsId = ListUtils.listGroup(goodsSkus, "goodsId");
        System.out.println(JSON.toJSONString(goodsId));
    }

    @Test
    public void test2() {
        HashSet<Integer> set = new HashSet<>();
        set.add(911);
        new LambdaUpdateChainWrapper<>(shoppingCartDetailMapper)
                .in(ShoppingCartDetail::getSkuId, set)
                .eq(ShoppingCartDetail::getMemberId, 71)
                .eq(ShoppingCartDetail::getStatus, 1)
                .set(ShoppingCartDetail::getStatus, 0)
                .update();
    }
}
