package com.xinmintx.hstx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.configuration.pay.PayConfig;
import com.xinmintx.hstx.mapper.GoodsOrderDetailMapper;
import com.xinmintx.hstx.pojo.po.GoodsOrder;
import com.xinmintx.hstx.pojo.po.GoodsOrderDetail;
import com.xinmintx.hstx.pojo.po.UOrder;
import com.xinmintx.hstx.service.UOrderSerivce;
import com.xinmintx.hstx.util.httpclient.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/24 0024
 * @time: 下午 17:21
 * @Description:
 */
@Async
@Slf4j
@Service
public class UOrderSerivceImpl implements UOrderSerivce {
    @Autowired
    private PayConfig payConfig;
    @Autowired
    private GoodsOrderDetailMapper goodsOrderDetailMapper;

    @Override
    public void goodsOrderRefund(GoodsOrder goodsOrder, UOrder uOrder) {
        if (goodsOrder.getOrderState() == 9){
            //已经退款了
            return;
        }
        //库存不足,退款
        log.info("========================== 执行退款 ==========================");
        Map<String, String> map = new HashMap<>();
        map.put("oriOrderId", uOrder.getOrderNo());
        BigDecimal orderAmt = goodsOrder.getTotalAmount().multiply(BigDecimal.valueOf(100));
        map.put("orderAmt", String.valueOf(orderAmt.longValue()));
        String json = HttpClientUtil.doPost(payConfig.getRefundUrl(), map);
        log.info("========================== " + json + " ==========================");
        if (StringUtils.isNotBlank(json)) {
            Map resultMap = JSON.parseObject(json, Map.class);
            int code = (int) resultMap.get("code");
            if (200 == code) {
                log.info("========================== 退款成功,修改订单 ==========================");
                //退款成功,修改订单状态为退款
                orderRefund(goodsOrder, uOrder);
            }
        }
    }


    /**
     * 订单退款
     *
     * @param goodsOrder
     */
    public void orderRefund(GoodsOrder goodsOrder, UOrder uOrder) {
        goodsOrder.setOrderState(9);
        goodsOrder.setIfPay(3);
        goodsOrder.updateById();
        List<GoodsOrderDetail> goodsOrderDetails = new LambdaQueryChainWrapper<>(goodsOrderDetailMapper)
                .eq(GoodsOrderDetail::getOrderId, goodsOrder.getId())
                .list();
        for (GoodsOrderDetail goodsOrderDetail : goodsOrderDetails) {
            goodsOrderDetail.setOrderState(9);
            goodsOrderDetail.setIfPay(3);
            goodsOrderDetail.updateById();
        }
        //退款的金额
        BigDecimal total = goodsOrder.getTotalAmount().multiply(BigDecimal.valueOf(100));
        long totalAmount = total.longValue();
        if (totalAmount == uOrder.getTotalFee()) {
            uOrder.setPayStatus("2");
        } else {
            //修改订单金额
            uOrder.setTotalFee(uOrder.getTotalFee() - totalAmount);
        }
        uOrder.updateById();
    }

}
