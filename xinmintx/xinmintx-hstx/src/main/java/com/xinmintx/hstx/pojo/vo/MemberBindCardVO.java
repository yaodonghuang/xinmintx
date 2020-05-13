package com.xinmintx.hstx.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/6
 * @time: 13:23
 * @Description: 接收前端银行卡部分信息
 */
@Data
public class MemberBindCardVO {
    private Integer memberId;

    //银行名称
    @NotBlank(message = "bankName不能为空")
    private String bankName;

/*    //银行卡类型
    @NotBlank(message = "bankName不能为空")
    private String bankType;*/

    //银行卡号
    @NotBlank(message = "bankCard不能为空")
    private String bankCard;

    //银行卡绑定姓名
    @NotBlank(message = "cardholderName不能为空")
    private String name;

    //绑定银行卡身份证
    @NotBlank(message = "idCard不能为空")
    private String idCard;

    //银行缩写
    @NotBlank(message = "shotName不能为空")
    private String shotName;

    //银行预留手机号
    @NotBlank(message = "cellphone不能为空")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
    private String cellphone;

}
