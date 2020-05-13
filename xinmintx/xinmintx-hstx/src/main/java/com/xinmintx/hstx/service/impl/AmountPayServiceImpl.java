package com.xinmintx.hstx.service.impl;

import com.xinmintx.hstx.configuration.pay.PayConfig;
import com.xinmintx.hstx.pojo.bo.WechatPayBo;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberAmountLog;
import com.xinmintx.hstx.pojo.po.UOrder;
import com.xinmintx.hstx.pojo.vo.PayNotify;
import com.xinmintx.hstx.service.AmountPayService;
import com.xinmintx.hstx.service.PayNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/3/9 0009
 * @time: 上午 9:39
 * @Description:
 */
@Service
public class AmountPayServiceImpl implements AmountPayService {

    @Autowired
    private PayNotifyService payNotifyService;

    /**
     * 余额支付
     *
     * @param wechatPayBo
     * @return
     */
    @Override
    public Map<String, Object> createOrder(WechatPayBo wechatPayBo, Member member) {
        Map<String, Object> resultMap = new HashMap<>();
        UOrder uOrder = new UOrder();
        uOrder.setUserId(wechatPayBo.getUserId());
        uOrder.setOrderNo(wechatPayBo.getOrderId());
        uOrder.setGoodsDesc(wechatPayBo.getPrdDesc());
        uOrder.setTotalFee(wechatPayBo.getOrderAmt());
        uOrder.setPayType("4");
        uOrder.setPayStatus("0");
        uOrder.setCreateTime(new Date());
        uOrder.insert();
        //扣除余额
        BigDecimal orderAmt = BigDecimal.valueOf(wechatPayBo.getOrderAmt()).divide(BigDecimal.valueOf(100));
        member.setCashAmount(member.getCashAmount().subtract(orderAmt));
        member.updateById();
        MemberAmountLog amountLog = new MemberAmountLog();
        amountLog.setMemberId(member.getId().longValue());
        amountLog.setType("2");
        amountLog.setPrice(orderAmt.multiply(BigDecimal.valueOf(-1)));
        amountLog.setBalance(member.getCashAmount());
        amountLog.setRemark(wechatPayBo.getPrdDesc());
        amountLog.setSource(6);
        amountLog.insert();
        resultMap.put("order_id", uOrder.getId());
        resultMap.put("order_no", uOrder.getOrderNo());
        resultMap.put("code", "SUCCESS");
        resultMap.remove("packages");
        return resultMap;
    }

    /**
     * 余额支付回调
     */
    @Async
    @Override
    public void amountNotify(WechatPayBo wechatPayBo) {
        int type = wechatPayBo.getType();
        PayNotify payNotify = new PayNotify();
        payNotify.setOrderId(wechatPayBo.getOrderId());
        payNotify.setSettleDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        payNotify.setCompleteDate(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        payNotify.setStatus("1");
        payNotify.setOrderAmt(String.valueOf(wechatPayBo.getOrderAmt()));
        payNotify.setAttach(wechatPayBo.getAttach());
        switch (type) {
            case 1:
                payNotifyService.eCardNotify(payNotify);
                break;
            case 2:
                payNotifyService.goldCardPayNotify(payNotify);
                break;
            case 3:
                payNotifyService.cardRenewNotify(payNotify);
                break;
            case 4:
                payNotifyService.makeCardNotify(payNotify);
                break;
            case 5:
                payNotifyService.wechatGoodsPayNotify(payNotify);
                break;
            case 6:
                payNotifyService.wechatMerchantPayNotify(payNotify);
                break;
            case 7:
                payNotifyService.ptPayNotify(payNotify);
                break;
            default:
                break;
        }
    }
}
