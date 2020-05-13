package com.xinmintx.business.controller;

import com.xinmintx.business.model.Goods;
import com.xinmintx.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("get")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @RequestMapping("selectGoods")
    public List<Goods> selectGoods(){
        List<Goods> goods = businessService.selectGoods();
        return goods;
    }
}
