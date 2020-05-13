package com.xinmintx.agent.configuration.pay;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by weiming on 16-7-3.
 */
@Data
@Component
public class PayConfig {
    //微信扫码支付结果通知URL
    public final static String WECHAT_NOTIFY_URL = "http://agent2.xinmintx.cn/api/wechatPayNotify";
    public final static String WECHAT_NOTIFY_URL2 = "http://agent2.xinmintx.cn/api/wechatMerchantPayNotify";

    public static final String SYSTEMERROR = "SYSTEMERROR";

    @Value("${partner.url}")
    private String partner;

    @Value("${agent.url}")
    private String agent;

    @Value("${goldMerchant.url}")
    private String goldMerchant;

    @Value("${generalMerchant.url}")
    private String generalMerchant;

    @Value("${silverMember.url}")
    private String silverMember;

    @Value("${communityMerchant.url}")
    private String communityMerchant;
}
