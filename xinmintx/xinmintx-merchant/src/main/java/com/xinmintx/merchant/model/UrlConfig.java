package com.xinmintx.merchant.model;

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
     *  商户拒绝订单调用退款url
     */
    @Value("${merchantRefuse.url}")
    private String merchantRefuse;

    /**
     *  商户提现回调url
     */
    @Value("${merchantpoboNotify.url}")
    private String poboNotify;

    /**
     *  商户提现url
     */
    @Value("${merchantpobo.url}")
    private String pobo;
}
