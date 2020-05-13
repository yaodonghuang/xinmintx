package com.xinmintx.hstx.service.impl;

import com.xinmintx.hstx.mapper.SysCardMapper;
import com.xinmintx.hstx.pojo.po.SysCard;
import com.xinmintx.hstx.service.SysCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sw
 * @date 2019\11\18
 */
@Service
public class SysCardServiceImpl implements SysCardService {

    @Autowired
    private SysCardMapper sysCardMapper;


    /**
     * 根据现在时间获取文案
     *
     * @return 当日文案
     */
    @Override
    public List<SysCard> findCharactersByDate() {
        //获取现在时间
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String time = ft.format(now);

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("DATE_FORMAT(create_time,'%Y-%m-%d')", time);
        int hours = now.getHours();
        if (hours >= 0 && hours <= 14) {
            //如果在15：00之前就查询上午美文
            hashMap.put("time_frame", 1);
            return sysCardMapper.selectByMap(hashMap);
        } else {
            //如果在15：00之后就查询下午美文
            hashMap.put("time_frame", 2);
            return sysCardMapper.selectByMap(hashMap);
        }
    }

    /**
     * 根据id查询文案
     *
     * @param sysCardId
     * @return 文案
     */
    @Override
    public SysCard findSysCardById(int sysCardId) {
        return sysCardMapper.selectById(sysCardId);
    }


}