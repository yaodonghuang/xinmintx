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
public class PayBean {
    private Integer userId; //用户
    private String body; //商品信息
    private Long totalFee; //价格 分
    private String openId;
    private String notifyUrl; //回调地址
}
