package com.xinmintx.merchant.model;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MerchantBankCard {

  private Integer id;

  private Integer merchantId;

  private String name;

  private String bankName;

  private String idCard;

  private String phone;

  private String cardNum;

  private Date createTime;

  private String shortName;

}
