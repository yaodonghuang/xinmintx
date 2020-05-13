package com.xinmintx.community.service.impl;

import com.xinmintx.community.mapper.CommunityDeputyMapper;
import com.xinmintx.community.mapper.CommunityExtMapper;
import com.xinmintx.community.mapper.MemberMapper;
import com.xinmintx.community.model.CommunityExt;
import com.xinmintx.community.model.CommunityDeputy;
import com.xinmintx.community.model.CommunityDeputyExample;
import com.xinmintx.community.model.Member;
import com.xinmintx.community.service.CommunityExtService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName:.CommunityServiceImpl
 * @author:chf
 * @Date:2020/2/10：15:38
 * @developerKits： win 10     jdk1.8
 */
@Service
public class CommunityExtServiceImpl implements CommunityExtService {

    @Resource
    private CommunityExtMapper communityExtMapper;

    @Resource
    private CommunityDeputyMapper communityDeputyMapper;

    @Resource
    private MemberMapper memberMapper;

    @Override
    public int addPickupLocation(CommunityExt communityExt) {
        CommunityExt com = communityExtMapper.selectByPrimaryKey(communityExt.getId());
        if (com.getPhoneNumber()!=null){
            return 0;
        }
        return communityExtMapper.updateByPrimaryKeySelective(communityExt);
    }

    @Override
    public int updatePickupLocation(CommunityExt communityExt) {
        return communityExtMapper.updateByPrimaryKeySelective(communityExt);
    }

    @Override
    public int addAssistInManaging(CommunityDeputy communityDeputies) {
        Member member = memberMapper.selectByPhone(communityDeputies.getPhone());
        if (member == null) {
            return 2;
        }

        CommunityDeputy list = communityDeputyMapper.selectByPrimaryKey(communityDeputies.getCommunityId());
        if (list != null) {
            return 0;
        }
        communityDeputies.setCreateTime(new Date());
        communityDeputyMapper.insertSelective(communityDeputies);
        return 1;
    }

    @Override
    public int update(CommunityDeputy communityDeputies) {
        Member member = memberMapper.selectByPhone(communityDeputies.getPhone());
        if (member == null) {
            return 0;
        }

        int i = 0;
        i = communityDeputyMapper.updateByPrimary(communityDeputies);
        return i;
    }

    @Override
    public CommunityDeputy queryAssistInManaging(Integer id) {
        return communityDeputyMapper.selectByPrimaryKey(id);
    }

    @Override
    public CommunityExt queryPickupLocation(Integer id) {
        return communityExtMapper.selectByPrimaryKey(id.longValue());
    }
}
