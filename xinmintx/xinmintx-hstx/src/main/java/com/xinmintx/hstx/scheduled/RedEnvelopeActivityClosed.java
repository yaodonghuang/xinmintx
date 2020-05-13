package com.xinmintx.hstx.scheduled;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.RedEnvelopeActivitiesMapper;
import com.xinmintx.hstx.pojo.po.RedEnvelopeActivities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class RedEnvelopeActivityClosed {

    @Autowired
    private RedEnvelopeActivitiesMapper redEnvelopeActivitiesMapper;

    /**
     * 整点执行 活动过期 定时关闭
     */
    //@Scheduled(cron = "0 0 0/1 * * ?")
    //明天i凌晨一点执行
    //@Scheduled(cron = "0 0 1 * * ?")
    @RequestMapping("/redEnvelopeActivityClosed")
    public void redEnvelopeActivityClosed() {
        List<RedEnvelopeActivities> list = new LambdaQueryChainWrapper<>(redEnvelopeActivitiesMapper)
                .eq(RedEnvelopeActivities::getStart, 1)
                .le(RedEnvelopeActivities::getRedEndTime, new Date()).list();
        for (RedEnvelopeActivities rList :list){
            rList.setStart(2);
            rList.updateById();
        }
    }
}
