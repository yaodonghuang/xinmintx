package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.vo.PayNotify;
import com.xinmintx.hstx.pojo.vo.PoboNotify;

/**
 *  第三方担保支付回调处理
 */
public interface PayNotifyService {
    /**
     * E卡购买微信支付回调
     */
    void eCardNotify(PayNotify payNotify);
    /**
     * 新民金卡购买微信支付回调
     * @param payNotify
     * @return
     */
    void goldCardPayNotify(PayNotify payNotify);

    /**
     * 会员续费
     * @param payNotify
     */
    void cardRenewNotify(PayNotify payNotify);

    /**
     * 制卡/补卡
     * @param payNotify
     */
    void makeCardNotify(PayNotify payNotify);
    /**
     * 商品购买微信支付回调
     * @param payNotify
     * @return
     */
    void wechatGoodsPayNotify(PayNotify payNotify);
    /**
     * 申请成为商户微信支付回调
     * @param payNotify
     * @return
     */
    void wechatMerchantPayNotify(PayNotify payNotify);

    /**
     * 拼团支付微信回调
     * @param payNotify
     * @return
     */
    void ptPayNotify(PayNotify payNotify);

    /**
     * 余额充值回调
     * @param payNotify
     */
    void rechargePayNotify(PayNotify payNotify);

    /**
     * 会员提现回调
     * @param poboNotify
     */
    void poboNotifyUrl(PoboNotify poboNotify);
}
