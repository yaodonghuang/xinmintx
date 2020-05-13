package com.xinmintx.hstx.scheduled;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/7 0007
 * @time: 下午 15:26
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BenefitScheduledTest {

    @Autowired
    private BenefitScheduled scheduled;

    @Test
    public void goodsBenefit() {
        scheduled.goodsBenefit();
    }
}
