package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.configuration.role.CreateRole;
import com.xinmintx.agent.mapper.*;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.PayNotifyService;
import com.xinmintx.agent.service.RoleShareService;
import com.xinmintx.agent.util.PinYinUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private UserMapper userMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private RoleShareService roleShareService;
    @Autowired
    private MemberReferrerMapper memberReferrerMapper;
    @Autowired
    private MemberCardInfoMapper memberCardInfoMapper;

    private UOrder uOrder;

    /**
     * 支付回调
     *
     * @param payNotify
     * @return
     */
    public boolean getWeChatPayNotify(PayNotify payNotify) {
        boolean flag = false;
        if ("1".equalsIgnoreCase(payNotify.getStatus())) {
            UOrderExample example = new UOrderExample();
            UOrderExample.Criteria criteria = example.createCriteria();
            criteria.andOrderNoEqualTo(payNotify.getOrderId());
            List<UOrder> orderList = uOrderMapper.selectByExample(example);
            if (orderList != null && orderList.size() > 0) {
                // 存在,修改保存返回参数
                UOrder uOrder = orderList.get(0);
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
                    uOrder.setPayType("1");
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
                        uOrderMapper.updateByPrimaryKeySelective(uOrder);
                        this.uOrder = uOrder;
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
     * 提交合伙人回调
     *
     * @param payNotify
     * @return
     */
    @Override
    public void partner(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            User user = updateUserByOrderId();
            if (user != null) {
                User referrer = userMapper.selectByPrimaryKey(user.getRecommender());
                roleShareService.shareProfit(referrer, CreateRole.PARTNER, uOrder);
            }

        }
    }

    /**
     * 提交代理回调
     *
     * @param payNotify
     * @return
     */
    @Override
    public void agent(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            User user = updateUserByOrderId();
            if (user != null) {
                User referrer = userMapper.selectByPrimaryKey(user.getRecommender());
                roleShareService.shareProfit(referrer, CreateRole.AGENT, uOrder);
            }

        }
    }

    /**
     * 提交黄金商户回调
     *
     * @param payNotify
     * @return
     */
    @Override
    public void goldMerchant(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            Merchant merchant = updateMerchantByOrderId();
            if (merchant != null) {
                User referrer = userMapper.selectByPrimaryKey(merchant.getRecommender());
                roleShareService.shareProfit(referrer, CreateRole.GOLD_MERCHANTS, uOrder);
            }
        }
    }

    /**
     * 提交普通商户回调
     *
     * @param payNotify
     * @return
     */
    @Override
    public void generalMerchant(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            Merchant merchant = updateMerchantByOrderId();
            if (merchant != null) {
                User referrer = userMapper.selectByPrimaryKey(merchant.getRecommender());
                roleShareService.shareProfit(referrer, CreateRole.GENERAL_MERCHANTS, uOrder);
            }
        }
    }
    /**
     * 提交社区商户回调
     *
     * @param payNotify
     * @return
     */
    @Override
    public void communityMerchant(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            Merchant merchant = updateMerchantByOrderId();
            /*if (merchant != null) {
                User referrer = userMapper.selectByPrimaryKey(merchant.getRecommender());
                roleShareService.shareProfit(referrer, CreateRole.COMMUNITY_MERCHANTS, uOrder);
            }*/
        }
    }

    /**
     * 提交新民金卡会员回调
     *
     * @param payNotify
     * @return
     */
    @Override
    public void silverMember(PayNotify payNotify) {
        boolean notify = getWeChatPayNotify(payNotify);
        if (notify) {
            Member member = updateMemberByOrderId();
            if (member != null) {
                User referrer = userMapper.selectByPrimaryKey(member.getRecommender());
                roleShareService.shareProfit(referrer, CreateRole.SILVER_MEMBER, uOrder);
            }
        }
    }

    /**
     * 提交合伙人,代理
     *
     * @return
     */
    private User updateUserByOrderId() {
        UserExample userExample = new UserExample();
        UserExample.Criteria userExampleCriteria = userExample.createCriteria();
        userExampleCriteria.andOrderIdEqualTo(uOrder.getId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0) {
            User user = users.get(0);
            user.setStatus(1);
            user.setIsReview(1);
            userMapper.updateByPrimaryKeySelective(user);
            //同步惠商角色
            //查询member表是否有代理
            Member member = selectMember(user.getCellphone());
            //惠商没有代理账号
            if (member == null) {
                member = new Member();
                //手机号
                member.setCellphone(user.getCellphone());
                //代理表id
                member.setUserId(user.getId());
                member.setIsReview(1);
                member.setMemberType(2);
                member.setGiftStart(1);
                //设置会员卡到期时间
                Date nowDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(nowDate);
                calendar.add(Calendar.YEAR, 1);
                Date endTime = calendar.getTime();
                member.setCardIndate(endTime);
                addMember(member);
                member.setName(user.getName());
                member.setIdcard(user.getIdcard());
                member.setGender(user.getGender());
                insertMemberCardInfo(member);
                //惠商有代理账号
            } else {
                //存在如果卡等级在新民金卡以下,即升级
                if (member.getMemberType() < 2) {
                    member.setMemberType(2);
                    member.setGiftStart(1);
                    //设置会员卡到期时间
                    Date nowDate = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(nowDate);
                    calendar.add(Calendar.YEAR, 1);
                    Date endTime = calendar.getTime();
                    member.setCardIndate(endTime);
                    memberMapper.updateByPrimaryKeySelective(member);
                    member.setName(user.getName());
                    member.setIdcard(user.getIdcard());
                    member.setGender(user.getGender());
                    insertMemberCardInfo(member);
                }
            }
            return user;
        }
        return null;
    }

    private void insertMemberCardInfo(Member member){
        //添加会员卡信息
        String idcard = member.getIdcard();
        MemberCardInfo info = new MemberCardInfo();
        info.setMemberId(member.getId());
        info.setName(member.getName());
        String hanYuPinyin = PinYinUtils.toHanYuPinyin(member.getName());
        info.setPyCode(hanYuPinyin);
        info.setIdcard(idcard);
        // 截取月份
        String month = idcard.substring(10, 12);
        // 截取天
        String day = idcard.substring(12, 14);
        String birthday = month + "月" + day + "日";
        info.setBirthday(birthday);
        info.setCellphone(member.getCellphone());
        info.setEntityCard(0);
        info.setCardType(2);
        info.setPayStatus(1);
        info.setStatus(1);
        info.setGender(member.getGender());
        memberCardInfoMapper.insertSelective(info);
        //生成会员卡号(前6位为身份证前6位,7-8,01位男,00位女,9-12为用户生日,13-16为自增/主键)
        StringBuilder builder = new StringBuilder();
        builder.append(idcard, 0, 4);
        builder.append(" ");
        builder.append(idcard, 4, 6);
        if (member.getGender() == 1) {
            builder.append("01");
        }else{
            builder.append("00");
        }
        builder.append(" ");
        builder.append(month);
        builder.append(day);
        builder.append(" ");
        String number = getNumber(String.valueOf(info.getId()));
        builder.append(number);
        info.setCardNumber(builder.toString());
        memberCardInfoMapper.updateByPrimaryKeySelective(info);
        member.setCardId(info.getId());
        memberMapper.updateByPrimaryKeySelective(member);
    }

    private String getNumber(String id) {
        if (id.length() < 4) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < (4 - id.length()); i++) {
                builder.append(String.valueOf(0));
            }
            builder.append(id);
            return builder.toString();
        } else {
            return id;
        }
    }
    /**
     * 提交商户
     *
     * @return
     */
    private Merchant updateMerchantByOrderId() {
        MerchantExample merchantExample = new MerchantExample();
        MerchantExample.Criteria merchantExampleCriteria = merchantExample.createCriteria();
        merchantExampleCriteria.andOrderIdEqualTo(uOrder.getId());
        List<Merchant> merchants = merchantMapper.selectByExample(merchantExample);
        if (merchants.size() > 0) {
            Merchant merchant = merchants.get(0);
            merchant.setIsReview(1);
            merchantMapper.updateByPrimaryKeySelective(merchant);
            return merchant;
        }
        return null;
    }

    /**
     * 提交新民金卡
     *
     * @return
     */
    private Member updateMemberByOrderId() {
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria memberExampleCriteria = memberExample.createCriteria();
        memberExampleCriteria.andOrderIdEqualTo(uOrder.getId());
        List<Member> members = memberMapper.selectByExample(memberExample);
        if (members.size() > 0) {
            Member member = members.get(0);
            member.setIsReview(1);
            member.setGiftStart(1);
            memberMapper.updateByPrimaryKeySelective(member);
            //推荐关系
            MemberReferrerExample memberReferrerExample = new MemberReferrerExample();
            MemberReferrerExample.Criteria referrerExampleCriteria = memberReferrerExample.createCriteria();
            referrerExampleCriteria.andMemberIdEqualTo(member.getId());
            List<MemberReferrer> memberReferrers = memberReferrerMapper.selectByExample(memberReferrerExample);
            if (memberReferrers.size() > 0) {
                MemberReferrer memberReferrer = memberReferrers.get(0);
                memberReferrer.setReferrerId(member.getRecommender());
                memberReferrer.setStatus(1);
                memberReferrerMapper.updateByPrimaryKey(memberReferrer);
            } else {
                //获取推荐人在代理商的账号,没有的话新增
                User user = userMapper.selectByPrimaryKey(member.getRecommender());
                MemberExample example = new MemberExample();
                MemberExample.Criteria criteria = example.createCriteria();
                criteria.andCellphoneEqualTo(user.getCellphone());
                List<Member> referrers = memberMapper.selectByExample(example);
                Member referrer = null;
                if (referrers == null || referrers.size() == 0){
                    referrer = new Member();
                    referrer.setName(user.getName());
                    referrer.setCellphone(user.getCellphone());
                    referrer.setGender(user.getGender());
                    referrer.setIdcard(user.getIdcard());
                    referrer.setRecommender(user.getRecommender());
                    referrer.setMemberType(0);
                    referrer.setIsReview(1);
                    referrer.setOpenid(user.getOpenid());
                    referrer.setAvatarUrl(user.getAvatar());
                    referrer.setUserId(user.getId());
                    memberMapper.insertSelective(referrer);
                }else{
                    referrer = referrers.get(0);
                }
                MemberReferrer memberReferrer = new MemberReferrer();
                memberReferrer.setReferrerId(referrer.getId());
                memberReferrer.setMemberId(member.getId());
                memberReferrer.setStatus(1);
                memberReferrerMapper.insert(memberReferrer);
            }
            return member;
        }
        return null;
    }

    /**
     * 查看member是否存在 此代理角色
     *
     * @param cellPhone
     * @return
     */
    public Member selectMember(String cellPhone) {
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        criteria.andCellphoneEqualTo(cellPhone);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if (members.size() > 0) {
            return members.get(0);
        }
        return null;
    }

    /**
     * 添加member角色
     *
     * @param member
     */
    public void addMember(Member member) {
        memberMapper.insertSelective(member);
    }
}
