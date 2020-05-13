package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.google.gson.Gson;
import com.xinmintx.hstx.configuration.pay.PayConfig;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.bo.GoodsSkuBo;
import com.xinmintx.hstx.pojo.bo.WechatPayBo;
import com.xinmintx.hstx.pojo.entity.GoodsOrderType;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.pojo.vo.GoodsOrderParentInterimVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.vo.ShopVo;
import com.xinmintx.hstx.service.*;
import com.xinmintx.hstx.util.DateUtil;
import com.xinmintx.hstx.util.FieldUtils;
import com.xinmintx.hstx.util.ListUtils;
import com.xinmintx.hstx.util.httpclient.HttpClientUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/29 0029
 * @time: 上午 10:48
 * @Description:
 */
@Service
@Transactional
public class GoodsOrderPayServiceImpl implements GoodsOrderPayService {

    @Autowired
    private WechatPayService wechatPayService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;
    @Autowired
    private GoodsOrderDetailMapper goodsOrderDetailMapper;
    @Autowired
    private ShippingAddressMapper shippingAddressMapper;
    @Autowired
    private ShoppingCartDetailMapper shoppingCartDetailMapper;
    @Autowired
    private GoodPtcodeInfoMapper goodPtcodeInfoMapper;
    @Autowired
    private GoodPtcodeMapper goodPtcodeMapper;
    @Autowired
    private GoodPtgoodMapper goodPtgoodMapper;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;
    @Autowired
    private GoodsOrderParentInterimMapper goodsOrderParentInterimMapper;
    @Autowired
    private GoodsOrderInterimMapper goodsOrderInterimMapper;
    @Autowired
    private GoodsOrderDetailInterimMapper goodsOrderDetailInterimMapper;
    @Autowired
    private GoodPanicBuyMapper goodPanicBuyMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GiftService giftService;
    @Autowired
    private AmountPayService amountPayService;
    @Autowired
    private PayConfig payConfig;

    /**
     * 购物车结算
     *
     * @param shopVo
     * @return
     */
    @Override
    public GoodsOrderParentInterimVo shoppingCartSettle(ShopVo shopVo) {
        Member member = memberService.findMemberByToken(shopVo.getToken());
        //1.获取用户购买的商品列表
        if (shopVo.getCartId() != null && shopVo.getCartId().length > 0) {
            //获取购物车的商品sku集合
            List<GoodsSkuBo> goodsSkuBos = selectSkuByIds(member, shopVo);
            if (goodsSkuBos.size() < 1) {
                return null;
            }
            return createGoodsOrders(member, goodsSkuBos, GoodsOrderType.SHOPPING_CART_SETTLE, null);
        }
        return null;
    }

    /**
     * 根据购物车id回去商品规格详情
     *
     * @param member
     * @param shopVo
     * @return
     */
    private List<GoodsSkuBo> selectSkuByIds(Member member, ShopVo shopVo) {
        Integer[] cartId = shopVo.getCartId();
        List<Integer> ids = Arrays.asList(cartId);
        List<ShoppingCartDetail> shoppingCartDetails = new LambdaQueryChainWrapper<>(shoppingCartDetailMapper)
                .eq(ShoppingCartDetail::getMemberId, member.getId())
                .eq(ShoppingCartDetail::getStatus, 1)
                .in(ShoppingCartDetail::getId, ids)
                .list();
        List<GoodsSkuBo> goodsSkuBos = new ArrayList<>();
        shoppingCartDetails.forEach(shoppingCartDetail -> {
            if (shoppingCartDetail.getType() == 1) {
                //普通商品
                GoodsSkuBo goodsSkuBo = selectMemberGoodsSku(member, shoppingCartDetail.getSkuId(), shoppingCartDetail.getTotal());
                if (goodsSkuBo != null) {
                    goodsSkuBo.setSkuType(1);
                    goodsSkuBos.add(goodsSkuBo);
                }
            }
            if (shoppingCartDetail.getType() == 4) {
                //抢购商品
                GoodsSkuBo goodsSkuBo = selectMemberPanicSku(member, shoppingCartDetail.getGoodsId(), shoppingCartDetail.getSkuId(), shoppingCartDetail.getTotal());
                if (goodsSkuBo != null) {
                    goodsSkuBo.setSkuType(2);
                    goodsSkuBos.add(goodsSkuBo);
                }
            }
        });
        return goodsSkuBos;
    }

    /**
     * 单个商品结算(普通商品立即购买)
     *
     * @param shopVo
     * @return
     */
    @Override
    public GoodsOrderParentInterimVo goodsSettle(ShopVo shopVo) {
        Member member = memberService.findMemberByToken(shopVo.getToken());
        List<GoodsSkuBo> goodsSkuBos = new ArrayList<>();
        GoodsSkuBo goodsSkuBo = selectMemberGoodsSku(member, shopVo.getSkuId(), shopVo.getTotal());
        goodsSkuBos.add(goodsSkuBo);
        if (shopVo.getGiftId() != null) {
            for (GoodsSkuBo skuBo : goodsSkuBos) {
                skuBo.setTotal(1);
            }
            return createGoodsOrders(member, goodsSkuBos, GoodsOrderType.GIFT_GOODS_SETTLE, shopVo.getGiftId());
        } else {
            return createGoodsOrders(member, goodsSkuBos, GoodsOrderType.GOODS_SETTLE, null);
        }
    }

    /**
     * 单个商品结算(抢购商品)
     *
     * @param shopVo
     * @return
     */
    @Override
    public GoodsOrderParentInterimVo goodsSettleByPanic(ShopVo shopVo) {
        if (shopVo.getPanicId() == null) {
            return null;
        }
        Member member = memberService.findMemberByToken(shopVo.getToken());
        List<GoodsSkuBo> goodsSkuBos = new ArrayList<>();
        GoodsSkuBo goodsSkuBo = selectMemberPanicSku(member, shopVo.getPanicId(), shopVo.getSkuId(), shopVo.getTotal());
        goodsSkuBos.add(goodsSkuBo);
        return createGoodsOrders(member, goodsSkuBos, GoodsOrderType.GOODS_PANIC_SETTLE, null);
    }

    /**
     * 查询用户对应的商品 sku的价格
     *
     * @param member
     * @param goodsSkuId
     * @param total
     * @return
     */
    private GoodsSkuBo selectMemberGoodsSku(Member member, Integer goodsSkuId, Integer total) {
        GoodsSku goodsSku = goodsSkuMapper.selectById(goodsSkuId);
        if (goodsSku == null) {
            return null;
        }
        goodsService.setMemberGoodsSkuPrice(goodsSku, member);
        return goodsService.getGoodsSkuBo(goodsSku, total);
    }

    /**
     * 查询用户对应的抢购商品 sku的价格
     *
     * @param member
     * @param goodsSkuId
     * @param total
     * @return
     */
    private GoodsSkuBo selectMemberPanicSku(Member member, Integer panicId, Integer goodsSkuId, Integer total) {
        GoodsSku goodsSku = goodsSkuMapper.selectById(goodsSkuId);
        if (goodsSku == null) {
            return null;
        }
        GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(panicId);
        goodsService.setMemberPanicSkuPrice(goodPanicBuy, goodsSku, member);
        GoodsSkuBo goodsSkuBo = goodsService.getGoodsSkuBo(goodsSku, total);
        goodsSkuBo.setGoodsId(goodPanicBuy.getId());
        return goodsSkuBo;
    }

    /**
     * 创建商品订单和支付订单
     *
     * @param shopVo
     * @return
     */
    @Override
    public ResultCode createGoodsPayOrder(ShopVo shopVo) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        if (shopVo.getAddressId() == null) {
            resultCode.setMsg("收货地址不存在");
            return resultCode;
        }
        ShippingAddress shippingAddress = shippingAddressMapper.selectById(shopVo.getAddressId());
        if (shippingAddress == null) {
            resultCode.setMsg("收货地址不存在");
            return resultCode;
        }
        int id = shopVo.getId();
        Member member = memberService.findMemberByToken(shopVo.getToken());
        GoodsOrderParentInterim parentInterim = goodsOrderParentInterimMapper.selectById(id);
        if (parentInterim == null || parentInterim.getStatus() == null || parentInterim.getStatus() == 0) {
            resultCode.setMsg("订单不存在");
            return resultCode;
        }
        if (shopVo.getPayType() != null && shopVo.getPayType() == 4) {
            if (StringUtils.isBlank(member.getPayPassword())) {
                resultCode.setCode(401);
                resultCode.setMsg("请先设置支付密码");
                return resultCode;
            }
            //余额支付
            if (StringUtils.isBlank(shopVo.getPassword())) {
                resultCode.setCode(402);
                resultCode.setMsg("请输入支付密码");
                return resultCode;
            }
            String password = DigestUtils.md5Hex(shopVo.getPassword());
            if (!member.getPayPassword().equals(password)) {
                resultCode.setCode(402);
                resultCode.setMsg("支付密码错误");
                return resultCode;
            }
            BigDecimal cashAmount = member.getCashAmount();
            if (cashAmount == null || cashAmount.compareTo(parentInterim.getTotalPrice()) < 0) {
                //余额不足
                resultCode.setMsg("余额不足");
                return resultCode;
            }
        } else {
            shopVo.setPayType(1);
        }
        int orderType = parentInterim.getOrderType();
        switch (orderType) {
            case 0:
                //购物车结算,修改购物车状态
                updateShoppingCart(parentInterim, member);
            case 1:
            case 4:
                //普通商品购买
                return createGoodsOrder(parentInterim, shopVo, member, shippingAddress);
            case 2:
                //礼包
                return createGoodsOrdersByGift(parentInterim, shopVo, member, shippingAddress);
            case 3:
                //拼团
                return createGoodsOrdersByGroup(parentInterim, shopVo, member, shippingAddress);
            default:
                break;
        }
        return null;
    }

    private ResultCode createGoodsOrder(GoodsOrderParentInterim parentInterim, ShopVo shopVo, Member member, ShippingAddress shippingAddress) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        //临时表数据保存至,正式表
        //获取订单
        parentInterim.setStatus(0);
        parentInterim.updateById();
        List<GoodsOrderInterim> goodsOrderInterims = new LambdaQueryChainWrapper<>(goodsOrderInterimMapper)
                .eq(GoodsOrderInterim::getOrderId, parentInterim.getId())
                .list();
        List<GoodsOrder> goodsOrders = new ArrayList<>();
        for (GoodsOrderInterim goodsOrderInterim : goodsOrderInterims) {
            GoodsOrder goodsOrder = FieldUtils.fieldTrans(goodsOrderInterim, GoodsOrder.class);
            goodsOrder.setAddressId(shopVo.getAddressId());
            goodsOrder.setReceiveAddress(shippingAddress.getRegion() + shippingAddress.getAddress());
            goodsOrder.setReceiveName(shippingAddress.getName());
            goodsOrder.setReceivePhone(shippingAddress.getCellphone());
            goodsOrder.setReceiveMessage(shopVo.getMessage());
            goodsOrder.setId(null);
            goodsOrder.insert();
            goodsOrders.add(goodsOrder);
            //获取订单详情
            List<GoodsOrderDetailInterim> orderDetailInterims = new LambdaQueryChainWrapper<>(goodsOrderDetailInterimMapper)
                    .eq(GoodsOrderDetailInterim::getOrderId, goodsOrderInterim.getId())
                    .list();
            if (goodsOrder.getOrderType() == 4) {
                //抢购
                GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(orderDetailInterims.get(0).getGoodsId());
                if (goodPanicBuy.getIsSale() == 0 || goodPanicBuy.getStatus() == 0 || new Date().after(goodPanicBuy.getEndTime())) {
                    resultCode.setMsg("商品已下架");
                    //回滚
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return resultCode;
                }
                int total = 0;
                for (GoodsOrderDetailInterim orderDetailInterim : orderDetailInterims) {
                    GoodsOrderDetail goodsOrderDetail = FieldUtils.fieldTrans(orderDetailInterim, GoodsOrderDetail.class);
                    goodsOrderDetail.setId(null);
                    goodsOrderDetail.setOrderId(goodsOrder.getId());
                    goodsOrderDetail.insert();
                    total += orderDetailInterim.getQuantity();
                }
                boolean restriction = goodsService.checkMemberRestriction(goodPanicBuy, member.getId(), total);
                if (!restriction) {
                    resultCode.setMsg("超过限购数量");
                    //回滚
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return resultCode;
                }
                if (total > goodPanicBuy.getStockNum()) {
                    resultCode.setMsg("库存不足");
                    //回滚
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return resultCode;
                }
            } else {
                for (GoodsOrderDetailInterim orderDetailInterim : orderDetailInterims) {
                    GoodsSku goodsSku = goodsSkuMapper.selectById(orderDetailInterim.getSkuId());
                    if (orderDetailInterim.getQuantity() > goodsSku.getStockNum()) {
                        resultCode.setMsg("库存不足");
                        //回滚
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return resultCode;
                    }
                    GoodsOrderDetail goodsOrderDetail = FieldUtils.fieldTrans(orderDetailInterim, GoodsOrderDetail.class);
                    goodsOrderDetail.setId(null);
                    goodsOrderDetail.setOrderId(goodsOrder.getId());
                    goodsOrderDetail.insert();
                }
            }
        }
        WechatPayBo wechatPayBo = new WechatPayBo();
        wechatPayBo.setUserId(member.getId());
        wechatPayBo.setOrderId(String.valueOf(System.currentTimeMillis()));
        wechatPayBo.setPrdDesc(parentInterim.getDescription());
        wechatPayBo.setOrderAmt(parentInterim.getTotalPrice().multiply(BigDecimal.valueOf(100)).longValue());
        wechatPayBo.setOpenId(member.getOpenid());
        wechatPayBo.setRetUrl(payConfig.getGoodsNotifyUrl());
        wechatPayBo.setType(5);
        Map<String, Object> map = null;
        if (shopVo.getPayType() == 4) {
            map = amountPayService.createOrder(wechatPayBo, member);
        } else {
            map = wechatPayService.unifiedorder(wechatPayBo);
        }
        if (map != null && "SUCCESS".equals(map.get("code").toString())) {
            for (GoodsOrder goodsOrder : goodsOrders) {
                goodsOrder.setOrderNum(map.get("order_no").toString());
                goodsOrder.setUOrderId((Integer) map.get("order_id"));
                goodsOrder.updateById();
            }
            if (shopVo.getPayType() == 4) {
                resultCode.setCode(205);
                resultCode.setMsg("支付成功");
                amountPayService.amountNotify(wechatPayBo);
            } else {
                resultCode.setCode(200);
                resultCode.setMsg("SUCCESS");
                resultCode.setData(map);
            }
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultCode;
    }

    /**
     * 商品礼包购买
     *
     * @param parentInterim
     * @param shopVo
     * @param member
     * @return
     */
    //@Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    private ResultCode createGoodsOrdersByGift(GoodsOrderParentInterim parentInterim, ShopVo shopVo, Member member, ShippingAddress shippingAddress) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");

        //获取商品价格
        BigDecimal totalPrice = parentInterim.getTotalPrice();
        //0元礼包领取
        if (parentInterim.getGiftId() != null && totalPrice.compareTo(BigDecimal.ZERO) == 0) {

            //领取礼包
            ResultCode<MemberGift> giftCode = giftService.getGift(member, parentInterim.getGiftId());
            MemberGift memberGift = giftCode.getData();
            if (memberGift == null) {
                resultCode.setMsg(giftCode.getMsg());
                return resultCode;
            }
            if (memberGift.getIfDelete() != null && memberGift.getIfDelete() == 1) {
                //礼包已经使用
                resultCode.setCode(501);
                resultCode.setMsg("礼包已被使用");
                return resultCode;
            }
            Gift gift = giftMapper.selectById(memberGift.getGiftId());
            if (gift == null) {
                resultCode.setMsg("礼包不存在");
                return resultCode;
            } else {
                int i = DateUtil.compareDateWithNow(gift.getEndDate());
                if (i < 0) {
                    //礼包过期
                    resultCode.setMsg("礼包已过期");
                    return resultCode;
                }
            }
            parentInterim.setStatus(0);
            parentInterim.updateById();
            //临时表数据保存至,正式表
            //获取订单
            List<GoodsOrderInterim> goodsOrderInterims = new LambdaQueryChainWrapper<>(goodsOrderInterimMapper)
                    .eq(GoodsOrderInterim::getOrderId, parentInterim.getId())
                    .list();
            //创建礼包订单
            //临时表数据保存至,正式表
            //获取订单
            for (GoodsOrderInterim goodsOrderInterim : goodsOrderInterims) {
                GoodsOrder goodsOrder = FieldUtils.fieldTrans(goodsOrderInterim, GoodsOrder.class);
                goodsOrder.setId(null);
                goodsOrder.setOrderState(2);
                goodsOrder.setIfPay(1);
                goodsOrder.setAddressId(shopVo.getAddressId());
                goodsOrder.setReceiveAddress(shippingAddress.getRegion() + shippingAddress.getAddress());
                goodsOrder.setReceiveName(shippingAddress.getName());
                goodsOrder.setReceivePhone(shippingAddress.getCellphone());
                goodsOrder.setReceiveMessage(shopVo.getMessage());
                goodsOrder.insert();
                //获取订单详情
                List<GoodsOrderDetailInterim> orderDetailInterims = new LambdaQueryChainWrapper<>(goodsOrderDetailInterimMapper)
                        .eq(GoodsOrderDetailInterim::getOrderId, goodsOrderInterim.getId())
                        .list();
                for (GoodsOrderDetailInterim orderDetailInterim : orderDetailInterims) {
                    GoodsOrderDetail goodsOrderDetail = FieldUtils.fieldTrans(orderDetailInterim, GoodsOrderDetail.class);
                    goodsOrderDetail.setId(null);
                    goodsOrderDetail.setOrderState(2);
                    goodsOrderDetail.setIfPay(1);
                    goodsOrderDetail.setOrderId(goodsOrder.getId());
                    goodsOrderDetail.insert();
                }
            }
            //删除用户礼包
            memberGift.setIfDelete(1);
            memberGift.updateById();
            //修改用户礼包数量
            int platformCount = member.getPlatformCount();
            if (platformCount > 0) {
                member.setPlatformCount(platformCount - 1);
                member.updateById();
            }
            resultCode.setCode(201);
            resultCode.setMsg("领取成功");
            return resultCode;
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultCode;
    }

    private ResultCode createGoodsOrdersByGroup(GoodsOrderParentInterim parentInterim, ShopVo shopVo, Member member, ShippingAddress shippingAddress) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        parentInterim.setStatus(0);
        parentInterim.updateById();
        GoodPtcodeInfo goodPtcodeInfo = new LambdaQueryChainWrapper<>(goodPtcodeInfoMapper)
                .eq(GoodPtcodeInfo::getGoodsOrderId, parentInterim.getId())
                .one();
        GoodsOrderInterim goodsOrderInterim = new LambdaQueryChainWrapper<>(goodsOrderInterimMapper)
                .eq(GoodsOrderInterim::getOrderId, parentInterim.getId())
                .one();
        GoodsOrder goodsOrder = FieldUtils.fieldTrans(goodsOrderInterim, GoodsOrder.class);
        goodsOrder.setAddressId(shopVo.getAddressId());
        goodsOrder.setReceiveAddress(shippingAddress.getRegion() + shippingAddress.getAddress());
        goodsOrder.setReceiveName(shippingAddress.getName());
        goodsOrder.setReceivePhone(shippingAddress.getCellphone());
        goodsOrder.setReceiveMessage(shopVo.getMessage());
        goodsOrder.setId(null);
        goodsOrder.insert();
        goodPtcodeInfo.setGoodsOrderId(goodsOrder.getId());
        goodPtcodeInfo.setAddressId(shippingAddress.getId());
        goodPtcodeInfo.setAddressName(shippingAddress.getName());
        goodPtcodeInfo.updateById();
        //获取订单详情
        GoodsOrderDetailInterim orderDetailInterim = new LambdaQueryChainWrapper<>(goodsOrderDetailInterimMapper)
                .eq(GoodsOrderDetailInterim::getOrderId, goodsOrderInterim.getId())
                .one();
        GoodsOrderDetail goodsOrderDetail = FieldUtils.fieldTrans(orderDetailInterim, GoodsOrderDetail.class);
        goodsOrderDetail.setId(null);
        goodsOrderDetail.setOrderId(goodsOrder.getId());
        goodsOrderDetail.insert();
        WechatPayBo wechatPayBo = new WechatPayBo();
        wechatPayBo.setUserId(member.getId());
        wechatPayBo.setOrderId(String.valueOf(System.currentTimeMillis()));
        wechatPayBo.setPrdDesc(parentInterim.getDescription());
        wechatPayBo.setOrderAmt(parentInterim.getTotalPrice().multiply(BigDecimal.valueOf(100)).longValue());
        wechatPayBo.setOpenId(member.getOpenid());
        wechatPayBo.setRetUrl(payConfig.getGoodsGroupNotifyUrl());
        wechatPayBo.setType(7);
        Map<String, Object> map = null;
        if (shopVo.getPayType() == 4) {
            map = amountPayService.createOrder(wechatPayBo, member);
        } else {
            map = wechatPayService.unifiedorder(wechatPayBo);
        }
        if (map != null && "SUCCESS".equals(map.get("code").toString())) {
            goodsOrder.setOrderNum(map.get("order_no").toString());
            goodsOrder.setUOrderId((Integer) map.get("order_id"));
            goodsOrder.updateById();
            if (shopVo.getPayType() == 4) {
                resultCode.setCode(205);
                resultCode.setMsg("支付成功");
                amountPayService.amountNotify(wechatPayBo);
            } else {
                resultCode.setCode(200);
                resultCode.setMsg("SUCCESS");
                resultCode.setData(map);
            }
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultCode;
    }

    /**
     * 商品付款
     *
     * @param shopVo
     * @return
     */
    @Override
    public ResultCode goodsPay(ShopVo shopVo) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(shopVo.getId());
        if (goodsOrder == null) {
            resultCode.setCode(400);
            resultCode.setMsg("订单不存在");
            return resultCode;
        }
        Date createTime = goodsOrder.getCreateTime();
        long l = createTime.getTime() + (30 * 60 * 1000);
        int i = DateUtil.compareDateWithNow(l);
        if (goodsOrder.getOrderState() != 1 || i < 0) {
            resultCode.setCode(401);
            resultCode.setMsg("订单已关闭");
            return resultCode;
        }
        Member member = memberService.findMemberByToken(shopVo.getToken());
        BigDecimal totalAmount = goodsOrder.getTotalAmount();
        BigDecimal totalPrice = totalAmount.multiply(BigDecimal.valueOf(100));
        //获取订单详情
        List<GoodsOrderDetail> goodsOrderDetails = new LambdaQueryChainWrapper<>(goodsOrderDetailMapper)
                .eq(GoodsOrderDetail::getOrderId, goodsOrder.getId())
                .list();
        GoodsOrderDetail goodsOrderDetail = goodsOrderDetails.get(0);
        WechatPayBo wechatPayBo = new WechatPayBo();
        wechatPayBo.setOrderId(String.valueOf(System.currentTimeMillis()));
        wechatPayBo.setUserId(member.getId());
        wechatPayBo.setPrdDesc(goodsOrderDetail.getGoodsName() + "等");
        wechatPayBo.setOrderAmt(totalPrice.longValue());
        wechatPayBo.setOpenId(member.getOpenid());
        switch (goodsOrder.getOrderType()) {
            case 1:
            case 4:
                wechatPayBo.setRetUrl(payConfig.getGoodsNotifyUrl());
                wechatPayBo.setType(5);
                break;
            case 2:
                wechatPayBo.setRetUrl(payConfig.getGoodsGroupNotifyUrl());
                wechatPayBo.setType(7);
                break;
            default:
                break;
        }
        if (goodsOrder.getOrderType() == 1) {
            for (GoodsOrderDetail orderDetail : goodsOrderDetails) {
                Goods goods = goodsMapper.selectById(orderDetail.getGoodsId());
                if (goods == null || goods.getIsDelete() == 1 || goods.getStatus() == 0 || goods.getGiftBag() == 1) {
                    resultCode.setMsg("商品已下架");
                    //取消订单
                    cancelGoodsOrder(goodsOrder, goodsOrderDetails);
                    return resultCode;
                }
            }
        }
        if (goodsOrder.getOrderType() == 4) {
            //抢购商品,判断抢购商品的库存,用户限购数,抢购时间
            GoodPanicBuy goodPanicBuy = goodPanicBuyMapper.selectById(goodsOrderDetail.getGoodsId());
            if (goodPanicBuy.getIsSale() == 0 || goodPanicBuy.getStatus() == 0 || new Date().after(goodPanicBuy.getEndTime())) {
                resultCode.setMsg("商品已下架");
                //取消订单
                cancelGoodsOrder(goodsOrder, goodsOrderDetails);
                return resultCode;
            }
            int total = 0;
            for (GoodsOrderDetail orderDetail : goodsOrderDetails) {
                total += orderDetail.getQuantity();
            }
            boolean restriction = goodsService.checkMemberRestriction(goodPanicBuy, member.getId(), total);
            if (!restriction) {
                resultCode.setMsg("超过限购数量");
                //取消订单
                cancelGoodsOrder(goodsOrder, goodsOrderDetails);
                return resultCode;
            }
            if (total > goodPanicBuy.getStockNum()) {
                resultCode.setMsg("库存不足");
                //取消订单
                cancelGoodsOrder(goodsOrder, goodsOrderDetails);
                return resultCode;
            }
        }
        // 社区菜品订单付款
        if (goodsOrder.getOrderType() == 3) {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("orderId", String.valueOf(goodsOrder.getId()));
            paramMap.put("memberId", String.valueOf(member.getId()));
            paramMap.put("communityId", String.valueOf(goodsOrder.getCommunityId()));
            String result = HttpClientUtil.doPost(payConfig.getCommunityOnePayUrl(), paramMap);
            resultCode = new Gson().fromJson(result, ResultCode.class);
            return resultCode;
        }
        if (goodsOrder.getOrderType() == 2) {
            //查询拼团信息
            GoodPtcodeInfo goodPtcodeInfo = new LambdaQueryChainWrapper<>(goodPtcodeInfoMapper)
                    .eq(GoodPtcodeInfo::getGoodsOrderId, goodsOrder.getId())
                    .one();
            if (goodPtcodeInfo == null) {
                //团不存在,或已结束
                resultCode.setCode(400);
                resultCode.setMsg("团不存在,或已结束");
                //取消订单
                cancelGoodsOrder(goodsOrder, goodsOrderDetails);
                return resultCode;
            }
            //获取拼团商品
            GoodPtgood goodPtgood = goodPtgoodMapper.selectById(goodPtcodeInfo.getPtgoodId());
            if (goodPtgood == null) {
                resultCode.setMsg("商品已下架");
                //取消订单
                cancelGoodsOrder(goodsOrder, goodsOrderDetails);
                return resultCode;
            }
            if (goodPtgood.getIsSale() == 0 || new Date().after(goodPtgood.getEndTime())) {
                resultCode.setMsg("商品已下架");
                //取消订单
                cancelGoodsOrder(goodsOrder, goodsOrderDetails);
                return resultCode;
            }
            int count = new LambdaQueryChainWrapper<>(goodPtcodeInfoMapper)
                    .eq(GoodPtcodeInfo::getPid, goodPtcodeInfo.getPid())
                    .eq(GoodPtcodeInfo::getIsJoin, 2)
                    .count();
            GoodPtcode goodPtcode = goodPtcodeMapper.selectById(goodPtcodeInfo.getPid());
            if (goodPtcode == null) {
                //团不存在,或已结束
                resultCode.setCode(400);
                resultCode.setMsg("团不存在,或已结束");
                //取消订单
                cancelGoodsOrder(goodsOrder, goodsOrderDetails);
                return resultCode;
            } else {
                if (goodPtcodeInfo.getIsHeader() != 1 && goodPtcode.getStatus() != 1) {
                    //支付人不是团长的请情况
                    resultCode.setCode(400);
                    resultCode.setMsg("团不存在,或已结束");
                    //取消订单
                    cancelGoodsOrder(goodsOrder, goodsOrderDetails);
                    return resultCode;
                }
            }
            int ptnumber = goodPtcode.getPtnumber();
            if (count == ptnumber) {
                //人数已满
                resultCode.setCode(400);
                resultCode.setMsg("人数已满");
                //取消订单
                cancelGoodsOrder(goodsOrder, goodsOrderDetails);
                return resultCode;
            }
        }
        if (shopVo.getPayType() == 4) {
            BigDecimal cashAmount = member.getCashAmount();
            if (StringUtils.isBlank(member.getPayPassword())) {
                resultCode.setCode(401);
                resultCode.setMsg("请先设置支付密码");
                return resultCode;
            }
            if (StringUtils.isBlank(shopVo.getPassword())) {
                resultCode.setMsg("请输入支付密码");
                return resultCode;
            }
            String password = DigestUtils.md5Hex(shopVo.getPassword());
            if (!member.getPayPassword().equals(password)) {
                resultCode.setMsg("支付密码错误");
                return resultCode;
            }
            if (cashAmount == null || cashAmount.compareTo(totalAmount) < 0) {
                //余额不足
                resultCode.setMsg("余额不足");
                return resultCode;
            }
        }
        Map<String, Object> map = null;
        if (shopVo.getPayType() == 4) {
            map = amountPayService.createOrder(wechatPayBo, member);
        } else {
            map = wechatPayService.unifiedorder(wechatPayBo);
        }
        if (map != null && "SUCCESS".equals(map.get("code").toString())) {
            goodsOrder.setOrderNum(map.get("order_no").toString());
            goodsOrder.setUOrderId((Integer) map.get("order_id"));
            goodsOrder.updateById();
            if (shopVo.getPayType() == 4) {
                resultCode.setCode(205);
                resultCode.setMsg("支付成功");
                amountPayService.amountNotify(wechatPayBo);
            } else {
                resultCode.setCode(200);
                resultCode.setMsg("SUCCESS");
                resultCode.setData(map);
            }
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultCode;
    }

    /**
     * 取消订单
     *
     * @param goodsOrder
     * @param goodsOrderDetails
     */
    private void cancelGoodsOrder(GoodsOrder goodsOrder, List<GoodsOrderDetail> goodsOrderDetails) {
        goodsOrder.setOrderState(4);
        goodsOrderMapper.updateById(goodsOrder);
        if (goodsOrderDetails == null || goodsOrderDetails.size() == 0) {
            goodsOrderDetails = new LambdaQueryChainWrapper<>(goodsOrderDetailMapper)
                    .eq(GoodsOrderDetail::getOrderId, goodsOrder.getId())
                    .list();
        }
        //修改子订单状态
        if (goodsOrderDetails != null && goodsOrderDetails.size() > 0) {
            for (GoodsOrderDetail goodsOrderDetail : goodsOrderDetails) {
                goodsOrderDetail.setOrderState(4);
                goodsOrderDetail.updateById();
            }
        }
    }

    /**
     * 修改购物车状态
     *
     * @param parentInterim
     */
    private void updateShoppingCart(GoodsOrderParentInterim parentInterim, Member member) {
        List<GoodsOrderInterim> orders = new LambdaQueryChainWrapper<>(goodsOrderInterimMapper)
                .eq(GoodsOrderInterim::getOrderId, parentInterim.getId())
                .list();
        if (orders != null && orders.size() > 0) {
            for (GoodsOrderInterim order : orders) {
                List<GoodsOrderDetailInterim> details = new LambdaQueryChainWrapper<>(goodsOrderDetailInterimMapper)
                        .eq(GoodsOrderDetailInterim::getOrderId, order.getId())
                        .list();
                for (GoodsOrderDetailInterim detail : details) {
                    new LambdaUpdateChainWrapper<>(shoppingCartDetailMapper)
                            .eq(ShoppingCartDetail::getSkuId, detail.getSkuId())
                            .eq(ShoppingCartDetail::getMemberId, member.getId())
                            .eq(ShoppingCartDetail::getStatus, 1)
                            .eq(ShoppingCartDetail::getType, order.getOrderType())
                            .set(ShoppingCartDetail::getStatus, 0)
                            .update();
                }
            }
        }
    }

    /**
     * 创建订单临时表信息
     *
     * @param member    用户
     * @param list      商品
     * @param orderType 订单类型(0,购物车,1普通购买,2礼包商品,3拼团,4抢购)
     */
    private GoodsOrderParentInterimVo createGoodsOrders(Member member, List<GoodsSkuBo> list, Integer orderType, Integer giftId) {
        //创建订单表父表临时表
        GoodsOrderParentInterim parentInterim = new GoodsOrderParentInterim();
        parentInterim.setDescription(list.get(0).getGoodsName() + "等");
        parentInterim.setMemberId(member.getId());
        parentInterim.setOrderType(orderType);
        parentInterim.setStatus(1);
        parentInterim.setGiftId(giftId);
        parentInterim.insert();
        //订单按厂家分组
        List<GoodsOrderDetailInterim> details = new ArrayList<>();
        List<BigDecimal> bigDecimals = new ArrayList<>();
        switch (orderType) {
            case 0:
                //购物车
                Map<String, List<GoodsSkuBo>> skuTypeMap = ListUtils.listGroup(list, "skuType");
                skuTypeMap.forEach((key, value) -> {
                    if (Integer.parseInt(key) == 1) {
                        //1普通商品
                        Map<String, List<GoodsSkuBo>> listMap = ListUtils.listGroup(value, "relateId");
                        saveGoodsOrder(parentInterim, member, listMap, 1, details, bigDecimals);
                    } else {
                        //抢购商品
                        //订单拆分成,每个抢购商品一个订单
                        Map<String, List<GoodsSkuBo>> listMap = ListUtils.listGroup(value, "goodsId");
                        saveGoodsOrder(parentInterim, member, listMap, 4, details, bigDecimals);
                    }
                });
                break;
            case 1:
                //普通购买
            case 2:
                //礼包
            case 3:
                //拼团
            case 4:
                //抢购
                Map<String, List<GoodsSkuBo>> listMap = ListUtils.listGroup(list, "relateId");
                saveGoodsOrder(parentInterim, member, listMap, orderType, details, bigDecimals);
                break;
            default:
                break;
        }
        BigDecimal totalFree = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : bigDecimals) {
            totalFree = totalFree.add(bigDecimal);
        }
        parentInterim.setTotalPrice(totalFree);
        parentInterim.updateById();
        GoodsOrderParentInterimVo parentInterimVo = FieldUtils.fieldTrans(parentInterim, GoodsOrderParentInterimVo.class);
        parentInterimVo.setDetails(details);
        return parentInterimVo;
    }

    /**
     * 保存订单信息
     *
     * @param parentInterim
     * @param member
     * @param listMap
     * @param orderType     订单类型(0购物车,1普通购买,2礼包商品,3拼团,4抢购)
     * @param details
     * @param bigDecimals
     */
    public void saveGoodsOrder(GoodsOrderParentInterim parentInterim, Member member, Map<String, List<GoodsSkuBo>> listMap, Integer orderType, List<GoodsOrderDetailInterim> details, List<BigDecimal> bigDecimals) {
        listMap.forEach((key, value) -> {
            //添加主表信息
            GoodsOrderInterim orderInterim = new GoodsOrderInterim();
            orderInterim.setOrderId(parentInterim.getId());
            orderInterim.setMemberId(member.getId());
            orderInterim.setOrderState(1);
            orderInterim.setCreateTime(new Date());
            orderInterim.setUpdateTime(new Date());
            orderInterim.setIfPay(0);
            orderInterim.setDividedState(1);
            orderInterim.setFactoryId(Integer.parseInt(key));
            switch (orderType) {
                case 0:
                    //0购物车
                case 1:
                    //1普通购买
                case 2:
                    //2礼包商品
                    orderInterim.setOrderType(1);
                    break;
                case 3:
                    //3拼团
                    orderInterim.setOrderType(2);
                    break;
                case 4:
                    //4抢购
                    orderInterim.setOrderType(4);
                    break;
                default:
                    break;
            }
            orderInterim.insert();
            //添加子表信息
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (GoodsSkuBo goodsSkuBo : value) {
                BigDecimal price = BigDecimal.ZERO;
                if (!orderType.equals(GoodsOrderType.GIFT_GOODS_SETTLE)) {
                    price = goodsSkuBo.getPrice();
                }
                BigDecimal totalAmount = price.multiply(BigDecimal.valueOf(goodsSkuBo.getTotal()));
                totalPrice = totalPrice.add(totalAmount);
                GoodsOrderDetailInterim detailInterim = new GoodsOrderDetailInterim();
                detailInterim.setMemberId(member.getId());
                detailInterim.setOrderId(orderInterim.getId());
                detailInterim.setGoodsId(goodsSkuBo.getGoodsId());
                detailInterim.setSkuId(goodsSkuBo.getId());
                detailInterim.setPrice(price);
                detailInterim.setQuantity(goodsSkuBo.getTotal());
                detailInterim.setGoodsName(goodsSkuBo.getGoodsName());
                detailInterim.setGoodsPic(goodsSkuBo.getPhotoUrl());
                detailInterim.setOrderState(1);
                detailInterim.setTotalAmount(totalAmount);
                detailInterim.setLack(0);
                detailInterim.setWeight(goodsSkuBo.getGoodsWeight());
                detailInterim.setCreateTime(new Date());
                detailInterim.setUpdateTime(new Date());
                detailInterim.setSpecName(goodsSkuBo.getSpecName());
                detailInterim.insert();
                details.add(detailInterim);
            }
            orderInterim.setTotalAmount(totalPrice);
            orderInterim.updateById();
            bigDecimals.add(totalPrice);
        });
    }

    @Override
    public ResultCode<GoodsOrderParentInterimVo> getGoodsPayOrder(Integer id) {
        ResultCode<GoodsOrderParentInterimVo> resultCode = new ResultCode<>();
        resultCode.setCode(500);
        resultCode.setMsg("订单不存在");
        GoodsOrderParentInterim parentInterim = goodsOrderParentInterimMapper.selectById(id);
        GoodsOrderParentInterimVo goodsOrderParentInterimVo = FieldUtils.fieldTrans(parentInterim, GoodsOrderParentInterimVo.class);
        List<GoodsOrderDetailInterim> details = new ArrayList<>();
        if (id != null) {
            List<GoodsOrderInterim> goodsOrderInterims = new LambdaQueryChainWrapper<>(goodsOrderInterimMapper)
                    .eq(GoodsOrderInterim::getOrderId, id)
                    .list();
            for (GoodsOrderInterim goodsOrderInterim : goodsOrderInterims) {
                List<GoodsOrderDetailInterim> goodsOrderDetailInterims = new LambdaQueryChainWrapper<>(goodsOrderDetailInterimMapper)
                        .eq(GoodsOrderDetailInterim::getOrderId, goodsOrderInterim.getId())
                        .list();
                details.addAll(goodsOrderDetailInterims);
            }
            goodsOrderParentInterimVo.setDetails(details);
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(goodsOrderParentInterimVo);
        }
        return resultCode;
    }


}
