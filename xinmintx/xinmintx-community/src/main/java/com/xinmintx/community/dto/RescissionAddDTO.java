package com.xinmintx.community.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@Data
public class RescissionAddDTO {

    @NotNull(message = "communityId不能为空")
    private Integer communityId;

    private Integer memberId;

    @NotBlank(message = "phone不能为空")
    private String phone;

    @NotNull(message = "type不能为空")
    private Integer type;

}
