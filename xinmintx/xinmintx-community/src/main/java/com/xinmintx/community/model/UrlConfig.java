package com.xinmintx.community.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author hyd
 */
@Data
@Component
public class UrlConfig {

    /**
     *  社区订单支付回调地址
     */
    @Value("${communityPayNotify.url}")
    private String orderPayNotify;

    /**
     *  支付地址
     */
    @Value("${communityPay.url}")
    private String communityPay;

    /**
     *  支付地址
     */
    @Value("${print.url}")
    private String printUrl;
}
