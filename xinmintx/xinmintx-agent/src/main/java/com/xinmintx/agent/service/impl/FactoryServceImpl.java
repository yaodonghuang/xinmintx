package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.FactoryMapper;
import com.xinmintx.agent.model.Factory;
import com.xinmintx.agent.service.FactoryServce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName:.FactoryServceImpl
 * @author:chf
 * @Date:2020/1/6：16:50
 * @developerKits： win 10     jdk1.8
 */
@Service
public class FactoryServceImpl implements FactoryServce {
    @Resource
    private FactoryMapper factoryMapper;

    @Override
    public int addFactory(Factory factory) {
        Factory factory1 = factoryMapper.selectByPrimaryKey(Long.parseLong(factory.getPhoneId()));
        if (factory1==null){
            return factoryMapper.insertSelective(factory);
        }else {
            return 0;
        }
    }
}
