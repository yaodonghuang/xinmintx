package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.ShoppingCartDetailMapper;
import com.xinmintx.hstx.mapper.SysCardMapper;
import com.xinmintx.hstx.pojo.po.SysCard;
import com.xinmintx.hstx.util.ListUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/22 0022
 * @time: 下午 17:30
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysCardTest {

    @Autowired
    private SysCardMapper sysCardMapper;
    @Autowired
    private ShoppingCartDetailMapper shoppingCartDetailMapper;

    @Test
    public void setSysCard() {
        List<SysCard> sysCards = new LambdaQueryChainWrapper<>(sysCardMapper)
                .orderByAsc(SysCard::getCreateTime)
                .list();
        Map<String, List<SysCard>> createTime = ListUtils.listGroup(sysCards, "createTime");
        Date now = new Date();
        AtomicInteger i = new AtomicInteger();
        createTime.forEach((key, value) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.set(Calendar.DATE,i.get());
            Date time = calendar.getTime();
            for (SysCard sysCard : value) {
                //sysCard.setCreateTime(time);
                sysCard.updateById();
            }
            i.getAndIncrement();
        });

    }

    @Test
    public void test2(){
        int total = shoppingCartDetailMapper.getMemberPanicTotalByPanicId(71, 30);
        System.out.println(total);
    }
}
