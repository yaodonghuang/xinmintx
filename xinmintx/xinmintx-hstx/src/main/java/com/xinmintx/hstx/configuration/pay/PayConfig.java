package com.xinmintx.hstx.configuration.pay;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author wcj
 * @date 16-7-3
 */
@Data
@Component
public class PayConfig {
    /**
     * 新民金卡购买
     */
    @Value("${goldCardNotifyUrl}")
    private String goldCardNotifyUrl;
    /**
     * E卡购买
     */
    @Value("${eCardNotifyUrl}")
    private String eCardNotifyUrl;
    /**
     * 会员卡续费
     */
    @Value("${cardRenewNotifyUrl}")
    private String cardRenewNotifyUrl;
    /**
     * 制卡/补卡
     */
    @Value("${makeCardNotifyUrl}")
    private String makeCardNotifyUrl;
    /**
     * 商品支付
     */
    @Value("${goodsNotifyUrl}")
    private String goodsNotifyUrl;
    /**
     * 商户支付
     */
    @Value("${merchantNotifyUrl}")
    private String merchantNotifyUrl;
    /**
     * 拼团微信支付回调地址
     */
    @Value("${goodsGroupNotifyUrl}")
    private String goodsGroupNotifyUrl;
    /**
     * 提现回调
     */
    @Value("${poboNotifyUrl}")
    private String poboNotifyUrl;

    /**
     * 支付
     */
    @Value("${pay.url}")
    private String payUrl;

    /**
     * 退款
     */
    @Value("${refund.url}")
    private String refundUrl;

    /**
     * 确认收货
     */
    @Value("${confirmReceipt.url}")
    private String confirmReceiptUrl;

    /**
     * 礼包领取
     */
    @Value("${gift.url}")
    private String giftUrl;

    /**
     *  我的[社区菜品]订单[单笔支付]
     */
    @Value("${communityPay.url}")
    private String communityOnePayUrl;

    /**
     * 余额充值
     */
    @Value("${rechargeUrl}")
    private String rechargeUrl;

    /**
     * 提现
     */
    @Value("${pobo.url}")
    private String poboUrl;


}
