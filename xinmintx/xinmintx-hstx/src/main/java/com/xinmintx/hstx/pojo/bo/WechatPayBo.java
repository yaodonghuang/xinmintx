package com.xinmintx.hstx.pojo.bo;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/28 0028
 * @time: 上午 9:45
 * @Description:
 */
@Data
public class WechatPayBo {

    private Integer userId;
    //订单号
    private String orderId;
    //描述
    private String prdDesc;
    //金额,分
    private Long orderAmt;
    private String openId;
    //回调地址
    private String retUrl;
    private String attach;
    /**
     * 回调类型(1E卡,2金卡,3会员续费,4,制卡/补卡,5商品购买,6申请成为商户,7拼团)
     */
    private Integer type;
}
