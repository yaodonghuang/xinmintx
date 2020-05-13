package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2019/11/17 0016
 * @time: 上午 10:00
 * @Description: 广告
 */
@RestController
@RequestMapping("/hs/ad")
public class AdController {


    @Autowired
    private AdService adService;

    /**
     * 获取广告
     *
     * @param type
     * @return
     */
    @GetMapping("/getAd/{type}")
    public ResultCode getAd(@PathVariable("type") Integer type) {
        return adService.selectAd(type);
    }


}
