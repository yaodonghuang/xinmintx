package com.xinmintx.factory.api.service.impl;

import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.factory.api.common.ResultCode;
import com.xinmintx.factory.api.mapper.GiftMapper;
import com.xinmintx.factory.api.mapper.MerchantMapper;
import com.xinmintx.factory.api.pojo.*;
import com.xinmintx.factory.api.service.IGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 礼包实现类
 */
@Service
public class GiftServiceImpl implements IGiftService {
    @Resource
    private GiftMapper giftMapper;
    @Resource
    private MerchantMapper merchantMapper;

    // 根据礼包类型获取礼包列表
    @Override
    public List<Gift> getPlatformList(String giftType, Long sourceId, String giftGroup, String token) {
        List<Gift> giftList = giftMapper.getPlatformList(giftType, sourceId, giftGroup);
        List<Gift> returnList = new ArrayList<Gift>();
        // 查询用户信息
        Integer memberId = giftMapper.getMemberIdByToken(token);
        if (memberId != null) {// 查询用户下所有的礼包
            List<MemberGift> mgList = giftMapper.getAllGiftByMemberId(memberId);
            if (giftList != null && giftList.size() > 0) {
                for (Gift gift : giftList) {
                    gift.setIfGet(false);
                    Boolean outDate = false;
                    if (gift.getEndDate() != null) {
                        Calendar now = Calendar.getInstance();
                        Calendar auditTime = Calendar.getInstance();
                        auditTime.clear();
                        auditTime.setTime(gift.getEndDate());
                        if (auditTime.after(now)) {
                            // 未过期
                            outDate = true;
                        }
                    }
                    if (!outDate) {
                        continue;
                    }
                    if (mgList != null && mgList.size() > 0) {
                        for (MemberGift mg : mgList) {
                            if (gift.getId().intValue() == mg.getGiftId().intValue()) {
                                gift.setIfGet(true);
                                break;
                            }
                        }
                    }
                    if (gift.getEndDate() != null) {
                        gift.setEndTimeStamp(String.valueOf(gift.getEndDate().getTime()));
                    }
                    returnList.add(gift);
                }
            }
        } else {// 去掉过期的礼包
            if (giftList != null && giftList.size() > 0) {
                giftList.forEach(gft -> {
                    if (gft.getEndDate() != null) {
                        Calendar now = Calendar.getInstance();
                        Calendar auditTime = Calendar.getInstance();
                        auditTime.clear();
                        auditTime.setTime(gft.getEndDate());
                        if (auditTime.after(now)) {
                            // 未过期
                            if (gft.getEndDate() != null) {
                                gft.setEndTimeStamp(String.valueOf(gft.getEndDate().getTime()));
                            }
                            gft.setIfGet(false);
                            returnList.add(gft);
                        }
                    }
                });
            }
        }
        return returnList;
    }

    // 根据token获取MemberId
    @Override
    public Integer getMemberIdByToken(String token) {
        return giftMapper.getMemberIdByToken(token);
    }

    // 根据会员id和礼包id获取绑定会员礼包
    @Override
    public int getGift(Integer memberId, Long giftId, BigDecimal price, String uuid) {
        return giftMapper.getGift(memberId, giftId, price, uuid);
    }

    // 查询这个用户下是否已经领取过该礼包
    @Override
    public Integer ifExistsGiftThisMember(Integer memberId, Long giftId) {
        return giftMapper.ifExistsGiftThisMember(memberId, giftId);
    }

    // 变更礼包数量
    @Override
    public int changeGiftQty(Long giftId) {
        return giftMapper.changeGiftQty(giftId);
    }

    // 获取我的礼包
    @Override
    public List<Gift> getMyGiftList(Integer memberId) {
        List<Gift> gftList = giftMapper.getMyGiftList(memberId);
        // 根据list中的对象属性sourceId查询是否存在商户,存在则给商户名称赋值,不存在则赋值平台红包(typeName)
        if (gftList != null && gftList.size() > 0) {
            gftList.forEach(gft -> {
                if (gft != null) {
                    if ("merchant".equalsIgnoreCase(gft.getGiftType()) && gft.getSourceId() != null) {
                        // 查询商户名称
                        gft.setTypeName(giftMapper.getTypeName(gft.getSourceId()));
                    } else {// 平台
                        gft.setTypeName("平台红包");
                    }
                }
            });
        }
        return gftList;
    }

    // 根据token获取会员信息
    @Override
    public Member getMemberByToken(String token) {
        return giftMapper.getMemberByToken(token);
    }

    // 获取礼包
    @Override
    public ResultCode getGift(String token, Long giftId) {
        ResultCode resultCode = new ResultCode();
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("请先登录账号");
        }
        Member mb = getMemberByToken(token);
        if (mb == null) {// token不存在
            throw new RuntimeException("token不存在,或者用户未登陆,请重新登陆");
        }
        Integer memberId = mb.getId();
        // 根据会员类型区分,银卡的情况,根据设定的次数领取所有礼包,普通会员的情况,只能用积分领取机会
        if ((mb.getMemberType() == null)) {
            throw new RuntimeException("会员类型不正确");
        }
        // 银卡及普通会员验证处理
        Gift gft = giftMapper.getGiftById(giftId);// 查询领取的礼包类型
        if (gft == null) {
            throw new RuntimeException("礼包不存在");
        }
        if (gft.getPrice() == null) {
            gft.setPrice(BigDecimal.ZERO);
        }
        Date cardIndate = mb.getCardIndate();
        if (cardIndate != null) {// 判断当前用户是否过期
            Calendar date = Calendar.getInstance();
            date.setTime(new Date());
            Calendar end = Calendar.getInstance();
            end.setTime(cardIndate);
            if (date.after(end)) {
                throw new RuntimeException("会员已过期!");
            }
        }
        if (mb.getMemberType() == 0) {// 普通
            throw new RuntimeException("普通会员无法领取礼包");
//            dealNormalCard(giftMapper, memberId, gft, mb);
        } else if (mb.getMemberType() == 2 || mb.getMemberType() == 1) {// 银卡(新民金卡)或者E卡会员
            dealSilverCard(giftMapper, memberId, gft, mb);
        } else {
            throw new RuntimeException("会员类型不正确");
        }
        Integer ifExists = ifExistsGiftThisMember(memberId, giftId);// 判断用户是否领取过礼包
        if (ifExists != null && ifExists == 1) {//存在的情况
            throw new RuntimeException("礼包已经领取过了,请不要重复领取");
        }
        // 领取礼包之前增加礼包唯一码
        String uuid = UUID.randomUUID().toString().replace("-", "");
        int result = getGift(memberId, giftId, gft.getPrice(), uuid);// 领取礼包
        if (result > 0) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            // 变更礼包数量
            int changeResult = changeGiftQty(giftId);
            if (changeResult == 0) {
                throw new RuntimeException("礼包数量不足");
            }
            return resultCode;
        }
        return resultCode;
    }

    /**
     * 删除礼包(仅限平台礼包)
     *
     * @param token
     * @param giftId
     * @return
     */
    @Override
    public ResultCode deleteGift(String token, Long giftId) {
        ResultCode rc = new ResultCode();
        Member mb = getMemberByToken(token);
        if (mb == null) {// token不存在
            throw new RuntimeException("token不存在,或者用户未登陆,请重新登陆");
        }
        Integer memberId = mb.getId();
        // 根据会员类型区分,银卡的情况,根据设定的次数领取所有礼包,普通会员的情况,只能用积分领取机会
        if ((mb.getMemberType() == null)) {
            throw new RuntimeException("会员类型不正确");
        }
        // 礼包类型是平台礼包才能删除
        Gift gft = giftMapper.getGiftById(giftId);// 查询领取的礼包类型
        if (gft == null) {
            throw new RuntimeException("礼包不存在");
        }
        if ("platform".equalsIgnoreCase(gft.getGiftType())) {
            // 平台礼包的情况,删除礼包记录
            int result = giftMapper.deleteGiftOfMember(memberId, giftId);
            if (result > 0) {// 更改用户的平台礼包可领取次数
                giftMapper.updatePlatformCount(memberId);
                rc.setCode(200);
                rc.setMsg("礼包删除成功!");
            }
        } else {
            throw new RuntimeException("只能删除平台礼包");
        }
        return rc;
    }

    /**
     * 礼包退款接口
     *
     * @param token
     * @param giftId
     * @return
     */
    @Override
    public ResultCode refundGift(String token, Long giftId) {
        ResultCode rc = new ResultCode();
        Member mb = getMemberByToken(token);
        if (mb == null) {// token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        Integer memberId = mb.getId();
        Gift gft = giftMapper.getGiftById(giftId);
        if (gft == null) {
            rc.setCode(500);
            rc.setMsg("礼包不存在!");
            return rc;
        }
        if (gft.getPrice() == null) {
            gft.setPrice(BigDecimal.ZERO);
        }
        int result = giftMapper.deleteGift(memberId, giftId);
        if (result > 0) {
            giftMapper.changeGiftQtyAdd(giftId);
            // 退还新民币
            giftMapper.updateNewCurrencyForMember(memberId, gft.getPrice());
            // 保存日志
            insertLogs(mb, gft, "refund", mb.getNewCurrency().add(gft.getPrice()), giftMapper, null);
            rc.setCode(200);
            rc.setMsg("SUCCESS");
        } else {
            rc.setCode(500);
            rc.setMsg("FAIL");
        }
        return rc;
    }

    /**
     * 拉去礼包日志接口
     *
     * @param token
     * @param type
     * @return
     */
    @Override
    public ResultCode giftLogs(String token, String type) {
        ResultCode rc = new ResultCode();
        Member mb = getMemberByToken(token);
        if (mb == null) {// token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        Integer memberId = mb.getId();
        List<GiftPurchaseLogs> logsList = giftMapper.getGiftLogs(memberId, type);
        if (logsList != null && logsList.size() > 0) {
            logsList.forEach(log -> {
                log.setCreateTimeStamp(String.valueOf(log.getCreateTime().getTime()));
            });
            rc.setCode(200);
            rc.setMsg("SUCCESS");
            rc.setData(logsList);
        }
        return rc;
    }

    /**
     * 查询是否领取过平台礼包
     *
     * @param token
     * @param type
     * @return
     */
    @Override
    public ResultCode ifGetPlatFormGift(String token, String type) {
        ResultCode rc = new ResultCode();
        Member mb = getMemberByToken(token);
        if (mb == null) {// token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        Integer memberId = mb.getId();
        Integer result = giftMapper.ifGetPlatFormGift(memberId, type);
        if (result == null) {// 不存在,没有领取过平台（type）礼包
            rc.setCode(200);
            rc.setMsg("false");
        } else {
            rc.setCode(200);
            rc.setMsg("true");
        }
        return rc;
    }

    /**
     * 商户核销接口
     *
     * @param memberId 会员id
     * @param uuid     礼包唯一标识
     * @return
     */
    @Override
    public ResultCode writeOff(Integer memberId, String uuid, String token) {
        ResultCode rc = new ResultCode();
        // 查询会员是否存在
        Member mb = giftMapper.getMemberById(memberId);
        if (mb == null) {
            rc.setCode(500);
            rc.setMsg("会员不存在！");
            return rc;
        }
        // 查询uuid对应的礼包是否存在
        Gift gft = giftMapper.selectGiftByUuid(uuid);
        if (gft == null) {
            rc.setCode(500);
            rc.setMsg("会员礼包不存在！");
            return rc;
        }
        // 查询会员是否存在
        Merchant mc = merchantMapper.queryByToken(token);
        if (mc == null) {
            rc.setCode(500);
            rc.setMsg("商户不存在！");
            return rc;
        }
        Integer merchantId = mc.getId();
        Gift gift = giftMapper.getGiftById(gft.getId());
        if (gift.getSourceId() != null) {
            if (gift.getSourceId().intValue() != merchantId.intValue()) {// 商户id和礼包绑定的商户id比较
                rc.setCode(500);
                rc.setMsg("礼包绑定商户和当前操作商户不一致,该商户不能核销该礼包");
                return rc;
            }
        } else {
            // 礼包来源商户id为空
            rc.setCode(500);
            rc.setMsg("礼包来源商户id为空！");
            return rc;
        }
        Integer ifExistsMerchant = giftMapper.ifExistsMerchant(merchantId);
        if (ifExistsMerchant == null) {
            rc.setCode(500);
            rc.setMsg("商铺不存在！");
            return rc;
        }
        int result = giftMapper.deleteGiftByUuid(memberId, uuid);
        if (result > 0) {
            // 保存日志
            insertLogs(mb, gft, "writeOff", mb.getNewCurrency(), giftMapper, merchantId);
            rc.setCode(200);
            rc.setMsg("SUCCESS");
        } else {
            rc.setCode(500);
            rc.setMsg("FAIL");
        }
        return rc;
    }

    /**
     * 商户待核销大列表
     *
     * @param token 商户token
     * @return
     */
    @Override
    public ResultCode waitWriteOff(String token) {
        ResultCode rc = new ResultCode();
        // 查询会员是否存在
        Merchant mc = merchantMapper.queryByToken(token);
        if (mc == null) {
            rc.setCode(500);
            rc.setMsg("商户不存在！");
            return rc;
        }
        Integer merchantId = mc.getId();
        List<Gift> giftList = giftMapper.getWaitWriteOffList(merchantId);
        if (giftList != null && giftList.size() > 0) {
            giftList.forEach(gift -> {
                // 查询待核销数量
                Long giftId = gift.getId();
                Integer getQty = giftMapper.getWaitWriteOffQty(giftId);
                getQty = getQty == null ? 0 : getQty;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                gift.setCreateDate(sdf.format(gift.getCreateTime()));
                gift.setEndDate2(sdf.format(gift.getEndDate()));
                gift.setGetQty(getQty);
                gift.setMerchantName(mc.getName());
            });
            rc.setData(giftList);
        }
        rc.setCode(200);
        rc.setMsg("SUCCESS");
        return rc;
    }

    /**
     * 商户待核销详情列表
     *
     * @param giftId
     * @return
     */
    @Override
    public ResultCode waitWriteOffDetail(Long giftId) {
        ResultCode rc = new ResultCode();
        List<MemberGiftResult> resultDetail = giftMapper.getWaitWriteOffDetailList(giftId);
        if (resultDetail != null && resultDetail.size() > 0) {
            resultDetail.forEach(rd -> {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                rd.setCreateDate(sdf.format(rd.getCreateTime()));
                rd.setEndTime(sdf.format(rd.getEndDate()));
            });
        }
        rc.setCode(200);
        rc.setMsg("SUCCESS");
        rc.setData(resultDetail);
        return rc;
    }

    /**
     * 商户已核销列表
     *
     * @param token
     * @return
     */
    @Override
    public ResultCode alreadyWriteOff(String token) {
        ResultCode rc = new ResultCode();
        // 查询会员是否存在
        Merchant mc = merchantMapper.queryByToken(token);
        if (mc == null) {
            rc.setCode(500);
            rc.setMsg("商户不存在！");
            return rc;
        }
        Integer merchantId = mc.getId();
        List<Gift> giftList = giftMapper.getAlreadyWriteOff(merchantId);
        if (giftList != null && giftList.size() > 0) {
            giftList.forEach(gift -> {
                Long giftId = gift.getId();
                Integer count = giftMapper.getFinishWriteOffNum(merchantId, giftId);
                count = count == null ? 0 : count;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                gift.setCreateDate(sdf.format(gift.getCreateTime()));
                gift.setEndDate2(sdf.format(gift.getEndDate()));
                gift.setFinishWriteOffCount(count);
                gift.setMerchantName(mc.getName());
            });
            rc.setData(giftList);
        }
        rc.setCode(200);
        rc.setMsg("SUCCESS");
        return rc;
    }

    /**
     * 保存日志接口
     *
     * @param memberId
     * @param giftId
     * @param type
     * @param balance
     * @param merchantId
     * @return
     */
    @Override
    public ResultCode saveLog(Integer memberId, Long giftId, BigDecimal price, String type,
                              BigDecimal balance, Integer merchantId) {
        ResultCode rc = new ResultCode();
        Member mb = new Member();
        mb.setId(memberId);
        Gift gft = null;
        if (giftId != null) {
            gft = new Gift();
            gft.setId(giftId);
            gft.setPrice(price);
        }
        insertLogs(mb, gft, type, balance, giftMapper, merchantId);
        rc.setCode(200);
        rc.setMsg("SUCCESS");
        return rc;
    }

    // 处理普通卡的逻辑
    private static void dealNormalCard(GiftMapper giftMapper, Integer memberId, Gift gft, Member mb) {
        // 查询用户下所有礼包类型,计算平台和商户礼包数量
        if ("platform".equalsIgnoreCase(gft.getGiftType())) {
            throw new RuntimeException("普通会员只能领取商户礼包!");
        }
        if ("birthGiftPackage".equalsIgnoreCase(gft.getGiftGroup())) {
            throw new RuntimeException("普通会员不能领取生日礼包!");
        }
        if ("merchant".equalsIgnoreCase(gft.getGiftType()) && !"birthGiftPackage".equalsIgnoreCase(gft.getGiftGroup())) {
            BigDecimal newCurrency = mb.getNewCurrency() == null ? BigDecimal.ZERO : mb.getNewCurrency();
            if (newCurrency.compareTo(gft.getPrice()) >= 0) {// 新民币大于礼包价格
                mb.setNewCurrency(newCurrency.subtract(gft.getPrice()));// 减掉新民币
                giftMapper.updateNewCurrency(mb);// 更新新民币
            } else {
                throw new RuntimeException("新民币余额不足,无法领取商户礼包");
            }
        }
    }

    // 处理银卡时候的逻辑
    private static void dealSilverCard(GiftMapper giftMapper, Integer memberId, Gift gft, Member mb) {
        // 查询用户下所有礼包类型,计算平台和商户礼包数量
        List<Gift> giftList = giftMapper.selectMemberAllGift(memberId);
        Integer pfQty = 0;
        Integer birthGftQty = 0;
        if (giftList != null && giftList.size() > 0) {
            for (Gift gift : giftList) {
                if ("platform".equalsIgnoreCase(gift.getGiftType())) {
                    pfQty++;// 计算出已经领取的平台礼包数量
                }
                if ("birthGiftPackage".equalsIgnoreCase(gift.getGiftGroup())) {
                    birthGftQty++;// 计算出生日礼包可领取数量
                }
            }
        }
        if ("platform".equalsIgnoreCase(gft.getGiftType())) {
            pfQty = pfQty + 1;// 计算领取之后的总数量,判断是否超过了设置的数量
            if (pfQty.compareTo(mb.getPlatformCount()) == 1) {
                throw new RuntimeException("超过可以领取的平台礼包数量!已有" + (pfQty - 1) + "个");
            }
        }
        if ("birthGiftPackage".equalsIgnoreCase(gft.getGiftGroup())) {
            birthGftQty = birthGftQty + 1;// 计算领取之后的总数量,判断是否超过了设置的数量
            if (birthGftQty.compareTo(mb.getBirthGiftCount()) == 1) {
                throw new RuntimeException("超过可以领取的生日礼包数量!已有" + (birthGftQty - 1) + "个");
            }
        }
        if ("merchant".equalsIgnoreCase(gft.getGiftType()) && !"birthGiftPackage".equalsIgnoreCase(gft.getGiftGroup())) {
            BigDecimal newCurrency = mb.getNewCurrency() == null ? BigDecimal.ZERO : mb.getNewCurrency();
            if (newCurrency.compareTo(gft.getPrice()) >= 0) {// 新民币大于礼包价格
                mb.setNewCurrency(newCurrency.subtract(gft.getPrice()));// 减掉新民币
                giftMapper.updateNewCurrency(mb);// 更新新民币
                // 保存日志
                insertLogs(mb, gft, "pay", mb.getNewCurrency(), giftMapper, null);
            } else {
                throw new RuntimeException("新民币余额不足,无法领取商户礼包");
            }
        } else if ("birthGiftPackage".equalsIgnoreCase(gft.getGiftGroup())) {
            // 保存日志
            gft.setPrice(BigDecimal.ZERO);
            insertLogs(mb, gft, "pay", mb.getNewCurrency(), giftMapper, null);
        }
    }

    /**
     * 保存日志方法
     *
     * @param mb
     * @param gft
     * @param type
     * @param balance
     */
    private static void insertLogs(Member mb, Gift gft, String type,
                                   BigDecimal balance, GiftMapper giftMapper, Integer merchantId) {
        GiftPurchaseLogs gpl = new GiftPurchaseLogs();
        gpl.setMemberId(Long.valueOf(mb.getId()));
        if (gft != null) {
            gpl.setGiftId(gft.getId());
            gpl.setPrice(gft.getPrice());
        } else {
            gpl.setGiftId(null);
            gpl.setPrice(BigDecimal.ZERO);
        }
        gpl.setBalance(balance);
        gpl.setType(type);
        if (merchantId != null) {
            gpl.setMerchantId(merchantId);
        }
        giftMapper.insertGiftLog(gpl);
    }

}
