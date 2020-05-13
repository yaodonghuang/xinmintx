package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.MemberMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.Member;
import com.xinmintx.agent.model.MemberExample;
import com.xinmintx.agent.service.SilverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SilverServiceImpl implements SilverService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addSilver(Member member) {
        if (userMapper.selectByPrimaryKey(member.getRecommender()).getUserRole() == 1){
            member.setIsReview(1);
        }else{
            member.setIsReview(0);
        }
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andIsReviewNotEqualTo(1);
        criteria.andCellphoneEqualTo(member.getCellphone());
        List<Member> silvers = memberMapper.selectByExample(memberExample);
        if(silvers.size() > 0){
            Integer id = silvers.get(0).getId();
            member.setId(id);
            try {
                memberMapper.updateByPrimaryKeySelective(member);
                return id;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }else {
            try {
                memberMapper.insertSelective(member);
                return member.getId();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

    }

    @Override
    public List<Member> selectSilverByUserid(int userId) {
        MemberExample example = new MemberExample();
        MemberExample.Criteria criteria = example.createCriteria();
        criteria.andRecommenderEqualTo(userId);
        criteria.andIsReviewEqualTo(1);
        return memberMapper.selectByExample(example);
    }
}
