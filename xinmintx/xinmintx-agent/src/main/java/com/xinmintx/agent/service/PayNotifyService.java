package com.xinmintx.agent.service;


import com.xinmintx.agent.model.PayNotify;

/**
 *  第三方担保支付回调处理
 */
public interface PayNotifyService {
    /**
     * 会员卡升级微信支付回调
     * @param payNotify
     * @return
     */
    //void wechatMemberPayNotify(PayNotify payNotify);

    /**
     * 提交合伙人回调
     *
     * @param payNotify
     * @return
     */
    void partner(PayNotify payNotify);

    /**
     * 提交代理回调
     *
     * @param payNotify
     * @return
     */
    void agent(PayNotify payNotify);

    /**
     * 提交黄金商户回调
     *
     * @param payNotify
     * @return
     */
    void goldMerchant(PayNotify payNotify);

    /**
     * 提交普通商户回调
     *
     * @param payNotify
     * @return
     */
    void generalMerchant(PayNotify payNotify);

    /**
     * 提交社区商户回调
     * @param payNotify
     */
    void communityMerchant(PayNotify payNotify);

    /**
     * 提交新民金卡会员回调
     *
     * @param payNotify
     * @return
     */
    void silverMember(PayNotify payNotify);
}
