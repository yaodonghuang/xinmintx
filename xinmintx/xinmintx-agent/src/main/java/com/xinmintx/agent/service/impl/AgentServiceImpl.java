package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.common.ShareholderTeam;
import com.xinmintx.agent.mapper.MemberMapper;
import com.xinmintx.agent.mapper.MerchantMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.Member;
import com.xinmintx.agent.model.MerchantExample;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.model.UserExample;
import com.xinmintx.agent.service.AgentService;
import com.xinmintx.agent.util.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private com.xinmintx.agent.service.WechatNotifyService wechatNotifyService;//同步Member表角色

    @Override
    public int addAgent(User user) {
        //设置角色
        user.setUserRole(5);
        if (userMapper.selectByPrimaryKey(user.getRecommender()).getUserRole() == 1) {
            Member member1 = wechatNotifyService.selectMember(user.getCellphone());
            if (member1 == null) {
                member1 = new Member();
                member1.setCellphone(user.getCellphone());//手机号
                member1.setUserId(user.getId());//代理表id
                member1.setIsReview(1);
                //代理升级金卡
                member1.setMemberType(3);
                //TODO 礼包领取
                member1.setGiftStart(1);
                wechatNotifyService.addMember(member1);
            } else {
                //卡等级判断
                if (member1.getMemberType() < 3) {
                    member1.setMemberType(3);
                }
                //TODO 礼包领取
                member1.setGiftStart(1);
                memberMapper.updateByPrimaryKeySelective(member1);
            }
            user.setStatus(1);
            user.setIsReview(1);
        } else {
            user.setStatus(2);
            user.setIsReview(0);
        }
        //判断是否支付
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andCellphoneEqualTo(user.getCellphone());
        criteria.andStatusNotEqualTo(1);
        criteria.andIsReviewNotEqualTo(1);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0) {
            user.setId(users.get(0).getId());
            try {
                userMapper.updateByPrimaryKeySelective(user);
                return user.getId();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            try {
                userMapper.insertSelective(user);
                return user.getId();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    @Override
    public List<User> selectAgentsByUserid(int userId) {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserRoleIn(list);
        criteria.andRecommenderEqualTo(userId);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        return userMapper.selectByExample(example);
    }

    /**
     * 查询合伙人代理团队（非直推人数）
     *
     * @param id
     * @return
     */
    @Override
    public List<ShareholderTeam> partnerTeamList(Integer id) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPartnerIdEqualTo(id);
        criteria.andRecommenderNotEqualTo(id);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        List<User> users = userMapper.selectByExample(userExample);
        List<ShareholderTeam> list = new ArrayList<>();
        for (User user : users) {
            ShareholderTeam shareholderTeam = new ShareholderTeam();
            shareholderTeam.setUser(user);
            //头像
            shareholderTeam.setAvatar(user.getAvatar());
            //查询推荐人名字
            User user1 = userMapper.selectByPrimaryKey(user.getRecommender());
            if(user1 != null){
                String name = user1.getName();
                shareholderTeam.setDirectName(name);
            }
            //查询人数
            UserExample agencyExample = new UserExample();
            UserExample.Criteria agencyCriteria = agencyExample.createCriteria();
            agencyCriteria.andUserRoleEqualTo(5);
            agencyCriteria.andRecommenderEqualTo(user.getRecommender());
            int agencySize = userMapper.selectByExample(agencyExample).size();
            shareholderTeam.setAgencyNum(agencySize);
            //推荐商户类型
            MerchantExample merchantExample = new MerchantExample();
            MerchantExample.Criteria merchantCriteria = merchantExample.createCriteria();
            merchantCriteria.andRecommenderEqualTo(user.getId());
            int merchantNum = merchantMapper.selectByExample(merchantExample).size();
            shareholderTeam.setMerchantNum(merchantNum);
            //合伙人
            UserExample partnerExample = new UserExample();
            UserExample.Criteria partnerCriteria = partnerExample.createCriteria();
            partnerCriteria.andUserRoleEqualTo(2);
            partnerCriteria.andRecommenderEqualTo(user.getRecommender());
            int partnerNum = userMapper.selectByExample(partnerExample).size();
            shareholderTeam.setPartnerNum(partnerNum);
            shareholderTeam.setTeamNum(0);
            //放入集合
            list.add(shareholderTeam);
        }
        Collections.sort(list);
        return list;
    }


}
