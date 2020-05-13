package com.xinmintx.hstx.service.impl;

import com.xinmintx.hstx.mapper.MemberIntegralMapper;
import com.xinmintx.hstx.pojo.po.MemberIntegral;
import com.xinmintx.hstx.service.MemberIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 添加积分
 *
 * @author sw 2019/11/20
 */
@Service
public class MemberIntegralServiceImpl implements MemberIntegralService {
    @Autowired
    private MemberIntegralMapper memberIntegralMapper;

    /**
     * 添加积分
     *
     * @param memberId
     * @param integralValue
     */
    @Override
    public void insertIntegral(int memberId, double integralValue) {
        //获取该会员的积分
        Map<String, Object> map = new HashMap<>();
        map.put("member_id", memberId);
        List<MemberIntegral> memberIntegrals = memberIntegralMapper.selectByMap(map);
        //如果会员积分表不存在该会员就直接添加积分
        if (memberIntegrals == null || memberIntegrals.size() == 0) {
            MemberIntegral memberIntegral = new MemberIntegral();
            memberIntegral.setIntegral(integralValue);
            memberIntegral.setMemberId(memberId);
            memberIntegral.setCreateTime(new Date());
            memberIntegralMapper.insert(memberIntegral);
        } else {
            //更新会员的积分
            MemberIntegral memberIntegralss = memberIntegrals.get(0);
            double newIntegral = memberIntegralss.getIntegral() + integralValue;
            memberIntegralss.setIntegral(newIntegral);
            memberIntegralMapper.updateById(memberIntegralss);
        }
    }
}
