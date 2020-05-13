package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.common.ShareholderTeam;
import com.xinmintx.agent.mapper.AccountExpirationMapper;
import com.xinmintx.agent.mapper.MerchantMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.PartnerService;
import com.xinmintx.agent.service.ShareholderService;
import com.xinmintx.agent.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class    ShareholderServiceImpl implements ShareholderService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private PartnerService partnerService;

    @Autowired
    private AccountExpirationMapper expirationMapper;

    /**
     * 查询全部高级合伙人
     *
     * @param user
     * @return
     */
    @Override
    public List<ShareholderTeam> selectShareholder(User user) {
        //查询全部高级合伙人
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andUserRoleEqualTo(7);
        userExampleCriteria.andStatusEqualTo(1);
        userExampleCriteria.andIsReviewEqualTo(1);
        List<User> users = userMapper.selectByExample(userExample);
        //直推人数
        List<Integer> roles = new ArrayList<>();
        roles.add(2);
        roles.add(7);
        List<ShareholderTeam> list = new ArrayList<>();
        for (User holderUser: users) {
            ShareholderTeam shareholderTeam = new ShareholderTeam();
            shareholderTeam.setUser(holderUser);
            //查询直推的合伙人或者高级合伙人(原本是合伙人,后升级成功过)人数
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andRecommenderEqualTo(holderUser.getId());
            criteria.andUserRoleIn(roles);
            criteria.andStatusEqualTo(1);
            criteria.andIsReviewEqualTo(1);
            int size = userMapper.selectByExample(example).size();
            shareholderTeam.setDirectNum(size);
            //查询团队人数,排除直推人数
            UserExample teamExample = new UserExample();
            UserExample.Criteria teamCriteria = teamExample.createCriteria();
            teamCriteria.andRecommenderNotEqualTo(holderUser.getId());
            teamCriteria.andShareholderIdEqualTo(holderUser.getId());
            teamCriteria.andStatusEqualTo(1);
            teamCriteria.andIsReviewEqualTo(1);
            int teamSize = userMapper.selectByExample(teamExample).size();
            shareholderTeam.setTeamNum(teamSize);
            list.add(shareholderTeam);
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public User selectPartner(String cellphone) {
        return partnerService.selectPartner(cellphone);
    }

    @Override
    public boolean updateUserRole(String cellphone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andCellphoneEqualTo(cellphone);
        User user = userMapper.selectByExample(userExample).get(0);
        //查询是否有到期日期
        AccountExpirationExample expirationExample = new AccountExpirationExample();
        AccountExpirationExample.Criteria exampleCriteria = expirationExample.createCriteria();
        exampleCriteria.andUserRoleEqualTo(7);
        AccountExpiration expiration = expirationMapper.selectByExample(expirationExample).get(0);
        if(expiration.getDayTime() != null && DateUtil.compareDateWithNow(expiration.getDayTime()) > -1){
            user.setEndTime(expiration.getDayTime());
        }else if (expiration.getDaysNo() != null && expiration.getDaysNo() > 0) {
            Date endTime = DateUtil.getDaysLaterDate(expiration.getDaysNo());
            user.setEndTime(endTime);
        }
        user.setUserRole(7);
        user.setShareholderId(null);//升级高级合伙人 所属高级合伙人团队id 剔除
        int i = userMapper.updateByPrimaryKey(user);
        return i > 0;
    }

    @Override
    public List<ShareholderTeam> selectTeam(Integer id) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andShareholderIdEqualTo(id);
        userExampleCriteria.andRecommenderNotEqualTo(id);
        userExampleCriteria.andStatusEqualTo(1);
        userExampleCriteria.andIsReviewEqualTo(1);
        List<User> users = userMapper.selectByExample(userExample);
        //直推人数
        List<ShareholderTeam> list = new ArrayList<>();
        for (User user : users) {
            ShareholderTeam shareholderTeam = new ShareholderTeam();
            shareholderTeam.setUser(user);
            //头像
            shareholderTeam.setAvatar(user.getAvatar());
            //查询推荐人名字
            String name = userMapper.selectByPrimaryKey(user.getRecommender()).getName();
            shareholderTeam.setDirectName(name);
            //查询推荐代理人数
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
