package com.xinmintx.hstx.scheduled;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinmintx.hstx.configuration.pay.PayConfig;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.entity.PtRedisBean;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.util.DateUtil;
import com.xinmintx.hstx.util.httpclient.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/18 0018
 * @time: 下午 21:56
 * @Description: 拼团定时任务
 */
@Slf4j
@Component
@Transactional
public class PtScheduled {

    @Autowired
    private UOrderMapper uOrderMapper;
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;
    @Autowired
    private GoodPtcodeMapper goodPtcodeMapper;
    @Autowired
    private GoodPtcodeInfoMapper goodPtcodeInfoMapper;
    @Autowired
    private GoodsOrderDetailMapper goodsOrderDetailMapper;
    @Autowired
    private PayConfig payConfig;


    @Autowired
    private Jedis jedis;

    /**
     * 拼团定时任务,1分钟执行一次
     */
    @Scheduled(cron = "0 1/1 * * * ?")
//    @Scheduled(cron = "1/5 * * * * ?")
    public void selectPtGroup() {
        //扫描redis 拼团未成功的信息
        List<String> hstxpt = jedis.lrange("hstxpt", 0, -1);
        if (hstxpt.size() < 1) {
            return;
        }
        for (int i = 0; i < hstxpt.size(); i++) {
            String json = hstxpt.get(i);
            PtRedisBean bean = JSON.parseObject(json, PtRedisBean.class);
            //筛选出拼团到期时间超过当前时间的记录
            Date endTime = bean.getEndTime();
            int compare = DateUtil.compareDateWithNow(endTime);
            if (compare < 0) {
                //进行退款
                groupFail(bean);
                //移除redis信息
                jedis.lrem("hstxpt", i, json);
            }
        }
    }

    /**
     * 拼团失败
     *
     * @param bean
     */
    @Async
    public void groupFail(PtRedisBean bean) {
        List<GoodsOrder> goodsOrders = getGoodsOrders(bean);
        if (goodsOrders == null || goodsOrders.size() < 1) {
            return;
        }
        //修改拼团信息为失败
        updatePtOrderFail(bean);
        for (GoodsOrder goodsOrder : goodsOrders) {
            //退款
            log.info("=================拼团失败 发起退款 goodsOrderId:{}=================", goodsOrder.getId());
            UOrder uOrder = uOrderMapper.selectById(goodsOrder.getUOrderId());
            Map<String,String> map = new HashMap<>();
            map.put("oriOrderId", uOrder.getOrderNo());
            map.put("orderAmt", String.valueOf(uOrder.getTotalFee()));
            String json = HttpClientUtil.doPost(payConfig.getRefundUrl(), map);
            if (StringUtils.isNotBlank(json)) {
                Map resultMap = JSON.parseObject(json, Map.class);
                int code = (int) resultMap.get("code");
                if (200 == code) {
                    //退款成功
                    //修改支付订单为已退款
                    updateUOrderFail(uOrder);

                }
            }
        }
    }

    /**
     * 修改支付订单为已退款
     *
     * @param uOrder
     */
    @Async
    public void updateUOrderFail(UOrder uOrder) {
        QueryWrapper<GoodsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_order_id",uOrder.getId());
        GoodsOrder goodsOrder = goodsOrderMapper.selectOne(queryWrapper);
        goodsOrder.setOrderState(8);
        goodsOrder.setIfPay(3);
        goodsOrder.updateById();
        Map<String,Object> map = new HashMap<>();
        map.put("order_id",goodsOrder.getId());
        List<GoodsOrderDetail> goodsOrderDetails = goodsOrderDetailMapper.selectByMap(map);
        for (GoodsOrderDetail goodsOrderDetail : goodsOrderDetails) {
            goodsOrderDetail.setOrderState(8);
            goodsOrderDetail.setIfPay(3);
            goodsOrderDetail.updateById();
        }
        uOrder.setPayStatus("2");
        uOrderMapper.updateById(uOrder);
    }

    /**
     * 修改拼团信息为失败,取消
     *
     * @param bean
     */
    @Async
    public void updatePtOrderFail(PtRedisBean bean) {
        GoodPtcode goodPtcode = new GoodPtcode();
        goodPtcode.setId(bean.getId());
        goodPtcode.setStatus(3);
        goodPtcodeMapper.updateById(goodPtcode);
    }

    /**
     * 获取订单列表
     *
     * @param bean
     * @return
     */
    public List<GoodsOrder> getGoodsOrders(PtRedisBean bean) {
        Map<String, Object> map = new HashMap<>();
        map.put("pid", bean.getId());
        map.put("is_join", 1);
        List<GoodPtcodeInfo> goodPtcodeInfos = goodPtcodeInfoMapper.selectByMap(map);
        if (goodPtcodeInfos.size() < 1) {
            return null;
        }
        List<Integer> goodsOrderIds = new ArrayList<>();
        for (GoodPtcodeInfo goodPtcodeInfo : goodPtcodeInfos) {
            goodsOrderIds.add(goodPtcodeInfo.getGoodsOrderId());
        }
        return goodsOrderMapper.selectBatchIds(goodsOrderIds);
    }
}
