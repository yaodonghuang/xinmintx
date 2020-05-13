package com.xinmintx.agent.controller;


import com.alibaba.fastjson.JSON;
import com.xinmintx.agent.model.PayNotify;
import com.xinmintx.agent.service.PayNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/27 0027
 * @time: 上午 11:55
 * @Description: 支付回调
 */
@Slf4j
@RestController
@RequestMapping("/api/payNotify")
public class PayNotifyController {

    @Autowired
    private PayNotifyService payNotifyService;

    /**
     * 提交合伙人回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/partner")
    public void partner(PayNotify payNotify) {
        log.info("==============提交合伙人回调=============" + JSON.toJSONString(payNotify));
        payNotifyService.partner(payNotify);
    }

    /**
     * 提交代理回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/agent")
    public void agent(PayNotify payNotify) {
        log.info("==============提交代理回调=============" + JSON.toJSONString(payNotify));
        payNotifyService.agent(payNotify);
    }

    /**
     * 提交黄金商户回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/goldMerchant")
    public void goldMerchant(PayNotify payNotify) {
        log.info("==============提交黄金商户回调=============" + JSON.toJSONString(payNotify));
        payNotifyService.goldMerchant(payNotify);
    }

    /**
     * 提交普通商户回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/generalMerchant")
    public void generalMerchant(PayNotify payNotify) {
        log.info("==============提交普通商户回调=============" + JSON.toJSONString(payNotify));
        payNotifyService.generalMerchant(payNotify);
    }

    /**
     * 提交提交社区商户回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/communityMerchant")
    public void communityMerchant(PayNotify payNotify) {
        log.info("==============提交社区商户回调=============" + JSON.toJSONString(payNotify));
        payNotifyService.communityMerchant(payNotify);
    }

    /**
     * 提交新民金卡会员回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/silverMember")
    public void silverMember(PayNotify payNotify) {
        log.info("==============提交新民金卡会员回调=============" + JSON.toJSONString(payNotify));
        payNotifyService.silverMember(payNotify);
    }
}
