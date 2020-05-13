package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.IndexModuleMapper;
import com.xinmintx.hstx.pojo.po.IndexModule;
import com.xinmintx.hstx.service.IndexModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/6 0006
 * @time: 下午 18:12
 * @Description:
 */
@Service
public class IndexModuleServiceImpl implements IndexModuleService {

    @Autowired
    private IndexModuleMapper indexModuleMapper;

    @Override
    public List<IndexModule> indexModuleService() {
        return new LambdaQueryChainWrapper<>(indexModuleMapper).eq(IndexModule::getStatus, 1).orderByAsc(IndexModule::getOrderNum).list();
    }
}
