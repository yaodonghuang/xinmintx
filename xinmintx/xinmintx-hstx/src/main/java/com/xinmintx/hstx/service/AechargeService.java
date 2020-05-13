package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.MemberBindCardVO;


import java.util.Map;

public interface AechargeService {
    Map unifiedorder(Integer memberId, Member member, Integer money);

    /**
     * 绑定银行卡 ( 传入信息不能为空，VO已过滤，
     *
     * @param token 获取当前登录账户信息，用于获取 member 的 id 属性
     * @param memberBindCardVO 表单信息，存储部分银行卡信息
     * @return void （ 已做全局异常捕捉 + 运行异常处理 返回 json 格式 ResultCode
     */
    void memberBindCard(String token, MemberBindCardVO memberBindCardVO);
}
