package com.xinmintx.merchant.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@Data
public class MerchantBankCardVo {

    private Integer deliveryId;

    private BigDecimal fee;

    private String distance;

    private String deliveryTime;

    private Integer type = 0;

    private Integer communityId;
}
