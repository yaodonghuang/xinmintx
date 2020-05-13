package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.service.IMemberTreeService;
import com.xinmintx.hstx.service.ReturnGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 退货和取消退货分润
 */
@Service
@Transactional
public class ReturnGoodsServiceImpl implements ReturnGoodsService {

    @Autowired
    private MemberBenefitMapper memberBenefitMapper;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    @Autowired
    private FactoryMapper factoryMapper;
    @Autowired
    private ShippingAddressMapper shippingAddressMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IMemberTreeService iMemberTreeService;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberReferrerMapper memberReferrerMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;
    @Autowired
    private FreezeMapper freezeMapper;

    private int flag = 1;


    /**
     * 退货和取消退货分润
     *
     * @param goodsOrderDetail 子订单
     */
    @Override
    public void returnGoods(GoodsOrderDetail goodsOrderDetail, int flag) {
        this.flag = flag;
        //获取利润
        BigDecimal profit = getProfit(goodsOrderDetail);
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(goodsOrderDetail.getOrderId());
        //利润不为空时分润
        if (profit != null) {
            //合伙人添加分润
            partner(goodsOrder, profit);
            //分公司和分公司推荐人分润
            branchAndRecommender(goodsOrder, profit);
            //矩阵分润
            IMemberTree(goodsOrder, profit);
            //通证池分润
            pool(goodsOrder, profit);
            //消费者推荐人分润
            consumerReferrer(goodsOrder, profit);
            //添加新民豆
            freezeBeans(goodsOrder, goodsOrderDetail);
        }
    }

    /**
     * 添加新民豆
     *
     * @param goodsOrder 底单
     */
    private void freezeBeans(GoodsOrder goodsOrder, GoodsOrderDetail goodsOrderDetail) {
        BigDecimal totalAmount = goodsOrderDetail.getTotalAmount();
        List<MemberBenefit> list = new LambdaQueryChainWrapper<>(memberBenefitMapper)
                .eq(MemberBenefit::getType, 7).list();
        if (list != null && list.size() > 0) {
            //获取新民豆比例
            Double percent = list.get(0).getPercent();
            BigDecimal per = BigDecimal.valueOf(percent);
            //计算新民豆
            BigDecimal multiply = totalAmount.multiply(per);
            BigDecimal bean = multiply.multiply(BigDecimal.valueOf(flag));

            Member member = memberMapper.selectById(goodsOrder.getMemberId());
            if (member != null) {
                //获取原来的新民豆
                BigDecimal freezeBeans = member.getFreezeBeans();
                member.setFreezeBeans(freezeBeans.add(bean));
                member.updateById();

                //添加冻结资金表
                Freeze one = new LambdaQueryChainWrapper<>(freezeMapper)
                        .eq(Freeze::getOrderId, goodsOrder.getId())
                        .eq(Freeze::getMemberId, goodsOrder.getMemberId())
                        .eq(Freeze::getType, 5)
                        .eq(Freeze::getState, 1).one();
                if (one != null) {
                    BigDecimal money = one.getMoney();
                    one.setMoney(money.add(bean));
                    one.setUpdateTime(new Date());
                    one.updateById();
                }
            }
        }
    }

    /**
     * 获取商品订单的利润  添加工厂冻结资金
     *
     * @param goodsOrderDetail 子订单
     * @return 利润
     */
    private BigDecimal getProfit(GoodsOrderDetail goodsOrderDetail) {

        //获取子订单总金额
        BigDecimal totalAmount = goodsOrderDetail.getTotalAmount();
        GoodsSku goodsSku = goodsSkuMapper.selectById(goodsOrderDetail.getSkuId());
        //采购总价
        double purchasePrice = goodsSku.getPurchasePrice().doubleValue() * goodsOrderDetail.getQuantity();
        BigDecimal bigDecimal = BigDecimal.valueOf(purchasePrice * flag);
        //利润
        double profit = totalAmount.doubleValue() - purchasePrice;

        GoodsOrder goodsOrder = goodsOrderMapper.selectById(goodsOrderDetail.getOrderId());
        if (goodsOrder != null) {
            Factory factory = factoryMapper.selectById(goodsOrder.getFactoryId());
            if (factory != null) {
                //获取原来的冻结资金
                BigDecimal freezingAmount = factory.getFreezingAmount();
                factory.setFreezingAmount(freezingAmount.add(bigDecimal));
                //添加工厂冻结资金
                factory.updateById();
                //添加冻结资金表
                Freeze one = new LambdaQueryChainWrapper<>(freezeMapper)
                        .eq(Freeze::getOrderId, goodsOrder.getId())
                        .eq(Freeze::getMemberId, factory.getFactoryId())
                        .eq(Freeze::getType, 4)
                        .eq(Freeze::getState, 1).one();
                if (one != null) {
                    BigDecimal money = one.getMoney();
                    one.setUpdateTime(new Date());
                    one.setMoney(money.add(bigDecimal));
                    one.updateById();
                }
            }

        }
        //获取分润比例
        MemberBenefit proportion = new LambdaQueryChainWrapper<>(memberBenefitMapper)
                .eq(MemberBenefit::getType, 8).one();
        return BigDecimal.valueOf(profit * flag * proportion.getPercent());
    }


    /**
     * 添加用户账户表
     *
     * @param userId   用户id
     * @param multiply 利润
     */
    private void insertUserAccount(Integer userId, BigDecimal multiply) {
        //查询账户表 查询是否存在该用户
        List<UserAccount> userAccountList = new LambdaQueryChainWrapper<>(userAccountMapper).eq(UserAccount::getUserId, userId).list();
        //走这里说明表里有该用户
        if (userAccountList != null && userAccountList.size() > 0) {
            //获取原来冻结金额
            UserAccount userAccounts = userAccountList.get(0);
            Double freezeMoney = userAccounts.getFreezeMoney();
            //走这里说明原来存在冻结金额 相加并存入数据库
            if (freezeMoney != null) {
                userAccounts.setFreezeMoney(multiply.doubleValue() + freezeMoney);
                userAccounts.updateById();
            }
        }
    }

    /**
     * 消费者推荐人分润
     *
     * @param goodsOrder 订单
     * @param profit     利润
     */
    private void consumerReferrer(GoodsOrder goodsOrder, BigDecimal profit) {
        //消费者推荐人
        Integer memberId = goodsOrder.getMemberId();
        List<MemberReferrer> list = new LambdaQueryChainWrapper<>(memberReferrerMapper)
                .eq(MemberReferrer::getMemberId, memberId)
                .eq(MemberReferrer::getStatus,1).list();
        if (list != null && list.size() > 0) {
            //获取推荐人id
            Integer referrerId = list.get(0).getReferrerId();
            //有推荐人
            if (referrerId != null && referrerId != 0) {
                //获取推荐人
                Member member = memberMapper.selectById(referrerId);
                if (member != null) {
                    //获取代理Userid
                    Integer userId = member.getUserId();
                    //获取消费者推荐人的分润比例
                    List<MemberBenefit> benefits = new LambdaQueryChainWrapper<>(memberBenefitMapper)
                            .eq(MemberBenefit::getType, 3).list();
                    if (benefits != null && benefits.size() > 0) {
                        Double percent = benefits.get(0).getPercent();
                        //获取消费者推荐人利润
                        BigDecimal per = BigDecimal.valueOf(percent);
                        BigDecimal multiply = profit.multiply(per);
                        //存在userId就添加用户账户表
                        if (userId != null && userId != 0) {
                            //添加账户表
                            insertUserAccount(userId, multiply);

                            //添加冻结资金表
                            Freeze one = new LambdaQueryChainWrapper<>(freezeMapper)
                                    .eq(Freeze::getOrderId, goodsOrder.getId())
                                    .eq(Freeze::getMemberId, userId)
                                    .eq(Freeze::getType, 1)
                                    .eq(Freeze::getState, 1).one();
                            if (one != null) {
                                BigDecimal money = one.getMoney();
                                one.setMoney(money.add(multiply));
                                one.setUpdateTime(new Date());
                                one.updateById();
                            }

                            //添加新民币
                        } else {
                            //获取member原来的冻结新民币
                            BigDecimal freezeCurrency = member.getFreezeCurrency();
                            //更新冻结新民币
                            member.setFreezeCurrency(freezeCurrency.add(multiply));
                            member.updateById();

                            //添加冻结资金表
                            Freeze freeze = new LambdaQueryChainWrapper<>(freezeMapper)
                                    .eq(Freeze::getOrderId, goodsOrder.getId())
                                    .eq(Freeze::getMemberId, member.getId())
                                    .eq(Freeze::getType, 2)
                                    .eq(Freeze::getState, 1).one();
                            if (freeze != null) {
                                BigDecimal money = freeze.getMoney();
                                freeze.setMoney(money.add(multiply));
                                freeze.updateById();
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * 通证池分润
     *
     * @param goodsOrder 订单
     * @param profit     利润
     */
    private void pool(GoodsOrder goodsOrder, BigDecimal profit) {
        List<MemberBenefit> list = new LambdaQueryChainWrapper<>(memberBenefitMapper).eq(MemberBenefit::getType, 5).list();
        if (list != null && list.size() > 0) {
            //获取通证池分润比例
            Double percent = list.get(0).getPercent();
            //获取通证池分润利润
            BigDecimal per = BigDecimal.valueOf(percent);
            BigDecimal multiply = profit.multiply(per);
            //添加账户表
            insertUserAccount(3, multiply);

            //添加冻结资金表
            Freeze one = new LambdaQueryChainWrapper<>(freezeMapper)
                    .eq(Freeze::getOrderId, goodsOrder.getId())
                    .eq(Freeze::getMemberId, 3)
                    .eq(Freeze::getType, 3)
                    .eq(Freeze::getState, 1).one();
            if (one != null) {
                BigDecimal money = one.getMoney();
                one.setMoney(money.add(multiply));
                one.setUpdateTime(new Date());
                one.updateById();
            }
        }
    }

    /**
     * 矩阵分润
     *
     * @param goodsOrder 订单
     * @param profit     利润
     */
    private void IMemberTree(GoodsOrder goodsOrder, BigDecimal profit) {
        //获取矩阵的所有会员id
        List<Integer> tenMemberId = iMemberTreeService.getTenMemberId(goodsOrder.getMemberId());
        if (tenMemberId != null && tenMemberId.size() > 0) {
            List<MemberBenefit> list = new LambdaQueryChainWrapper<>(memberBenefitMapper)
                    .eq(MemberBenefit::getType, 4).list();
            if (list != null && list.size() > 0) {
                //获取矩阵分润比例
                Double percent = list.get(0).getPercent();
                //获取矩阵分润利润
                BigDecimal per = BigDecimal.valueOf(percent);
                BigDecimal multiply = profit.multiply(per);
                //计算每个人的利润
                int size = tenMemberId.size();
                BigDecimal divide = multiply.divide(new BigDecimal(size));

                //遍历所有矩阵的会员id
                for (Integer integer : tenMemberId) {
                    //加入新民币
                    Member member = memberMapper.selectById(integer);
                    if (member != null) {
                        //更新冻结的新民币
                        BigDecimal freezeCurrency = member.getFreezeCurrency();
                        member.setFreezeCurrency(freezeCurrency.add(divide));
                        member.updateById();

                    }
                }
                //添加冻结资金表
                List<Freeze> freezeList = new LambdaQueryChainWrapper<>(freezeMapper)
                        .eq(Freeze::getOrderId, goodsOrder.getId())
                        .eq(Freeze::getType, 6)
                        .eq(Freeze::getState, 1).list();
                if (freezeList != null && freezeList.size() > 0) {
                    for (Freeze one : freezeList) {
                        BigDecimal money = one.getMoney();
                        one.setMoney(money.add(divide));
                        one.setUpdateTime(new Date());
                        one.updateById();
                    }
                }
            }
        }
    }

    /**
     * 分公司和分公司推荐人分润
     *
     * @param goodsOrder 订单
     * @param profit     利润
     */
    private void branchAndRecommender(GoodsOrder goodsOrder, BigDecimal profit) {
        //分公司分润
        Integer addressId = goodsOrder.getAddressId();
        if (addressId != null && addressId != 0) {
            //获取地址
            ShippingAddress shippingAddress = shippingAddressMapper.selectById(addressId);
            if (shippingAddress != null) {
                //获取地区code
                String regionCode = shippingAddress.getRegionCode();
                if (StringUtils.isNoneBlank(regionCode)) {
                    //获取分公司
                    List<User> list = new LambdaQueryChainWrapper<>(userMapper)
                            .eq(User::getUserRole, 3)
                            .eq(User::getRegionCode, regionCode)
                            .eq(User::getIsReview, 1).list();
                    if (list != null && list.size() > 0) {
                        //分公司
                        User branch = list.get(0);
                        List<MemberBenefit> memberBenefit = new LambdaQueryChainWrapper<>(memberBenefitMapper).eq(MemberBenefit::getType, 2).list();
                        if (memberBenefit != null && memberBenefit.size() > 0) {
                            //获取分公司利润比例
                            Double percent = memberBenefit.get(0).getPercent();
                            BigDecimal per = BigDecimal.valueOf(percent);
                            //计算分公司利润金额
                            BigDecimal multiply = profit.multiply(per);
                            //加入账户表
                            insertUserAccount(branch.getId(), multiply);

                            //添加冻结资金表
                            Freeze one = new LambdaQueryChainWrapper<>(freezeMapper)
                                    .eq(Freeze::getOrderId, goodsOrder.getId())
                                    .eq(Freeze::getMemberId, branch.getId())
                                    .eq(Freeze::getType, 1)
                                    .eq(Freeze::getState, 1).one();
                            if (one != null) {
                                BigDecimal money = one.getMoney();
                                one.setMoney(money.add(multiply));
                                one.setUpdateTime(new Date());
                                one.updateById();
                            }

                            //分公司推荐人
                            //获取分公司推荐人id
                            Integer recommenderId = branch.getRecommender();
                            //获取分公司推荐人user对象
                            User recommenderUser = userMapper.selectById(recommenderId);
                            if (recommenderUser != null) {
                                List<MemberBenefit> recommenderBenefit = new LambdaQueryChainWrapper<>(memberBenefitMapper).eq(MemberBenefit::getType, 6).list();
                                if (recommenderBenefit != null && recommenderBenefit.size() > 0) {
                                    //获取分公司推荐人利润比例
                                    Double recommenderPercent = recommenderBenefit.get(0).getPercent();
                                    BigDecimal recommenderPer = BigDecimal.valueOf(recommenderPercent);
                                    //计算分公司推荐人分润金额
                                    BigDecimal recommenderMultiply = profit.multiply(recommenderPer);
                                    //加入分公司推荐人账户表
                                    insertUserAccount(recommenderId, recommenderMultiply);
                                    //加入冻结账户表
                                    //添加冻结资金表
                                    Freeze recommenderFreeze = new LambdaQueryChainWrapper<>(freezeMapper)
                                            .eq(Freeze::getOrderId, goodsOrder.getId())
                                            .eq(Freeze::getMemberId, recommenderUser.getId())
                                            .eq(Freeze::getType, 1)
                                            .eq(Freeze::getState, 1).one();
                                    if (recommenderFreeze != null) {
                                        BigDecimal moneys = recommenderFreeze.getMoney();
                                        recommenderFreeze.setMoney(moneys.add(recommenderMultiply));
                                        recommenderFreeze.setUpdateTime(new Date());
                                        recommenderFreeze.updateById();
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * 合伙人分润
     *
     * @param goodsOrder 订单对象
     * @param profit     商品利润
     */
    private void partner(GoodsOrder goodsOrder, BigDecimal profit) {
        //获取厂家
        Integer factoryId = goodsOrder.getFactoryId();
        Factory factory = factoryMapper.selectById(factoryId);
        if (factory != null) {
            //获取推荐合伙人id
            Integer userId = factory.getReferrerId();
            //去user表查询 是否存在该user 有就添加
            User user = userMapper.selectById(userId);
            if (user != null) {
                //获取合伙人分润比例
                List<MemberBenefit> list = new LambdaQueryChainWrapper<>(memberBenefitMapper).eq(MemberBenefit::getType, 1).list();
                if (list != null && list.size() > 0) {
                    Double percent = list.get(0).getPercent();
                    BigDecimal per = BigDecimal.valueOf(percent);
                    //计算合伙人分润金额
                    BigDecimal multiply = profit.multiply(per);
                    //加入账户表
                    insertUserAccount(userId, multiply);

                    //添加冻结资金表
                    Freeze one = new LambdaQueryChainWrapper<>(freezeMapper)
                            .eq(Freeze::getOrderId, goodsOrder.getId())
                            .eq(Freeze::getMemberId, userId)
                            .eq(Freeze::getType, 1)
                            .eq(Freeze::getState, 1).one();
                    if (one != null) {
                        BigDecimal money = one.getMoney();
                        one.setMoney(money.add(multiply));
                        one.setUpdateTime(new Date());
                        one.updateById();
                    }
                }
            }
        }
    }


}
