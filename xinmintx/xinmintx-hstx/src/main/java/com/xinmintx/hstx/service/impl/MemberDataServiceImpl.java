package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.MemberCardInfoMapper;
import com.xinmintx.hstx.mapper.MemberMapper;
import com.xinmintx.hstx.mapper.MemberReferrerMapper;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberCardInfo;
import com.xinmintx.hstx.pojo.po.MemberReferrer;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.IMemberTreeService;
import com.xinmintx.hstx.service.MemberDataService;
import com.xinmintx.hstx.util.FieldUtils;
import com.xinmintx.hstx.util.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/24 0024
 * @time: 下午 13:23
 * @Description:
 */
@Service
public class MemberDataServiceImpl implements MemberDataService {
    @Autowired
    private MemberReferrerMapper memberReferrerMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberCardInfoMapper memberCardInfoMapper;


    /**
     * 获取用户下面的关联E卡金卡,列表
     *
     * @param memberId
     * @return
     */
    @Override
    public List<MemberVo> getRelationList(Integer memberId) {
        List<MemberReferrer> memberReferrers = new LambdaQueryChainWrapper<>(memberReferrerMapper)
                .eq(MemberReferrer::getReferrerId, memberId)
                .eq(MemberReferrer::getStatus, 1)
                .list();
        if (memberReferrers == null || memberReferrers.size() == 0) {
            return new ArrayList<>();
        }
        Set<Integer> ids = new HashSet<>();
        for (MemberReferrer memberReferrer : memberReferrers) {
            ids.add(memberReferrer.getMemberId());
        }
        if (ids.size() == 0) {
            return new ArrayList<>();
        }
        List<Member> members = new LambdaQueryChainWrapper<>(memberMapper)
                .in(Member::getId, ids)
                .in(Member::getMemberType, 1, 2)
                .ge(Member::getCardIndate, new Date())
                .list();
        if (members == null || members.size() == 0) {
            return new ArrayList<>();
        }
        List<MemberVo> memberVos = ListUtils.listTrans(members, MemberVo.class);
        for (MemberVo memberVo : memberVos) {
            if (memberVo.getCardId() != null) {
                MemberCardInfo info = memberCardInfoMapper.selectById(memberVo.getCardId());
                if (info != null && StringUtils.isNotBlank(info.getAddress())) {
                    memberVo.setAddress(info.getAddress());
                }
            }
        }
        return memberVos;
    }
}
