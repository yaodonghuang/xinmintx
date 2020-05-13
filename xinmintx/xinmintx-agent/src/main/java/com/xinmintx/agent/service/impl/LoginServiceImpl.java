package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.*;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.modelDTO.NumberMedol;
import com.xinmintx.agent.service.LoginService;
import com.xinmintx.agent.service.PickupService;
import com.xinmintx.agent.util.SmsUtil;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import java.util.List;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Jedis jedis;
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private PickupService pickupService;

    @Autowired
    private AgentRecommendsMapper agentRecommendsMapper;
    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @Override
    public String sendcode(String phone) {
        //1 查数据库
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andCellphoneEqualTo(phone);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0) {

            //2 生成6位随机数
            final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
            //存入缓存
            jedis.set(phone, code);
            System.out.println("phone=" + phone + ";msCode=" + code);
            //30分钟过期
            jedis.expire(phone, 1800);
            try {
                SendSmsResponse response = smsUtil.sendSms(phone, "SMS_176440093", "惠商", "{\"code\":\"" + code + "\"}");
                System.out.println("msUtil="+ JSON.toJSONString(response));
                return response.getCode();
            } catch (ClientException e) {
                e.printStackTrace();
            }
        } else {
            return "500";
        }
        return "400";
    }

    @Override
    public User selectByPhone(String phone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andCellphoneEqualTo(phone);
        return userMapper.selectByExample(example).get(0);
    }

    @Override
    public NumberMedol selectNumberByUserid(Integer userId) {
        NumberMedol numberMedol = new NumberMedol();
        numberMedol.setPartnerNumber(selectNumberByUserid(userId, 2));
        numberMedol.setCompanyNumber(selectNumberByUserid(userId, 3));
        numberMedol.setAgentNumber(selectNumberByUserid(userId, 5));
        numberMedol.setStaffNumber(selectNumberByUserid(userId, 6));
        numberMedol.setTeamNumber(selectNumberByshareholderId(userId).size());//高级合伙人团队人数
        numberMedol.setAgentTeamNumber(selectNumberByPartnerId(userId));//合伙人 代理团队数量
        MerchantExample merchantExample = new MerchantExample();
        MerchantExample.Criteria merchantCriteria = merchantExample.createCriteria();
        merchantCriteria.andIsReviewEqualTo(1);
        merchantCriteria.andRecommenderEqualTo(userId);
        int merchantNumber = (int) merchantMapper.countByExample(merchantExample);
        numberMedol.setMerchantNumber(merchantNumber);

        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria memberExampleCriteria = memberExample.createCriteria();
        memberExampleCriteria.andIsReviewEqualTo(1);
        memberExampleCriteria.andRecommenderEqualTo(userId);
        memberExampleCriteria.andMemberTypeEqualTo(2);
        int silverNumber = (int) memberMapper.countByExample(memberExample);
        numberMedol.setSilverNumber(silverNumber);
        //提货点人数
        int size = pickupService.selectpickupByUserid(userId).size();
        numberMedol.setPickUpNumber(size);
        return numberMedol;
    }

    /**
     * 根据登陆合伙人id查询非直推代理人数
     * @return
     */
    private Integer selectNumberByPartnerId(Integer id) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPartnerIdEqualTo(id);
        criteria.andRecommenderNotEqualTo(id);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        List<User> users = userMapper.selectByExample(userExample);
        return users.size();
    }

    /**
     * 根据登陆高级合伙人id查询shareholderId
     * @param userId
     * @return
     */
    private List<User> selectNumberByshareholderId(Integer userId) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andShareholderIdEqualTo(userId);
        criteria.andRecommenderNotEqualTo(userId);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

    /**
     *查询登陆者的 推荐过的每个角色的数量
     * @param userId
     * @param userRole
     * @return
     */
    public int selectNumberByUserid(Integer userId, int userRole) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andRecommenderEqualTo(userId);
        criteria.andUserRoleEqualTo(userRole);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        return (int) userMapper.countByExample(userExample);
    }

    @Override
    public void saveOpenId(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public double selectBalanceByUserid(Integer userId) {
        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria criteria = userAccountExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserAccount> userAccounts = userAccountMapper.selectByExample(userAccountExample);
        if (userAccounts.size() < 1) {
            return 0;
        }
        UserAccount userAccount = userAccounts.get(0);
        return userAccount.getMoney();
    }

    @Override
    public User selectUserByOpenid(String openid) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openid);
        criteria.andStatusEqualTo(1);
        criteria.andIsReviewEqualTo(1);
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void promotedPartner(User user) {
        //推荐代理是否超过5人升级合伙人
        User i = addressMapper.selectId(user.getId());
        AgentRecommendsExample agentRecommendsExample = new AgentRecommendsExample();
        List<AgentRecommends> agentRecommends = agentRecommendsMapper.selectByExample(agentRecommendsExample);
        if (i.getId() >= agentRecommends.get(0).getNumber()) {
            user.setUserRole(2);
            user.setStartAgentPartner(1);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }
}
