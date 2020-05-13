package com.xinmintx.merchant.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@Data
public class MerchantBindCardDTO {
    private Integer merchantId;

    @NotBlank(message = "name不能为空")
    private String name;

    @NotBlank(message = "bankName不能为空")
    private String bankName;

    @NotBlank(message = "idCard不能为空")
    private String idCard;

    @NotBlank(message = "cardNum不能为空")
    private String cardNum;

    private String shortName;

    @NotBlank(message = "phone不能为空")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
    private String phone;

}
