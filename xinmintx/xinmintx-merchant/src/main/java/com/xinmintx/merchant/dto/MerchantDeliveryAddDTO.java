package com.xinmintx.merchant.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@Data
public class MerchantDeliveryAddDTO {

    private Integer deliveryId;

    @NotBlank(message = "deliveryTime不能为空")
    private String deliveryTime;

    @NotNull(message = "type不能为空")
    private Integer type;

    @NotNull(message = "fee不能为空")
    private BigDecimal fee;

    @NotNull(message = "communityId不能为空")
    private Integer communityId;

    private String distance;

    private Integer merchantId;
}
