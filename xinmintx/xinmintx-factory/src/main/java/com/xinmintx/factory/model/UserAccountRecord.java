package com.xinmintx.factory.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserAccountRecord {
    private Integer id;

    private Integer userId;

    private Integer orderId;

    private Integer userAccountId;

    private BigDecimal moneyChange;

    private String description;

    private Date createTime;

    private BigDecimal changPrice;
}
