package com.xinmintx.agent.controller;

import com.xinmintx.agent.model.UserExample;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
@Transactional
public class TimedTask {

    public Date date;
    /**
     * 定时任务 月初清除 讲师业绩
     */
    @Scheduled(cron = "0 0 1 1 * ?")
    public void teacherRemovalPerformance() throws ParseException {
        Date a = new Date();//获取当前的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String str = df.format(a);//获取String类型的时间
        date = df.parse(str);

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static Logger getLog() {
        return log;
    }
}
