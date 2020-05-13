package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.ResultCode;

public interface MemberbonusInfoService {
    ResultCode bonusList(Member member);

    ResultCode getEndTime();

    ResultCode previousList(Member member);
}
