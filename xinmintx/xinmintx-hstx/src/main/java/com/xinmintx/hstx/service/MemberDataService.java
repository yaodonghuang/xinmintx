package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/24 0024
 * @time: 下午 13:23
 * @Description:
 */
public interface MemberDataService {

    List<MemberVo> getRelationList(Integer memberId);
}
