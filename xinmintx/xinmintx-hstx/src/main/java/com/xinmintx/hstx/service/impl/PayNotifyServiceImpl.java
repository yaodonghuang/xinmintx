package com.xinmintx.hstx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.entity.PtRedisBean;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.pojo.vo.PayNotify;
import com.xinmintx.hstx.pojo.vo.PoboNotify;
import com.xinmintx.hstx.service.*;
import com.xinmintx.hstx.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 第三方微信回调
 */
@Transactional
@Service
@Slf4j
public class PayNotifyServiceImpl implements PayNotifyService {

    @Autowired
    private UOrderMapper uOrderMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;
    @Autowired
    private GoodsOrderDetailMapper goodsOrderDetailMapper;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodPtcodeMapper goodPtcodeMapper;
    @Autowired
    private GoodPtcodeInfoMapper goodPtcodeInfoMapper;
    @Autowired
    private IntegralAccessMapper integralAccessMapper;
    @Autowired
    private MemberBeansRecordMapper memberBeansRecordMapper;
    @Autowired
    private GoodPtgoodMapper goodPtgoodMapper;
    @Autowired
    private MemberReferrerMapper memberReferrerMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleShareMapper roleShareMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserAccountRecordMapper userAccountRecordMapper;
    @Autowired
    private IMemberTreeService iMemberTreeService;
    @Autowired
    private BenefitService benefitService;
    @Autowired
    private FreezeMapper freezeMapper;
    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private IMemberTreeService memberTreeService;
    @Autowired
    private MemberCardOrderMapper memberCardOrderMapper;
    @Autowired
    private MemberCardOrderDetailMapper memberCardOrderDetailMapper;
    @Autowired
    private MemberCardInfoMapper memberCardInfoMapper;
    @Autowired
    private GoodPanicBuyMapper goodPanicBuyMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UOrderSerivce uOrderSerivce;
    @Autowired
    private Jedis jedis;
    @Autowired
    private MemberAmountLogMapper memberAmountLogMapper;
    @Autowired
    private FactoryCashInfoMapper factoryCashInfoMapper;
    @Autowired
    private MemberBonusMapper memberBonusMapper;

    private UOrder order;

    /**
     * 支付回调
     *
     * @param payNotify
     * @return
     */
    public boolean getWeChatPayNotify(PayNotify payNotify) {
        boolean flag = false;
        if ("1".equalsIgnoreCase(payNotify.getStatus())) {
            UOrder uOrder = new LambdaQueryChainWrapper<>(uOrderMapper)
                    .eq(UOrder::getOrderNo, payNotify.getOrderId())
                    .one();
            if (uOrder != null && "0".equals(uOrder.getPayStatus())) {
                // 存在,修改保存返回参数
                if ("0".equals(uOrder.getPayStatus())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    try {
                        uOrder.setPayTime(sdf.parse(payNotify.getCompleteDate()));
                        uOrder.setCompleteDate(sdf.parse(payNotify.getCompleteDate()));
                        uOrder.setSettleDate(sdf.parse(payNotify.getSettleDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    uOrder.setPayStatus("1");
                    uOrder.setPayTime(new Date());
                    // 第三方数据
                    uOrder.setVersionId(payNotify.getVersionId());
                    uOrder.setMerchantId(payNotify.getMerchantId());
                    uOrder.setOrderId(payNotify.getOrderId());
                    if (StringUtils.isNotEmpty(payNotify.getStatus())) {
                        uOrder.setStatus(Integer.valueOf(payNotify.getStatus()));
                    }
                    if (StringUtils.isNotEmpty(payNotify.getNotifyTyp())) {
                        uOrder.setNotifyTyp(Integer.valueOf(payNotify.getNotifyTyp()));
                    }
                    uOrder.setPayOrdNo(payNotify.getPayOrdNo());
                    if (StringUtils.isNotEmpty(payNotify.getOrderAmt())) {
                        uOrder.setOrderAmt(new BigDecimal(payNotify.getOrderAmt()));
                    }
                    uOrder.setNotifyUrl(payNotify.getNotifyUrl());
                    uOrder.setSignType(payNotify.getSignType());
                    uOrder.setSignature(payNotify.getSignature());
                    uOrder.setAttach(payNotify.getAttach());
                    try {
                        uOrder.updateById();
                        this.order = uOrder;
                        // 成功
                        flag = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 新民E卡购买微信支付回调
     */
    @Override
    public void eCardNotify(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            Member member = memberMapper.selectById(order.getUserId());
            member.setMemberType(1);
            //设置卡为可用
            member.setCardStatus(1);
            //设置会员卡过期时间
            setMemberCardIndate(member);
            member.updateById();
            //修改会员卡订单信息
            updateMemberCardOrder(member);
            if (StringUtils.isNotBlank(payNotify.getAttach())) {
                //更换绑定关系
                updateMemberReferrer(Integer.parseInt(payNotify.getAttach()));
            } else {
                //确定绑定关系
                updateMemberReferrer();
            }
            memberCardShare(member, 7);
        }
    }

    /**
     * 新民金卡购买微信支付回调
     *
     * @param payNotify
     * @return
     */
    @Override
    public void goldCardPayNotify(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            Member member = memberMapper.selectById(order.getUserId());
            member.setMemberType(2);
            //设置卡为可用
            member.setCardStatus(1);
            //设置会员卡过期时间
            setMemberCardIndate(member);
            // 升级新民金卡之后平台礼包+2，商户礼包增加194新民币,增加生日礼包可领取次数1
            dealNewCurrency(member, ifExistsGift(member));
            member.updateById();
            //修改会员卡订单信息
            updateMemberCardOrder(member);
            if (StringUtils.isNotBlank(payNotify.getAttach())) {
                //更换绑定关系
                updateMemberReferrer(Integer.parseInt(payNotify.getAttach()));
            } else {
                //确定绑定关系
                updateMemberReferrer();
            }
            memberCardShare(member, 6);
        }
    }

    /**
     * 买卡升级分润
     *
     * @param member 升级人
     * @param role   卡类型（6金卡，7E卡）
     */
    private void memberCardShare(Member member, Integer role) {
        //会员升级,推荐人分润
        MemberReferrer one = new LambdaQueryChainWrapper<>(memberReferrerMapper)
                .eq(MemberReferrer::getMemberId, member.getId())
                .eq(MemberReferrer::getStatus, 1)
                .one();
        if (one != null) {
            Member referrer = memberMapper.selectById(one.getReferrerId());
            //推荐人的角色为代理
            if (referrer.getUserId() != null) {
                //推荐人为代理商角色
                //推荐分成
                shareMoneyByUserId(referrer.getUserId(), role, 1);
                //推荐人上级分成
                //获取推荐人上级
                User user = userMapper.selectById(referrer.getUserId());
                if (user != null && user.getRecommender() != null) {
                    //推荐人的上级分润
                    shareMoneyByUserId(user.getRecommender(), role, 2);
                }
            } else {
                //余额分润
                shareMoneyByMemberId(referrer.getId(), role, 1);
                //获取推荐人的上级
                MemberReferrer memberReferrer = new LambdaQueryChainWrapper<>(memberReferrerMapper)
                        .eq(MemberReferrer::getMemberId, referrer.getId())
                        .eq(MemberReferrer::getStatus, 1)
                        .one();
                if (memberReferrer != null) {
                    Member superMember = memberMapper.selectById(memberReferrer.getReferrerId());
                    if (superMember.getUserId() != null) {
                        //代理分润
                        shareMoneyByUserId(superMember.getUserId(), role, 2);
                    } else {
                        //余额分润
                        shareMoneyByMemberId(superMember.getId(), role, 2);
                    }
                }
            }
        }
        //会员升级,矩阵分润
        shareMoneyByTree(member.getId(), role);

        //如果是E卡就查询会员奖池和特殊账号的钱
        if (role == 7) {
            //获取特殊账号金额
            RoleShare pecialAccount = new LambdaQueryChainWrapper<>(roleShareMapper)
                    .eq(RoleShare::getUserRole,14)
                    .eq(RoleShare::getRecommendType,2)
                    .eq(RoleShare::getRecommendRole,7)
                    .one();
            if (pecialAccount != null) {
                Double money = pecialAccount.getMoney();
                BigDecimal bigDecimalMoney = BigDecimal.valueOf(money);
                //获取特殊账户
                Member specMember = memberMapper.selectById(1);
                if (specMember != null) {
                    //获取原来特殊账号的钱
                    BigDecimal cashAmount = specMember.getCashAmount();
                    if (cashAmount == null) {
                        cashAmount = BigDecimal.ZERO;
                    }
                    specMember.setCashAmount(cashAmount.add(bigDecimalMoney));
                    specMember.updateById();
                }
            }
            //获取奖池金额
            RoleShare prizePool = new LambdaQueryChainWrapper<>(roleShareMapper)
                    .eq(RoleShare::getUserRole,13)
                    .eq(RoleShare::getRecommendType,2)
                    .eq(RoleShare::getRecommendRole,7)
                    .one();
            if (prizePool != null) {
                Double money = prizePool.getMoney();
                BigDecimal bigDecimalMoney = BigDecimal.valueOf(money);
                Date date = new Date();
                //查询符合条件的奖金池
                MemberBonus memberBonus = new LambdaQueryChainWrapper<>(memberBonusMapper)
                        .le(MemberBonus::getBeginDate, date)  //大于开始时间
                        .ge(MemberBonus::getEndDate, date)  //小于结束时间
                        .eq(MemberBonus::getStatus, 1)         //开启的
                        .one();
                //存在奖金池
                if (memberBonus != null) {
                    //获取原奖金池总金额
                    BigDecimal totalAmount = memberBonus.getTotalAmount();
                    if (totalAmount == null) {
                        totalAmount = BigDecimal.ZERO;
                    }
                    memberBonus.setTotalAmount(totalAmount.add(bigDecimalMoney));
                    memberBonus.updateById();
                }
            }
        }
    }


    /**
     * 设置会员卡过期时间
     *
     * @param member
     */
    private void setMemberCardIndate(Member member) {
        if (member.getCardIndate() != null) {
            //获取会员卡过期时间
            Date cardIndate = member.getCardIndate();
            //判断会员卡是否已经过期
            int i = DateUtil.compareDateWithNow(cardIndate);
            Calendar calendar = Calendar.getInstance();
            if (i < 0) {
                //已经过期
                calendar.setTime(new Date());
            } else {
                //还未过期
                calendar.setTime(cardIndate);
            }
            //一年后的时间
            calendar.add(Calendar.YEAR, 1);
            Date time = calendar.getTime();
            member.setCardIndate(time);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            //一年后的时间
            calendar.add(Calendar.YEAR, 1);
            Date time = calendar.getTime();
            member.setCardIndate(time);
        }
    }

    /**
     * 修改会员卡订单状态
     */
    private void updateMemberCardOrder(Member member) {
        //获取订单
        MemberCardOrder memberCardOrder = new LambdaQueryChainWrapper<>(memberCardOrderMapper)
                .eq(MemberCardOrder::getOrderId, order.getId())
                .one();
        //获取订单详情
        List<MemberCardOrderDetail> details = new LambdaQueryChainWrapper<>(memberCardOrderDetailMapper)
                .eq(MemberCardOrderDetail::getCardOrderId, memberCardOrder.getId())
                .list();
        memberCardOrder.setPayStatus(1);
        memberCardOrder.setOrderStatus(5);
        for (MemberCardOrderDetail detail : details) {
            //订单详情中如果存在实体卡,则订单状态为待发货,否则即为已收货
            if (detail.getOrderType() == 0) {
                memberCardOrder.setOrderStatus(2);
            }
            if (detail.getCardId() != null) {
                MemberCardInfo info = memberCardInfoMapper.selectById(detail.getCardId());
                info.setMemberId(member.getId());
                info.setStatus(1);
                info.updateById();
                member.setCardId(detail.getCardId());
                member.updateById();
            }
        }
        memberCardOrder.updateById();
    }

    /**
     * 会员卡续费
     *
     * @param payNotify
     */
    @Override
    public void cardRenewNotify(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            Member member = memberMapper.selectById(order.getUserId());
            //设置会员卡过期时间
            setMemberCardIndate(member);
            //设置卡为可用
            member.setCardStatus(1);
            member.updateById();
            // 升级新民金卡之后平台礼包+2，商户礼包增加194新民币,增加生日礼包可领取次数1
            dealNewCurrency(member, ifExistsGift(member));
            //修改会员卡订单信息
            updateMemberCardOrder(member);
        }
    }

    /**
     * 制卡/补卡
     *
     * @param payNotify
     */
    @Override
    public void makeCardNotify(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            Member member = memberMapper.selectById(order.getUserId());
            //获取会员卡信息
            MemberCardInfo memberCardInfo = memberCardInfoMapper.selectById(member.getCardId());
            //设置已有实体卡
            memberCardInfo.setEntityCard(1);
            memberCardInfo.updateById();
            //修改会员卡订单信息
            updateMemberCardOrder(member);
        }
    }

    private Boolean ifExistsGift(Member member) {
        List<Merchant> merchants = new LambdaQueryChainWrapper<>(merchantMapper)
                .eq(Merchant::getRegionCode, member.getRegionCode())
                .list();
        if (merchants != null && merchants.size() > 0) {
            for (Merchant merchant : merchants) {
                List<Gift> gftList = new LambdaQueryChainWrapper<>(giftMapper)
                        .eq(Gift::getSourceId, merchant.getId())
                        .list();
                if (gftList != null && gftList.size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // 升级新民金卡之后平台礼包+2，商户礼包增加194新民币,增加生日礼包可领取次数1
    private void dealNewCurrency(Member member, Boolean flag) {
        if (flag) {// 存在商铺礼包
            BigDecimal price = BigDecimal.ZERO;
            if (member.getIntegral() != null) {// 根据积分设置新民币
                if (member.getNewCurrency() != null) {
                    member.setNewCurrency(member.getNewCurrency().add(new BigDecimal(member.getIntegral())));
                } else {
                    member.setNewCurrency(new BigDecimal(member.getIntegral()));
                }
                price = new BigDecimal(member.getIntegral());
            } else {
                if (member.getNewCurrency() != null) {
                    member.setNewCurrency(member.getNewCurrency().add(new BigDecimal("194")));
                } else {
                    member.setNewCurrency(new BigDecimal("194"));
                }
                price = new BigDecimal("194");
            }
            MemberCurrencyRecord record = new MemberCurrencyRecord();
            record.setMemberId(member.getId());
            record.setCurrencyChange(price);
            record.setDescription("升级新民金卡");
            record.insert();
            // 保存日志
            GiftPurchaseLogs gpl = new GiftPurchaseLogs();
            gpl.setMemberId(Long.valueOf(member.getId()));
            gpl.setGiftId(null);
            gpl.setPrice(price);
            gpl.setBalance(member.getNewCurrency());
            gpl.setType("income");
            gpl.setMerchantId(null);
            giftMapper.insertGiftLog(gpl);
        } else {
            if (member.getPlatformCount() != null) {// 设置升级后的平台礼包可领取次数
                member.setPlatformCount(member.getPlatformCount() + 2);
            } else {
                member.setPlatformCount(2);
            }
        }
        if (member.getBirthGiftCount() != null) {// 设置生日礼包可领取次数
            member.setBirthGiftCount(member.getBirthGiftCount() + 1);
        } else {
            member.setBirthGiftCount(1);
        }
    }

    /**
     * 新民金卡升级矩阵分润
     *
     * @param memberId
     */
    public void shareMoneyByTree(Integer memberId, Integer role) {
        List<Integer> ids = memberTreeService.getTenMemberId(memberId);
        if (ids != null && ids.size() > 0) {
            RoleShare roleShare = new LambdaQueryChainWrapper<>(roleShareMapper)
                    .eq(RoleShare::getUserRole, 9)
                    .eq(RoleShare::getRecommendType, 2)
                    .eq(RoleShare::getRecommendRole, role)
                    .one();
            double money = roleShare.getMoney() / ids.size();
            for (Integer id : ids) {
                Member member = memberMapper.selectById(id);
                if (member.getUserId() != null && member.getUserId() != 0) {
                    //代理商角色
                    shareMoney(userMapper.selectById(member.getUserId()), money, role);
                } else {
                    //余额分润
                    shareMemberMoney(member, money, role);
                }
            }
        }
    }

    /**
     * 根据用户id和分润类型给代理商进行分润
     *
     * @param userId
     * @param type
     */
    public void shareMoneyByUserId(Integer userId, Integer role, Integer type) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return;
        }
        //获取推荐人用户角色
        Integer userRole = user.getUserRole();
        //分润规则
        RoleShare roleShare = new LambdaQueryChainWrapper<>(roleShareMapper)
                .eq(RoleShare::getUserRole, userRole)
                .eq(RoleShare::getRecommendRole, role)
                .eq(RoleShare::getRecommendType, type)
                .one();
        if (roleShare != null) {
            //获取分润金额
            Double money = roleShare.getMoney();
            //分润
            shareMoney(user, money, role);
        }
    }

    /**
     * 根据用户id和分润类型给惠商用户进行分润(余额)
     *
     * @param memberId
     * @param type
     */
    public void shareMoneyByMemberId(Integer memberId, Integer role, Integer type) {
        Member member = memberMapper.selectById(memberId);
        if (member == null) {
            return;
        }
        //获取推荐人用户角色
        //分润规则
        RoleShare roleShare = new LambdaQueryChainWrapper<>(roleShareMapper)
                .eq(RoleShare::getUserRole, 8)
                .eq(RoleShare::getRecommendRole, role)
                .eq(RoleShare::getRecommendType, type)
                .one();
        if (roleShare != null) {
            //获取分润金额
            Double money = roleShare.getMoney();
            //分润
            shareMemberMoney(member, money, role);
        }
    }

    /**
     * 惠商用户余额分润
     *
     * @param member
     * @param money
     */
    private void shareMemberMoney(Member member, Double money, Integer role) {
        if (member == null || money <= 0) {
            return;
        }
        BigDecimal cashAmount = member.getCashAmount() == null ? BigDecimal.ZERO : member.getCashAmount();
        member.setCashAmount(cashAmount.add(BigDecimal.valueOf(money)));
        member.updateById();
        MemberAmountLog memberAmountLog = new MemberAmountLog();
        memberAmountLog.setMemberId(member.getId().longValue());
        memberAmountLog.setType("2");
        memberAmountLog.setPrice(BigDecimal.valueOf(money));
        memberAmountLog.setBalance(member.getCashAmount());
        if (role == 6) {
            memberAmountLog.setRemark("新民金卡升级分润");
            memberAmountLog.setSource(3);
        } else {
            memberAmountLog.setRemark("新民E卡升级分润");
            memberAmountLog.setSource(10);
        }
        memberAmountLog.insert();
    }

    /**
     * 给用户账户新民分润
     *
     * @param member
     * @param money
     */
    @Deprecated
    public void shareNewMoney(Member member, Double money) {
        if (member == null || money <= 0) {
            return;
        }
        if (member.getMemberType() < 2 || member.getCardStatus() == null || member.getCardStatus() == 0) {
            //用户为金卡以下用户,或者会员过期,新民币冻结
            BigDecimal freezeCurrency = member.getFreezeCurrency() == null ? BigDecimal.ZERO : member.getFreezeCurrency();
            member.setFreezeCurrency(freezeCurrency.add(BigDecimal.valueOf(money)));
            member.updateById();
            Freeze freeze = new Freeze();
            freeze.setMemberId(member.getId());
            freeze.setType(7);
            freeze.setMoney(BigDecimal.valueOf(money));
            freeze.setCreateTime(new Date());
            freeze.setUpdateTime(new Date());
            freeze.setState(1);
            freeze.insert();
        } else {
            //用户角色为新民金卡以上,直接新民币分润
            BigDecimal newCurrency = member.getNewCurrency() == null ? BigDecimal.ZERO : member.getNewCurrency();
            member.setNewCurrency(newCurrency.add(BigDecimal.valueOf(money)));
            member.updateById();
            //记录账户变化
            MemberCurrencyRecord record = new MemberCurrencyRecord();
            record.setMemberId(member.getId());
            record.setCurrencyChange(BigDecimal.valueOf(money));
            record.setDescription("新民金卡升级分润");
            record.setCreateTime(new Date());
            record.insert();
        }
    }


    /**
     * 给代理商用户账户分润
     *
     * @param user
     * @param money
     */
    public void shareMoney(User user, Double money, Integer role) {
        if (user == null || money <= 0) {
            return;
        }
        //获取用户账户
        UserAccount userAccount = new LambdaQueryChainWrapper<>(userAccountMapper).eq(UserAccount::getUserId, user.getId()).one();
        if (userAccount != null) {
            double accountMoney = userAccount.getMoney() == null ? 0 : userAccount.getMoney();
            userAccount.setMoney(accountMoney + money);
            userAccount.updateById();
        } else {
            userAccount = new UserAccount();
            userAccount.setMoney(money);
            userAccount.setUserId(user.getId());
            userAccountMapper.insert(userAccount);
        }
        //更新用户账户记录
        UserAccountRecord userAccountRecord = new UserAccountRecord();
        userAccountRecord.setUserId(user.getId());
        userAccountRecord.setUserAccountId(userAccount.getId());
        userAccountRecord.setMoneyChange(BigDecimal.valueOf(money));
        if (role == 6) {
            userAccountRecord.setDescription("新民金卡升级分润");
        } else {
            userAccountRecord.setDescription("新民E卡升级分润");
        }
        userAccountRecord.setCreateTime(new Date());
        userAccountRecord.insert();
    }

    /**
     * 商品购买微信支付回调
     *
     * @param payNotify
     * @return
     */
    @Override
    public void wechatGoodsPayNotify(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            //确认绑定用户关系
            updateMemberReferrer();
            //获取订单列表
            List<GoodsOrder> goodsOrders = new LambdaQueryChainWrapper<>(goodsOrderMapper)
                    .eq(GoodsOrder::getUOrderId, order.getId())
                    .list();
            for (GoodsOrder goodsOrder : goodsOrders) {
                if (goodsOrder.getOrderType() == 1) {
                    //修改订单状态,库存,销量
                    updateGoodsOrder(goodsOrder);
                    benefitService.benfit(goodsOrder);
                }
                if (goodsOrder.getOrderType() == 4) {
                    //抢购
                    updateGoodsOrderPanic(goodsOrder);
                }
            }
        }
    }

    /**
     * 修改订单状态,库存,销量
     *
     * @param goodsOrder
     */
    @Async
    public void updateGoodsOrder(GoodsOrder goodsOrder) {
        goodsOrder.setIfPay(1);
        goodsOrder.setOrderState(2);
        goodsOrderMapper.updateById(goodsOrder);
        //获取订单详情
        List<GoodsOrderDetail> goodsOrderDetails = new LambdaQueryChainWrapper<>(goodsOrderDetailMapper)
                .eq(GoodsOrderDetail::getOrderId, goodsOrder.getId())
                .list();
        for (GoodsOrderDetail goodsOrderDetail : goodsOrderDetails) {
            //1.修改库存,sku销量
            Integer quantity = goodsOrderDetail.getQuantity();
            GoodsSku goodsSku = goodsSkuMapper.selectById(goodsOrderDetail.getSkuId());
            goodsOrderDetail.setOrderState(2);
            goodsOrderDetail.setIfPay(1);
            goodsSku.setStockNum(goodsSku.getStockNum() - quantity);
            int sales = goodsSku.getGoodsSales() == null ? 0 : goodsSku.getGoodsSales();
            goodsSku.setGoodsSales(sales + quantity);
            goodsSkuMapper.updateById(goodsSku);
            //库存不足时
            if (goodsSku.getStockNum() < 0) {
                //标记为缺货
                goodsOrderDetail.setLack(1);
            }
            goodsOrderDetail.updateById();
            //2.修改商品销量
            Goods goods = goodsMapper.selectById(goodsOrderDetail.getGoodsId());
            sales = goods.getSalesActual() == null ? 0 : goods.getSalesActual();
            goods.setSalesActual(sales + quantity);
            goodsMapper.updateById(goods);
        }

    }

    /**
     * 修改订单状态,库存,销量
     *
     * @param goodsOrder
     */
    @Async
    public void updateGoodsOrderPanic(GoodsOrder goodsOrder) {
        goodsOrder.setIfPay(1);
        goodsOrder.setOrderState(2);
        goodsOrder.updateById();
        Member member = memberMapper.selectById(goodsOrder.getMemberId());
        //获取订单详情
        List<GoodsOrderDetail> goodsOrderDetails = new LambdaQueryChainWrapper<>(goodsOrderDetailMapper)
                .eq(GoodsOrderDetail::getOrderId, goodsOrder.getId())
                .list();
        //购买的总数
        int total = 0;
        GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(goodsOrderDetails.get(0).getGoodsId());
        for (GoodsOrderDetail goodsOrderDetail : goodsOrderDetails) {
            goodsOrderDetail.setOrderState(2);
            goodsOrderDetail.setIfPay(1);
            goodsOrderDetail.updateById();
            Integer quantity = goodsOrderDetail.getQuantity();
            total += quantity;
        }
        //获取限购数量
        boolean restriction = goodsService.checkMemberRestriction(goodPanicBuy, member.getId(), total);
        if (goodPanicBuy.getStockNum() >= total && restriction) {
            for (GoodsOrderDetail goodsOrderDetail : goodsOrderDetails) {
                //购买成功,添加用户抢购信息里面
                Integer quantity = goodsOrderDetail.getQuantity();
                GoodPanicBuyMember goodPanicBuyMember = new GoodPanicBuyMember();
                goodPanicBuyMember.setPanicBuyId(goodPanicBuy.getId());
                goodPanicBuyMember.setMemberId(goodsOrder.getMemberId());
                goodPanicBuyMember.setSkuid(goodsOrderDetail.getSkuId());
                goodPanicBuyMember.setQuantity(quantity);
                goodPanicBuyMember.insert();
            }
            //修改库存
            goodPanicBuy.setStockNum(goodPanicBuy.getStockNum() - total);
            //修改销量
            int sales = goodPanicBuy.getSalesActual() == null ? 0 : goodPanicBuy.getSalesActual();
            goodPanicBuy.setSalesActual(sales + total);
            goodPanicBuy.updateById();
            //分润
            benefitService.benfit(goodsOrder);
        } else {
            //库存不足,退款
            uOrderSerivce.goodsOrderRefund(goodsOrder, order);
        }
    }

    /**
     * 申请成为商户微信支付回调
     *
     * @param payNotify
     * @return
     */
    @Override
    public void wechatMerchantPayNotify(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            Merchant merchant = merchantMapper.selectById(order.getUserId());
            merchant.setIsReview(1);
            merchantMapper.updateById(merchant);
        }
    }

    @Override
    public void ptPayNotify(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            //确认绑定用户关系
            updateMemberReferrer();
            //获取订单
            QueryWrapper<GoodsOrder> goodsOrderQueryWrapper = new QueryWrapper<>();
            goodsOrderQueryWrapper.eq("u_order_id", order.getId());
            GoodsOrder goodsOrder = goodsOrderMapper.selectOne(goodsOrderQueryWrapper);
            //修改拼团状态
            QueryWrapper<GoodPtcodeInfo> goodPtcodeInfoQueryWrapper = new QueryWrapper<>();
            goodPtcodeInfoQueryWrapper.eq("goods_order_id", goodsOrder.getId());
            GoodPtcodeInfo goodPtcodeInfo = goodPtcodeInfoMapper.selectOne(goodPtcodeInfoQueryWrapper);
            //获取团长信息
            GoodPtcode goodPtcode = goodPtcodeMapper.selectById(goodPtcodeInfo.getPid());
            if (goodPtcodeInfo.getIsHeader() == 1) {
                //修改团长状态
                goodPtcode.setStatus(1);
                goodPtcodeMapper.updateById(goodPtcode);
                PtRedisBean ptRedisBean = new PtRedisBean();
                ptRedisBean.setId(goodPtcode.getId());
                ptRedisBean.setEndTime(goodPtcode.getEndtimeDatetime());
                jedis.lpush("hstxpt", JSON.toJSONString(ptRedisBean));
            }
            //获取该团拼团成功人数
            QueryWrapper<GoodPtcodeInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("pid", goodPtcodeInfo.getPid());
            queryWrapper.eq("is_join", 1);
            long count = goodPtcodeInfoMapper.selectCount(queryWrapper);
            if (goodPtcode.getPtnumber() == count) {
                //该团已成功,已满员,执行退款
                uOrderSerivce.goodsOrderRefund(goodsOrder, order);
            } else {
                //加入该团
                goodPtcodeInfo.setIsJoin(1);
                goodPtcodeInfoMapper.updateById(goodPtcodeInfo);
                grouping();
                //如果该团剩余1个位置,加入之后即为拼团成功
                if ((goodPtcode.getPtnumber() - 1) == count) {
                    //拼团成功,下单
                    updatePtOrderSuccess(goodPtcode);
                    updateGoodsOrderSuccess(goodPtcode);
                    //移除redis对应信息
                    List<String> hstxpt = jedis.lrange("hstxpt", 0, -1);
                    for (int i = 0; i < hstxpt.size(); i++) {
                        String json = hstxpt.get(i);
                        PtRedisBean bean = JSON.parseObject(json, PtRedisBean.class);
                        if (bean.getId().equals(goodPtcode.getId())) {
                            jedis.lrem("hstxpt", i, json);
                        }
                    }
                }
            }
        }
    }

    public void grouping() {
        QueryWrapper<GoodsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_order_id", order.getId());
        GoodsOrder goodsOrder = goodsOrderMapper.selectOne(queryWrapper);
        goodsOrder.setOrderState(7);
        goodsOrder.setIfPay(1);
        goodsOrder.updateById();
        Map<String, Object> map = new HashMap<>();
        map.put("order_id", goodsOrder.getId());
        List<GoodsOrderDetail> goodsOrderDetails = goodsOrderDetailMapper.selectByMap(map);
        for (GoodsOrderDetail goodsOrderDetail : goodsOrderDetails) {
            goodsOrderDetail.setOrderState(7);
            goodsOrderDetail.setIfPay(1);
            goodsOrderDetail.updateById();
        }
    }

    /**
     * 拼团成功修改拼团表信息
     *
     * @param goodPtcode
     */
    public void updatePtOrderSuccess(GoodPtcode goodPtcode) {
        //修改团长信息,为拼团成功
        goodPtcode.setStatus(2);
        goodPtcodeMapper.updateById(goodPtcode);
    }

    /**
     * 拼团成功修改订单状态,库存,销量
     *
     * @param goodPtcode
     */
    @Async
    public void updateGoodsOrderSuccess(GoodPtcode goodPtcode) {
        Integer id = goodPtcode.getId();
        Map<String, Object> map = new HashMap<>();
        map.put("pid", id);
        map.put("is_join", 1);
        List<GoodPtcodeInfo> goodPtcodeInfos = goodPtcodeInfoMapper.selectByMap(map);
        List<Integer> ids = new ArrayList<>();
        for (GoodPtcodeInfo goodPtcodeInfo : goodPtcodeInfos) {
            ids.add(goodPtcodeInfo.getGoodsOrderId());
        }
        List<GoodsOrder> goodsOrders = goodsOrderMapper.selectBatchIds(ids);
        for (GoodsOrder goodsOrder : goodsOrders) {
            goodsOrder.setOrderState(2);
            goodsOrderMapper.updateById(goodsOrder);
            //1.修改库存,sku销量
            Integer goodsOrderId = goodsOrder.getId();
            QueryWrapper<GoodsOrderDetail> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("order_id", goodsOrderId);
            GoodsOrderDetail detail = goodsOrderDetailMapper.selectOne(queryWrapper);
            GoodsSku goodsSku = goodsSkuMapper.selectById(detail.getSkuId());
            Integer quantity = detail.getQuantity();
            goodsSku.setStockNum(goodsSku.getStockNum() - quantity);
            int sales = goodsSku.getGoodsSales() == null ? 0 : goodsSku.getGoodsSales();
            goodsSku.setGoodsSales(sales + quantity);
            goodsSkuMapper.updateById(goodsSku);
            if (goodsSku.getStockNum() < 0) {
                //标记为缺货
                detail.setLack(1);
                QueryWrapper<GoodPtcodeInfo> infoQueryWrapper = new QueryWrapper<>();
                infoQueryWrapper.eq("goods_order_id", goodsOrderId);
                GoodPtcodeInfo goodPtcodeInfo = goodPtcodeInfoMapper.selectOne(infoQueryWrapper);
                goodPtcodeInfo.setLack(1);
                goodPtcodeInfoMapper.updateById(goodPtcodeInfo);
            }
            detail.setOrderState(2);
            detail.updateById();
            //2.修改商品销量
            GoodPtgood ptgood = new LambdaQueryChainWrapper<>(goodPtgoodMapper)
                    .eq(GoodPtgood::getPtgoodsId, goodPtcode.getPtgoodsId())
                    .one();
            Goods goods = goodsMapper.selectById(ptgood.getGoodsId());
            sales = goods.getSalesActual() == null ? 0 : goods.getSalesActual();
            goods.setSalesActual(sales + quantity);
            goods.updateById();
            //3.修改拼团商品销量
            GoodPtgood goodPtgood = goodPtgoodMapper.selectById(goodPtcode.getPtgoodsId());
            sales = goodPtgood.getPtSales() == null ? 0 : goodPtgood.getPtSales();
            goodPtgood.setPtSales(sales + quantity);
            goodPtgoodMapper.updateById(goodPtgood);
            //分润
            if (goodPtcode.getPtUserId() != null) {
                benefitService.benfit(goodsOrder, goodPtcode.getPtUserId());
            } else {
                benefitService.benfit(goodsOrder);
            }
        }
    }

    /**
     * 确定绑定关系
     */
    public void updateMemberReferrer() {
        //如果有绑定关系且没有绑定的上的,绑定关系
        MemberReferrer memberReferrer = new LambdaQueryChainWrapper<>(memberReferrerMapper)
                .eq(MemberReferrer::getMemberId, order.getUserId())
                .one();
        if (memberReferrer != null && memberReferrer.getStatus() == 0) {
            memberReferrer.setStatus(1);
            memberReferrer.updateById();
        }
    }

    /**
     * 更换绑定关系
     *
     * @param referrerId
     */
    public void updateMemberReferrer(Integer referrerId) {
        if (referrerId.equals(order.getUserId())) {
            //推荐人为自己本身
            return;
        }
        //如果有绑定关系且没有绑定的上的,绑定关系
        MemberReferrer memberReferrer = new LambdaQueryChainWrapper<>(memberReferrerMapper)
                .eq(MemberReferrer::getMemberId, order.getUserId())
                .one();
        if (memberReferrer != null) {
            memberReferrer.setReferrerId(referrerId);
            memberReferrer.setStatus(1);
            memberReferrer.updateById();
        } else {
            memberReferrer = new MemberReferrer();
            memberReferrer.setMemberId(order.getUserId());
            memberReferrer.setReferrerId(referrerId);
            memberReferrer.setStatus(1);
            memberReferrer.insert();
        }
    }


    @Deprecated
    public void getDATABonus() {
        Double totalFee = order.getTotalFee().doubleValue() / 100;
        QueryWrapper<IntegralAccess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("access_type", 5);
        IntegralAccess integralAccess = integralAccessMapper.selectOne(queryWrapper);
        Double integralValue = integralAccess.getIntegralValue();
        totalFee *= integralValue;
        //获取人数
        List<Integer> memberId = iMemberTreeService.getTenMemberId(order.getUserId());
        if (memberId != null && memberId.size() > 0) {
            //每个人获取的红利
            double bonus = totalFee / memberId.size();
            List<Member> members = memberMapper.selectBatchIds(memberId);
            for (Member member : members) {
                BigDecimal bigDecimal = member.getNewCurrency() == null ? BigDecimal.valueOf(0) : member.getNewCurrency();
                member.setNewCurrency(bigDecimal.add(BigDecimal.valueOf(bonus)));
                memberMapper.updateById(member);
                MemberCurrencyRecord currencyRecord = new MemberCurrencyRecord();
                currencyRecord.setMemberId(member.getId());
                currencyRecord.setCurrencyChange(BigDecimal.valueOf(bonus));
                currencyRecord.setDescription("+" + bonus);
                currencyRecord.setCreateTime(new Date());
                //记录变化
                MemberDataBonusRecord record = new MemberDataBonusRecord();
                record.setMemberId(member.getId());
                record.setBonusChange(BigDecimal.valueOf(bonus));
                record.setDescription("+" + bonus);
                record.setCreateTime(new Date());
                record.insert();
            }
        }
    }

    /**
     * 获取新民豆
     */
    @Deprecated
    public void getNewBeans() {
        QueryWrapper<IntegralAccess> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("access_type", 4);
        IntegralAccess integralAccess = integralAccessMapper.selectOne(queryWrapper);
        Double integralValue = integralAccess.getIntegralValue();
        Double totalFee = order.getTotalFee().doubleValue() / 100;
        double integral = totalFee * integralValue;
        Member member = memberMapper.selectById(order.getUserId());
        double integrals = integral + member.getNewBeans().doubleValue();
        member.setNewBeans(BigDecimal.valueOf(integrals));
        memberMapper.updateById(member);
        MemberBeansRecord record = new MemberBeansRecord();
        record.setMemberId(member.getId());
        record.setBeansChange(BigDecimal.valueOf(integral));
        record.setDescription("消费" + totalFee + "元获取");
        memberBeansRecordMapper.insert(record);
    }

    @Override
    public void rechargePayNotify(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
//            LambdaQueryChainWrapper<UOrder> uOrderLambdaQueryChainWrapper = new LambdaQueryChainWrapper<>(uOrderMapper);
//            UOrder uOrder = uOrderLambdaQueryChainWrapper.eq(UOrder::getId, order).one();
//            LambdaQueryChainWrapper<Member> memberLambdaQueryChainWrapper = new LambdaQueryChainWrapper<>(memberMapper);
//            Member member = memberLambdaQueryChainWrapper.eq(Member::getId, uOrder.getUserId()).one();
//            UOrder uOrder = uOrderMapper.selectById(payNotify.getOrderId());
            //用户充值余额
            Member member = memberMapper.selectById(order.getUserId());
            BigDecimal cashAmount = member.getCashAmount();
            BigDecimal price = new BigDecimal(payNotify.getOrderAmt()).divide(BigDecimal.valueOf(100), 2);
            member.setCashAmount(cashAmount.add(price));
            member.updateById();
            //充值金额记录
            MemberAmountLog memberAmountLog = new MemberAmountLog();
            memberAmountLog.setMemberId(member.getId().longValue());
            memberAmountLog.setCreateTime(new Date());
            memberAmountLog.setType("2");
            memberAmountLog.setPrice(price);
            memberAmountLog.setBalance(member.getCashAmount());
            memberAmountLog.setRemark("余额充值");
            memberAmountLog.setSource(1);
            memberAmountLogMapper.insert(memberAmountLog);
        }
    }

    /**
     * 会员提现回调
     *
     * @param poboNotify
     */
    @Override
    public void poboNotifyUrl(PoboNotify poboNotify) {
        if (poboNotify == null) {
            return;
        }
        if ("01".equals(poboNotify.getOrderStatus())) {
            FactoryCashInfo factoryCashInfo = new LambdaQueryChainWrapper<>(factoryCashInfoMapper)
                    .eq(FactoryCashInfo::getRequestSn, poboNotify.getRequestSN())
                    .one();
            Member member = memberMapper.selectById(factoryCashInfo.getMemberId());
            BigDecimal xnAmt = StringUtils.isNotEmpty(poboNotify.getTxnAmt()) ? new BigDecimal(poboNotify.getTxnAmt()) : BigDecimal.ZERO;
            BigDecimal fee = StringUtils.isNotEmpty(poboNotify.getFee()) ? new BigDecimal(poboNotify.getFee()) : BigDecimal.ZERO;
            BigDecimal money = (xnAmt.add(fee)).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
            member.setCashAmount(member.getCashAmount().subtract(money));
            member.updateById();
            MemberAmountLog memberAmountLog = new MemberAmountLog();
            memberAmountLog.setMemberId(member.getId().longValue());
            memberAmountLog.setCreateTime(new Date());
            memberAmountLog.setType("2");
            memberAmountLog.setPrice(money);
            memberAmountLog.setBalance(member.getCashAmount());
            memberAmountLog.setRemark("用户提现");
            memberAmountLog.setSource(7);
            memberAmountLog.insert();
        }
    }
}
