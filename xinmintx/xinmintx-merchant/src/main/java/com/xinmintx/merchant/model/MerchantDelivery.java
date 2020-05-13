package com.xinmintx.merchant.model;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class MerchantDelivery {

  private Integer id;

  private Integer merchantId;

  private Integer communityId;

  private Integer type;

  private BigDecimal fee;

  private String deliveryTime;

  private String distance;
}
