package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.pojo.vo.PayNotify;
import com.xinmintx.hstx.pojo.vo.PoboNotify;
import com.xinmintx.hstx.service.PayNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/27 0027
 * @time: 上午 11:55
 * @Description: 支付回调
 */
@RestController
@RequestMapping("/hs/pay")
public class PayNotifyController {

    @Autowired
    private PayNotifyService payNotifyService;
    /**
     * E卡购买微信支付回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/eCardNotify")
    public void eCardNotify(PayNotify payNotify){
        payNotifyService.eCardNotify(payNotify);
    }

    /**
     * 新民金卡购买微信支付回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/goldCardPayNotify")
    public void goldCardPayNotify(PayNotify payNotify) {
        payNotifyService.goldCardPayNotify(payNotify);
    }
    /**
     * 会员续费微信支付回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/cardRenewNotify")
    public void cardRenewNotify(PayNotify payNotify) {
        payNotifyService.cardRenewNotify(payNotify);
    }

    /**
     * 制卡/补卡微信支付回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/makeCardNotify")
    public void makeCardNotify(PayNotify payNotify) {
        payNotifyService.makeCardNotify(payNotify);
    }

    /**
     * 商品购买微信支付回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/wechatGoodsPayNotify")
    public void wechatGoodsPayNotify(PayNotify payNotify) {
        payNotifyService.wechatGoodsPayNotify(payNotify);
    }

    /**
     * 申请成为商户微信支付回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/wechatMerchantPayNotify")
    public void wechatMerchantPayNotify(PayNotify payNotify) {
        payNotifyService.wechatMerchantPayNotify(payNotify);
    }

    /**
     * 拼团支付微信回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/ptPayNotify")
    public void ptPayNotify(PayNotify payNotify) {
        payNotifyService.ptPayNotify(payNotify);
    }

    /**
     * 充值 支付微信回调
     *
     * @param payNotify
     * @return
     */
    @PostMapping("/rechargePayNotify")
    public void rechargePayNotify(PayNotify payNotify) {
        payNotifyService.rechargePayNotify(payNotify);
    }

    /**
     * 充值 支付微信回调
     *
     * @param poboNotify
     * @return
     */
    @PostMapping("/poboNotifyUrl")
    public void poboNotifyUrl(PoboNotify poboNotify) {
        payNotifyService.poboNotifyUrl(poboNotify);
    }
}
