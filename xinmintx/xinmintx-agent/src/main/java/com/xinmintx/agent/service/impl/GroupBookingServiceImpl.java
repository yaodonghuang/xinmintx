package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.GoodPtgoodBeanMapper;
import com.xinmintx.agent.mapper.GoodsPtUserBeanMapper;
import com.xinmintx.agent.mapper.GoodsPtUserMapper;
import com.xinmintx.agent.model.GoodPtgood;
import com.xinmintx.agent.model.GoodsPtUser;
import com.xinmintx.agent.service.GroupBookingService;
import com.xinmintx.agent.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:.GroupBookingServiceImpl
 * @author:chf
 * @Date:2019/12/18：14:15
 * @developerKits： win 10     jdk1.8
 */
@Service
public class GroupBookingServiceImpl implements GroupBookingService {
    @Autowired
    private GoodPtgoodBeanMapper goodPtgoodMapper;

    @Autowired
    private GoodsPtUserMapper userMapper;

    @Autowired
    private GoodsPtUserBeanMapper ptUserBeanMapper;

    @Override
    public Map<Object,Object> addGroupBooking(int userId, Integer people, Integer shopingId) {
        //查询pood_ptgood表中有无该商品可拼团记录
        GoodPtgood ptgood = goodPtgoodMapper.selectGoodPt(people,shopingId);
        Map<Object,Object> map = new HashMap<>();
        int i = 0;
        if (ptgood!=null) {
            GoodsPtUser ptUser = ptUserBeanMapper.selectPtUser(userId,ptgood.getPtgoodsId(),shopingId);
            if (ptUser!=null){
                map.put("ptUserId",ptUser.getId());
            }
            if (ptUser == null) {
                GoodsPtUser goodsPtUser = new GoodsPtUser();
                goodsPtUser.setUserId(userId);
                goodsPtUser.setGoodPtId(ptgood.getPtgoodsId());
                goodsPtUser.setgoodsId(shopingId.toString());
                goodsPtUser.setCreateTime(DateUtils.getNowDate());
                goodsPtUser.setUpdateTime(DateUtils.getNowDate());
                i =  userMapper.insertSelective(goodsPtUser);
                map.put("ptUserId",goodsPtUser.getId());
            }
            i = 2;
        }
        map.put("i",i);
        return map;
    }
}
