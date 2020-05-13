package com.xinmintx.agent.modelDTO;

import com.xinmintx.agent.model.UnitPhoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantAndUnitphoto extends UnitPhoto{
    private Integer id;

    private Integer orderId;

    private String name;

    private String idcard;

    private Integer recommender;

    private String merchantName;

    private Integer merchantType;

    private Integer merchantTable;

    private String address;

    private String regionName;

    private String regionCode;

    private Integer merchantBranchOfficeId;

    private BigDecimal turnover;

    private Integer isReview;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;


}
