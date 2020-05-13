package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.MemberMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.mapper.StaffProfitMapper;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private com.xinmintx.agent.service.WechatNotifyService wechatNotifyService;//同步Member表角色

    @Autowired
    private StaffProfitMapper staffProfitMapper;

    @Autowired
    private MemberMapper memberMapper;
    @Override
    public int addStaff(User user) {
        user.setUserRole(6);
        user.setStatus(1);
        user.setIsReview(1);
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andCellphoneEqualTo(user.getCellphone());
        userExampleCriteria.andStatusNotEqualTo(1);
        userExampleCriteria.andIsReviewNotEqualTo(1);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() > 0){
            //未审核和未付款账号修改为员工
            int id = userList.get(0).getId();
            user.setId(id);
            try {
                try {
                    userMapper.updateByPrimaryKeySelective(user);
                    return id;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }else{
            //新增员工
            try {
                userMapper.insertSelective(user);
                Member member1 = wechatNotifyService.selectMember(user.getCellphone());
                if( member1 == null){
                    member1 = new Member();
                    member1.setCellphone(user.getCellphone());//手机号
                    member1.setUserId(user.getId());//代理表id
                    member1.setIsReview(1);
                    wechatNotifyService.addMember(member1);
                }
                return user.getId();
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    @Override
    public List<User> selectStaffByUserid(int userId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserRoleEqualTo(6);
        criteria.andRecommenderEqualTo(userId);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        return userMapper.selectByExample(example);
    }

    @Override
    public int updateBasic(int userId, double money) {
        StaffProfitExample example = new StaffProfitExample();
        StaffProfitExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andMerchantTypeEqualTo(5);
        List<StaffProfit> staffProfits = staffProfitMapper.selectByExample(example);
        StaffProfit staffProfit = null;
        if (staffProfits.size() > 0) {
            staffProfit = staffProfits.get(0);
            staffProfit.setProfit(money);
            return staffProfitMapper.updateByPrimaryKeySelective(staffProfit);
        } else {
            staffProfit = new StaffProfit();
            staffProfit.setUserId(userId);
            staffProfit.setProfit(money);
            staffProfit.setMerchantType(5);
            return staffProfitMapper.insert(staffProfit);
        }
    }

    @Override
    public int updateGold(int userId, double money, double commission) {
        commission = commission / 100;
        StaffProfitExample example = new StaffProfitExample();
        StaffProfitExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andMerchantTypeEqualTo(4);
        List<StaffProfit> staffProfits = staffProfitMapper.selectByExample(example);
        StaffProfit staffProfit = null;
        if (staffProfits.size() > 0) {
            staffProfit = staffProfits.get(0);
            staffProfit.setProfit(money);
            staffProfit.setPercent(commission);
            return staffProfitMapper.updateByPrimaryKeySelective(staffProfit);
        } else {
            staffProfit = new StaffProfit();
            staffProfit.setUserId(userId);
            staffProfit.setProfit(money);
            staffProfit.setMerchantType(4);
            staffProfit.setPercent(commission);
            return staffProfitMapper.insert(staffProfit);
        }
    }

    @Override
    public StaffProfit selectStaffProfit(int userId, int i) {
        StaffProfitExample example = new StaffProfitExample();
        StaffProfitExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andMerchantTypeEqualTo(i);
        List<StaffProfit> staffProfits = staffProfitMapper.selectByExample(example);
        StaffProfit staffProfit = null;
        if (staffProfits.size() > 0){
            staffProfit = staffProfits.get(0);
            if (i == 2){
                staffProfit.setPercent(staffProfit.getPercent() * 100);
            }
            return staffProfit;
        }else{
            staffProfit = new StaffProfit();
            staffProfit.setProfit(0.0);
            staffProfit.setPercent(0.0);
        }
        return staffProfit;
    }
}
