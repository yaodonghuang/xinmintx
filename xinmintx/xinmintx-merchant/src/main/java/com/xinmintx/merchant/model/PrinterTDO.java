package com.xinmintx.merchant.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/4
 * @time: 13:43
 * @Description:
 */
@Data
public class PrinterTDO {
    private String merchantName;//商户名称
    private String CommunityName;//社区名称
    private String receiveName;//买家姓名
    private String receivePhone;//买家电话
    private String receiveAddress;//社区地址
    private Date createTime;//下单时间
    private String receiveMessage;//用户评价
    private double totalPrice;//订单总价
    private Integer deputyHelp; //是否开启内帮办
    private List<OrderExt> voList;//用户订单


}
