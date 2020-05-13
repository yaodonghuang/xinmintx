package com.xinmintx.factory.model;

/**
 * @ClassName: com.xinmintx.factory.model.CashierDesk
 * @Author:Pikachu
 * @Date: 2019/12/6 16:28
 * @Version: v1.0
 */

public class CashierDesk {
    private long  orderSize;//订单总数
    private Double  turnover;//一营业额
    private long  PageView;//浏览量

    public long getOrderSize() {
        return orderSize;
    }

    public void setOrderSize(long orderSize) {
        this.orderSize = orderSize;
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

    public long getPageView() {
        return PageView;
    }

    public void setPageView(long pageView) {
        PageView = pageView;
    }
}
