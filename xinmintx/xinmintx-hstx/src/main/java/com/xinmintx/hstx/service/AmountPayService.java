package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.bo.WechatPayBo;
import com.xinmintx.hstx.pojo.po.Member;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/3/9 0009
 * @time: 上午 9:39
 * @Description:
 */
public interface AmountPayService {

    /**
     * 余额支付
     *
     * @param wechatPayBo
     * @return
     */
    Map<String, Object> createOrder(WechatPayBo wechatPayBo, Member member);

    /**
     * 余额支付回调
     */
    void amountNotify(WechatPayBo wechatPayBo);
}
