package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.configuration.pay.PayConfig;
import com.xinmintx.hstx.exception.BaseException;
import com.xinmintx.hstx.exception.errorCodeStants.ErrorCodeConStants;
import com.xinmintx.hstx.mapper.MemberBankcardMapper;
import com.xinmintx.hstx.mapper.MemberMapper;
import com.xinmintx.hstx.pojo.bo.WechatPayBo;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberBankcard;
import com.xinmintx.hstx.pojo.vo.MemberBindCardVO;
import com.xinmintx.hstx.service.AechargeService;
import com.xinmintx.hstx.service.WechatPayService;
import com.xinmintx.hstx.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class AechargeServiceImpl implements AechargeService {
    @Autowired
    private WechatPayService wechatPayService;
    @Autowired
    private PayConfig payConfig;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberBankcardMapper memberBankcardMapper;
    @Override
    public Map unifiedorder(Integer memberId, Member member, Integer money) {
        WechatPayBo wechatPayBo = new WechatPayBo();
        wechatPayBo.setUserId(memberId);
        wechatPayBo.setOrderId(String.valueOf(System.currentTimeMillis()));
        wechatPayBo.setPrdDesc("充值");
        wechatPayBo.setOrderAmt((long) (money * 100));
        wechatPayBo.setOpenId(member.getOpenid());
        wechatPayBo.setRetUrl(payConfig.getRechargeUrl());
        return wechatPayService.unifiedorder(wechatPayBo);
    }


    @Override
    public void memberBindCard(String token, MemberBindCardVO memberBindCardVO) {

        //获取 token 对应用户信息
        Member member = new LambdaQueryChainWrapper<>(memberMapper)
                .eq(Member::getToken, token)
                .one();
        //过滤 token 为空 或找不到 用户对象 id 属性
        if ( member == null || member.getId() == 0 ){
            throw new BaseException(ErrorCodeConStants.ABNORMAL_TOKEN);
        }

        //验证储存对象是否存在（银行卡号作为标识
        MemberBankcard bankCardTest = new LambdaQueryChainWrapper<>(memberBankcardMapper)
                .eq(MemberBankcard::getBankCard, memberBindCardVO.getBankCard())
                .one();
        if (bankCardTest!=null){
            throw new BaseException(ErrorCodeConStants.OBJECT_ALREADY_EXISTS);
        }

        //创建银行卡存储对象
        memberBindCardVO.setMemberId(member.getId());
        MemberBankcard memberBankcard = BeanUtil.copyProperties(memberBindCardVO,MemberBankcard.class,true);
        if (!memberBankcard.insertOrUpdate()){
            throw new BaseException(ErrorCodeConStants.ABNORMAL_SQL);
        }
    }



}
