package com.xinmintx.agent.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDTO {
    private Integer id;

    private String name;

    private String merchantName;

    private Integer merchantType;

    private String address;

    private BigDecimal turnover;

    private String photourl;
}
