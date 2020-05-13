package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.Member;

import java.util.List;

/**
 * 加入打卡签到表
 * @author  sw 2019/11/20
 */
public interface MemberCheckInService {


    int findMemberCheckInId (int memberId,int sysCardId);

    int insertCheck (int memberId,int sysCardId);

    int conutCard(int memberId);

    List<Object> findDate(Integer id,Integer sysCardId);

    Integer findMemberIdById(Integer id);

    Member findMemberIdByCheckInId(Integer dakaId);
}
