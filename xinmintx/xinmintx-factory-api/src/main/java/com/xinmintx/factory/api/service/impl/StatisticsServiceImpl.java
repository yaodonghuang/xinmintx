package com.xinmintx.factory.api.service.impl;

import com.xinmintx.factory.api.mapper.StatisticsMapper;
import com.xinmintx.factory.api.mapper.VenderMapper;
import com.xinmintx.factory.api.pojo.Factory;
import com.xinmintx.factory.api.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: com.xinmintx.factory.service.impl.StatisticsServiceImpl
 * @Author:Pikachu
 * @Date: 2019/12/6 16:48
 * @Version: v1.0
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private VenderMapper venderMapper;
    @Autowired
private StatisticsMapper statisticsMapper;
    @Override
    public long queryOrderSize(String token, String bDate, String eDate) {
        Factory factory = venderMapper.queryByToken(token);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String beginDate = null;
        String endDate = null;
        try {
            Date getBDate = sdf.parse(bDate);
            Date getEDate = sdf.parse(eDate);
            beginDate =String.valueOf(getBDate.getTime()/1000);
            endDate = String.valueOf(getEDate.getTime()/1000+Long.valueOf(86400));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (factory!=null){
            long id = factory.getFactoryId();
            long num =statisticsMapper.queryOrderSize(id,beginDate,endDate) ;
            return num;
        }else {
            return 0;
        }
    }

    @Override
    public Double queryTurnover(String token, String bDate, String eDate) {
        Factory factory = venderMapper.queryByToken(token);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String beginDate = null;
        String endDate = null;
        try {
            Date getBDate = sdf.parse(bDate);
            Date getEDate = sdf.parse(eDate);
            beginDate =String.valueOf(getBDate.getTime()/1000);
            endDate = String.valueOf(getEDate.getTime()/1000+Long.valueOf(86400));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long orderStatu =6;
        if (factory!=null){
            long id = factory.getFactoryId();
            Double turnover =statisticsMapper.queryTurnover(id,beginDate,endDate,orderStatu);
            if (turnover!=null){
                return turnover;
            }
        }
        return null;
    }
}
