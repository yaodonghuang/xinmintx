package com.xinmintx.merchant.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@Data
public class MyCommunityVo {
    private Integer merchantId;
    private Integer communityId;
    private String communityName;
    private Integer presidentId;
    private String presidentName;
    private String presidentCellphone;

    private List<DeliveryVo> deliver;
}
