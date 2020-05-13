package com.xinmintx.hstx.service;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberBankcard;
import com.xinmintx.hstx.pojo.vo.MemberBankcardVo;

import java.util.List;

public interface BankCardService {
    List<MemberBankcardVo> select(int id);

    MemberBankcard selectByCardByMemberId(String cardNumbers);

    int payPasswordverify(Member member, String pwd);
}
