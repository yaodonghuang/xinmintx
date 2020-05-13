package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.AccountExpirationMapper;
import com.xinmintx.agent.mapper.MemberMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.PartnerService;
import com.xinmintx.agent.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccountExpirationMapper expirationMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private com.xinmintx.agent.service.WechatNotifyService wechatNotifyService;//同步Member表角色

    @Override
    public int addPartner(User user) {
        user.setUserRole(2);
        //查询是否有到期日期
        AccountExpirationExample expirationExample = new AccountExpirationExample();
        AccountExpirationExample.Criteria exampleCriteria = expirationExample.createCriteria();
        exampleCriteria.andUserRoleEqualTo(2);
        AccountExpiration expiration = expirationMapper.selectByExample(expirationExample).get(0);
        if (expiration.getDayTime() != null && DateUtil.compareDateWithNow(expiration.getDayTime()) > -1) {
            user.setEndTime(expiration.getDayTime());
        } else if (expiration.getDaysNo() != null && expiration.getDaysNo() > 0) {
            Date endTime = DateUtil.getDaysLaterDate(expiration.getDaysNo());
            user.setEndTime(endTime);
        }
        //添加代理角色为合伙人
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andCellphoneEqualTo(user.getCellphone());
        criteria.andUserRoleEqualTo(5);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0) {
            user.setId(users.get(0).getId());
            if (userMapper.selectByPrimaryKey(user.getRecommender()).getUserRole() == 1) {
                Member member1 = wechatNotifyService.selectMember(user.getCellphone());
                if (member1 == null) {
                    member1 = new Member();
                    member1.setCellphone(user.getCellphone());//手机号
                    member1.setUserId(user.getId());//代理表id
                    member1.setIsReview(1);
                    member1.setMemberType(2);
                    member1.setGiftStart(1);
                    wechatNotifyService.addMember(member1);
                } else {
                    //卡等级判断
                    if (member1.getMemberType() < 2) {
                        member1.setMemberType(2);
                    }
                    member1.setGiftStart(1);
                    memberMapper.updateByPrimaryKeySelective(member1);
                }
                user.setStatus(1);
                user.setIsReview(1);
            } else {
                user.setStatus(2);
                user.setIsReview(0);
            }
            try {
                try {
                    userMapper.updateByPrimaryKey(user);
                    return user.getId();
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        if (userMapper.selectByPrimaryKey(user.getRecommender()).getUserRole() == 1) {
            Member member1 = wechatNotifyService.selectMember(user.getCellphone());
            if (member1 == null) {
                member1.setCellphone(user.getCellphone());//手机号
                member1.setUserId(user.getId());//代理表id
                member1.setIsReview(1);
                wechatNotifyService.addMember(member1);
            }
            user.setStatus(1);
            user.setIsReview(1);
        } else {
            user.setStatus(2);
            user.setIsReview(0);
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andCellphoneEqualTo(user.getCellphone());
        userExampleCriteria.andStatusNotEqualTo(1);
        userExampleCriteria.andIsReviewNotEqualTo(1);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() > 0) {
            //未审核和未付款账号修改为合伙人
            int id = userList.get(0).getId();
            user.setId(id);
            try {
                userMapper.updateByPrimaryKeySelective(user);
                return id;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            //新增合伙人
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
    public List<User> selectPartnerByUserid(int userId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserRoleEqualTo(2);
        criteria.andRecommenderEqualTo(userId);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        return userMapper.selectByExample(example);
    }

    @Override
    public User selectPartnerByCellphone(String cellphone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andCellphoneEqualTo(cellphone);
        criteria.andUserRoleEqualTo(5);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * 查询合伙人和高级合伙人
     *
     * @param cellphone
     * @return
     */
    @Override
    public User selectPartnerAndShareholder(String cellphone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andCellphoneEqualTo(cellphone);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(7);
        criteria.andUserRoleIn(list);
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * 查询合伙人
     *
     * @param cellphone
     * @return
     */
    @Override
    public User selectPartner(String cellphone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andCellphoneEqualTo(cellphone);
        criteria.andUserRoleEqualTo(2);
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

}
