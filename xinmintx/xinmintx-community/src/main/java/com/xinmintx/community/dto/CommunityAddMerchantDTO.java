package com.xinmintx.community.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@Data
public class CommunityAddMerchantDTO {

    private Integer communityId;

//    @NotBlank(message = "号码不能为空")
//    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
//    private String phone;


    private List<Integer> types;

    private Integer merchantId;
}
