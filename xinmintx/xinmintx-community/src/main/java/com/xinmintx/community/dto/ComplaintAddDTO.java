package com.xinmintx.community.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

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
public class ComplaintAddDTO {

    private Integer memberId;

    @NotNull(message = "社区id不能为空")
    private Integer communityId;

    @NotBlank(message = "phone不能为空")
    private String phone;

    @NotNull(message = "type不能为空")
    private Integer type;

    @NotBlank(message = "content不能为空")
    private String content;
}
