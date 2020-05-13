package com.xinmintx.merchant.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserAccount {
    private Integer id;
    private Integer userId;
    private BigDecimal money;
    private BigDecimal freezeMoney;
}
