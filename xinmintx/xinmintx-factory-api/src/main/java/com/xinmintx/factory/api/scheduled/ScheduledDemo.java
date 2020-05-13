package com.xinmintx.factory.api.scheduled;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * 定时任务demo
 */
@Component
public class ScheduledDemo {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
// 参考网址  https://www.cnblogs.com/pejsidney/p/9046818.html

//    //每隔2秒执行一次
//    @Scheduled(fixedRate = 2000)
//    public void testTasks() {
//        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//    }

//    //每天3：05执行
//    @Scheduled(cron = "0 05 03 ? * *")
//    public void testTasks() {
//        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//    }
}
