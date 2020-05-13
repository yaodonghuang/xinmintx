package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.*;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/14 0014
 * @time: 下午 14:21
 * @Description: 分润逻辑
 */
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StaffProfitMapper staffProfitMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private RoleShareMapper shareMapper;

    @Autowired
    private UOrderMapper uOrderMapper;

    @Autowired
    private UserAccountRecordMapper recordMapper;

    /**
     * 创建角色实现分润逻辑
     *
     * @param user   提交人(提交人角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人))
     * @param roleId 需要创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,新民金卡)
     * @param uOrder 订单
     */
    @Override
    public void shareBenefit(User user, String roleId, UOrder uOrder) {
        int userRole = user.getUserRole();
        double totalFee = (double) uOrder.getTotalFee() / 100;
        if (userRole == 6) {
            //员工提交时为特殊情况,需要查询代理为员工设置的分润金额
            //1.获取员工提交的商户类型 4,黄金商户;5,普通商户
            int recommendRole = Integer.parseInt(roleId);
            //2.获取上级的抽水
            //2.1查询分润表,获取对应分润信息
            RoleShare roleShare = getRoleShare(userRole, 3, recommendRole);
            //2.2获取员工上级具体获取的分成
            double recommenderMoney = roleShare.getMoney();
            //3.获取给员工设置的抽水
            StaffProfitExample staffProfitExample = new StaffProfitExample();
            StaffProfitExample.Criteria staffProfitExampleCriteria = staffProfitExample.createCriteria();
            staffProfitExampleCriteria.andUserIdEqualTo(user.getId());
            staffProfitExampleCriteria.andMerchantTypeEqualTo(recommendRole);
            List<StaffProfit> staffProfits = staffProfitMapper.selectByExample(staffProfitExample);
            //获取员工的抽水金额
            Double staffMoney = 0.00;
            if (staffProfits.size() > 0) {
                staffMoney = staffProfits.get(0).getProfit();
            }
            //2.1获取上级对象
            User superior = getSuperior(user);
            //3.上级分成
            gainProfit(superior.getId(), recommenderMoney - staffMoney, uOrder.getId());
            //4.员工分成
            gainProfit(user.getId(), staffMoney, uOrder.getId());
            //如果上级不为boss,boss抽成
            if (superior.getUserRole() != 1) {
                //5.boss分成
                //5.1获取boss抽水金额
                roleShare = getRoleShare(1, 2, recommendRole);
                Double bossMoney = roleShare.getMoney();
                totalFee -= bossMoney;
                //5.2boss分成
                gainProfit(1, bossMoney, uOrder.getId());
            }
            //6.剩下金额为公司账户
            double remainMoney = totalFee - recommenderMoney;
            //6.1公司账户分成
            gainProfit(2, remainMoney, uOrder.getId());
        } else {
            //需要创建的对象角色
            int recommendRole = Integer.parseInt(roleId);
            //1.获取本人抽成
            RoleShare roleShare = getRoleShare(userRole, 1, recommendRole);
            Double selfMoney = roleShare.getMoney();
            totalFee -= selfMoney;
            //2.获取上级对象
            User superior = getSuperior(user);
            roleShare = getRoleShare(superior.getUserRole(), 2, recommendRole);
             //2.1获取上级享受分成
            Double superMoney = roleShare.getMoney();
            totalFee -= superMoney;
            //3.本人抽水
            gainProfit(user.getId(), selfMoney, uOrder.getId());
            //4.上级抽水
            gainProfit(superior.getId(), superMoney, uOrder.getId());

            //如果提交人为高级合伙人,提交的角色为合伙人,获取团队奖励
            if (userRole == 7 && recommendRole == 1) {
                roleShare = getRoleShare(7, 4, recommendRole);
                Double money = roleShare.getMoney();
                totalFee -= money;
                gainProfit(user.getId(), money, uOrder.getId());
            }

            //被提交人为代理,这个代理是学员,给他的讲师抽成
            if (recommendRole == 3) {
                //查询创建的代理的信息
                UserExample userExample = new UserExample();
                UserExample.Criteria userExampleCriteria = userExample.createCriteria();
                userExampleCriteria.andOrderIdEqualTo(uOrder.getId());
                User agent = userMapper.selectByExample(userExample).get(0);
                if (agent.getStudent() != null && agent.getStudent() == 1 && agent.getTeacherId()!= null) {
                    //这个代理是学员,给他的讲师抽成
                    //获取讲师的信息
                    User teacher = userMapper.selectByPrimaryKey(agent.getTeacherId());
                    int grade = teacher.getLecturerGrade() == null ? 1 : teacher.getLecturerGrade();
                    Integer teacherRole = 0;
                    switch (grade){
                        case 2:
                            teacherRole = 11;
                            break;
                        case 3:
                            teacherRole = 12;
                            break;
                        default:
                            teacherRole = 10;
                            break;
                    }
                    //讲师分成
                    roleShare = getRoleShare(teacherRole, 2, recommendRole);
                    Double teacherMoney = roleShare.getMoney();
                    totalFee -= teacherMoney;
                    gainProfit(teacher.getId(), teacherMoney, uOrder.getId());
                }
            }

            //如果提交人角色为合伙人 并且 提交的角色也为合伙人 并且 合伙人属于某个高级合伙人的情况 高级合伙人获取抽成
            if (userRole == 2 && recommendRole == 1 && user.getShareholderId() != null) {
                //获取上级高级合伙人抽成
                roleShare = getRoleShare(7, 4, recommendRole);
                Double shareholderMoney = roleShare.getMoney();
                totalFee -= shareholderMoney;
                //高级合伙人团队分成
                gainProfit(user.getShareholderId(), shareholderMoney, uOrder.getId());
            }
            //如果提交人为代理,且提交的角色为代理,且这个代理属于某个合伙人团队,该合伙人额外享受团队分成(合伙人团队人员提交)
            if (userRole == 5 && recommendRole == 3 && user.getPartnerId() != null) {
                roleShare = getRoleShare(2, 4, recommendRole);
                Double partnerMoney = roleShare.getMoney();
                totalFee -= partnerMoney;
                //合伙人团队分成
                gainProfit(user.getPartnerId(), partnerMoney, uOrder.getId());
            }
            //如果上级不为boss,boss抽成
            if (superior.getUserRole() != 1) {
                //5.boss分成
                //5.1获取boss抽水金额
                roleShare = getRoleShare(1, 2, recommendRole);
                Double bossMoney = roleShare.getMoney();
                totalFee -= bossMoney;
                //5.2boss分成
                gainProfit(1, bossMoney, uOrder.getId());
            }
            //6.剩下金额为公司账户
            double remainMoney = totalFee;
            //6.1公司账户分成
            gainProfit(2, remainMoney, uOrder.getId());
        }
    }

    /**
     * 按条件获取分润规则
     *
     * @param userRole      提交人角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人)
     * @param recommendType 分润类型(1,推荐获取分成;2,享受分成,3,上级获取分成)
     * @param recommendRole 需要创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,新民金卡)
     * @return
     */
    private RoleShare getRoleShare(int userRole, int recommendType, int recommendRole) {
        RoleShareExample example = new RoleShareExample();
        RoleShareExample.Criteria criteria = example.createCriteria();
        criteria.andUserRoleEqualTo(userRole);
        criteria.andRecommendTypeEqualTo(recommendType);
        criteria.andRecommendRoleEqualTo(recommendRole);
        return shareMapper.selectByExample(example).get(0);
    }

    /**
     * 获取上级对象
     *
     * @param user 需要获取上级的user
     * @return
     */
    private User getSuperior(User user) {
        if (user.getRecommender() == null) {
            return userMapper.selectByPrimaryKey(1);
        }
        //获取员工的上级
        return userMapper.selectByPrimaryKey(user.getRecommender());
    }

    /**
     * 获取抽水
     *
     * @param userId 要获取抽水的用户id
     * @param money  抽水金额
     */
    private void gainProfit(int userId, double money, Integer orderId) {
        if (money == 0) {
            return;
        }
        //获取账户
        UserAccountExample accountExample = new UserAccountExample();
        UserAccountExample.Criteria accountExampleCriteria = accountExample.createCriteria();
        accountExampleCriteria.andUserIdEqualTo(userId);
        List<UserAccount> userAccounts = userAccountMapper.selectByExample(accountExample);
        if (userAccounts.size() < 1) {
            //账户不存在,新建账号
            UserAccount userAccount = new UserAccount();
            userAccount.setUserId(userId);
            userAccount.setMoney(money);
            userAccountMapper.insertSelective(userAccount);
            //记录账户金额变动信息
            accountRecord(userId, orderId, userAccount.getId(), money);
        } else {
            UserAccount userAccount = userAccounts.get(0);
            userAccount.setMoney(userAccount.getMoney() + money);
            userAccountMapper.updateByPrimaryKeySelective(userAccount);
            //记录账户金额变动信息
            accountRecord(userId, orderId, userAccount.getId(), money);
        }
    }

    /**
     * 记录账户金额变化
     *
     * @param userId         用户id
     * @param orderId        订单id
     * @param userAcccountId 账户id
     * @param momey          金额
     */
    private void accountRecord(int userId, Integer orderId, int userAcccountId, double momey) {
        UOrder uOrder = uOrderMapper.selectByPrimaryKey(orderId);
        UserAccountRecord record = new UserAccountRecord();
        record.setDescription(uOrder.getGoodsDesc());
        record.setUserId(userId);
        record.setOrderId(orderId);
        record.setUserAccountId(userAcccountId);
        record.setMoneyChange(new BigDecimal(momey));
        recordMapper.insertSelective(record);
    }

    /**
     * 分公司获取奖励
     *
     * @param userId 合伙人id
     */
    @Override
    public void companyAward(int userId) {
        //获取合伙人
        User user = userMapper.selectByPrimaryKey(userId);
        RoleShare roleShare = getRoleShare(user.getUserRole(), 1, 2);
        Double money = roleShare.getMoney();
        gainProfit(userId, money, null);
    }
}
