package com.xinmintx.agent.common;

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
    private String orderId;
    private String prdDesc;
    private Long orderAmt;
    private String openId;
    //回调地址
    private String retUrl;
}
