package com.xinmintx.agent.service.impl;

import com.alibaba.fastjson.JSON;
import com.xinmintx.agent.common.WechatPayBo;
import com.xinmintx.agent.configuration.pay.PayConfig;
import com.xinmintx.agent.mapper.UOrderMapper;
import com.xinmintx.agent.model.UOrder;
import com.xinmintx.agent.model.UOrderExample;
import com.xinmintx.agent.service.WechatPayService;
import com.xinmintx.agent.util.httpclient.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/27 0027
 * @time: 上午 9:55
 * @Description:
 */
@Slf4j
@Service
public class WechatPayServiceImpl implements WechatPayService {

    @Value("${pay.url}")
    private String payUrl;

    @Autowired
    private UOrderMapper uOrderMapper;
    @Override
    public Map<String, Object> unifiedorder(WechatPayBo wechatPayBo) {
        Map<String, String> paramMap = new HashMap<>();
        // 订单号
        paramMap.put("orderId", wechatPayBo.getOrderId());
        // 订单金额
        paramMap.put("orderAmt", String.valueOf(wechatPayBo.getOrderAmt()));
        paramMap.put("openid", wechatPayBo.getOpenId());
        // 描述
        paramMap.put("prdDesc", wechatPayBo.getPrdDesc());
        // 回调地址
        paramMap.put("retUrl", wechatPayBo.getRetUrl());
        paramMap.put("flag", "1");
        String json = HttpClientUtil.doPost(payUrl, paramMap);
        Map map = JSON.parseObject(json, Map.class);
        int code = (int) map.get("code");
        if (code == 200 && map.containsKey("data")) {
            Map resultMap = JSON.parseObject(map.get("data").toString(), Map.class);
            UOrder uOrder = new UOrder();
            uOrder.setUserId(wechatPayBo.getUserId());
            uOrder.setOrderNo(wechatPayBo.getOrderId());
            String packages = resultMap.get("packages").toString();
            uOrder.setPrepayId(packages.substring(packages.indexOf("=") + 1));
            uOrder.setGoodsDesc(wechatPayBo.getPrdDesc());
            uOrder.setTotalFee(wechatPayBo.getOrderAmt());
            uOrder.setPayType("1");
            uOrder.setPayStatus("0");
            uOrder.setCreateTime(new Date());
            uOrderMapper.insertSelective(uOrder);
            resultMap.put("package", resultMap.get("packages"));
            resultMap.put("order_id", uOrder.getId());
            resultMap.put("order_no", uOrder.getOrderNo());
            resultMap.put("code", "SUCCESS");
            resultMap.remove("packages");
            log.info("======wechat======"+JSON.toJSONString(resultMap));
            return resultMap;
        }
        return null;
    }
}
