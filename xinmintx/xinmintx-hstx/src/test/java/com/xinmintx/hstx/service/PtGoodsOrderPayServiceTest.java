package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.vo.ShopVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/17 0017
 * @time: 下午 21:09
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PtGoodsOrderPayServiceTest {

    @Autowired
    private PtOrderService service;

    @Test
    public void initiateGroup() {
        ShopVo shopVo = new ShopVo();
        shopVo.setToken("4df66883-fe4d-48a2-be55-5f6b6aeb3957");
        shopVo.setSkuId(566);
        shopVo.setTotal(1);
        shopVo.setAddressId(156);
        shopVo.setPtGoodId(46);

        service.initiateGroup(shopVo);
    }

    @Test
    public void joinGroup() {
    }
}
