package com.xinmintx.hstx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.configuration.pay.PayConfig;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.bo.GoodsSkuBo;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.pojo.vo.GoodsOrderDetailVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.GoodsOrderService;
import com.xinmintx.hstx.service.GoodsService;
import com.xinmintx.hstx.util.DateUtil;
import com.xinmintx.hstx.util.FieldUtils;
import com.xinmintx.hstx.util.ListUtils;
import com.xinmintx.hstx.util.httpclient.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/10 0010
 * @time: 上午 9:02
 * @Description:
 */
@Service
public class GoodsOrderImpl implements GoodsOrderService {

    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    @Autowired
    private ShippingAddressMapper shippingAddressMapper;

    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    @Autowired
    private GoodsSpecValueMapper goodsSpecValueMapper;

    @Autowired
    private GoodsOrderDetailMapper goodsOrderDetailMapper;

    @Autowired
    private FactoryMapper factoryMapper;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private PayConfig payConfig;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private MerchantGoodsMapper merchantGoodsMapper;

    /**
     * 根据会员id查询全部订单
     *
     * @param memberId 会员id
     * @return 订单
     */
    @Override
    public ResultCode queryAllOrderByMemberId(Integer memberId) {
        ResultCode<Object> resultCode = new ResultCode<>();
        List<Object> list = new ArrayList<>();

        QueryWrapper<GoodsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time").eq("user_delete", 0).eq("member_id", memberId).in("order_type", 1, 2, 3, 4);
        //查询全部主订单
        List<GoodsOrder> goodsOrders = goodsOrderMapper.selectList(queryWrapper);

        //没有订单
        if (goodsOrders == null || goodsOrders.size() == 0) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(new ArrayList<>());
            return resultCode;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = new Date();
            //有订单并遍历全部订单
            for (GoodsOrder goodsOrder : goodsOrders) {
                // 社区菜品订单
                Integer orderType = goodsOrder.getOrderType().intValue();
                //判断订单是否超时(前提是待付款的)
                if (goodsOrder.getOrderState() == 1) {
                    Date createTime = goodsOrder.getCreateTime();
                    long l = createTime.getTime() + (30 * 60 * 1000);
                    Date date = new Date(l);
                    int i = DateUtil.compareDateWithNow(date);
                    //如果超时修改订单状态(数据库也更改状态)
                    if (i < 0) {
                        goodsOrder.setOrderState(4);
                        goodsOrderMapper.updateById(goodsOrder);
                        //获取子订单并修改子订单状态
                        Map<String, Object> hashMap = new HashMap<>();
                        hashMap.put("order_id", goodsOrder.getId());
                        List<GoodsOrderDetail> goodsOrderDetails = goodsOrderDetailMapper.selectByMap(hashMap);
                        if (goodsOrderDetails != null && goodsOrderDetails.size() > 0) {
                            for (GoodsOrderDetail goodsOrderDetail : goodsOrderDetails) {
                                goodsOrderDetail.setOrderState(4);
                                goodsOrderDetailMapper.updateById(goodsOrderDetail);
                            }
                        }
                    }
                }

                HashMap<Object, Object> hashMap = new HashMap<>();
                //获取发货时间
                Date beginDate = goodsOrder.getSendDate();
                long days = 10;
                if (beginDate != null) {
                    String beginDateStr = format.format(beginDate);
                    String nowDateStr = format.format(endDate);
                    try {
                        beginDate = format.parse(beginDateStr);
                        endDate = format.parse(nowDateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //计算剩余天数
                    days = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
                }

                //剩余天数
                hashMap.put("countDate", days);
                //主订单id
                hashMap.put("goodsOrderId", goodsOrder.getId());
                //主订单订单号
                hashMap.put("courierNumber", goodsOrder.getCourierNumber());
                //hashMap.put("ifPay", goodsOrder.getIfPay());
                // 社区菜品订单
                if(orderType == 3){
                    // 查询商铺和社区信息
                    getMerchantAndCommunityInfo(goodsOrder, hashMap);
                }else{
                    //查询店铺
                    Factory factory = factoryMapper.selectById(goodsOrder.getFactoryId().longValue());
                    if (factory != null) {
                        hashMap.put("factoryId", factory.getFactoryId());
                        hashMap.put("factoryName", factory.getName());
                    } else {
                        hashMap.put("factoryId", "商家id不存在");
                        hashMap.put("factoryName", "商家不存在");
                    }
                }
                //查询订单子表的全部订单
                Map<String, Object> map = new HashMap<>();
                map.put("order_id", goodsOrder.getId());
                List<GoodsOrderDetail> goodsOrderDetails = goodsOrderDetailMapper.selectByMap(map);

                List<GoodsOrderDetailVo> goodsOrderDetailVos = ListUtils.listTrans(goodsOrderDetails, GoodsOrderDetailVo.class);

                //List<Object> goodsOrderDetailList = new ArrayList<>();

                //遍历订单子表
                for (GoodsOrderDetailVo goodsOrderDetail : goodsOrderDetailVos) {
                    if(orderType == 3){
                        Integer goodsId = goodsOrderDetail.getGoodsId();
                        if(goodsId != null){
                            MerchantGoods mg = merchantGoodsMapper.selectById(goodsId);
                            if(mg != null){
                                goodsOrderDetail.setSpecValue(String.valueOf(mg.getBigdecimal()));
                            }
                        }else{
                            goodsOrderDetail.setSpecValue("无规格");
                        }
                    } else {
                        //获取规格参数,并添加规格参数
                        Integer skuId = goodsOrderDetail.getSkuId();
                        GoodsSku goodsSku = goodsSkuMapper.selectById(skuId);
                        if (goodsSku != null) {
                            GoodsSkuBo goodsSkuBo = goodsService.getGoodsSkuBo(goodsSku, null);
                            if (goodsSkuBo != null) {
                                goodsOrderDetail.setSpecValue(goodsSkuBo.getSpecName());
                            }
                        }else{
                            goodsOrderDetail.setSpecValue("无规格");
                        }
                        //goodsOrderDetailList.add(goodsOrderDetail);
                    }
                }
                //订单集合
                hashMap.put("orderDetailList", goodsOrderDetailVos);
                //总价
                hashMap.put("totalPrice", goodsOrder.getTotalAmount());
                //提醒发货
                hashMap.put("ifRemind", goodsOrder.getIfRemind());
                //订单类型
                hashMap.put("orderType", goodsOrder.getOrderType());
                //订单状态
                hashMap.put("orderState", goodsOrder.getOrderState());
                list.add(hashMap);
            }
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(list);
            return resultCode;
        }
    }

    // 获取商户和社区信息
    private void getMerchantAndCommunityInfo(GoodsOrder goodsOrder, HashMap<Object, Object> hashMap){
        // 查询商铺
        Merchant merchant = merchantMapper.selectById(goodsOrder.getMerchantId());
        if(merchant != null){
            hashMap.put("merchantId", goodsOrder.getMerchantId());
            hashMap.put("merchantName", merchant.getName());
        } else {
            hashMap.put("merchantId", "供货商id不存在");
            hashMap.put("merchantName", "供货商不存在");
        }
        // 查询社区
        Community community = communityMapper.selectById(goodsOrder.getCommunityId());
        if(community != null){
            hashMap.put("communityId", goodsOrder.getCommunityId());
            hashMap.put("communityName", community.getName());
        } else {
            hashMap.put("communityId", "社区id不存在");
            hashMap.put("communityName", "社区不存在");
        }
    }

    /**
     * 获取订单详情
     *
     * @param id 主订单表id
     * @return 订单详情
     */
    @Override
    public ResultCode getGoodsOrderDetail(Integer id) {
        ResultCode<Object> resultCode = new ResultCode<>();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        //查询主表订单信息
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(id);
        //如果是待付款就判断是否超时(超时修改数据库状态为已取消)
        if (goodsOrder != null) {
            if (goodsOrder.getOrderState() == 1) {
                Date createTime = goodsOrder.getCreateTime();
                long l = createTime.getTime() + (30 * 60 * 1000);
                Date date = new Date(l);
                int i = DateUtil.compareDateWithNow(date);
                if (i < 0) {
                    goodsOrder.setOrderState(4);
                    goodsOrderMapper.updateById(goodsOrder);
                    //获取子订单并修改子订单状态
                    Map<String, Object> hashMap = new HashMap<>();
                    hashMap.put("order_id", goodsOrder.getId());
                    List<GoodsOrderDetail> goodsOrderDetails = goodsOrderDetailMapper.selectByMap(hashMap);
                    if (goodsOrderDetails != null && goodsOrderDetails.size() > 0) {
                        for (GoodsOrderDetail goodsOrderDetail : goodsOrderDetails) {
                            goodsOrderDetail.setOrderState(4);
                            goodsOrderDetailMapper.updateById(goodsOrderDetail);
                        }
                    }
                }
            }
            HashMap<Object, Object> hashMap = new HashMap<>();
            //订单主表id
            hashMap.put("goodsOrderId", goodsOrder.getId());
            //主订单订单号
            hashMap.put("courierNumber", goodsOrder.getCourierNumber());
            //收件人姓名
            hashMap.put("name", goodsOrder.getReceiveName());
            //收件地址
            hashMap.put("address", goodsOrder.getReceiveAddress());
            //收件人电话
            hashMap.put("phone", goodsOrder.getReceivePhone());
            //订单创建时间
            hashMap.put("createTime", goodsOrder.getCreateTime());
            //订单号
            hashMap.put("orderNum", goodsOrder.getOrderNum());
            //总金额
            hashMap.put("totalAmount", goodsOrder.getTotalAmount());
            //主订单状态
            hashMap.put("orderState", goodsOrder.getOrderState());
            //主表ifPay
            //hashMap.put("ifPay", goodsOrder.getIfPay());
            Integer orderType = goodsOrder.getOrderType().intValue();
            // 社区菜品订单
            if(orderType == 3){
                // 查询商铺和社区信息
                getMerchantAndCommunityInfo(goodsOrder, hashMap);
            }else{
                //查询店铺
                Factory factory = factoryMapper.selectById(goodsOrder.getFactoryId().longValue());
                if (factory != null) {
                    hashMap.put("factoryId", factory.getFactoryId());
                    hashMap.put("factoryName", factory.getName());
                } else {
                    hashMap.put("factoryId", "商家id不存在");
                    hashMap.put("factoryName", "商家不存在");
                }
            }
            //获取订单子表
            Map<String, Object> map = new HashMap<>();
            map.put("order_id", goodsOrder.getId());
            List<GoodsOrderDetail> goodsOrderDetails = goodsOrderDetailMapper.selectByMap(map);
            if (goodsOrderDetails != null && goodsOrderDetails.size() > 0) {
                List<GoodsOrderDetailVo> goodsOrderDetailVos = ListUtils.listTrans(goodsOrderDetails, GoodsOrderDetailVo.class);
                //遍历订单子表
                for (GoodsOrderDetailVo goodsOrderDetail : goodsOrderDetailVos) {
                    if(orderType == 3){
                        Integer goodsId = goodsOrderDetail.getGoodsId();
                        if(goodsId != null){
                            MerchantGoods mg = merchantGoodsMapper.selectById(goodsId);
                            if(mg != null){
                                goodsOrderDetail.setSpecValue(String.valueOf(mg.getBigdecimal()));
                            }
                        }else{
                            goodsOrderDetail.setSpecValue("无规格");
                        }
                    } else {
                        //获取规格参数
                        Integer skuId = goodsOrderDetail.getSkuId();
                        GoodsSku goodsSku = goodsSkuMapper.selectById(skuId);
                        String value = "";
                        if (goodsSku != null) {
                            String specValueId = goodsSku.getSpecValueId();
                            String[] spec = specValueId.split("_");
                            for (int i = 0; i < spec.length; i++) {
                                if (i == spec.length - 1) {
                                    value += goodsSpecValueMapper.selectById(Integer.parseInt(spec[i])).getValue();
                                } else {
                                    value += goodsSpecValueMapper.selectById(Integer.parseInt(spec[i])).getValue() + "-";
                                }
                            }
                        }
                        goodsOrderDetail.setSpecValue(value);
                    }
                }
                hashMap.put("orderDetailList", goodsOrderDetailVos);
                resultCode.setData(hashMap);
                return resultCode;
            } else {
                resultCode.setData(hashMap);
                return resultCode;
            }
        } else {
            resultCode.setData(new HashMap<>());
            return resultCode;
        }
    }

    /**
     * 取消订单
     *
     * @param id 主订单id
     * @return ResultCode
     */
    @Override
    public ResultCode cancelOrder(Integer id) {
        ResultCode<Object> resultCode = new ResultCode<>();
        //获取主底单,并把主订单状态改为已取消
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(id);
        if (goodsOrder == null) {
            resultCode.setCode(500);
            resultCode.setMsg("订单不存在");
            return resultCode;
        }
        if (goodsOrder.getOrderState() != 1) {
            resultCode.setCode(500);
            resultCode.setMsg("订单不能取消");
            return resultCode;
        }
        goodsOrder.setOrderState(4);
        goodsOrderMapper.updateById(goodsOrder);
        //获取子订单,并把子订单状态全部改为已取消
        List<GoodsOrderDetail> list = new LambdaQueryChainWrapper<>(goodsOrderDetailMapper).eq(GoodsOrderDetail::getOrderId, goodsOrder.getId()).list();
        if (list != null && list.size() > 0) {
            for (GoodsOrderDetail goodsOrderDetail : list) {
                goodsOrderDetail.setOrderState(4);
                goodsOrderDetail.updateById();
            }
        }
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 删除订单
     *
     * @param id 主订单id
     * @return ResultCode
     */
    @Override
    public ResultCode deleteOrder(Integer id) {
        ResultCode<Object> resultCode = new ResultCode<>();
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(id);
        if (goodsOrder == null) {
            resultCode.setCode(500);
            resultCode.setMsg("订单不存在");
            return resultCode;
        }
        goodsOrder.setUserDelete(1);
        goodsOrderMapper.updateById(goodsOrder);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 提醒订单发货
     *
     * @param id 主订单id
     * @return ResultCode
     */
    @Override
    public ResultCode remindOrder(Integer id) {
        ResultCode<Object> resultCode = new ResultCode<>();
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(id);
        if (goodsOrder == null) {
            resultCode.setCode(500);
            resultCode.setMsg("订单不存在");
            return resultCode;
        }
        goodsOrder.setIfRemind(1);
        goodsOrderMapper.updateById(goodsOrder);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 修改收货地址
     *
     * @param orderId   主订单id
     * @param addressId 地址id
     * @return
     */
    @Override
    public ResultCode updateAddress(Integer orderId, Integer addressId) {
        ResultCode<Object> resultCode = new ResultCode<>();
        //获取要修改的地址
        ShippingAddress shippingAddress = shippingAddressMapper.selectById(addressId);
        if (shippingAddress == null) {
            resultCode.setCode(500);
            resultCode.setMsg("地址不存在");
            return resultCode;
        }
        //获取订单并修改地址
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(orderId);
        if (goodsOrder == null) {
            resultCode.setCode(500);
            resultCode.setMsg("订单不存在");
            return resultCode;
        }
        String region = shippingAddress.getRegion();
        String address = shippingAddress.getAddress();
        goodsOrder.setReceiveAddress(region + address);
        goodsOrder.setReceiveName(shippingAddress.getName());
        goodsOrder.setReceivePhone(shippingAddress.getCellphone());
        goodsOrder.setAddressId(addressId);
        goodsOrderMapper.updateById(goodsOrder);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 用户退款
     *
     * @param id 主订单表id
     * @return ResultCode
     */
    @Override
    public ResultCode cancelOrderDetail(Integer id, String reason, String message) {
        ResultCode<Object> resultCode = new ResultCode<>();
        //获取主订单并修改主订单状态
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(id);
        if (goodsOrder != null) {
            goodsOrder.setOrderState(8);
            goodsOrder.setRefundInformation(reason);
            goodsOrder.setReturnMessage(message);
            goodsOrder.updateById();

            //获取子订单并修改子订单状态
            List<GoodsOrderDetail> list = new LambdaQueryChainWrapper<>(goodsOrderDetailMapper).eq(GoodsOrderDetail::getOrderId, goodsOrder.getId()).list();
            if (list != null && list.size() > 0) {
                for (GoodsOrderDetail goodsOrderDetail : list) {
                    goodsOrderDetail.setOrderState(8);
                    goodsOrderDetail.updateById();
                }
            }
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
        }
        return resultCode;

    }

    /**
     * 获取退款详情
     *
     * @param id 子订单表id
     * @return ResultCode
     */
    @Override
    public ResultCode queryCancelOrderDetail(Integer id) {
        ResultCode<Object> resultCode = new ResultCode<>();
        HashMap<Object, Object> hashMap = new HashMap<>();
        //获取子订单表
        GoodsOrderDetail goodsOrderDetail = goodsOrderDetailMapper.selectById(id);
        if (goodsOrderDetail != null) {
            //获取主订单表
            GoodsOrder goodsOrder = goodsOrderMapper.selectById(goodsOrderDetail.getOrderId());
            if (goodsOrder != null) {
                //获取厂家
                Factory factory = factoryMapper.selectById(goodsOrder.getFactoryId());
                hashMap.put("factoryId", factory.getFactoryId());
                hashMap.put("factoryName", factory.getName());
                //hashMap.put("ifPay", goodsOrder.getIfPay());
                hashMap.put("address", goodsOrder.getReceiveAddress());
                hashMap.put("name", goodsOrder.getReceiveName());
                hashMap.put("phone", goodsOrder.getReceivePhone());
                hashMap.put("message", goodsOrder.getReceiveMessage());
                //查询规格
                GoodsOrderDetailVo goodsOrderDetailVo = FieldUtils.fieldTrans(goodsOrderDetail, GoodsOrderDetailVo.class);
                //获取规格参数,并添加规格参数
                Integer skuId = goodsOrderDetailVo.getSkuId();
                GoodsSku goodsSku = goodsSkuMapper.selectById(skuId);
                String specValue = "";
                if (goodsSku != null) {
                    String specValueId = goodsSku.getSpecValueId();
                    String[] spec = specValueId.split("_");
                    for (int i = 0; i < spec.length; i++) {
                        if (i == spec.length - 1) {
                            specValue += goodsSpecValueMapper.selectById(Integer.parseInt(spec[i])).getValue();
                        } else {
                            specValue += goodsSpecValueMapper.selectById(Integer.parseInt(spec[i])).getValue() + "-";
                        }
                    }
                    goodsOrderDetailVo.setSpecValue(specValue);
                } else {
                    goodsOrderDetailVo.setSpecValue("无规格");
                }

                List<GoodsOrderDetailVo> list = new ArrayList<>();
                list.add(goodsOrderDetailVo);
                hashMap.put("orderDetail", list);

                resultCode.setCode(200);
                resultCode.setMsg("SUCCESS");
                resultCode.setData(hashMap);
                return resultCode;
            }
        }
        resultCode.setCode(500);
        resultCode.setMsg("FAIL");
        resultCode.setData(hashMap);
        return resultCode;
    }

    /**
     * 取消退款
     *
     * @param id 主订单id
     * @return ResultCode
     */
    @Override
    public ResultCode cancelRefund(Integer id) {
        ResultCode<Object> resultCode = new ResultCode<>();
        //获取主订单并更改主订单状态为待发货
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(id);
        if (goodsOrder != null) {
            goodsOrder.setOrderState(2);
            goodsOrder.updateById();
            //获取子订单并修改子订单状态为待发货
            List<GoodsOrderDetail> list = new LambdaQueryChainWrapper<>(goodsOrderDetailMapper).eq(GoodsOrderDetail::getOrderId, goodsOrder.getId()).list();
            if (list != null && list.size() > 0) {
                for (GoodsOrderDetail goodsOrderDetail : list) {
                    goodsOrderDetail.setOrderState(2);
                    goodsOrderDetail.updateById();
                }
                resultCode.setCode(200);
                resultCode.setMsg("SUCCESS");
            }
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
        }
        return resultCode;

    }

    /**
     * 确认收货
     *
     * @param id 主订单id
     * @return ResultCode
     */
    @Override
    public ResultCode confirmReceipt(Integer id) {
        GoodsOrder goodsOrder = goodsOrderMapper.selectById(id);
        ResultCode<Object> resultCode = new ResultCode<>();
        if (goodsOrder.getTotalAmount().doubleValue() > 0) {
            Map<String, String> map = new HashMap<>();
            map.put("orderId", String.valueOf(id));
            String json = HttpClientUtil.doPost(payConfig.getConfirmReceiptUrl(), map);
            Map resultMap = JSONObject.parseObject(json, Map.class);
            if ((int) resultMap.get("code") != 200) {
                resultCode.setCode(500);
                resultCode.setMsg("FAIL");
                return resultCode;
            }
        }
        //获取主订单
        if (goodsOrder != null) {
            //修改子订单的全部状态为已收货
            List<GoodsOrderDetail> list = new LambdaQueryChainWrapper<>(goodsOrderDetailMapper).eq(GoodsOrderDetail::getOrderId, goodsOrder.getId()).list();
            for (GoodsOrderDetail goodsOrderDetail : list) {
                //只有是待收货状态才能改为已收货
                if (goodsOrderDetail.getOrderState() == 3) {
                    goodsOrderDetail.setOrderState(5);
                    goodsOrderDetail.updateById();
                }
            }
            //修改主订单状态为已收货
            goodsOrder.setOrderState(5);
            goodsOrder.updateById();
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
        }
        return resultCode;

    }

    /**
     * 取消退货
     *
     * @param id 子订单表id
     * @return ResultCode
     */
    @Override
    public ResultCode cancelReturn(Integer id) {
        ResultCode<Object> resultCode = new ResultCode<>();
        GoodsOrderDetail goodsOrderDetail = goodsOrderDetailMapper.selectById(id);
        if (goodsOrderDetail != null && goodsOrderDetail.getOrderState() == 11) {
            goodsOrderDetail.setOrderState(5);
            goodsOrderDetail.updateById();
            resultCode.setCode(200);
            resultCode.setMsg("取消成功");
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("取消失败");
        }
        return resultCode;
    }

    /**
     * 延长收货
     *
     * @param id 主订单表id
     * @return ResultCode
     */
    @Override
    public ResultCode receipt(Integer id) {
        ResultCode<Object> resultCode = new ResultCode<>();
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setId(id);
        goodsOrder.setIfDelayed(1);
        boolean flag = goodsOrder.updateById();
        if (flag) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
        }
        return resultCode;
    }

    /**
     * 用户退货
     *
     * @param id 子订单表id
     * @return ResultCode
     */
    @Override
    public ResultCode returns(Integer id, String reason, String message) {
        ResultCode<Object> resultCode = new ResultCode<>();
        //获取子订单表
        GoodsOrderDetail goodsOrderDetail = goodsOrderDetailMapper.selectById(id);
        if (goodsOrderDetail != null) {
            //把子订单的状态改为"申请退货"
            goodsOrderDetail.setOrderState(11);
            goodsOrderDetail.setRefundInformation(reason);
            goodsOrderDetail.setReturnMessage(message);
            goodsOrderDetail.updateById();
        }
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }
}
