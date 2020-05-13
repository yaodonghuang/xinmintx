package com.xinmintx.business.service.impl;

import com.xinmintx.business.mapper.BusinessMapper;
import com.xinmintx.business.model.Goods;
import com.xinmintx.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Goods> selectGoods() {

        return businessMapper.selectGoods();
    }
}
