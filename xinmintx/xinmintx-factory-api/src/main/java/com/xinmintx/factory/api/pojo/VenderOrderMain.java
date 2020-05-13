package com.xinmintx.factory.api.pojo;

import java.util.List;

/**
 * @ClassName: com.xinmintx.factory.model.VenderOrderMain
 * @Author:Pikachu
 * @Date: 2020/1/13 16:19
 * @Version: v1.0
 */

public class VenderOrderMain {
    private String orderId;
    private List<VenderOrder> voList;

    public VenderOrderMain(String orderId){
        this.orderId = orderId;
    }

    public VenderOrderMain(String orderId, List<VenderOrder> voList){
        this.orderId = orderId;
        this.voList = voList;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<VenderOrder> getVoList() {
        return voList;
    }

    public void setVoList(List<VenderOrder> voList) {
        this.voList = voList;
    }
}
