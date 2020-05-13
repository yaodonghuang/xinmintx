package com.xinmintx.hstx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.xinmintx.hstx.configuration.pay.PayConfig;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.bo.WechatPayBo;
import com.xinmintx.hstx.pojo.entity.MemberCard;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.pojo.vo.MemberCardOrderVo;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.IMemberTreeService;
import com.xinmintx.hstx.service.MemberDataService;
import com.xinmintx.hstx.service.WechatPayService;
import com.xinmintx.hstx.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberUpgradeMapper memberUpgradeMapper;
    @Autowired
    private MemberReferrerMapper memberReferrerMapper;
    @Autowired
    private WechatPayService wechatPayService;
    @Autowired
    private MemberDataBonusRecordMapper memberDataBonusRecordMapper;
    @Autowired
    private PayConfig payConfig;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MemberCardCostMapper memberCardCostMapper;
    @Autowired
    private MemberCardOrderMapper memberCardOrderMapper;
    @Autowired
    private MemberCardOrderDetailMapper memberCardOrderDetailMapper;
    @Autowired
    private MemberCardInfoMapper memberCardInfoMapper;
    @Autowired
    private MemberAmountLogMapper memberAmountLogMapper;
    @Autowired
    private MemberCurrencyRecordMapper memberCurrencyRecordMapper;
    @Autowired
    private IMemberTreeService memberTreeService;
    @Autowired
    private MemberDataService memberDataService;
    @Autowired
    private Jedis jedis;

    /**
     * 消费者升级银卡订单
     *
     * @param member
     * @return
     */
    @Override
    public Map upgradeSilverByUserid(Member member, Integer memberId) {
        Integer memberType = member.getMemberType();
        if (memberType == 1) {
            MemberUpgrade memberUpgrade = memberUpgradeMapper.selectById(2);
            double money = memberUpgrade.getMoney();
            String description = memberUpgrade.getDescription();
            WechatPayBo wechatPayBo = new WechatPayBo();
            wechatPayBo.setOrderId(String.valueOf(System.currentTimeMillis()));
            wechatPayBo.setUserId(member.getId());
            wechatPayBo.setPrdDesc(description);
            wechatPayBo.setOrderAmt((long) (money * 100));
            wechatPayBo.setOpenId(member.getOpenid());
            wechatPayBo.setRetUrl(payConfig.getGoldCardNotifyUrl());
            wechatPayBo.setType(2);
            if (memberId != null) {
                wechatPayBo.setAttach(String.valueOf(memberId));
            }
            return wechatPayService.unifiedorder(wechatPayBo);
        }
        return null;
    }

    /**
     * 根据token获取member对象
     *
     * @param token
     * @return member对象
     */
    @Override
    public Member findMemberByToken(String token) {
        Member member = new LambdaQueryChainWrapper<>(memberMapper).eq(Member::getToken, token).one();
        if (member == null) {
            return null;
        }
        int memberType = member.getMemberType();
        if (memberType > 0 && member.getCardStatus() != null && member.getCardStatus() == 1) {
            //购买了会员卡的,并且是还未过期的
            Date cardIndate = member.getCardIndate();
            if (cardIndate != null) {
                int i = DateUtil.compareDateWithNow(cardIndate);
                if (i < 0) {
                    //会员已过期
                    member.setCardStatus(0);
                    member.updateById();
                }
            }
        }
        member.setName(EmojiUtil.utfemojiRecovery(member.getName()));
        return member;
    }

    @Override
    public MemberVo getMemberByToken(String token) {
        Member member = findMemberByToken(token);
        if (member == null) {
            return null;
        }
        member.setName(EmojiUtil.utfemojiRecovery(member.getName()));
        MemberVo memberVo = FieldUtils.fieldTrans(member, MemberVo.class);
        if (memberVo.getCardId() != null) {
            //获取会员卡信息
            MemberCardInfo info = memberCardInfoMapper.selectById(memberVo.getCardId());
            if (info != null) {
                if (!member.getMemberType().equals(info.getCardType())) {
                    info.setCardType(member.getMemberType());
                    info.updateById();
                }
                memberVo.setCardInfo(info);
            }
        }
        //获取收益
        List<MemberAmountLog> list = new LambdaQueryChainWrapper<>(memberAmountLogMapper)
                .eq(MemberAmountLog::getMemberId, memberVo.getId())
                .eq(MemberAmountLog::getType, 2)           //可用金额
                .in(MemberAmountLog::getSource, 2, 3, 4)  //排除充值
                .list();
        BigDecimal money = new BigDecimal(0.0);
        if (list != null && list.size() > 0) {
            for (MemberAmountLog memberAmountLog : list) {
                money = money.add(memberAmountLog.getPrice());
            }
        }
        memberVo.setEarnings(money);
        //获取DATA资产
        Integer count = memberTreeService.getNextMemberQty(memberVo.getId());
        //下游人数/系统匹配值
        int memberCount = count == null ? 0 : count;
        List<MemberVo> memberVos = memberDataService.getRelationList(memberVo.getId());
        //关联金卡值
        int goldCount = memberVos.size();
        //data资产值
        int dataCount = memberCount + goldCount;
        memberVo.setDataCount(dataCount);
        memberVo.setMemberCount(memberCount);
        memberVo.setGoldCount(goldCount);
        return memberVo;
    }

    /**
     * 保存微信信息
     *
     * @param member
     */
    @Override
    public void updateMember(Member member) {
        memberMapper.updateById(member);
    }

    @Override
    public Member selectMemberByOpenid(String openid) {
        Member member = new LambdaQueryChainWrapper<>(memberMapper)
                .eq(Member::getOpenid, openid)
                .one();
        if (member != null) {
            member.setName(EmojiUtil.utfemojiRecovery(member.getName()));
        }
        return member;
    }


    /**
     * 获取新民币
     *
     * @param token 用户token
     * @return 新民币
     */
    @Override
    public ResultCode getNewCurrencyByToken(String token) {
        ResultCode<Object> resultCode = new ResultCode<>();
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("token", token);
        List<Member> members = memberMapper.selectByMap(hashMap);
        //有新民币
        if (members != null && members.size() > 0) {
            resultCode.setCode(200);
            resultCode.setData(members.get(0).getNewCurrency());
            resultCode.setMsg("SUCCESS");
            return resultCode;
            //无新民币
        } else {
            resultCode.setCode(500);
            resultCode.setData(null);
            resultCode.setMsg("FAIL");
            return resultCode;
        }

    }

    /**
     * 获取DATA红利
     *
     * @param memberId 会员id
     * @return ResultCode
     */
    @Override
    public double getDataBonusRecord(Integer memberId) {
        Map<String, Object> hashMap = new HashMap<>();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        hashMap.put("DATE_FORMAT(create_time,'%Y-%m-%d')", dateString);
        hashMap.put("member_id", memberId);
        List<MemberDataBonusRecord> memberDataBonusRecords = memberDataBonusRecordMapper.selectByMap(hashMap);

        if (memberDataBonusRecords != null && memberDataBonusRecords.size() > 0) {
            double flag = 0.0;
            for (MemberDataBonusRecord memberDataBonusRecord : memberDataBonusRecords) {
                flag += memberDataBonusRecord.getBonusChange().doubleValue();
            }
            return flag;
        } else {
            return 0;
        }
    }

    @Override
    public Member insertOrSelect(Member member, Integer referrerId) {
        Member one = new LambdaQueryChainWrapper<>(memberMapper)
                .eq(Member::getOpenid, member.getOpenid())
                .one();
        if (one != null) {
            one.setName(member.getName());
            one.setGender(member.getGender());
            one.setAvatarUrl(member.getAvatarUrl());
            one.updateById();
            if (referrerId != null) {
                MemberReferrer memberReferrer = new LambdaQueryChainWrapper<>(memberReferrerMapper)
                        .eq(MemberReferrer::getMemberId, one.getId())
                        .one();
                if (memberReferrer != null && memberReferrer.getStatus() == 0) {
                    memberReferrer.setReferrerId(referrerId);
                    memberReferrer.updateById();
                }
            }
            return one;
        }
        member.setIsReview(1);
        member.insert();
        if (referrerId != null) {
            //推荐人
            Member referrer = memberMapper.selectById(referrerId);
            MemberReferrer memberReferrer = new MemberReferrer();
            memberReferrer.setReferrerId(referrer.getId());
            memberReferrer.setMemberId(member.getId());
            memberReferrer.setId(0);
            memberReferrer.insert();
        }
        return member;
    }

    /**
     * 分配礼包
     *
     * @param member 会员
     * @param code   地区代码
     * @return ResultCode
     */
    @Override
    public ResultCode distribution(Member member, String code) {
        ResultCode<Object> resultCode = new ResultCode<>();
        Integer giftStart = member.getGiftStart();
        if (giftStart != null && giftStart == 1) {
            User one = new LambdaQueryChainWrapper<>(userMapper)
                    .eq(User::getRegionCode, code)
                    .eq(User::getUserRole, 3)
                    .eq(User::getStatus, 1)
                    .eq(User::getIsReview, 1).one();
            if (one != null) {
                Integer platformCount = member.getPlatformCount();
                if (platformCount == null) {
                    member.setPlatformCount(1);
                } else {
                    member.setPlatformCount(platformCount + 1);
                }
                Integer merchantCount = member.getMerchantCount();
                if (merchantCount == null) {
                    member.setMerchantCount(1);
                } else {
                    member.setMerchantCount(merchantCount + 1);
                }
                Integer branchOfficeCount = member.getBranchOfficeCount();
                if (branchOfficeCount == null) {
                    member.setBranchOfficeCount(1);
                } else {
                    member.setBranchOfficeCount(branchOfficeCount + 1);
                }
            } else {
                Integer platformCount = member.getPlatformCount();
                if (platformCount == null) {
                    member.setPlatformCount(2);
                } else {
                    member.setPlatformCount(platformCount + 2);
                }
            }
            member.setGiftStart(0);
            member.updateById();
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
        }
        return resultCode;
    }

    /**
     * 用户退出
     *
     * @param member 会员对象
     * @return ResultCode
     */
    @Override
    public ResultCode loginOut(Member member) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        if (member != null) {
            member.setToken("");
            member.updateById();
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        }
        return resultCode;
    }

    /**
     * 保存卡信息,生成订单
     *
     * @param memberCardInfo
     * @return
     */
    @Override
    public ResultCode saveMemberCardInfo(MemberCardInfo memberCardInfo, Member member) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        //判断用户会员卡信息
        if (member.getMemberType() > 0) {
            //已拥有会员卡
            if (member.getCardStatus() > 0) {
                //会员卡还未过期
                if (member.getMemberType().equals(memberCardInfo.getCardType())) {
                    resultCode.setMsg("会员卡已办理,请勿重复办理");
                    return resultCode;
                }
                if (member.getMemberType() > memberCardInfo.getCardType()) {
                    resultCode.setMsg("会员卡不可向下申请");
                    return resultCode;
                }
            }
        }
        boolean isCheck = checkMemberCardInfo(memberCardInfo);
        if (isCheck) {
            resultCode.setMsg("参数为空");
            return resultCode;
        }
        //检查是否E卡,是否已经办过卡
        boolean bool = checkCardInfo(memberCardInfo, member);
        if (bool) {
            resultCode.setMsg("当前手机号或者微信号已经申请过新民E卡");
            return resultCode;
        }
        //保存会员卡信息
        saveCardInfo(memberCardInfo, member);

        MemberCardCost memberCardCost = new LambdaQueryChainWrapper<>(memberCardCostMapper)
                .eq(MemberCardCost::getType, memberCardInfo.getCardType())
                .one();
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal cost = memberCardCost.getCost();
        MemberCardCost cardCost = null;
        totalPrice = totalPrice.add(cost);
        if (memberCardInfo.getCardType().equals(MemberCard.GOLD_CARD) && memberCardInfo.getEntityCard() == 1) {
            //新民金卡且需要实体卡,加上制卡费
            cardCost = new LambdaQueryChainWrapper<>(memberCardCostMapper)
                    .eq(MemberCardCost::getType, 0)
                    .one();
            totalPrice = totalPrice.add(cardCost.getCost());
        }
        MemberCardOrder memberCardOrder = new MemberCardOrder();
        memberCardOrder.setOrderType(1);
        memberCardOrder.setDescription("开通" + memberCardCost.getDescription());
        memberCardOrder.setTotalPrice(totalPrice);
        memberCardOrder.setPayStatus(0);
        memberCardOrder.setOrderStatus(1);
        memberCardOrder.insert();
        List<MemberCardOrderDetail> details = new ArrayList<>();
        MemberCardOrderDetail detail = new MemberCardOrderDetail();
        detail.setCardOrderId(memberCardOrder.getId());
        detail.setCardId(memberCardInfo.getId());
        detail.setOrderType(memberCardCost.getType());
        detail.setDescription(memberCardCost.getDescription());
        detail.setPrice(memberCardCost.getCost());
        detail.insert();
        details.add(detail);
        if (cardCost != null) {
            MemberCardOrderDetail orderDetail = new MemberCardOrderDetail();
            orderDetail.setCardOrderId(memberCardOrder.getId());
            orderDetail.setOrderType(cardCost.getType());
            orderDetail.setDescription(cardCost.getDescription());
            orderDetail.setPrice(cardCost.getCost());
            orderDetail.insert();
            details.add(orderDetail);
        }
        MemberCardOrderVo memberCardOrderVo = FieldUtils.fieldTrans(memberCardOrder, MemberCardOrderVo.class);
        memberCardOrderVo.setDetails(details);
        memberCardOrderVo.setCardInfo(memberCardInfo);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(memberCardOrderVo);
        return resultCode;
    }

    /**
     * 验证是否办过E卡
     *
     * @param memberCardInfo
     * @param member         true 办理过, false 未办理
     */
    private boolean checkCardInfo(MemberCardInfo memberCardInfo, Member member) {
        if (memberCardInfo.getCardType() == 1) {
            MemberCardInfo one = new LambdaQueryChainWrapper<>(memberCardInfoMapper)
                    .eq(MemberCardInfo::getCardType, 1)
                    .eq(MemberCardInfo::getStatus, 1)
                    .eq(MemberCardInfo::getCellphone, member.getCellphone())
                    .last("limit 1")
                    .one();
            if (one != null) {
                return true;
            }
            one = new LambdaQueryChainWrapper<>(memberCardInfoMapper)
                    .eq(MemberCardInfo::getCardType, 1)
                    .eq(MemberCardInfo::getStatus, 1)
                    .eq(MemberCardInfo::getOpenid, member.getOpenid())
                    .last("limit 1")
                    .one();
            return one != null;
        }
        return false;
    }

    /**
     * 保存会员卡信息
     *
     * @param memberCardInfo
     * @param member
     */
    private void saveCardInfo(MemberCardInfo memberCardInfo, Member member) {
        if (memberCardInfo.getCardType() == 1) {
            memberCardInfo.setCellphone(member.getCellphone());
            memberCardInfo.setOpenid(member.getOpenid());
            memberCardInfo.setStatus(0);
        }
        //保存卡信息
        memberCardInfo.insert();
        String cardNo = generateMemberCardNo(memberCardInfo, member);
        memberCardInfo.setCardNumber(cardNo);
        memberCardInfo.updateById();
    }

    /**
     * 生成会员卡号
     *
     * @param memberCardInfo
     * @param member
     * @return
     */
    private String generateMemberCardNo(MemberCardInfo memberCardInfo, Member member) {
        StringBuilder builder = new StringBuilder();
        //生成会员卡号(前6位为身份证前6位,无身份证为手机号前6位,7-8,01位男,00位女,9-12为用户生日,13-16为自增/主键)
        String idcard = memberCardInfo.getIdcard();
        if (StringUtils.isNotBlank(idcard)) {
            builder.append(idcard, 0, 4);
            builder.append(" ");
            builder.append(idcard, 4, 6);
        } else {
            String cellphone = memberCardInfo.getCellphone();
            builder.append(cellphone, 0, 4);
            builder.append(" ");
            builder.append(cellphone, 4, 6);
        }
        if (member.getGender() == 1) {
            builder.append("01");
        } else {
            builder.append("00");
        }
        String birthday = memberCardInfo.getBirthday();
        // 截取月份
        String month = birthday.substring(0, 2);
        // 截取天
        String day = birthday.substring(2, 4);
        builder.append(" ");
        builder.append(month);
        builder.append(day);
        builder.append(" ");
        int id = memberCardInfo.getId();
        //如果id超过9999就减去9999,直到小于9999
        while (id > 9999) {
            id -= 9999;
        }
        String number = getNumber(String.valueOf(id));
        builder.append(number);
        return builder.toString();
    }

    private String getNumber(String id) {
        if (id.length() < 4) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < (4 - id.length()); i++) {
                builder.append(0);
            }
            builder.append(id);
            return builder.toString();
        } else {
            return id;
        }
    }

    /**
     * 会员卡续费
     *
     * @param member
     * @return
     */
    @Override
    public MemberCardOrderVo memberCardRenew(Member member) {
        if (member.getMemberType() == 0) {
            return null;
        }
        MemberCardInfo memberCardInfo = null;
        //有会员卡信息的
        if (member.getCardId() != null) {
            //获取用户原本的卡信息
            memberCardInfo = memberCardInfoMapper.selectById(member.getCardId());
            if (memberCardInfo != null && !member.getMemberType().equals(memberCardInfo.getCardType())) {
                memberCardInfo.setCardType(member.getMemberType());
                memberCardInfo.updateById();
            }
        }
        //获取对应会员卡金额
        MemberCardCost memberCardCost = new LambdaQueryChainWrapper<>(memberCardCostMapper)
                .eq(MemberCardCost::getType, member.getMemberType())
                .one();
        BigDecimal cost = memberCardCost.getCost();
        //生成订单
        MemberCardOrder memberCardOrder = new MemberCardOrder();
        memberCardOrder.setOrderType(2);
        memberCardOrder.setDescription("续费" + memberCardCost.getDescription());
        memberCardOrder.setTotalPrice(cost);
        memberCardOrder.setPayStatus(0);
        memberCardOrder.setOrderStatus(1);
        memberCardOrder.insert();
        //生成订单详情
        MemberCardOrderDetail detail = new MemberCardOrderDetail();
        detail.setCardOrderId(memberCardOrder.getId());
        if (member.getCardId() != null) {
            detail.setCardId(member.getCardId());
        }
        detail.setOrderType(member.getMemberType());
        detail.setDescription(memberCardCost.getDescription());
        detail.setPrice(memberCardCost.getCost());
        detail.insert();
        MemberCardOrderVo memberCardOrderVo = FieldUtils.fieldTrans(memberCardOrder, MemberCardOrderVo.class);
        List<MemberCardOrderDetail> list = new ArrayList<>();
        list.add(detail);
        memberCardOrderVo.setDetails(list);
        memberCardOrderVo.setCardInfo(memberCardInfo);
        return memberCardOrderVo;
    }

    /**
     * 会员卡制卡
     *
     * @param memberCardInfo
     * @param member
     * @return
     */
    @Override
    public ResultCode<MemberCardOrderVo> makeMemberCard(MemberCardInfo memberCardInfo, Member member) {
        ResultCode<MemberCardOrderVo> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        if (member.getMemberType() < MemberCard.GOLD_CARD) {
            //未拥有新民金卡
            resultCode.setMsg("未拥有新民金卡");
            return resultCode;
        }
        if (member.getCardId() != null) {
            //拥有会员卡信息
            MemberCardInfo info = memberCardInfoMapper.selectById(member.getCardId());
            if (info != null && info.getEntityCard() != null && info.getEntityCard() == 1) {
                //已拥有实体卡,应该补卡
                resultCode.setMsg("已拥有实体卡,请勿重复操作,如需补卡,请申请挂失补卡");
                return resultCode;
            }
        }
        //保存会员卡信息
        //制卡时未拥有实体卡,支付完成后修改状态
        memberCardInfo.setEntityCard(0);
        boolean isCheck = checkMemberCardInfo(memberCardInfo);
        if (isCheck) {
            resultCode.setMsg("参数为空");
            return resultCode;
        }
        saveCardInfo(memberCardInfo, member);
        member.setCardId(memberCardInfo.getId());
        member.updateById();
        //获取制卡费
        MemberCardCost memberCardCost = new LambdaQueryChainWrapper<>(memberCardCostMapper)
                .eq(MemberCardCost::getType, MemberCard.CARD_COST)
                .one();
        BigDecimal cost = memberCardCost.getCost();
        //生成订单
        MemberCardOrder memberCardOrder = new MemberCardOrder();
        memberCardOrder.setOrderType(3);
        memberCardOrder.setDescription("制作实体卡");
        memberCardOrder.setTotalPrice(cost);
        memberCardOrder.setPayStatus(0);
        memberCardOrder.setOrderStatus(1);
        memberCardOrder.insert();
        //生成订单详情
        MemberCardOrderDetail detail = new MemberCardOrderDetail();
        detail.setCardOrderId(memberCardOrder.getId());
        detail.setCardId(memberCardInfo.getId());
        detail.setOrderType(memberCardCost.getType());
        detail.setDescription(memberCardCost.getDescription());
        detail.setPrice(memberCardCost.getCost());
        detail.insert();
        MemberCardOrderVo memberCardOrderVo = FieldUtils.fieldTrans(memberCardOrder, MemberCardOrderVo.class);
        List<MemberCardOrderDetail> list = new ArrayList<>();
        list.add(detail);
        memberCardOrderVo.setDetails(list);
        memberCardOrderVo.setCardInfo(memberCardInfo);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(memberCardOrderVo);
        return resultCode;
    }

    /**
     * 会员卡补卡
     *
     * @param member
     * @return
     */
    @Override
    public ResultCode<MemberCardOrderVo> reissueMemberCard(Member member) {
        ResultCode<MemberCardOrderVo> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        if (member.getMemberType() == 0) {
            //为拥有会员卡
            resultCode.setMsg("您还未购买会员卡");
            return resultCode;
        }
        if (member.getCardId() == null) {
            //没有信息
            resultCode.setMsg("您还未填制作实体卡,请先申请制作实体卡");
            return resultCode;
        }
        //获取会员卡信息
        MemberCardInfo memberCardInfo = memberCardInfoMapper.selectById(member.getCardId());
        if (memberCardInfo == null) {
            //没有信息
            resultCode.setMsg("您还未填制作实体卡,请先申请制作实体卡");
            return resultCode;
        }
        if (memberCardInfo.getEntityCard() == null || memberCardInfo.getEntityCard() == 0) {
            //还未申请实体卡
            resultCode.setMsg("您还未申请实体卡,请先申请制作实体卡");
            return resultCode;
        }
        //获取制卡费
        MemberCardCost memberCardCost = new LambdaQueryChainWrapper<>(memberCardCostMapper)
                .eq(MemberCardCost::getType, MemberCard.CARD_COST)
                .one();
        BigDecimal cost = memberCardCost.getCost();
        //生成新的卡号
        memberCardInfo.setId(null);
        saveCardInfo(memberCardInfo, member);
        //生成订单
        MemberCardOrder memberCardOrder = new MemberCardOrder();
        memberCardOrder.setOrderType(4);
        memberCardOrder.setDescription("挂失补卡");
        memberCardOrder.setTotalPrice(cost);
        memberCardOrder.setPayStatus(0);
        memberCardOrder.setOrderStatus(1);
        memberCardOrder.insert();
        //生成订单详情
        MemberCardOrderDetail detail = new MemberCardOrderDetail();
        detail.setCardOrderId(memberCardOrder.getId());
        detail.setCardId(memberCardInfo.getId());
        detail.setOrderType(memberCardCost.getType());
        detail.setDescription(memberCardCost.getDescription());
        detail.setPrice(memberCardCost.getCost());
        detail.insert();
        MemberCardOrderVo memberCardOrderVo = FieldUtils.fieldTrans(memberCardOrder, MemberCardOrderVo.class);
        List<MemberCardOrderDetail> list = new ArrayList<>();
        list.add(detail);
        memberCardOrderVo.setDetails(list);
        memberCardOrderVo.setCardInfo(memberCardInfo);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(memberCardOrderVo);
        return resultCode;
    }

    /**
     * 获取会员卡订单信息
     *
     * @param id
     * @return
     */
    @Override
    public ResultCode<MemberCardOrderVo> getMemberCardOrder(Integer id) {
        ResultCode<MemberCardOrderVo> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        if (id == null) {
            resultCode.setMsg("订单不存在");
            return resultCode;
        }
        MemberCardOrder memberCardOrder = memberCardOrderMapper.selectById(id);
        if (memberCardOrder == null) {
            resultCode.setMsg("订单不存在");
            return resultCode;
        }
        List<MemberCardOrderDetail> details = new LambdaQueryChainWrapper<>(memberCardOrderDetailMapper)
                .eq(MemberCardOrderDetail::getCardOrderId, memberCardOrder.getId())
                .list();
        if (details.size() < 1) {
            resultCode.setMsg("订单不存在");
            return resultCode;
        }
        MemberCardOrderVo memberCardOrderVo = FieldUtils.fieldTrans(memberCardOrder, MemberCardOrderVo.class);
        memberCardOrderVo.setDetails(details);
        for (MemberCardOrderDetail detail : details) {
            if (detail.getCardId() != null) {
                MemberCardInfo info = memberCardInfoMapper.selectById(detail.getCardId());
                if (info != null) {
                    memberCardOrderVo.setCardInfo(info);
                }
            }
        }
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(memberCardOrderVo);
        return resultCode;
    }

    /**
     * 创建会员卡支付信息
     *
     * @param memberCardOrderVo
     * @param member
     * @return
     */
    @Override
    public ResultCode createMemberCardOrder(MemberCardOrderVo memberCardOrderVo, Member member) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("SUCCESS");
        MemberCardOrder memberCardOrder = memberCardOrderMapper.selectById(memberCardOrderVo.getId());
        if (memberCardOrder.getPayStatus() == 1) {
            //订单已支付
            resultCode.setMsg("订单已支付");
            return resultCode;
        }
        int orderType = memberCardOrder.getOrderType();
        switch (orderType) {
            case 1:
                //购买会员卡
                return createOrderByOrderId(memberCardOrder, memberCardOrderVo, member);
            case 2:
                //会员卡续费
                return memberCardRenew(memberCardOrder, memberCardOrderVo, member);
            case 3:
            case 4:
                //制卡/补卡
                return makeCard(memberCardOrder, memberCardOrderVo, member);
            default:
                return null;
        }
    }

    /**
     * 购买会员卡,创建支付订单
     *
     * @param memberCardOrderVo
     * @param member
     * @return
     */
    private ResultCode createOrderByOrderId(MemberCardOrder memberCardOrder, MemberCardOrderVo memberCardOrderVo, Member member) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("SUCCESS");
        List<MemberCardOrderDetail> memberCardOrderDetails = new LambdaQueryChainWrapper<>(memberCardOrderDetailMapper)
                .eq(MemberCardOrderDetail::getCardOrderId, memberCardOrder.getId())
                .list();
        BigDecimal totalPrice = memberCardOrder.getTotalPrice();
        BigDecimal multiply = totalPrice.multiply(BigDecimal.valueOf(100));
        WechatPayBo wechatPayBo = new WechatPayBo();
        wechatPayBo.setOrderId(String.valueOf(System.currentTimeMillis()));
        wechatPayBo.setPrdDesc(memberCardOrder.getDescription());
        wechatPayBo.setOrderAmt(multiply.longValue());
        if (memberCardOrderVo.getReferrerId() != null) {
            wechatPayBo.setAttach(String.valueOf(memberCardOrderVo.getReferrerId()));
        }
        //E卡
        wechatPayBo.setRetUrl(payConfig.getECardNotifyUrl());
        wechatPayBo.setType(1);
        for (MemberCardOrderDetail memberCardOrderDetail : memberCardOrderDetails) {
            if (memberCardOrderDetail.getOrderType() != null && memberCardOrderDetail.getOrderType().equals(MemberCard.GOLD_CARD)) {
                //新民金卡
                wechatPayBo.setRetUrl(payConfig.getGoldCardNotifyUrl());
                wechatPayBo.setType(2);
            }
        }
        wechatPayBo.setOpenId(member.getOpenid());
        wechatPayBo.setUserId(member.getId());
        Map<String, Object> map = wechatPayService.unifiedorder(wechatPayBo);
        if (map != null && "SUCCESS".equals(map.get("code").toString())) {
            memberCardOrder.setOrderId((Integer) map.get("order_id"));
            memberCardOrder.setAddressId(memberCardOrderVo.getAddressId());
            memberCardOrder.setMessage(memberCardOrderVo.getMessage());
            memberCardOrder.updateById();
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(map);
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultCode;
    }

    /**
     * 会员卡续费,创建支付订单
     *
     * @param memberCardOrder
     * @param memberCardOrderVo
     * @param member
     * @return
     */
    private ResultCode memberCardRenew(MemberCardOrder memberCardOrder, MemberCardOrderVo memberCardOrderVo, Member member) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("SUCCESS");
        BigDecimal totalPrice = memberCardOrder.getTotalPrice();
        BigDecimal multiply = totalPrice.multiply(BigDecimal.valueOf(100));
        WechatPayBo wechatPayBo = new WechatPayBo();
        wechatPayBo.setOrderId(String.valueOf(System.currentTimeMillis()));
        wechatPayBo.setPrdDesc(memberCardOrder.getDescription());
        wechatPayBo.setOrderAmt(multiply.longValue());
        wechatPayBo.setAttach(String.valueOf(memberCardOrderVo.getReferrerId()));
        wechatPayBo.setRetUrl(payConfig.getCardRenewNotifyUrl());
        wechatPayBo.setType(3);
        wechatPayBo.setOpenId(member.getOpenid());
        wechatPayBo.setUserId(member.getId());
        Map<String, Object> map = wechatPayService.unifiedorder(wechatPayBo);
        if (map != null && "SUCCESS".equals(map.get("code").toString())) {
            memberCardOrder.setOrderId((Integer) map.get("order_id"));
            memberCardOrder.setAddressId(memberCardOrderVo.getAddressId());
            memberCardOrder.setMessage(memberCardOrderVo.getMessage());
            memberCardOrder.updateById();
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(map);
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultCode;
    }

    /**
     * 会员卡制卡/补卡,创建支付订单
     *
     * @param memberCardOrder
     * @param memberCardOrderVo
     * @param member
     * @return
     */
    private ResultCode makeCard(MemberCardOrder memberCardOrder, MemberCardOrderVo memberCardOrderVo, Member member) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("SUCCESS");
        BigDecimal totalPrice = memberCardOrder.getTotalPrice();
        BigDecimal multiply = totalPrice.multiply(BigDecimal.valueOf(100));
        WechatPayBo wechatPayBo = new WechatPayBo();
        wechatPayBo.setOrderId(String.valueOf(System.currentTimeMillis()));
        wechatPayBo.setPrdDesc(memberCardOrder.getDescription());
        wechatPayBo.setOrderAmt(multiply.longValue());
        wechatPayBo.setAttach(String.valueOf(memberCardOrderVo.getReferrerId()));
        wechatPayBo.setRetUrl(payConfig.getMakeCardNotifyUrl());
        wechatPayBo.setType(4);
        wechatPayBo.setOpenId(member.getOpenid());
        wechatPayBo.setUserId(member.getId());
        Map<String, Object> map = wechatPayService.unifiedorder(wechatPayBo);
        if (map != null && "SUCCESS".equals(map.get("code").toString())) {
            memberCardOrder.setOrderId((Integer) map.get("order_id"));
            memberCardOrder.setAddressId(memberCardOrderVo.getAddressId());
            memberCardOrder.setMessage(memberCardOrderVo.getMessage());
            memberCardOrder.updateById();
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(map);
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultCode;
    }

    private boolean checkMemberCardInfo(MemberCardInfo info) {
        if (info != null) {
            //E卡默认制作实体卡
            if (info.getCardType() == 1) {
                info.setEntityCard(1);
            }
            //如果不选择是否制作实体卡,默认不制作
            if (info.getEntityCard() == null || info.getEntityCard() != 1) {
                info.setEntityCard(0);
            }
            if (info.getCardType() == 1) {
                info.setEntityCard(0);
            }
            return FieldUtils.isFieldEmpty(info, "name", "pyCode", "gender", "birthday", "cellphone", "address", "entityCard", "cardType");
        }
        return false;
    }

    /**
     * 查询资金变动记录
     *
     * @param member 会员对象
     * @return ResultCode
     */
    @Override
    public ResultCode getMemberAmountLog(Member member) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(null);
        if (member == null) {
            return resultCode;
        }
        //查询全部记录
        List<MemberAmountLog> logList = new LambdaQueryChainWrapper<>(memberAmountLogMapper)
                .eq(MemberAmountLog::getMemberId, member.getId())
                .eq(MemberAmountLog::getType, 2)    //可用金额
                .orderByDesc(MemberAmountLog::getCreateTime).list(); //时间倒序
        resultCode.setData(logList);
        return resultCode;
    }

    /**
     * 查询新名币变动记录
     *
     * @param member 会员对象
     * @return ResultCode
     */
    @Override
    public ResultCode getMemberCurrencyRecord(Member member) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(null);
        if (member == null) {
            return resultCode;
        }
        //查询新名币全部记录
        List<MemberCurrencyRecord> list = new LambdaQueryChainWrapper<>(memberCurrencyRecordMapper)
                .eq(MemberCurrencyRecord::getMemberId, member.getId())
                .orderByDesc(MemberCurrencyRecord::getCreateTime).list();
        LinkedHashMap<String, HashMap> linkedHashMap = new LinkedHashMap<>();
        if (list != null && list.size() > 0) {
            for (MemberCurrencyRecord memberCurrencyRecord : list) {
                //获取新名币变动
                BigDecimal currencyChange = memberCurrencyRecord.getCurrencyChange();
                //获取时间 转为yyyy-MM格式
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
                String dateString = formatter.format(memberCurrencyRecord.getCreateTime().getTime());
                //如果是第一次添加
                if (linkedHashMap == null || linkedHashMap.size() == 0) {
                    HashMap<Object, Object> hashMap = new HashMap<>();
                    //初始化支出和收入为0
                    hashMap.put("in", new BigDecimal(0.0));
                    hashMap.put("out", new BigDecimal(0.0));
                    //正数
                    if (currencyChange.compareTo(BigDecimal.ZERO) >= 0) {
                        hashMap.put("in", currencyChange);
                        //负数
                    } else {
                        hashMap.put("out", currencyChange);
                    }
                    List<MemberCurrencyRecord> memberCurrencyRecordList = new ArrayList<>();
                    memberCurrencyRecordList.add(memberCurrencyRecord);
                    hashMap.put("memberCurrencyRecordList", memberCurrencyRecordList);
                    linkedHashMap.put(dateString, hashMap);
                } else {
                    HashMap memberCurrencyRecordsHashMap = linkedHashMap.get(dateString);
                    //没有同一天的
                    if (memberCurrencyRecordsHashMap == null || memberCurrencyRecordsHashMap.size() == 0) {
                        HashMap<Object, Object> hashMap = new HashMap<>();
                        //获取新名币变动
                        hashMap.put("in", new BigDecimal(0.0));
                        hashMap.put("out", new BigDecimal(0.0));
                        //正数
                        if (currencyChange.compareTo(BigDecimal.ZERO) >= 0) {
                            BigDecimal in = (BigDecimal) hashMap.get("in");
                            BigDecimal add = in.add(currencyChange);
                            hashMap.put("in", add);
                            //负数
                        } else {
                            BigDecimal out = (BigDecimal) hashMap.get("out");
                            BigDecimal add = out.add(currencyChange);
                            hashMap.put("out", add);
                        }
                        List<MemberCurrencyRecord> memberCurrencyRecordList = new ArrayList<>();
                        memberCurrencyRecordList.add(memberCurrencyRecord);
                        hashMap.put("memberCurrencyRecordList", memberCurrencyRecordList);
                        linkedHashMap.put(dateString, hashMap);
                    } else {
                        //获取已相加的收入
                        BigDecimal in = (BigDecimal) memberCurrencyRecordsHashMap.get("in");
                        //获取已相加的支出
                        BigDecimal out = (BigDecimal) memberCurrencyRecordsHashMap.get("out");
                        //正数
                        if (currencyChange.compareTo(BigDecimal.ZERO) >= 0) {
                            BigDecimal add = in.add(currencyChange);
                            memberCurrencyRecordsHashMap.put("in", add);
                            //负数
                        } else {
                            BigDecimal add = out.add(currencyChange);
                            memberCurrencyRecordsHashMap.put("out", add);
                        }
                        List memberCurrencyRecordList = (List) memberCurrencyRecordsHashMap.get("memberCurrencyRecordList");
                        memberCurrencyRecordList.add(memberCurrencyRecord);
                    }
                }
            }
        }
        resultCode.setData(linkedHashMap);
        return resultCode;
    }

    /**
     * 获取新名币详情
     *
     * @param member 会员对象
     * @param date   日期
     * @param flag   支出或收入或全部
     * @return ResultCode
     */
    @Override
    public ResultCode getMemberCurrencyRecordInOrOut(Member member, String date, Integer flag) {
        String[] split = date.split("-");
        String s = split[1];
        if (s.length() == 1) {
            s = "0" + s;
        }
        date = split[0] + "-" + s;
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(null);
        //根据日期和会员id查询新名币明细
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("DATE_FORMAT(create_time,'%Y-%m')", date);
        hashMap.put("member_id", member.getId());
        List<MemberCurrencyRecord> recordsList = memberCurrencyRecordMapper.selectByMap(hashMap);
        //如果为空
        if (recordsList == null || recordsList.size() == 0) {
            return resultCode;
        }
        BigDecimal in = new BigDecimal(0.0);
        BigDecimal out = new BigDecimal(0.0);
        List<Object> inList = new ArrayList<>();
        List<Object> outList = new ArrayList<>();
        List<Object> allList = new ArrayList<>();
        Collections.reverse(recordsList);
        //遍历新名币明细
        for (MemberCurrencyRecord memberCurrencyRecord : recordsList) {
            BigDecimal currencyChange = memberCurrencyRecord.getCurrencyChange();
            //如果为正
            if (currencyChange.compareTo(BigDecimal.ZERO) >= 0) {
                in = in.add(currencyChange);
                inList.add(memberCurrencyRecord);
                allList.add(memberCurrencyRecord);
                //为负
            } else {
                out = out.add(currencyChange);
                outList.add(memberCurrencyRecord);
                allList.add(memberCurrencyRecord);
            }
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("in", in);
        map.put("out", out);
        //如果为1就获取收入
        if (flag == 1) {
            map.put("memberCurrencyRecordList", inList);
            resultCode.setData(map);
            //-1就获取支出
        } else if (flag == -1) {
            map.put("memberCurrencyRecordList", outList);
            resultCode.setData(map);
        } else {
            map.put("memberCurrencyRecordList", allList);
            resultCode.setData(map);
        }
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 设置 修改 忘记支付密码
     *
     * @param member             会员对象
     * @param code               验证码
     * @param payPassword        支付密码
     * @param confirmPayPassword 确认支付密码
     * @return ResultCode
     */
    @Override
    public ResultCode updatePayPassword(Member member, String code, String payPassword, String confirmPayPassword) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        if (member == null) {
            resultCode.setCode(4003);
            resultCode.setMsg("登陆失效");
            return resultCode;
        }
        if (StringUtils.isBlank(payPassword) || StringUtils.isBlank(confirmPayPassword) || !StringUtils.equals(payPassword, confirmPayPassword)) {
            resultCode.setMsg("密码两次不一样");
            return resultCode;
        }
        String callCode = jedis.get(member.getCellphone());
        if (StringUtils.isBlank(callCode)) {
            resultCode.setMsg("验证码为空");
            return resultCode;
        }
        if (!StringUtils.equals(callCode, code)) {
            resultCode.setMsg("验证码错误");
            return resultCode;
        }
        member.setPayPassword(DigestUtils.md5Hex(payPassword));
        member.updateById();
        resultCode.setCode(200);
        resultCode.setMsg("设置成功");
        //支付密码设置成功后就删除reids的验证码
        jedis.del(member.getCellphone());
        return resultCode;
    }

    /**
     * 根据开始时间和结束时间获取这段时间该用户推荐的数量,排名
     *
     * @param member
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public MemberVo getMemberByECard(Member member, Date startTime, Date endTime) {
        MemberVo one = FieldUtils.fieldTrans(member, MemberVo.class);
        List<MemberVo> memberVos = getMembersByECard(startTime, endTime);
        if (memberVos == null || memberVos.size() == 0) {
            //没人参加活动,当前推荐数量为0,排名第一
            one.setECardCount(0);
            one.setRanking("未参与活动,暂无排名");
        }else{
            for (MemberVo memberVo : memberVos) {
                if (memberVo.getId().equals(member.getId())) {
                    one.setECardCount(memberVo.getECardCount());
                    one.setRanking(memberVo.getRanking());
                    break;
                }
            }
            if (StringUtils.isBlank(one.getRanking())){
                one.setRanking("未参与活动,暂无排名");
            }
        }
        return getSum(one);
    }

    //获取总推荐人数和总奖金
    @Override
    public MemberVo getSum(MemberVo one){
        List<MemberReferrer> list = new LambdaQueryChainWrapper<>(memberReferrerMapper)
                .eq(MemberReferrer::getReferrerId, one.getId())
                .eq(MemberReferrer::getStatus, 1)
                .list();
        Set<Integer> ids = new HashSet<>();
        list.forEach(memberReferrer -> ids.add(memberReferrer.getMemberId()));
        List<MemberCardInfo> infos = new ArrayList<>();
        if (ids.size() > 0) {
            infos = new LambdaQueryChainWrapper<>(memberCardInfoMapper)
                    .eq(MemberCardInfo::getCardType, 1)
                    .eq(MemberCardInfo::getStatus, 1)
                    .in(MemberCardInfo::getMemberId, ids)
                    .list();
        }
        //累计推荐人数
        one.setTotalECardCount(infos.size());
        //获取累计奖金
        List<MemberAmountLog> memberAmountLogs = new LambdaQueryChainWrapper<>(memberAmountLogMapper)
                .eq(MemberAmountLog::getMemberId, one.getId())
                .in(MemberAmountLog::getSource, 3, 8, 9, 10)
                .list();
        BigDecimal totalBonus = BigDecimal.ZERO;
        for (MemberAmountLog memberAmountLog : memberAmountLogs) {
            BigDecimal price = memberAmountLog.getPrice() == null ? BigDecimal.ZERO : memberAmountLog.getPrice();
            totalBonus = totalBonus.add(price);
        }
        one.setTotalBonus(totalBonus);
        return one;
    }
    @Override
    public List<MemberVo> getMembersByECard(Date startTime, Date endTime) {
        //时间区间的所有新申请的E卡
        List<MemberCardInfo> infos = new LambdaQueryChainWrapper<>(memberCardInfoMapper)
                .eq(MemberCardInfo::getCardType, 1)
                .eq(MemberCardInfo::getStatus, 1)
                .ge(MemberCardInfo::getCreateTime, startTime)
                .le(MemberCardInfo::getCreateTime, endTime)
                .list();
        if (infos == null || infos.size() == 0) {
            return null;
        }
        //申请的E卡的id
        Set<Integer> eCardIds = new HashSet<>();
        infos.forEach(memberCardInfo -> eCardIds.add(memberCardInfo.getMemberId()));
        //获取这些E卡的推荐关系
        List<MemberReferrer> memberReferrers = new LambdaQueryChainWrapper<>(memberReferrerMapper)
                .eq(MemberReferrer::getStatus, 1)
                .in(MemberReferrer::getMemberId, eCardIds)
                .list();
        if (memberReferrers == null || memberReferrers.size() == 0) {
            return null;
        }
        //推荐人的id
        Set<Integer> referrerIds = new HashSet<>();
        memberReferrers.forEach(memberReferrer -> referrerIds.add(memberReferrer.getReferrerId()));
        //获取推荐人列表
        List<Member> referrers = new LambdaQueryChainWrapper<>(memberMapper)
                .in(Member::getId, referrerIds)
                .list();
        List<MemberVo> memberVos = ListUtils.listTrans(referrers, MemberVo.class);
        //推荐关系按推荐人id分组
        Map<String, List<MemberReferrer>> referrerId = ListUtils.listGroup(memberReferrers, "referrerId");
        //设置用户的推荐人数
        memberVos.forEach(memberVo -> memberVo.setECardCount(referrerId.get(String.valueOf(memberVo.getId())).size()));
        //按用户推荐的人数分组
        Map<String, List<MemberVo>> eCardCount = ListUtils.listGroup(memberVos, "eCardCount");
        //map倒序
        Map<String, List<MemberVo>> orderByDesc = MapUtil.orderByDescInt(eCardCount);
        Map<String, List<MemberCardInfo>> memberId = ListUtils.listGroup(infos, "memberId");
        List<MemberVo> list = new ArrayList<>();
        orderByDesc.forEach((key, value) -> {
            if (value.size() > 1) {
                //value的长度大于1即这个排名为多人,按推荐的最后一个人的时间进行排序
                eCardOrderByAsc(value, referrerId, memberId);
            }
            //value的长度为1即这个排名为1人
            list.addAll(value);
        });
        List<MemberVo> result = new ArrayList<>();
        int i = 1;
        for (MemberVo memberVo : list) {
            MemberVo one = new MemberVo();
            one.setId(memberVo.getId());
            one.setAvatarUrl(memberVo.getAvatarUrl());
            one.setECardCount(memberVo.getECardCount());
            one.setRanking(String.valueOf(i));
            one.setCashAmount(memberVo.getCashAmount());
            one.setName(EmojiUtil.utfemojiRecovery(memberVo.getName()));
            i++;
            result.add(one);
        }
        result.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getRanking())));
        result.forEach(memberVo -> {
            if (Integer.parseInt(memberVo.getRanking()) > 99) {
                memberVo.setRanking("99+");
            }
        });
        for (MemberVo memberVo : result) {
            if (memberVo.getId() == 46) {
                System.out.println(JSON.toJSONString(memberVo));
            }
        }
        return result;
    }

    private void eCardOrderByAsc(List<MemberVo> memberVos, Map<String, List<MemberReferrer>> referrerId, Map<String, List<MemberCardInfo>> memberId) {
        memberVos.forEach(memberVo -> {
            final Date[] date = {null};
            List<MemberReferrer> memberReferrers = referrerId.get(String.valueOf(memberVo.getId()));
            memberReferrers.forEach(memberReferrer -> {
                List<MemberCardInfo> memberCardInfos = memberId.get(String.valueOf(memberReferrer.getMemberId()));
                memberCardInfos.forEach(memberCardInfo -> {
                    if (date[0] == null) {
                        date[0] = memberCardInfo.getCreateTime();
                    } else if (date[0].before(memberCardInfo.getCreateTime())) {
                        date[0] = memberCardInfo.getCreateTime();
                    }
                });
            });
            memberVo.setReferrerTime(date[0]);
        });
        memberVos.sort(Comparator.comparing(MemberVo::getReferrerTime));
    }
}
