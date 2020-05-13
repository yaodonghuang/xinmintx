package com.xinmintx.hstx.service.impl;

import com.xinmintx.hstx.mapper.GoodsOrderMapper;
import com.xinmintx.hstx.service.RefundBenefitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/8 0008
 * @time: 上午 9:15
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RefundBenefitServiceImplTest {
    @Autowired
    private RefundBenefitService service;
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Test
    public void refundBenefit() {
        service.refundBenefit(goodsOrderMapper.selectById(763));
    }
}
