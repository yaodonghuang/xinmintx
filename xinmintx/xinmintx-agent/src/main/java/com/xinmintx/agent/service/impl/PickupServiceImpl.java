package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.Member;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.model.UserExample;
import com.xinmintx.agent.service.PickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PickupServiceImpl implements PickupService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private com.xinmintx.agent.service.WechatNotifyService wechatNotifyService;//同步Member表角色

    @Override
    public int addPickUp(User user) {
        user.setUserRole(11);
        user.setStatus(1);
        user.setIsReview(1);
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andCellphoneEqualTo(user.getCellphone());
        userExampleCriteria.andStatusNotEqualTo(1);
        userExampleCriteria.andIsReviewNotEqualTo(1);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() > 0){
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
    public List<User> selectpickupByUserid(int userId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserRoleEqualTo(11);
        criteria.andRecommenderEqualTo(userId);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        return userMapper.selectByExample(example);
    }
}
