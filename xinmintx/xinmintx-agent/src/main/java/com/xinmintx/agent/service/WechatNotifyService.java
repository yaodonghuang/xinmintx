package com.xinmintx.agent.service;


import com.xinmintx.agent.mchpay.MchPayNotify;
import com.xinmintx.agent.mchpay.WechatResult;
import com.xinmintx.agent.model.Member;

/**
 * Created by qiuyue on 7/3/16.
 */
public interface WechatNotifyService {

    public WechatResult wechatNotify(MchPayNotify mchPayNotify);

    public WechatResult wechatMerchantPayNotify(MchPayNotify mchPayNotify);

    public Member selectMember(String cellPhone);

    public void addMember(Member member);

}
