package com.xinmintx.hstx.scheduled;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.configuration.pay.PayConfig;
import com.xinmintx.hstx.mapper.GoodsOrderDetailMapper;
import com.xinmintx.hstx.mapper.GoodsOrderMapper;
import com.xinmintx.hstx.pojo.po.GoodsOrder;
import com.xinmintx.hstx.pojo.po.GoodsOrderDetail;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.util.httpclient.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 每日查询已发货订单是否到期(10天),到期就变为确认收货
 */
@Slf4j
@Component
public class GoodsOrderScheduled {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;
    @Autowired
    private GoodsOrderDetailMapper goodsOrderDetailMapper;
    @Autowired
    private PayConfig payConfig;

    /**
     * 每天00:00更新确认收货状态
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkGoodsOrder() {
        //主订单表状态只能是待收货已付款
        List<GoodsOrder> list = new LambdaQueryChainWrapper<>(goodsOrderMapper)
                .eq(GoodsOrder::getOrderState, 3)
                .eq(GoodsOrder::getUserDelete, 0).list();
        if (list != null && list.size() > 0) {
            for (GoodsOrder goodsOrder : list) {

                //订单发货时间
                Date beginDate = goodsOrder.getSendDate();
                //现在时间
                Date endDate = new Date();

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String beginDateStr = format.format(beginDate);
                String endDateStr = format.format(endDate);

                //查看是否延长收货
                Integer ifDelayed = goodsOrder.getIfDelayed();
                long day = 0;

                try {
                    beginDate = format.parse(beginDateStr);
                    endDate = format.parse(endDateStr);
                    //计算发货天数
                    day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
                    //如果用户延长收货,就减三天
                    if (ifDelayed == 1) {
                        day -= 3;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //如果大于等于10天了就自动改为已收货状态
                if (day >= 10) {
                    Map<String, String> map = new HashMap<>();
                    map.put("orderId", String.valueOf(goodsOrder.getId()));
                    String json = HttpClientUtil.doPost(payConfig.getConfirmReceiptUrl(), map);
                    Map resultMap = JSONObject.parseObject(json, Map.class);
                    if ((int) resultMap.get("code") != 200) {
                        break;
                    }
                    goodsOrder.setOrderState(5);
                    goodsOrder.setUpdateTime(new Date());
                    goodsOrderMapper.updateById(goodsOrder);

                    //把子订单也改为已收货状态
                    List<GoodsOrderDetail> goodsOrderDetailList = new LambdaQueryChainWrapper<>(goodsOrderDetailMapper).eq(GoodsOrderDetail::getOrderId, goodsOrder.getId()).list();
                    if (goodsOrderDetailList != null && goodsOrderDetailList.size() > 0) {
                        for (GoodsOrderDetail goodsOrderDetail : goodsOrderDetailList) {
                            //只有是待收货状态的才能改为已收货
                            if (goodsOrderDetail.getOrderState() == 3) {
                                goodsOrderDetail.setOrderState(5);
                                goodsOrderDetail.setUpdateTime(new Date());
                                goodsOrderDetail.updateById();
                            }
                        }
                    }
                }
            }
        }
    }
}
