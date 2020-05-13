package com.xinmintx.hstx.service;


import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.MemberVo;

public interface MemberLoginService {
    MemberVo memberLogin(MemberVo memberVo);

    String sendcode(String phone);

    Member shareLogin(String phone, Integer memberCheckInId);
}
