package com.xinmintx.merchant.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: com.xinmintx.merchant.model.CommunityOrderExt
 * @Author:Pikachu
 * @Date: 2020/2/22 14:39
 * @Version: v1.0
 */

public class CommunityOrderExt {
    private Double totalValue;
    private String communityName;
    private Integer acceptOrder;
    private Date createTime;
    private String time;
    private Map<Integer,CommunityOrder> orderMap= new HashMap<>();
    private Map<String,CommunityOrderCount> orderQuantity;

    public Integer getAcceptOrder() {
        return acceptOrder;
    }

    public void setAcceptOrder(Integer acceptOrder) {
        this.acceptOrder = acceptOrder;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Map<Integer, CommunityOrder> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<Integer, CommunityOrder> orderMap) {
        this.orderMap = orderMap;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Map<String, CommunityOrderCount> getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Map<String, CommunityOrderCount> orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
