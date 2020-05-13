package com.xinmintx.merchant.service.impl;

import com.google.gson.Gson;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.mapper.GiftMapper;
import com.xinmintx.merchant.mapper.MerchantMapper;
import com.xinmintx.merchant.mapper.MerchantPrinterMapper;
import com.xinmintx.merchant.model.*;
import com.xinmintx.merchant.service.IMerchantService;
import com.xinmintx.merchant.util.DateUtils;
import com.xinmintx.merchant.util.HttpClientUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 礼包实现类
 */
@Service
public class MerchantServiceImpl implements IMerchantService {
    @Resource
    private GiftMapper giftMapper;
    @Resource
    private MerchantMapper merchantMapper;
    @Resource
    private UrlConfig urlConfig;
    @Resource
    private MerchantPrinterMapper merchantPrinterMapper;

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
            // 判断如果是生日礼包,则会员生日礼包次数 -1
            if ("birthGiftPackage".equalsIgnoreCase(gift.getGiftGroup())) {
                giftMapper.updateBirthdayCount(memberId);
            }
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

    @Override
    public ResultCode createGoods(MerchantGoods merchantGoods, String token) {
        ResultCode code = new ResultCode();
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant != null) {
            //查询该商家是否存在打印机
            MerchantPrinterExample example = new MerchantPrinterExample();
            MerchantPrinterExample.Criteria criteria = example.createCriteria();
            criteria.andMerchantIdEqualTo(merchant.getId());
            List<MerchantPrinter> merchantPrinters = merchantPrinterMapper.selectByExample(example);
            if (merchantPrinters == null || merchantPrinters.size() == 0) {
                code.setCode(501);
                code.setMsg("对不起您还没有绑定打印机,请先购买打印机并绑定您的商户");
                return code;
            }
            if (merchantGoods != null) {
                merchantGoods.setMerchantId(merchant.getId());
                Long mgoodsId = merchantMapper.createGoods(merchantGoods);
                if (merchantGoods.getPicList() != null && merchantGoods.getPicList().size() > 0) {
                    List<MerchantGoodsPic> list = merchantGoods.getPicList();
                    if (list != null && list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setMgoodsId(merchantGoods.getId());
                        }
                        merchantMapper.addPic(list);
                    }
                    if (mgoodsId > 0) {
                        code.setCode(200);
                        code.setMsg("菜品添加成功");
                        return code;
                    } else {
                        code.setCode(500);
                        code.setMsg("菜品添加失败");
                        return code;
                    }
                } else {
                    code.setMsg("缺少商品图片,创建失败");
                    return code;
                }
            } else {
                code.setCode(200);
                code.setMsg("菜品参数为空,创建失败");
                return code;
            }
        } else {
            code.setMsg("用户不存在");
            return code;
        }
    }

    @Override
    public ResultCode queryMerchantGoods(Long goodsId) {
        ResultCode code = new ResultCode();
        MerchantGoods merchantGoods = merchantMapper.queryMerchantGoods(goodsId);
        merchantGoods.setPicList(merchantMapper.queryMerchantGoodsPic(goodsId));
        if (merchantGoods != null) {
            code.setCode(200);
            code.setData(merchantGoods);
            return code;
        } else {
            code.setMsg("菜品不存在");
            return code;
        }
    }

    @Override
    public ResultCode upMerchantGoods(MerchantGoods merchantGoods) {
        ResultCode code = new ResultCode();
        if (merchantGoods.getPicList() != null && merchantGoods.getPicList().size() > 0) {
            Long mgoodsId = merchantGoods.getId();
            merchantMapper.delPic(mgoodsId);
            for (int i = 0; i < merchantGoods.getPicList().size(); i++) {
                merchantGoods.getPicList().get(i).setMgoodsId(mgoodsId);
            }
            merchantMapper.addPic(merchantGoods.getPicList());
        } else {
            code.setMsg("缺少商品图片，修改失败");
            return code;
        }
        int i = merchantMapper.upMerchantGoods(merchantGoods);
        if (i > 0) {
            code.setCode(200);
            code.setMsg("修改成功");
            return code;
        } else {
            code.setMsg("修改失败");
            return code;
        }
    }

    @Override
    public ResultCode queryMerchantGoodsList(String token) {
        ResultCode code = new ResultCode();
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant != null) {
            List<MerchantGoods> list = merchantMapper.queryMerchantGoodsList(merchant.getId());
            if (list.size() > 0 && list != null) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setPicList(merchantMapper.queryMerchantGoodsPic(list.get(i).getId()));
                }
                code.setCode(200);
                code.setData(list);
                return code;
            } else {
                code.setMsg("暂无菜品");
                return code;
            }
        } else {
            code.setMsg("用户不存在");
            return code;
        }
    }

    @Override
    public ResultCode upGoodsState(Long goodsId, Integer state) {
        ResultCode code = new ResultCode();
        int i = merchantMapper.upGoodsState(goodsId, state);
        if (i > 0) {
            code.setCode(200);
            code.setMsg("修改成功");
            return code;
        } else {
            code.setCode(200);
            code.setMsg("修改失败");
            return code;
        }
    }

    @Override
    public ResultCode queryGoodsListByState(String token, Integer state) {
        ResultCode code = new ResultCode();
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant != null) {
            List<MerchantGoods> list = merchantMapper.queryMerchantGoodsListByState(merchant.getId(), state);
            if (list.size() > 0 && list != null) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setPicList(merchantMapper.queryMerchantGoodsPic(list.get(i).getId()));
                }
                code.setCode(200);
                code.setData(list);
                return code;
            } else {
                code.setMsg("暂无菜品");
                return code;
            }
        } else {
            code.setMsg("商户不存在");
            return code;
        }
    }

    @Override
    public ResultCode delGoods(Long goodsId) {
        ResultCode code = new ResultCode();
        int i = merchantMapper.delGoods(goodsId);
        if (i > 0) {
            merchantMapper.delGoodsPic(goodsId);
            code.setCode(200);
            code.setMsg("删除成功");
            return code;
        } else {
            code.setCode(200);
            code.setMsg("删除失败");
            return code;
        }
    }

    @Override
    public ResultCode getCommunityOrderList(Integer communityId, String token, Integer type) {
        ResultCode code = new ResultCode();
        List<VenderOrderMain> ls = new ArrayList<>();
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant != null) {
            if (type.equals(1)) {
                Integer merchantId = merchant.getId();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                long time = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L - 28800L);
                String beginDate = String.valueOf(time - Long.valueOf(86400));
                String endDate = String.valueOf(time);
                List<GoodsOrder> list = merchantMapper.queryOrderList(communityId, merchantId, beginDate, endDate);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        VenderOrderMain main = new VenderOrderMain();
                        main.setOrderId(String.valueOf(list.get(i).getId()));
                        main.setReceiveName(list.get(i).getReceiveName());
                        main.setReceivePhone(list.get(i).getReceivePhone());
                        main.setReceiveAddress(list.get(i).getReceiveAddress());
                        main.setOrderStatu(list.get(i).getOrderState());
                        main.setCreateTime(list.get(i).getCreateTime());
                        main.setuOrderId(list.get(i).getUOrderId());
                        main.setMerchantId(list.get(i).getMerchantId());
                        main.setDeputyInfo(list.get(i).getDeputyInfo());
                        main.setPrintNumber(list.get(i).getPrintNumber());
                        main.setDeputyHelp(list.get(i).getDeputyHelp());
                        main.setConsigneeInfo(list.get(i).getConsigneeInfo());
                        main.setTotalAmount(new BigDecimal(String.valueOf(list.get(i).getTotalAmount())));
                        main.setVoList(merchantMapper.queryOrder(list.get(i).getId()));
                        ls.add(main);
                    }
                    code.setCode(200);
                    code.setData(ls);
                    return code;
                } else {
                    code.setMsg("没有订单");
                    return code;
                }
            } else {
                Integer merchantId = merchant.getId();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                long time = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L - 28800L);
                String beginDate = String.valueOf(time);
                String endDate = String.valueOf(time + Long.valueOf(86400));
                List<GoodsOrder> list = merchantMapper.queryOrderList(communityId, merchantId, beginDate, endDate);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        VenderOrderMain main = new VenderOrderMain();
                        main.setOrderId(String.valueOf(list.get(i).getId()));
                        main.setReceiveName(list.get(i).getReceiveName());
                        main.setReceivePhone(list.get(i).getReceivePhone());
                        main.setReceiveAddress(list.get(i).getReceiveAddress());
                        main.setOrderStatu(list.get(i).getOrderState());
                        main.setCreateTime(list.get(i).getCreateTime());
                        main.setVoList(merchantMapper.queryOrder(list.get(i).getId()));
                        ls.add(main);
                    }
                    code.setCode(200);
                    code.setData(ls);
                    return code;
                } else {
                    code.setMsg("没有订单");
                    return code;
                }
            }

        } else {
            code.setMsg("商户不存在");
            return code;
        }
    }

    @Override
    public ResultCode getCommunityOrder(String token, Integer type) {
        ResultCode code = new ResultCode();
        Merchant merchant = merchantMapper.queryByToken(token);
        if (type.equals(1)) {
            Map map = new HashMap<>();
            if (merchant != null) {
                Integer merchantId = merchant.getId();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                long time = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L - 28800L);
                String beginDate = String.valueOf(time - Long.valueOf(86400));
                String endDate = String.valueOf(time);
                List<CommunityOrder> list = merchantMapper.queryCommunityOrder(merchantId, beginDate, endDate);
                //quzhong
                Set<Long> set = new HashSet();
                for (int i = 0; i < list.size(); i++) {
                    set.add(list.get(i).getCommunityId());
                }
                //cun chu she qu
                for (long communityId : set) {
                    Map<Integer, CommunityOrder> map1 = new HashMap();
                    int dingDanZongJia = 0;
                    int Index = -1;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getBigdecimal() != null && list.get(i).getQuantity() != null) {
                            if (communityId == list.get(i).getCommunityId()) {
                                if (!map1.containsKey(list.get(i).getGoodsId())) {
                                    map1.put(list.get(i).getGoodsId(), list.get(i));
                                    list.get(i).setTotal(list.get(i));
                                    dingDanZongJia += (int) (list.get(i).getTotalAmount() * 100);
                                } else {
                                    CommunityOrder communityOrder = map1.get(list.get(i).getGoodsId());
                                    communityOrder.setQuantity(communityOrder.getQuantity() + list.get(i).getQuantity());
                                    communityOrder.setTotal(communityOrder);
                                    communityOrder.setTotalAmount(communityOrder.getTotalAmount() + list.get(i).getTotalAmount());
                                    dingDanZongJia += (int) (list.get(i).getTotalAmount() * 100);
                                    map1.put(list.get(i).getGoodsId(), communityOrder);
                                }
                                Index = i;
                            }
                        }
                    }
                    CommunityOrderExt communityOrderExt = new CommunityOrderExt();
                    communityOrderExt.setTotalValue((double) dingDanZongJia / 100);
                    if (Index != -1) {
                        communityOrderExt.setCommunityName(list.get(Index).getName());
                        communityOrderExt.setAcceptOrder(list.get(Index).getAcceptOrder());
                        communityOrderExt.setCreateTime(list.get(Index).getCreateTime());
                    }
                    communityOrderExt.setOrderMap(map1);
                    map.put(communityId, communityOrderExt);
                }
                code.setData(map);
            }
            return code;
        } else {
            Map map = new HashMap<>();
            if (merchant != null) {
                Integer merchantId = merchant.getId();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                long time = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L - 28800L);
                String beginDate = String.valueOf(time);
                String endDate = String.valueOf(time + Long.valueOf(86400));
                List<CommunityOrder> list = merchantMapper.queryCommunityOrder(merchantId, beginDate, endDate);
                //quzhong
                Set<Long> set = new HashSet();
                for (int i = 0; i < list.size(); i++) {
                    set.add(list.get(i).getCommunityId());
                }
                //cun chu she qu
                for (long communityId : set) {
                    Map<Integer, CommunityOrder> map1 = new HashMap();
                    int dingDanZongJia = 0;
                    int Index = -1;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getBigdecimal() != null && list.get(i).getQuantity() != null) {
                            if (communityId == list.get(i).getCommunityId()) {
                                if (!map1.containsKey(list.get(i).getGoodsId())) {
                                    map1.put(list.get(i).getGoodsId(), list.get(i));
                                    list.get(i).setTotal(list.get(i));
                                    dingDanZongJia += (int) (list.get(i).getTotalAmount() * 100);
                                } else {
                                    CommunityOrder communityOrder = map1.get(list.get(i).getGoodsId());
                                    communityOrder.setQuantity(communityOrder.getQuantity() + list.get(i).getQuantity());
                                    communityOrder.setTotal(communityOrder);
                                    communityOrder.setTotalAmount(communityOrder.getTotalAmount() + list.get(i).getTotalAmount());
                                    dingDanZongJia += (int) (list.get(i).getTotalAmount() * 100);
                                    map1.put(list.get(i).getGoodsId(), communityOrder);
                                }
                                Index = i;
                            }
                        }
                    }
                    CommunityOrderExt communityOrderExt = new CommunityOrderExt();
                    communityOrderExt.setTotalValue((double) dingDanZongJia / 100);
                    if (Index != -1) {
                        communityOrderExt.setCommunityName(list.get(Index).getName());
                        communityOrderExt.setAcceptOrder(list.get(Index).getAcceptOrder());
                        communityOrderExt.setCreateTime(list.get(Index).getCreateTime());
                    }
                    communityOrderExt.setOrderMap(map1);
                    map.put(communityId, communityOrderExt);
                }
                code.setData(map);
            }
            return code;
        }
    }

    @Override
    public ResultCode upacceptOrder(String token, Integer communityId, Integer acceptOrder) {
        ResultCode code = new ResultCode();
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant != null) {
            Integer merchantId = merchant.getId();
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            long time = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L - 28800L);
            String beginDate = String.valueOf(time - Long.valueOf(86400));
            String endDate = String.valueOf(time);
            List<GoodsOrder> list = merchantMapper.queryOrderList(communityId, merchantId, beginDate, endDate);
            if (list != null && list.size() > 0) {
                List<Long> idList = new ArrayList<>();
                list.forEach(god -> {
                    idList.add(god.getId());
                });
                if (idList != null && idList.size() > 0) {
                    merchantMapper.updateOrderAcceptOrder(idList, acceptOrder);
                }
                code.setCode(200);
                code.setMsg("success");
                return code;
            } else {
                code.setMsg("订单为空");
                return code;
            }

        } else {
            code.setMsg("未查到商户信息");
            return code;
        }
    }

    @Override
    public ResultCode upOrderStatu(Integer orderId, Integer orderStatu) {
        ResultCode code = new ResultCode();
        merchantMapper.upGoodsOrderStatu(orderId, orderStatu);
        List<GoodsOrderDetail> list = merchantMapper.selectOrder(orderId);
        if (list != null && list.size() > 0) {
            List<Long> idList = new ArrayList<>();
            list.forEach(god -> {
                idList.add(god.getId());
            });
            if (idList != null && idList.size() > 0) {
                int i = merchantMapper.upOrderStatu(idList, orderStatu);
                if (i > 0) {
                    code.setCode(200);
                    code.setMsg("success");
                    return code;
                } else {
                    code.setCode(200);
                    code.setMsg("false");
                    return code;
                }
            } else {
                code.setCode(200);
                code.setMsg("未获取到订单号");
                return code;
            }
        } else {
            code.setCode(200);
            code.setMsg("未获取到订单");
            return code;
        }
    }

    @Override
    public ResultCode orderHistory(String token, String times) {
        ResultCode code = new ResultCode();
        Merchant merchant = merchantMapper.queryByToken(token);
        Map map = new HashMap();
        List listInfo = new ArrayList();
        if (merchant != null) {
            Integer merchantId = merchant.getId();
            if (times != null && times.length() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Long bDate = null;
                try {
                    Date getBDate = sdf.parse(times);
                    bDate = getBDate.getTime() / 1000;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                for (int s = 0; s <= 89; s++) {
                    String beginDate = String.valueOf(bDate - Long.valueOf(86400) * (s + 1));
                    String endDate = String.valueOf(bDate - Long.valueOf(86400) * s);
                    List<CommunityOrder> list = merchantMapper.queryOrderHistory(merchantId, beginDate, endDate);
                    //quzhong
                    Set<Long> set = new HashSet();
                    for (int i = 0; i < list.size(); i++) {
                        set.add(list.get(i).getCommunityId());
                    }
                    //cun chu she qu
                    for (long communityId : set) {
                        Map<Integer, CommunityOrder> map1 = new HashMap();
                        int dingDanZongJia = 0;
                        int Index = -1;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getBigdecimal() != null && list.get(i).getQuantity() != null) {
                                if (communityId == list.get(i).getCommunityId()) {
                                    if (!map1.containsKey(list.get(i).getGoodsId())) {
                                        map1.put(list.get(i).getGoodsId(), list.get(i));
                                        list.get(i).setTotal(list.get(i));
                                        dingDanZongJia += list.get(i).getTotalAmount();
                                    } else {
                                        CommunityOrder communityOrder = map1.get(list.get(i).getGoodsId());
                                        communityOrder.setQuantity(communityOrder.getQuantity() + list.get(i).getQuantity());
                                        communityOrder.setTotal(communityOrder);
                                        communityOrder.setTotalAmount(communityOrder.getTotalAmount() + list.get(i).getTotalAmount());
                                        dingDanZongJia += (int) (list.get(i).getTotalAmount() * 100);
                                        map1.put(list.get(i).getGoodsId(), communityOrder);
                                    }
                                    Index = i;
                                }
                            }
                        }
                        CommunityOrderExt communityOrderExt = new CommunityOrderExt();
                        communityOrderExt.setTotalValue((double) dingDanZongJia / 100);
                        if (Index != -1) {
                            communityOrderExt.setCommunityName(list.get(Index).getName());
                            communityOrderExt.setAcceptOrder(list.get(Index).getAcceptOrder());
                            communityOrderExt.setCreateTime(list.get(Index).getCreateTime());
                        }
                        communityOrderExt.setOrderMap(map1);
//                       listInfo.add(communityOrderExt);
                        map.put(communityId, communityOrderExt);
                    }
                    code.setData(map);
                }
                return code;
            } else {
                for (int s = 0; s <= 89; s++) {
                    long time = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L - 28800L);
                    String beginDate = String.valueOf(time - Long.valueOf(86400) * (s + 1));
                    String endDate = String.valueOf(time - Long.valueOf(86400) * s);
                    List<CommunityOrder> list = merchantMapper.queryOrderHistory(merchantId, beginDate, endDate);
                    //quzhong
                    Set<Long> set = new HashSet();
                    for (int i = 0; i < list.size(); i++) {
                        set.add(list.get(i).getCommunityId());
                    }
                    //cun chu she qu
                    for (long communityId : set) {
                        Map<Integer, CommunityOrder> map1 = new HashMap();
                        int dingDanZongJia = 0;
                        int Index = -1;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getBigdecimal() != null && list.get(i).getQuantity() != null) {
                                if (communityId == list.get(i).getCommunityId()) {
                                    if (!map1.containsKey(list.get(i).getGoodsId())) {
                                        map1.put(list.get(i).getGoodsId(), list.get(i));
                                        list.get(i).setTotal(list.get(i));
                                        dingDanZongJia += (int) (list.get(i).getTotalAmount() * 100);
                                    } else {
                                        CommunityOrder communityOrder = map1.get(list.get(i).getGoodsId());
                                        communityOrder.setQuantity(communityOrder.getQuantity() + list.get(i).getQuantity());
                                        communityOrder.setTotal(communityOrder);
                                        communityOrder.setTotalAmount(communityOrder.getTotalAmount() + list.get(i).getTotalAmount());
                                        dingDanZongJia += (int) (list.get(i).getTotalAmount() * 100);
                                        map1.put(list.get(i).getGoodsId(), communityOrder);
                                    }
                                    Index = i;
                                }
                            }
                        }
                        CommunityOrderExt communityOrderExt = new CommunityOrderExt();
                        communityOrderExt.setTotalValue((double) dingDanZongJia / 100);
                        if (Index != -1) {
                            communityOrderExt.setCommunityName(list.get(Index).getName());
                            communityOrderExt.setAcceptOrder(list.get(Index).getAcceptOrder());
                            communityOrderExt.setCreateTime(list.get(Index).getCreateTime());
                        }
                        communityOrderExt.setOrderMap(map1);
                        map.put(communityId, communityOrderExt);
//                        listInfo.add(communityOrderExt);
                    }
                    code.setData(map);
                }
                return code;
            }
        } else {
            code.setMsg("未查到商户信息");
            return code;
        }
    }

    @Override
    public ResultCode historicalOrderDetails(String token, String times, Integer communityId) {
        ResultCode code = new ResultCode();
        List<VenderOrderMain> ls = new ArrayList<>();
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant != null) {
            Integer merchantId = merchant.getId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Long bDate;
            Date getBDate = null;
            try {
                getBDate = sdf.parse(times);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            bDate = getBDate.getTime() / 1000;
            String beginDate = String.valueOf(bDate);
            String endDate = String.valueOf(bDate + Long.valueOf(86400));
            List<GoodsOrder> list = merchantMapper.queryHistoricalOrder(communityId, merchantId, beginDate, endDate);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    VenderOrderMain main = new VenderOrderMain();
                    main.setOrderId(String.valueOf(list.get(i).getId()));
                    main.setReceiveName(list.get(i).getReceiveName());
                    main.setReceivePhone(list.get(i).getReceivePhone());
                    main.setReceiveAddress(list.get(i).getReceiveAddress());
                    main.setOrderStatu(list.get(i).getOrderState());
                    main.setCreateTime(list.get(i).getCreateTime());
                    main.setVoList(merchantMapper.queryOrder(list.get(i).getId()));
                    ls.add(main);
                }
                code.setCode(200);
                code.setData(ls);
                return code;
            } else {
                code.setMsg("没有订单");
                return code;
            }
        } else {
            code.setMsg("未查到商户信息");
            return code;
        }
    }

    @Override
    public ResultCode queryBank(String bank) {
        ResultCode code = new ResultCode();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("bank", bank);
        String url = "http://a3.work/open/demo3/index.php";
        code.setData(HttpClientUtil.doPost(url, paramMap));
        return code;
    }

    /**
     * 拒绝订单
     *
     * @param communityId
     * @param token
     * @return
     */
    @Override
    public ResultCode refuseOrder(Integer communityId, String token) {
        ResultCode resultInfo = new ResultCode();
        ResultCode rc = getCommunityOrderList(communityId, token, 1);
        if (rc != null && rc.getData() != null && rc.getData() instanceof List) {
            List<VenderOrderMain> vomList = (List<VenderOrderMain>) rc.getData();
            // 1.获取支付订单信息，准备退款
            if (vomList != null && vomList.size() > 0) {
                for (VenderOrderMain vom : vomList) {
                    // 订单主键
                    String orderId = vom.getOrderId();
                    // 支付订单id
                    Long uOrderId = vom.getuOrderId();
                    // 总金额
                    BigDecimal totalAmount = vom.getTotalAmount();
                    totalAmount = totalAmount == null ? BigDecimal.ZERO : totalAmount;
                    // 商户id
                    Long merchantId = vom.getMerchantId();
                    // 内帮办配送费
                    String deputyInfo = vom.getDeputyInfo();
                    BigDecimal deputyFee = BigDecimal.ZERO;
                    BigDecimal count = BigDecimal.ONE;
                    if (StringUtils.isNotEmpty(deputyInfo)) {
                        deputyFee = new BigDecimal(deputyInfo.split(",")[1]);
                        count = new BigDecimal(deputyInfo.split(",")[2]);
                    }
                    // 提货点费用
                    String consigneeInfo = vom.getConsigneeInfo();
                    BigDecimal consigneeFee = BigDecimal.ZERO;
                    if (StringUtils.isNotEmpty(consigneeInfo)) {
                        consigneeFee = new BigDecimal(consigneeInfo.split(",")[1]);
                        count = new BigDecimal(consigneeInfo.split(",")[2]);
                    }
                    // 退的费用是该订单金额 + 内帮办金额/订单数量 + 提货点金额/订单数量
                    totalAmount = totalAmount.add(((deputyFee.add(consigneeFee)).divide(count, 2, BigDecimal.ROUND_HALF_DOWN)));
                    // 1.调第三方退款
                    String orderNum = merchantMapper.getOrderNoByIds(uOrderId);
                    // "https://factory-api.xinmintx.cn/api/wx/reimburse"
                    String url = urlConfig.getMerchantRefuse();
                    Map<String, String> paramMap = new HashMap<>(2);
                    paramMap.put("oriOrderId", orderNum);
                    paramMap.put("orderAmt", totalAmount.toString());
                    String string = HttpClientUtil.doPost(url, paramMap);
                    ResultCode refuseInfo = new Gson().fromJson(string, ResultCode.class);
                    if (refuseInfo.getCode() == 200 && !string.contains("余额不足")) {
                        // 退款成功,修改社区订单状态为已拒单
                        merchantMapper.updateOrderAndDetailState(orderId, 9, 3, 3);
                        // 有内帮办费用，扣除内帮办配送费冻结金额，保存日志
                        if (StringUtils.isNotEmpty(deputyInfo)) {
                            String memberId = deputyInfo.split(",")[0];
                            String memberMoney = deputyInfo.split(",")[1];
                            BigDecimal returnMoney = new BigDecimal(memberMoney).divide(count, 2, BigDecimal.ROUND_HALF_DOWN);
                            merchantMapper.reduceMemberFreezMoney(memberId, returnMoney.toString());
                            BigDecimal balance = merchantMapper.getFreezingAmount(memberId);
                            balance = balance == null ? BigDecimal.ZERO : balance;
                            MemberAmountLog mal = new MemberAmountLog();
                            mal.setSource(5);
                            mal.setMemberId(Long.valueOf(memberId));
                            mal.setType("1");
                            mal.setPrice(returnMoney.multiply(new BigDecimal("-1")));
                            mal.setBalance(balance);
                            mal.setRemark("商户拒单，内帮办配送费退回给用户");
                            merchantMapper.insertLogs(mal);
                        }
                        if (consigneeFee.compareTo(BigDecimal.ZERO) == 1) {
                            // 扣除提货点人的冻结金额
                            String userId = consigneeInfo.split(",")[0];
                            BigDecimal returnMoney = consigneeFee.divide(count, 2, BigDecimal.ROUND_HALF_DOWN);
                            UserAccount ua = merchantMapper.getUserAccount(userId);
                            if (ua != null) {
                                // 修改记录
                                ua.setFreezeMoney(ua.getFreezeMoney().subtract(returnMoney));
                                merchantMapper.updateUserAccount(ua);
                                // 2.user_account_record
                                UserAccountRecord userAccountRecord = new UserAccountRecord();
                                userAccountRecord.setUserId(Integer.valueOf(userId));
                                userAccountRecord.setOrderId(Integer.valueOf(orderId));
                                userAccountRecord.setUserAccountId(ua.getId());
                                userAccountRecord.setMoneyChange(returnMoney.multiply(new BigDecimal("-1")));
                                userAccountRecord.setDescription("商户拒单，退还用户提货点费用！");
                                merchantMapper.insertUserAccountRecord(userAccountRecord);
                            }
                        }
                        // 扣除商户冻结金额，保存日志
                        merchantMapper.reduceMerchantFreezMoney(merchantId, totalAmount);
                        BigDecimal balance = merchantMapper.getFreezingAmountOfMerchant(merchantId);
                        balance = balance == null ? BigDecimal.ZERO : balance;
                        MerchantAmountLog mal = new MerchantAmountLog();
                        mal.setMerchantId(merchantId);
                        mal.setType("1");
                        mal.setPrice(totalAmount.multiply(new BigDecimal("-1")));
                        mal.setBalance(balance);
                        mal.setRemark("商户拒单，退还用户金额");
                        merchantMapper.insertMerchantLogs(mal);
                        resultInfo.setCode(200);
                        resultInfo.setMsg("拒单成功");
                    } else {
                        resultInfo.setCode(500);
                        resultInfo.setMsg("调取第三方退款失败，拒单失败");
                        return resultInfo;
                    }
                }
            }
        } else {
            return rc;
        }
        return resultInfo;
    }

    /**
     * 提现回调接口
     *
     * @param pn
     * @return
     */
    @Override
    public ResultCode paymentOnBehalfOfNotify(PoboNotify pn) {
        ResultCode rc = new ResultCode();
        rc.setCode(200);
        if (pn != null) {
            rc.setData(pn);
            // 保存代付回调信息
            merchantMapper.insertPn(pn);
        }
        return rc;
    }

    @Override
    public ResultCode queryGoodsDetail(Long communityId, Long goodsId) {
        ResultCode code = new ResultCode();
        GoodsDetail goodsDetail = new GoodsDetail();
        MerchantGoods merchantGoods = merchantMapper.queryMerchantGoods(goodsId);
        if (merchantGoods != null) {
            goodsDetail.setId(merchantGoods.getId());
            goodsDetail.setBigdecimal(merchantGoods.getBigdecimal());
            goodsDetail.setCommunityPrice(merchantGoods.getCommunityPrice());
            goodsDetail.setName(merchantGoods.getName());
            goodsDetail.setDescription(merchantGoods.getDescription());
            goodsDetail.setConsigneeAddress(merchantMapper.queryAddress(communityId));
            goodsDetail.setPicList(merchantMapper.queryMerchantGoodsPic(goodsId));
            goodsDetail.setList(merchantMapper.queryGoodsOrder(goodsId));
            code.setCode(200);
            code.setData(goodsDetail);
            return code;
        } else {
            code.setMsg("商品不存在");
            return code;
        }
    }

    @Override
    public ResultCode totalOrder(String token) {
        ResultCode code = new ResultCode();
        Merchant merchant = merchantMapper.queryByToken(token);
        List listInfo = new ArrayList();
        if (null != merchant) {
            Integer merchantId = merchant.getId();
            CommunityOrderExt communityOrderExt = getCommunityOrderExt(merchantId);
            if (communityOrderExt != null) {
                listInfo.add(communityOrderExt);
            }
            code.setData(listInfo);
        }

        return code;
    }

    @Override
    public ResultCode getOrderListByStatus(Integer communityId, String token, Integer type, Integer orderStatus) {
        ResultCode code = new ResultCode();
        List<VenderOrderMain> ls = new ArrayList<>();
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant != null) {
            if (type.equals(1)) {
                Integer merchantId = merchant.getId();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                long time = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L - 28800L);
                String beginDate = String.valueOf(time - Long.valueOf(86400));
                String endDate = String.valueOf(time);
                List<GoodsOrder> list = merchantMapper.queryOrdersList(communityId, merchantId, beginDate, endDate,orderStatus);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        VenderOrderMain main = new VenderOrderMain();
                        main.setOrderId(String.valueOf(list.get(i).getId()));
                        main.setReceiveName(list.get(i).getReceiveName());
                        main.setReceivePhone(list.get(i).getReceivePhone());
                        main.setReceiveAddress(list.get(i).getReceiveAddress());
                        main.setOrderStatu(list.get(i).getOrderState());
                        main.setCreateTime(list.get(i).getCreateTime());
                        main.setuOrderId(list.get(i).getUOrderId());
                        main.setMerchantId(list.get(i).getMerchantId());
                        main.setDeputyInfo(list.get(i).getDeputyInfo());
                        main.setPrintNumber(list.get(i).getPrintNumber());
                        main.setDeputyHelp(list.get(i).getDeputyHelp());
                        main.setConsigneeInfo(list.get(i).getConsigneeInfo());
                        main.setTotalAmount(new BigDecimal(String.valueOf(list.get(i).getTotalAmount())));
                        main.setVoList(merchantMapper.queryOrder(list.get(i).getId()));
                        ls.add(main);
                    }
                    code.setCode(200);
                    code.setData(ls);
                    return code;
                } else {
                    code.setMsg("没有订单");
                    return code;
                }
            } else {
                Integer merchantId = merchant.getId();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                long time = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L - 28800L);
                String beginDate = String.valueOf(time);
                String endDate = String.valueOf(time + Long.valueOf(86400));
                List<GoodsOrder> list = merchantMapper.queryOrdersList(communityId, merchantId, beginDate, endDate,orderStatus);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        VenderOrderMain main = new VenderOrderMain();
                        main.setOrderId(String.valueOf(list.get(i).getId()));
                        main.setReceiveName(list.get(i).getReceiveName());
                        main.setReceivePhone(list.get(i).getReceivePhone());
                        main.setReceiveAddress(list.get(i).getReceiveAddress());
                        main.setOrderStatu(list.get(i).getOrderState());
                        main.setCreateTime(list.get(i).getCreateTime());
                        main.setVoList(merchantMapper.queryOrder(list.get(i).getId()));
                        ls.add(main);
                    }
                    code.setCode(200);
                    code.setData(ls);
                    return code;
                } else {
                    code.setMsg("没有订单");
                    return code;
                }
            }

        } else {
            code.setMsg("商户不存在");
            return code;
        }
    }

    @Override
    public ResultCode queryMerchantAuditStatus(String token) {
        ResultCode code = new ResultCode();
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant!=null){
            int isReview = merchant.getIsReview();
            code.setCode(200);
            code.setData(isReview);
            return code;
        }else {
            code.setMsg("商户未入驻,请先登录");
            return code;
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
        gpl.setGiftId(gft.getId());
        gpl.setPrice(gft.getPrice());
        gpl.setBalance(balance);
        gpl.setType(type);
        if (merchantId != null) {
            gpl.setMerchantId(merchantId);
        }
        giftMapper.insertGiftLog(gpl);
    }

    private CommunityOrderExt getCommunityOrderExt(Integer merchantId) {
        long time = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L);
        String beginDate = String.valueOf(time - Long.valueOf(86400));
        String endDate = String.valueOf(time);
        List<CommunityOrder> list = merchantMapper.queryOrderHistory(merchantId, beginDate, endDate);
        Map<Integer, CommunityOrder> map1 = new HashMap();
        Map<String,CommunityOrderCount> map = new HashMap<>();
        int num = 1;
        int dingDanZongJia = 0;
        int Index = -1;
        if (list.size() > 0 && null != list) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getBigdecimal() != null && list.get(i).getQuantity() != null) {
                    if (!map1.containsKey(list.get(i).getGoodsId())) {
                        list.get(i).setTotal(list.get(i));
                        dingDanZongJia += (int) (100 * list.get(i).getTotalAmount());
                        map1.put(list.get(i).getGoodsId(), list.get(i));
                    } else {
                        CommunityOrder communityOrder = map1.get(list.get(i).getGoodsId());
                        communityOrder.setQuantity(communityOrder.getQuantity() + list.get(i).getQuantity());
                        communityOrder.setTotal(communityOrder);
                        communityOrder.setTotalAmount(((double) ((int) (communityOrder.getTotalAmount() * 100) + (int) (list.get(i).getTotalAmount() * 100))) / 100);
                        dingDanZongJia += (int) (100 * list.get(i).getTotalAmount());
                        map1.put(list.get(i).getGoodsId(), communityOrder);
                    }
                    Index = i;
                }

                if (!map.containsKey(list.get(i).getName())){
                    CommunityOrderCount communityOrderCount = new CommunityOrderCount();
                    communityOrderCount.setCont(num);
                    communityOrderCount.setCommunityName(list.get(i).getName());
                    map.put(list.get(i).getName(),communityOrderCount);
                }else {
                    CommunityOrderCount communityOrderCount = new CommunityOrderCount();
                    communityOrderCount.setCommunityName(list.get(i).getName());
                    num++;
                    communityOrderCount.setCont(num);
                    map.put(list.get(i).getName(),communityOrderCount);
                }
            }
            CommunityOrderExt communityOrderExt = new CommunityOrderExt();
            communityOrderExt.setTotalValue((double) dingDanZongJia / 100);
            if (Index != -1) {
                communityOrderExt.setCommunityName(list.get(Index).getName());
                communityOrderExt.setAcceptOrder(list.get(Index).getAcceptOrder());
                communityOrderExt.setCreateTime(list.get(Index).getCreateTime());
            }
            long times = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L -28800L);
            communityOrderExt.setTime(String.valueOf(times));
            communityOrderExt.setOrderMap(map1);
            communityOrderExt.setOrderQuantity(map);
            return communityOrderExt;
        }
        return null;
    }

}
