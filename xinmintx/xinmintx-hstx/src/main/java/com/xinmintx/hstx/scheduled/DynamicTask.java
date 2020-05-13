package com.xinmintx.hstx.scheduled;

import com.xinmintx.hstx.mapper.CronMapper;
import com.xinmintx.hstx.pojo.po.Cron;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 动态设置定时任务
 *
 * @author hyd
 */
@RestController
@EnableScheduling
@Slf4j
public class DynamicTask {
    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Resource
    private CronMapper cronMapper;

    @Bean
    public ThreadPoolTaskScheduler trPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    private static HashMap<String, ScheduledFuture<?>> scheduleMap = new HashMap<>();

    /**
     * 动态调度
     *
     * @param id
     * @return
     */
    @RequestMapping("schedule")
    public String schedule(@RequestParam(value = "id", required = false) Long id) {
        try {
            if (id == null) {
                // 默认id：1
                id = Long.valueOf(1);
            }
            Cron cron = cronMapper.selectById(id);
            if (cron == null) {
                return "schedule fail!";
            } else {
                if (!StringUtils.isEmpty(cron.getClassName())) {
                    // 处理cron时间
                    if (!StringUtils.isEmpty(cron.getTime())) {
                        setTime(cron);
                    } else {
                        if (StringUtils.isEmpty(cron.getCron())) {
                            return "cron empty";
                        }
                    }
                    ScheduledFuture<?> scheduledFuture = scheduleMap.get(cron.getClassName());
                    if ((scheduledFuture != null)) {
                        scheduledFuture.cancel(true);
                    }
                    ScheduledFuture<?> future = threadPoolTaskScheduler.schedule((Runnable) Class.forName(cron.getClassName()).newInstance(), new CronTrigger(cron.getCron()));
                    scheduleMap.put(cron.getClassName(), future);
                } else {
                    return "className empty";
                }
            }
            log.info(cron.getCron());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "schedule";
    }

    /**
     * 停止定时任务
     *
     * @return
     */
    @RequestMapping("endTask")
    public String endTask(@RequestParam(value = "id", required = false) Long id) {
        Cron cron = cronMapper.selectById(id);
        if (cron != null && !StringUtils.isEmpty(cron.getClassName())) {
            ScheduledFuture<?> scheduledFuture = scheduleMap.get(cron.getClassName());
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
        }
        log.info("==========================endTask============================");
        return "endTask";
    }

    // 根据cron的time设置cron
    private void setTime(Cron cron) {
        StringBuffer sb = new StringBuffer();
        Date time = cron.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        sb.append(cal.get(Calendar.SECOND) + 1).append(" ").append(cal.get(Calendar.MINUTE)).append(" ")
                .append(cal.get(Calendar.HOUR_OF_DAY)).append(" ").append(cal.get(Calendar.DAY_OF_MONTH))
                .append(" ").append(cal.get(Calendar.MONTH) + 1).append(" ?");
        cron.setCron(sb.toString());
    }

}
