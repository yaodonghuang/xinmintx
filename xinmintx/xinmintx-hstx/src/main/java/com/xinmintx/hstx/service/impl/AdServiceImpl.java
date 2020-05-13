package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.AdvertisingMapper;
import com.xinmintx.hstx.pojo.po.Advertising;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.AdService;
import com.xinmintx.hstx.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private AdvertisingMapper advertisingMapper;

    /**
     * 获取广告
     *
     * @return
     */
    @Override
    public ResultCode selectAd(Integer type) {
        ResultCode<Object> resultCode = new ResultCode<>();
        //获取广告
        List<Advertising> advertisingList = new LambdaQueryChainWrapper<>(advertisingMapper)
                .orderByAsc(Advertising::getOrderNum)   //排序
                .eq(Advertising::getAdType, type)       //根据类型获取
                .eq(Advertising::getStatus, 1).list();  //广告是有效的

        if (advertisingList != null && advertisingList.size() > 0) {
            Map<String, List<Advertising>> place = ListUtils.listGroup(advertisingList, "adPlace");
           // Map<String, List<Advertising>> sortMapByKey = MapUtil.sortMapByKey(place);
            List<Object> list = new ArrayList<>();
            AtomicInteger i = new AtomicInteger(1);
            place.forEach((key, value) -> {
                HashMap<Object, Object> hashMap = new HashMap<>();
                hashMap.put("place", i.get());
                hashMap.put("adList", value);
                list.add(hashMap);
                i.getAndIncrement();
            });

            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(list);
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
            resultCode.setData(new ArrayList<>());
        }
        return resultCode;
    }
}
