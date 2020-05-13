package com.xinmintx.hstx.service.impl;

import com.xinmintx.hstx.mapper.DepositSpecificationMapper;
import com.xinmintx.hstx.pojo.po.DepositSpecification;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.AechargeService;
import com.xinmintx.hstx.service.UpToTheAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class upToTheAmountServiceImpl implements UpToTheAmountService {


    @Autowired
    private DepositSpecificationMapper depositSpecificationMapper;

    @Override
    public DepositSpecification upToTheAmount() {
        DepositSpecification depositSpecification = new DepositSpecification();
        DepositSpecification depositSpecification1 = depositSpecification.selectById(1);
        return depositSpecification1;
    }
}
