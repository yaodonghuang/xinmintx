package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.MerchantVo;
import com.xinmintx.hstx.pojo.po.Merchant;

import java.util.Map;


public interface MerchantService {


    Merchant addMerchant(MerchantVo merchantVo);

    Map createOrder(Merchant merchant, Member member);

}
