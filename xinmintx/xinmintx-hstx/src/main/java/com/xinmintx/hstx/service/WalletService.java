package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberBankcard;
import com.xinmintx.hstx.pojo.vo.ResultCode;

public interface WalletService {
    ResultCode factoryGetMoney(MemberBankcard bankCard, String money, Member member);
}
