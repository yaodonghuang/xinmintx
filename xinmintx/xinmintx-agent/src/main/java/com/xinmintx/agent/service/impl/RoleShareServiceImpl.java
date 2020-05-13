package com.xinmintx.agent.service.impl;


import com.xinmintx.agent.configuration.role.CreateRole;
import com.xinmintx.agent.configuration.role.FixedAccount;
import com.xinmintx.agent.configuration.role.RecommendType;
import com.xinmintx.agent.configuration.role.ReferrerRole;
import com.xinmintx.agent.mapper.*;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.RoleShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/1/21 0021
 * @time: 下午 20:52
 * @Description:
 */
@Service
@Transactional
public class RoleShareServiceImpl implements RoleShareService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StaffProfitMapper staffProfitMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private RoleShareMapper roleShareMapper;
    @Autowired
    private UserAccountRecordMapper userAccountRecordMapper;

    /**
     * 提交人
     */
    private User user;
    /**
     * 订单
     */
    private UOrder uOrder;
    /**
     * 被创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,新民金卡)
     */
    private Integer createRole;
    /**
     * 总金额
     */
    private double totalFee;

    /**
     * 创建角色实现分润逻辑
     *
     * @param user       提交人(提交人角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人))
     * @param createRole 需要创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,新民金卡)
     * @param uOrder     订单
     */
    @Override
    public void shareProfit(User user, Integer createRole, UOrder uOrder) {
        this.user = user;
        this.createRole = createRole;
        if (uOrder != null) {
            this.uOrder = uOrder;
            getTotalFee();
        }
        switch (createRole) {
            case CreateRole.PARTNER:
                partner();
                break;
            case CreateRole.COMPANY:
                company();
                break;
            case CreateRole.AGENT:
                agent();
                break;
            case CreateRole.GOLD_MERCHANTS:
            case CreateRole.GENERAL_MERCHANTS:
                merchants();
                break;
            case CreateRole.SILVER_MEMBER:
                silverMember();
                break;
            default:
                break;
        }
    }

    /**
     * 提交合伙人分润
     */
    private void partner() {
        gainSelf();
        gainSuperior();
        //如果提交人为高级合伙人,提交合伙人时,获取团队奖励
        if (user.getUserRole().equals(ReferrerRole.SENIOR_PARTNER)) {
            double teamMoney = getRoleShare(ReferrerRole.SENIOR_PARTNER, RecommendType.TEAM);
            gainProfit(user.getId(), teamMoney);
            totalFee -= teamMoney;
        }
        //如果提交人角色为合伙人,并且提交人属于某个高级合伙人的团队,高级合伙人获取团队奖励
        if (user.getUserRole().equals(ReferrerRole.PARTNER) && user.getShareholderId() != null) {
            double teamMoney = getRoleShare(ReferrerRole.SENIOR_PARTNER, RecommendType.TEAM);
            gainProfit(user.getShareholderId(), teamMoney);
            totalFee -= teamMoney;
        }
        gainLast();
    }

    /**
     * 提交分公司分润
     */
    private void company() {
        UOrder uOrder = new UOrder();
        uOrder.setGoodsDesc("提交分公司");
        this.uOrder = uOrder;
        double money = getRoleShare(user.getUserRole(), RecommendType.DIRECT);
        gainProfit(user.getId(), money);
    }

    /**
     * 提交代理分润
     */
    private void agent() {
        gainSelf();
        gainSuperior();
        //学员讲师抽成
        gainTeacher();
        //如果提交人为代理,且这个代理属于某个合伙人团队,该合伙人额外享受团队奖励(合伙人团队人员提交)
        if (user.getUserRole().equals(ReferrerRole.AGENT) && user.getPartnerId() != null) {
            double teamMoney = getRoleShare(ReferrerRole.PARTNER, RecommendType.TEAM);
            gainProfit(user.getPartnerId(), teamMoney);
            totalFee -= teamMoney;
        }
        gainLast();
    }

    /**
     * 提交普通商户分润
     */
    private void merchants() {
        //员工提交时为特殊情况,需要查询代理为员工设置的分润金额
        if (user.getUserRole().equals(ReferrerRole.STAFF)) {
            //获取总抽成
            double money = getRoleShare(ReferrerRole.STAFF, RecommendType.SUPERIOR);
            //获取给员工设置的分润金额
            double staffMoney = getStaffProfit();
            //员工抽成
            gainProfit(user.getId(), staffMoney);
            //总抽成扣除员工抽成,剩下为员工上级抽成
            double superiorMoney = money - staffMoney;
            //获取员工上级对象
            User superior = getSuperior();
            if (superior != null) {
                //上级抽成
                gainProfit(superior.getId(), superiorMoney);
            }
            totalFee -= money;
        } else {
            gainSelf();
            gainSuperior();
        }
        gainLast();
    }

    /**
     * 提交新民金卡分润
     */
    private void silverMember() {
        gainSelf();
        gainSuperior();
        gainLast();
    }

    /**
     * 获取总支付金额
     *
     * @return
     */
    private void getTotalFee() {
        double totalFee = uOrder.getTotalFee().doubleValue();
        totalFee /= 100;
        this.totalFee = totalFee;
    }

    /**
     * 本人抽水
     */
    private void gainSelf() {
        //获取本人抽成
        double selfMoney = getRoleShare(user.getUserRole(), RecommendType.DIRECT);
        //本人抽水
        gainProfit(user.getId(), selfMoney);
        totalFee -= selfMoney;
    }
    /**
     * 上级抽水
     */
    private void gainSuperior() {
        //获取上级对象
        User superior = getSuperior();
        if (superior != null) {
            //获取上级享受分成
            double superMoney = getRoleShare(superior.getUserRole(), RecommendType.INDIRECT);
            //上级抽水
            gainProfit(superior.getId(), superMoney);
            totalFee -= superMoney;
        }
    }

    /**
     * 最后抽水/boss抽水和剩下金额进公司
     */
    private void gainLast() {
        //获取boss抽水金额
        double money = getRoleShare(FixedAccount.BOSS, RecommendType.INDIRECT);
        //boss抽水
        gainProfit(FixedAccount.BOSS, money);
        totalFee -= money;
        //最后进公司
        gainProfit(FixedAccount.COMPANY, totalFee);
    }

    /**
     * 如果被创建人是学员讲师抽成
     */
    private void gainTeacher() {
        User createUser = getCreateUser();
        if (createUser != null && createUser.getStudent() != null && createUser.getStudent() == 1 && createUser.getTeacherId() != null) {
            //获取讲师信息
            User teacher = userMapper.selectByPrimaryKey(createUser.getTeacherId());
            if (teacher != null) {
                Integer teacherRole;
                int grade = teacher.getLecturerGrade() == null ? 1 : teacher.getLecturerGrade();
                switch (grade) {
                    case 2:
                        teacherRole = ReferrerRole.INTERMEDIATE_LECTURER;
                        break;
                    case 3:
                        teacherRole = ReferrerRole.SENIOR_LECTURER;
                        break;
                    default:
                        teacherRole = ReferrerRole.PRIMARY_LECTURER;
                        break;
                }
                //获取讲师分成金额
                double teacherMoney = getRoleShare(teacherRole, RecommendType.INDIRECT);
                gainProfit(teacher.getId(), teacherMoney);
                totalFee -= teacherMoney;
            }
        }
    }

    /**
     * 按条件获取分润规则
     *
     * @param userRole      提交人角色(1,boss;2,合伙人;3,分公司;4,联合创始人;5,代理;6,员工;7,高级合伙人;8,用户/新民金卡;9,矩阵;10,初级讲师;11,中级讲师;12,高级讲师)
     * @param recommendType 分润类型(1,推荐获取分成;2,享受分成,3,上级获取分成)
     * @return
     */
    private double getRoleShare(Integer userRole, Integer recommendType) {
        RoleShareExample example = new RoleShareExample();
        RoleShareExample.Criteria criteria = example.createCriteria();
        criteria.andUserRoleEqualTo(userRole);
        criteria.andRecommendTypeEqualTo(recommendType);
        criteria.andRecommendRoleEqualTo(createRole);
        List<RoleShare> roleShares = roleShareMapper.selectByExample(example);
        if (roleShares.size() > 0) {
            return roleShares.get(0).getMoney();
        }
        return 0.0;
    }

    /**
     * 获取给员工设置的商户奖金
     *
     * @return
     */
    private double getStaffProfit() {
        StaffProfitExample staffProfitExample = new StaffProfitExample();
        StaffProfitExample.Criteria staffProfitExampleCriteria = staffProfitExample.createCriteria();
        staffProfitExampleCriteria.andUserIdEqualTo(user.getId());
        staffProfitExampleCriteria.andMerchantTypeEqualTo(createRole);
        List<StaffProfit> staffProfits = staffProfitMapper.selectByExample(staffProfitExample);
        if (staffProfits.size() > 0) {
            return staffProfits.get(0).getProfit();
        }
        return 0.0;
    }

    /**
     * 获取上级对象
     *
     * @return
     */
    private User getSuperior() {
        if (user.getRecommender() == null) {
            //无推荐人
            return null;
        }
        if (user.getRecommender().equals(FixedAccount.BOSS)) {
            //上级为boss
            return null;
        }
        //获取员工的上级
        return userMapper.selectByPrimaryKey(user.getRecommender());
    }

    /**
     * 获取被创建的user对象
     *
     * @return
     */
    private User getCreateUser() {
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andOrderIdEqualTo(uOrder.getId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    /**
     * 获取抽水
     *
     * @param userId 要获取抽水的用户id
     * @param money  抽水金额
     */
    private void gainProfit(Integer userId, double money) {
        if (money <= 0) {
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
            accountRecord(userId, userAccount.getId(), money);
        } else {
            UserAccount userAccount = userAccounts.get(0);
            double userMoney = userAccount.getMoney() == null ? 0.0 : userAccount.getMoney();
            userAccount.setMoney(userMoney + money);
            userAccountMapper.updateByPrimaryKeySelective(userAccount);
            //记录账户金额变动信息
            accountRecord(userId, userAccount.getId(), money);
        }
    }

    /**
     * 记录账户金额变化
     *
     * @param userId        用户id
     * @param userAccountId 账户id
     * @param money         金额
     */
    private void accountRecord(Integer userId, Integer userAccountId, double money) {
        String description = user.getName() + uOrder.getGoodsDesc();
        UserAccountRecord record = new UserAccountRecord();
        record.setUserId(userId);
        record.setOrderId(uOrder.getId());
        record.setUserAccountId(userAccountId);
        record.setMoneyChange(new BigDecimal(money));
        record.setDescription(description);
        userAccountRecordMapper.insertSelective(record);
    }

}
