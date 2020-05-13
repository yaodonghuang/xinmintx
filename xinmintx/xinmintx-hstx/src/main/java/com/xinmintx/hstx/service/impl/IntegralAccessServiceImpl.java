package com.xinmintx.hstx.service.impl;


import com.xinmintx.hstx.mapper.IntegralAccessMapper;
import com.xinmintx.hstx.pojo.po.IntegralAccess;
import com.xinmintx.hstx.service.IntegralAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegralAccessServiceImpl implements IntegralAccessService {
    @Autowired
    private IntegralAccessMapper integralAccessMapper;
    /**
     * 根据获取方式获得积分
     * @param id 获取方式
     * @return 积分
     */
    @Override
    public double getIntegeral(int id) {
        IntegralAccess integralAccess = integralAccessMapper.selectById(id);
        return integralAccess.getIntegralValue();
    }
}
