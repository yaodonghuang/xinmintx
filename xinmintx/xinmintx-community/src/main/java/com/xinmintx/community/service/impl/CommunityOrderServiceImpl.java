package com.xinmintx.community.service.impl;

import com.google.gson.Gson;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.mapper.CommunityMapper;
import com.xinmintx.community.mapper.CommunityOrderMapper;
import com.xinmintx.community.mapper.CommunityVoteMapper;
import com.xinmintx.community.model.*;
import com.xinmintx.community.service.CommunityOrderService;
import com.xinmintx.community.util.StringUtil;
import com.xinmintx.community.util.httpclient.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author hyd
 */
@Service
public class CommunityOrderServiceImpl implements CommunityOrderService {
    private static final Logger log = LoggerFactory.getLogger(CommunityOrderServiceImpl.class);

    @Resource
    private CommunityMapper communityMapper;
    @Resource
    private CommunityVoteMapper communityVoteMapper;
    @Resource
    private CommunityOrderMapper communityOrderMapper;
    @Resource
    private UrlConfig urlConfig;

    /**
     * 获取社区菜品列表
     *
     * @param id
     * @param type
     * @param name
     * @return
     */
    @Override
    public ResultCode getGoodsList(Long id, Integer type, String name) {
        ResultCode rc = new ResultCode();
        rc.setCode(200);
        if (id == null) {
            rc.setMsg("NO DATA");
            return rc;
        }
        log.info("id:" + id + ",type:" + type + ",name:" + name);
        List<Long> merchantIdsList = communityMapper.getMerchantIdsByCommunityId(id);
        if (merchantIdsList != null && merchantIdsList.size() > 0) {
            // 社区下绑定有商户,根据商户id查询相关信息
            List<MerchantGoods> goodsList = communityMapper.getGoodsListByIds(merchantIdsList, name, type);
            rc.setData(goodsList);
            rc.setMsg("SUCCESS");
        } else {
            rc.setMsg("NO DATA");
        }
        return rc;
    }

    /**
     * 创建订单
     *
     * @param mgList
     * @param token
     * @return
     */
    @Override
    public ResultCode orderCreation(List<MerchantGoods> mgList, String token) {
        ResultCode rc = new ResultCode();
        if (StringUtils.isEmpty(token)) {
            rc.setCode(500);
            rc.setMsg("请先登录账号");
            return rc;
        }
        Member mb = communityMapper.getMemberByToken(token);
        if (mb == null) {
            // token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        if (mgList == null || mgList.size() == 0) {
            // 为选择商品
            rc.setCode(500);
            rc.setMsg("请选择商品");
            return rc;
        }
        // 验证提货点是否存在
        Integer ifExistsConsigneeAddress = communityVoteMapper.ifExistsConsigneeAddress(mgList.get(0).getCommunityId());
        if (ifExistsConsigneeAddress == null) {
            // 提货点为空
            rc.setCode(500);
            rc.setMsg("提货点未设置，请联系社长设置提货点信息！");
            return rc;
        }
        Community getCommunity = communityMapper.queryAddress(mgList.get(0).getCommunityId());
        if (getCommunity != null) {
            String consigneePhone = getCommunity.getPhoneNumber();
            BigDecimal consigneeMoney = getCommunity.getConsigneeMoney();
            // 查询提货人id
            Integer id = communityMapper.getUserIdByPhone(consigneePhone, 11);
            if (id == null) {
                rc.setCode(500);
                rc.setMsg("提货点账号必须是代理账号！");
                return rc;
            }
        }
        Integer ifBelongToCommunity = communityVoteMapper.ifBelongToCommunity(mgList.get(0).getCommunityId(), mb.getId());
        if (ifBelongToCommunity == null) {
            // 会员不是该社区的,不能发起投票
            rc.setCode(500);
            rc.setMsg("您不是该社区的,请先加入社区才能创建订单!");
            return rc;
        }
        // 对所有商品进行筛选，同一个商户的创建一个订单
        List<Integer> idList = dataDeal(sort(mgList, mb.getId()));
        if (idList != null && idList.size() > 0) {
            if (idList.size() == 1) {
                // 一个订单的情况
                rc = getPayInfoOne(idList.get(0), mb, mgList.get(0).getCommunityId());
            } else {
                // 多个订单的情况
                rc = getPayInfoMany(idList, mb, mgList.get(0).getCommunityId());
            }
        }
        return rc;
    }

    /**
     * 支付回调接口
     *
     * @param payNotify
     * @return
     */
    @Override
    public ResultCode orderPayNotify(PayNotify payNotify) {
        ResultCode rc = new ResultCode();
        Boolean flag = PayNotifyResult(payNotify);
        if (flag) {
            List<GoodsOrder> orderList = communityOrderMapper.getOrderIdByOrderNum(payNotify.getOrderId());
            if (orderList != null && orderList.size() > 0) {
                for (GoodsOrder go : orderList) {
                    // 1.付款成功，修改主订单状态和订单详情表状态
                    communityOrderMapper.updateOrderAndDetailState(go.getId(), 2, 1);
                    // 2.查询出内帮办的id和金额,先打到member表的冻结余额中
                    dealMemberNotify(go.getId());
                    // 3.修改商户的冻结金额
                    dealMerchantNotify(go.getMerchantId(), go.getTotalAmount());
                    // 4.查询出提货点的id和金额，钱打给user
                    dealUserNotify(go.getId());
                    // 5.支付成功调打印机打印订单
                    print(go.getId());
                }
            }
            rc.setCode(200);
            rc.setMsg("SUCCESS");
        } else {
            rc.setCode(500);
            rc.setMsg("回调失败！");
        }
        return rc;
    }

    /**
     * 根据商户有没有设置自动打印调取打印接口
     *
     * @param id
     */
    private void print(Integer id) {
        Integer ifAutoPrint = communityOrderMapper.ifAutoPrint(id);
        if (ifAutoPrint != null && ifAutoPrint.intValue() == 1) {
            // 开启自动打印，调打印接口
            String url = urlConfig.getPrintUrl();
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("orderId", id.toString());
            HttpClientUtil.doPost(url, paramMap);
        }
    }

    /**
     * 支付回调处理代理商金额
     *
     * @param id
     */
    private void dealUserNotify(Integer id) {
        String info = communityOrderMapper.getUserInfo(id);
        if (StringUtils.isNotEmpty(info)) {
            String userId = info.split(",")[0];
            String cost = info.split(",")[1];
            // 处理两个表：1.user_account
            UserAccount ua = communityOrderMapper.getUserAccount(userId);
            if (ua == null) {
                //创建记录
                ua = new UserAccount();
                ua.setUserId(Integer.valueOf(userId));
                ua.setFreezeMoney(new BigDecimal(cost));
                communityOrderMapper.insertUserAccount(ua);
            } else {
                // 修改记录
                ua.setFreezeMoney(ua.getFreezeMoney().add(new BigDecimal(cost)));
                communityOrderMapper.updateUserAccount(ua);
            }
            // 2.user_account_record
            UserAccountRecord userAccountRecord = new UserAccountRecord();
            userAccountRecord.setUserId(Integer.valueOf(userId));
            userAccountRecord.setOrderId(id);
            userAccountRecord.setUserAccountId(ua.getId());
            userAccountRecord.setMoneyChange(new BigDecimal(cost));
            userAccountRecord.setDescription("社区提货点入账");
            communityOrderMapper.insertUserAccountRecord(userAccountRecord);
        }
    }

    /**
     * 处理商户余额及日志
     */
    private void dealMerchantNotify(Long merchantId, BigDecimal totalAmount) {
        MerchantAmountLog mcal = new MerchantAmountLog();
        communityOrderMapper.updateMerchantMoney(merchantId, totalAmount);
        BigDecimal balance = communityOrderMapper.getBalance(merchantId);
        balance = balance == null ? BigDecimal.ZERO : balance;
        mcal.setMerchantId(merchantId);
        mcal.setType("1");
        mcal.setPrice(totalAmount);
        mcal.setBalance(balance);
        mcal.setRemark("菜篮子订单收款");
        communityOrderMapper.insertMerchantLogs(mcal);
    }

    /**
     * 回调处理用户金额及日志
     *
     * @param orderId
     */
    private void dealMemberNotify(Integer orderId) {
        String info = communityOrderMapper.getDeputyInfo(orderId);
        if (StringUtils.isNotEmpty(info)) {
            MemberAmountLog mal = new MemberAmountLog();
            String id = info.split(",")[0];
            String cost = info.split(",")[1];
            mal.setPrice(new BigDecimal(cost));
            BigDecimal fMoney = communityOrderMapper.getFreezingAmount(id);
            mal.setMemberId(Long.valueOf(id));
            if (fMoney != null) {
                fMoney = fMoney.add(new BigDecimal(cost));
                mal.setBalance(fMoney);
                communityOrderMapper.updateMemberMoney(id, String.valueOf(fMoney));
            } else {
                mal.setBalance(new BigDecimal(cost));
                communityOrderMapper.updateMemberMoney(id, cost);
            }
            // 保存入账日志
            mal.setType("1");
            mal.setRemark("内帮办配送费");
            communityOrderMapper.insertLogs(mal);
        }
    }

    /**
     * 返回结果验证
     *
     * @return
     */
    private Boolean PayNotifyResult(PayNotify payNotify) {
        boolean flag = false;
        if ("1".equalsIgnoreCase(payNotify.getStatus())) {
            uOrder uo = communityOrderMapper.getUorderById(payNotify.getOrderId());
            if ("0".equals(uo.getPayStatus())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                try {
                    uo.setPayTime(sdf.parse(payNotify.getCompleteDate()));
                    uo.setCompleteDate(sdf.parse(payNotify.getCompleteDate()));
                    uo.setSettleDate(sdf.parse(payNotify.getSettleDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                uo.setPayStatus("1");
                uo.setPayTime(new Date());
                uo.setPayType("1");
                // 第三方数据
                uo.setVersionId(payNotify.getVersionId());
                uo.setMerchantId(payNotify.getMerchantId());
                uo.setOrderId(payNotify.getOrderId());
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(payNotify.getStatus())) {
                    uo.setStatus(Integer.valueOf(payNotify.getStatus()));
                }
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(payNotify.getNotifyTyp())) {
                    uo.setNotifyTyp(Integer.valueOf(payNotify.getNotifyTyp()));
                }
                uo.setPayOrdNo(payNotify.getPayOrdNo());
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(payNotify.getOrderAmt())) {
                    uo.setOrderAmt(new BigDecimal(payNotify.getOrderAmt()));
                }
                uo.setNotifyUrl(payNotify.getNotifyUrl());
                uo.setSignType(payNotify.getSignType());
                uo.setSignature(payNotify.getSignature());
                uo.setAttach(payNotify.getAttach());
                communityOrderMapper.updateUOrder(uo);
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取配送费信息
     *
     * @param mgList
     * @return
     */
    @Override
    public ResultCode getFeeInfo(List<MerchantGoods> mgList, String token) {
        ResultCode rc = new ResultCode();
        if (StringUtils.isEmpty(token)) {
            rc.setCode(500);
            rc.setMsg("请先登录账号");
            return rc;
        }
        Member mb = communityMapper.getMemberByToken(token);
        if (mb == null) {
            // token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        // 拿到所有商户id
        Set<Long> idSet = new HashSet<>();
        mgList.forEach(mg -> {
            idSet.add(mg.getMerchantId());
        });
        List<Long> merchantIds = new ArrayList<>();
        if (idSet != null && idSet.size() > 0) {
            idSet.forEach(id -> {
                merchantIds.add(id);
            });
        }
        // 拿到社区id
        Long communityId = mgList.get(0).getCommunityId();
        BigDecimal totalAmount = BigDecimal.ZERO;
        // 判断用户是否设置帮办代提
        Integer ifDeputyHelp = communityOrderMapper.ifDeputyHelp(communityId, mb.getId());
        if (ifDeputyHelp != null && ifDeputyHelp == 1) {
            // 根据社区和商户设置配送费
            BigDecimal money0 = communityOrderMapper.getDeputyCost(communityId, 0);
            money0 = money0 == null ? BigDecimal.ZERO : money0;
            totalAmount = totalAmount.add(money0);
            Community getCommunity = communityMapper.queryAddress(communityId);
            if (getCommunity != null) {
                BigDecimal consigneeMoney = getCommunity.getConsigneeMoney();
                consigneeMoney = consigneeMoney == null ? BigDecimal.ZERO : consigneeMoney;
                totalAmount = totalAmount.add(consigneeMoney);
            }
        } else {
            // 自提的情况，只收提货点费用
            Community getCommunity = communityMapper.queryAddress(communityId);
            if (getCommunity != null) {
                BigDecimal consigneeMoney = getCommunity.getConsigneeMoney();
                consigneeMoney = consigneeMoney == null ? BigDecimal.ZERO : consigneeMoney;
                totalAmount = totalAmount.add(consigneeMoney);
            }
        }
        rc.setCode(200);
        rc.setData(totalAmount);
        return rc;
    }

    /**
     * 我的订单单笔支付
     *
     * @param orderId
     * @param memberId
     * @param communityId
     * @return
     */
    @Override
    public ResultCode payInfoOne(Integer orderId, Integer memberId, Long communityId) {
        Member mb = communityMapper.getMemberById(memberId);
        return getPayInfoOne(orderId, mb, communityId);
    }

    /**
     * 多订单支付
     *
     * @param idList
     * @param mb
     * @return
     */
    private ResultCode getPayInfoMany(List<Integer> idList, Member mb, Long communityId) {
        ResultCode rc = new ResultCode();
        String result = "";
        String orderNum = "";
        BigDecimal totalAmount = communityOrderMapper.getManyAmount(idList);
        Map<String, String> paramMap = new HashMap<>(5);
        paramMap.put("orderId", UUID.randomUUID().toString());
        orderNum = paramMap.get("orderId");
        // 判断用户是否设置帮办代提
        Integer ifDeputyHelp = communityOrderMapper.ifDeputyHelp(communityId, mb.getId());
        if (ifDeputyHelp != null && ifDeputyHelp == 1) {
            // 根据社区和商户设置配送费
            totalAmount = sumFee(communityId, totalAmount, rc, idList);
        } else {
            // 自提的情况，只收提货点费用
            Community getCommunity = communityMapper.queryAddress(communityId);
            if (getCommunity != null) {
                String consigneePhone = getCommunity.getPhoneNumber();
                BigDecimal consigneeMoney = getCommunity.getConsigneeMoney();
                consigneeMoney = consigneeMoney == null ? BigDecimal.ZERO : consigneeMoney;
                totalAmount = totalAmount.add(consigneeMoney);
                Integer id = communityMapper.getUserIdByPhone(consigneePhone, 11);
                if (id != null) {
                    communityOrderMapper.updateOrderInfoOfConsignee(idList, id + "," + consigneeMoney + "," + idList.size());
                }
            }
        }
        paramMap.put("orderAmt", StringUtil.getPrettyNumber(totalAmount));
        paramMap.put("openid", mb.getOpenid());
        paramMap.put("prdDesc", "菜篮子商品");
        // 配置回调地址
        paramMap.put("retUrl", urlConfig.getOrderPayNotify());
        result = HttpClientUtil.doPost(urlConfig.getCommunityPay(), paramMap);
        log.info("orderId:" + paramMap.get("orderId") + ",result:" + result);
        rc = new Gson().fromJson(result, ResultCode.class);
        dealResult(rc, mb, orderNum, paramMap, idList);
        return rc;
    }

    /**
     * 计算配送费逻辑
     *
     * @param communityId
     * @param totalAmount
     * @param rc
     * @param idList
     * @return
     */
    private BigDecimal sumFee(Long communityId, BigDecimal totalAmount, ResultCode rc, List<Integer> idList) {
        BigDecimal money0 = communityOrderMapper.getDeputyCost(communityId, 0);
        money0 = money0 == null ? BigDecimal.ZERO : money0;
        totalAmount = totalAmount.add(money0);
        StringBuffer sb = new StringBuffer();
        // 查询内帮办和外帮办各自的钱和会员信息
        Map<String, String> map = communityOrderMapper.getMemberSendFee(communityId, 0);
        sb = dealMap(map, sb);
        sb.append(idList.size());
        // 判断是内帮办和提货点的人是否同一个，同一个则把钱都打到提货点的代理账号上
        totalAmount = dealDeputyAndConsignee(communityId, idList, sb, map, totalAmount);
        return totalAmount;
    }

    // 拼接string
    private StringBuffer dealMap(Map<String, String> map, StringBuffer sb) {
        if (map != null && map.size() > 0) {
            String id = String.valueOf(map.get("id"));
            String cost = String.valueOf(map.get("cost"));
            if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(cost)) {
                sb.append(id).append(",").append(cost).append(",");
            }
        }
        return sb;
    }

    /**
     * 提货点和内帮办逻辑
     *
     * @param communityId
     * @param idList
     * @param sb
     */
    private BigDecimal dealDeputyAndConsignee(Long communityId, List<Integer> idList, StringBuffer sb, Map<String, String> map, BigDecimal totalAmount) {
        Community getCommunity = communityMapper.queryAddress(communityId);
        if (getCommunity != null) {
            String consigneePhone = getCommunity.getPhoneNumber();
            BigDecimal consigneeMoney = getCommunity.getConsigneeMoney();
            consigneeMoney = consigneeMoney == null ? BigDecimal.ZERO : consigneeMoney;
            totalAmount = totalAmount.add(consigneeMoney);
            // 查询提货人id
            Integer id = communityMapper.getUserIdByPhone(consigneePhone, 11);
            // 查询内帮办手机号，和提货点手机号匹配，相同则是同一个人
            String deputyPhone = communityOrderMapper.getPhoneOfDeputy(communityId, 0);
            if (StringUtils.isEmpty(consigneePhone)) {
                // 说明没配置提货点，走内帮办路线
                communityOrderMapper.updateOrderInfoOfPay(idList, sb.toString());
            } else {
                // 设置了提货点，则判断是否设置了内帮办
                if (StringUtils.isNotEmpty(deputyPhone)) {
                    // 判断电话是否相同
                    if (consigneePhone.equalsIgnoreCase(deputyPhone)) {
                        // 相同则内帮办的钱加给提货点，走提货点
                        if (map != null && map.size() > 0) {
                            String cost = String.valueOf(map.get("cost"));
                            consigneeMoney = consigneeMoney.add(new BigDecimal(cost));
                        }
                        if (id != null) {
                            communityOrderMapper.updateOrderInfoOfConsignee(idList, id + "," + consigneeMoney + "," + idList.size());
                        }
                    } else {
                        // 不同则各走各的
                        if (id != null) {
                            communityOrderMapper.updateOrderInfoOfConsignee(idList, id + "," + consigneeMoney + "," + idList.size());
                        }
                        communityOrderMapper.updateOrderInfoOfPay(idList, sb.toString());
                    }
                } else {
                    // 走提货点
                    if (id != null) {
                        communityOrderMapper.updateOrderInfoOfConsignee(idList, id + "," + consigneeMoney + "," + idList.size());
                    }
                }
            }
        } else {
            // 提货点信息查询不到，走内帮办
            communityOrderMapper.updateOrderInfoOfPay(idList, sb.toString());
        }
        return totalAmount;
    }

    /**
     * 保存uOrder表信息
     *
     * @param rc
     * @param mb
     * @param orderNum
     * @param paramMap
     * @param idList
     */
    private void dealResult(ResultCode rc, Member mb, String orderNum,
                            Map<String, String> paramMap, List<Integer> idList) {
        if (rc != null && rc.getData() != null && rc.getCode() == 200) {
            // 插入uorder表数据
            Map<String, String> rcMap = (Map<String, String>) rc.getData();
            uOrder uo = new uOrder();
            uo.setUserId(mb.getId());
            uo.setOrderNo(orderNum);
            uo.setPrepayId(rcMap.get("packages").split("=")[1]);
            uo.setGoodsDesc(paramMap.get("prdDesc"));
            uo.setTotalFee(Long.valueOf(paramMap.get("orderAmt")));
            uo.setPayType("1");
            uo.setPayStatus("0");
            communityOrderMapper.insertUorderInfo(uo);
            Integer uOrderId = uo.getId();
            // 更新所有订单的uorderId
            communityOrderMapper.updateUOrderIdInIds(uOrderId, idList);
        }
    }

    /**
     * 单个订单支付
     *
     * @param id
     * @param mb
     * @return
     */
    private ResultCode getPayInfoOne(Integer id, Member mb, Long communityId) {
        ResultCode rc = new ResultCode();
        String result = "";
        String orderNum = "";
        List<Integer> idList = new ArrayList<>();
        idList.add(id);
        Map<String, String> map = communityOrderMapper.getOrderInfo(id);
        List<String> prdDescList = communityOrderMapper.getProDesc(id);
        Map<String, String> paramMap = new HashMap<>();
        if (map != null && map.size() > 0) {
            paramMap.put("orderId", map.get("orderNum"));
            // 判断订单在uorder中是否存在，存在则重新生成一个订单号，并修改订单的订单号
            Integer ifExistsOrderNum = communityOrderMapper.ifExistsOrderNum(map.get("orderNum"));
            if (ifExistsOrderNum != null && ifExistsOrderNum == 1) {
                paramMap.put("orderId", UUID.randomUUID().toString());
                communityOrderMapper.updateOrderNumById(id, paramMap.get("orderId"));
            }
            orderNum = paramMap.get("orderId");
            BigDecimal totalAmount = new BigDecimal(String.valueOf(map.get("totalAmount")));
            // 判断用户是否设置帮办代提
            Integer ifDeputyHelp = communityOrderMapper.ifDeputyHelp(communityId, mb.getId());
            if (ifDeputyHelp != null && ifDeputyHelp == 1) {
                // 根据社区和商户设置配送费
                totalAmount = sumFee(communityId, totalAmount, rc, idList);
            } else {
                // 自提的情况，只收提货点费用
                Community getCommunity = communityMapper.queryAddress(communityId);
                if (getCommunity != null) {
                    String consigneePhone = getCommunity.getPhoneNumber();
                    BigDecimal consigneeMoney = getCommunity.getConsigneeMoney();
                    consigneeMoney = consigneeMoney == null ? BigDecimal.ZERO : consigneeMoney;
                    totalAmount = totalAmount.add(consigneeMoney);
                    Integer userId = communityMapper.getUserIdByPhone(consigneePhone, 11);
                    if (userId != null) {
                        communityOrderMapper.updateOrderInfoOfConsignee(idList, userId + "," + consigneeMoney + "," + idList.size());
                    }
                }
            }
            paramMap.put("orderAmt", StringUtil.getPrettyNumber(totalAmount));
            paramMap.put("openid", mb.getOpenid());
            if (prdDescList != null && prdDescList.size() > 0) {
                StringBuffer sb = new StringBuffer();
                prdDescList.forEach(prdDesc -> {
                    sb.append(prdDesc);
                });
                if (StringUtils.isNotEmpty(sb.toString())) {
                    paramMap.put("prdDesc", sb.toString());
                } else {
                    paramMap.put("prdDesc", "无");
                }
            }
            paramMap.put("retUrl", urlConfig.getOrderPayNotify());
            result = HttpClientUtil.doPost(urlConfig.getCommunityPay(), paramMap);
            log.info("orderId:" + paramMap.get("orderId") + ",result:" + result);
        }
        rc = new Gson().fromJson(result, ResultCode.class);
        dealResult(rc, mb, orderNum, paramMap, idList);
        return rc;
    }

    /**
     * 数据处理
     *
     * @param sortMap
     */
    private List<Integer> dataDeal(Map<Long, List<MerchantGoods>> sortMap) {
        List<Integer> idList = new ArrayList<>();
        if (sortMap != null && sortMap.size() > 0) {
            List<String> addressList = new ArrayList<>();
            sortMap.forEach((k, v) -> {
                if (v != null && v.size() > 0) {
                    // 保存主订单数据
                    Integer id = insertOrderInfo(v.get(0), addressList);
                    idList.add(id);
                    if (id != null && id > 0) {
                        List<GoodsOrderDetail> goList = new ArrayList<>();
                        v.forEach(mg -> {
                            getDetailInfoList(id, goList, mg);
                        });
                        if (goList != null && goList.size() > 0) {
                            // 保存订单详情数据
                            communityOrderMapper.insertOrderDetailList(goList);
                            BigDecimal totalPrice = BigDecimal.ZERO;
                            for (GoodsOrderDetail go : goList) {
                                totalPrice = totalPrice.add(go.getPrice().multiply(new BigDecimal(go.getQuantity())));
                            }
                            // 更新订单主表总价格
                            communityOrderMapper.updateTotalAmount(id, totalPrice);
                        }
                    }
                }
            });
        }
        return idList;
    }

    private void selectAddress(List<String> addressList, MerchantGoods mg) {
        // 查询地址
        if (addressList == null || addressList.size() == 0) {
            String address = communityOrderMapper.getAddressById(mg.getAddressId());
            if (StringUtils.isNotEmpty(address)) {
                addressList.add(address);
            }
        }
    }

    /**
     * 插入订单主表
     *
     * @param mg
     */
    private Integer insertOrderInfo(MerchantGoods mg, List<String> addressList) {
        // 组装订单实体类
        GoodsOrder go = new GoodsOrder();
        go.setMemberId(mg.getMemberId());
        go.setOrderNum(UUID.randomUUID().toString());
        go.setOrderState(1);
        go.setIfPay(0);
        go.setOrderType(3);
        go.setMerchantId(mg.getMerchantId());
        go.setCommunityId(mg.getCommunityId());
        go.setIfDelete(Byte.valueOf("0"));
        go.setUserDelete(0);
        go.setIfRemind(0);
        go.setIfDelayed(0);
        // 查询地址
        selectAddress(addressList, mg);
        go.setAddressId(mg.getAddressId());
        if (addressList != null && addressList.size() > 0) {
            go.setReceiveAddress(addressList.get(0).split("#")[0]);
            go.setReceivePhone(addressList.get(0).split("#")[2]);
            go.setReceiveName(addressList.get(0).split("#")[1]);
        }
        // 查询该订单用户是自提还是帮办
        Integer deputyHelp = communityOrderMapper.ifDeputyHelp(mg.getCommunityId(), mg.getMemberId());
        if (deputyHelp != null) {
            go.setDeputyHelp(deputyHelp);
        } else {
            go.setDeputyHelp(0);
        }
        go.setReceiveMessage(mg.getRemark());
        communityOrderMapper.insertOrder(go);
        return go.getId();
    }

    /**
     * 获取详情列表
     *
     * @return
     */
    private void getDetailInfoList(Integer id, List<GoodsOrderDetail> goList, MerchantGoods mg) {
        GoodsOrderDetail god = new GoodsOrderDetail();
        god.setMemberId(mg.getMemberId());
        god.setOrderId(id);
        god.setGoodsId(mg.getId().intValue());
        god.setPrice(new BigDecimal(mg.getCommunityPrice()));
        god.setQuantity(mg.getQuantity());
        god.setGoodsName(mg.getName());
        god.setGoodsPic(mg.getPic());
        god.setOrderState(1);
        god.setTotalAmount(new BigDecimal(mg.getCommunityPrice()));
        god.setIfPay(0);
        goList.add(god);
    }

    /**
     * 商品分类
     *
     * @param mgList
     * @return
     */
    private Map<Long, List<MerchantGoods>> sort(List<MerchantGoods> mgList, Integer memberId) {
        Set<Long> idSet = new HashSet<>();
        // 根据商户id分类
        mgList.forEach(mg -> {
            MerchantGoods getMg = communityOrderMapper.getGoodsInfoById(mg.getId());
            if (getMg != null) {
                mg.setCommunityPrice(getMg.getCommunityPrice());
                mg.setMerchantId(getMg.getMerchantId());
                mg.setName(getMg.getName());
                mg.setMemberId(memberId);
                idSet.add(mg.getMerchantId());
            }
        });
        // 根据商户id构建map
        Map<Long, List<MerchantGoods>> map = new HashMap<>(idSet.size());
        if (idSet != null && idSet.size() > 0) {
            Iterator<Long> it = idSet.iterator();
            while (it.hasNext()) {
                Long merchantId = it.next();
                map.put(merchantId, new ArrayList<>());
            }
        }
        // 比对相同商户id的数据放到一起
        for (MerchantGoods mg : mgList) {
            if (map != null && map.size() > 0) {
                map.forEach((k, v) -> {
                    if (mg.getMerchantId().intValue() == k.intValue()) {
                        v.add(mg);
                    }
                });
            }
        }
        return map;
    }
}
