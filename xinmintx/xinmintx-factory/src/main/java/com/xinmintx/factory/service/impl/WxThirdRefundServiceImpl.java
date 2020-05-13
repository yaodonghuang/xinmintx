package com.xinmintx.factory.service.impl;

import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.mapper.OrderMapper;
import com.xinmintx.factory.model.GoodsOrder;
import com.xinmintx.factory.service.IFactoryInfoService;
import com.xinmintx.factory.service.ReimburseService;
import com.xinmintx.factory.service.WxThirdRefundService;
import com.xinmintx.factory.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信第三方退款
 */
@Transactional
@Service
public class WxThirdRefundServiceImpl implements WxThirdRefundService {
    @Autowired
    private IFactoryInfoService iFactoryInfoService;
    @Autowired
    private OrderMapper odMapper;
    @Autowired
    private ReimburseService reimburseService;

    /**
     * 根据小订单id退款
     *
     * @param detailOrderId 订单详情表id
     * @return
     */
    @Override
    public ResultCode wxThirdRefund(Integer detailOrderId) {
        ResultCode rc = new ResultCode();
        Map<String, String> paramMap = new HashMap<>();
        GoodsOrder order = iFactoryInfoService.getOrderInfoById(detailOrderId);
        // 订单详情表状态为退款中才能进行退款,不然报错
        Integer orderState = odMapper.getDetailOrderState(detailOrderId);
        if (orderState == null || (orderState != 8 && orderState != 12)) {// 退款中才能退款
            rc.setCode(500);
            rc.setMsg("状态为退款中才能退款");
            return rc;
        }
        String orderNo = odMapper.getOrderNoById(order.getUOrderId());// 获取支付订单号
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal getMoney = odMapper.getTotalAmount(detailOrderId);
        if (getMoney != null) {
            totalAmount = getMoney;// 查询子表退款金额
        }
        paramMap.put("oriOrderId", orderNo);
        paramMap.put("orderAmt", StringUtil.getPrettyNumber(totalAmount));
        rc = reimburseService.reimburse(paramMap);// 调用第三方微信退款接口
        dealRefund(rc, detailOrderId, order);// 退款之后对订单表进行状态变更
        return rc;
    }

    /**
     * 根据主订单id退款
     *
     * @param orderId
     * @return
     */
    @Override
    public ResultCode wxThirdRefundMain(Integer orderId) {
        ResultCode rc = new ResultCode();
        Map<String, String> paramMap = new HashMap<>();
        GoodsOrder order = odMapper.getOriOrderById(orderId);
        if (order.getOrderState() == null || (order.getOrderState() != 8 && order.getOrderState() != 12)) {// 退款中才能退款
            rc.setCode(500);
            rc.setMsg("状态为退款中才能退款");
            return rc;
        }
        String orderNo = odMapper.getOrderNoById(order.getUOrderId());// 获取支付订单号
        if (StringUtils.isEmpty(orderNo)) {
            rc.setCode(500);
            rc.setMsg("退款订单号为空");
            return rc;
        }
        paramMap.put("oriOrderId", orderNo);
        rc = reimburseService.reimburse(paramMap);// 调用第三方微信退款接口
        if (rc.getCode() == 200) {
            // 请求 退款分润扣除 https://hsapi.xinmintx.cn/hs/divided/returns/{id}
            StringUtil.post("https://hsapi.xinmintx.cn/hs/divided/returns/" + orderId);
            odMapper.updateOrderStateById(order.getId(), 9);
            odMapper.updateOrderDetailStateById(order.getId(), 9);
        } else {
            odMapper.updateOrderStateById(order.getId(), 10);
            odMapper.updateOrderDetailStateById(order.getId(), 10);
        }
        return rc;
    }

    // 订单修改逻辑
    private void dealRefund(ResultCode rc, Integer detailOrderId, GoodsOrder order) {
        if (rc.getCode() == 200) {// 退款成功
            // 请求 退货分润扣除 http://hsapi.xinmintx.cn/hs/divided/refunds/{id}
            StringUtil.post("https://hsapi.xinmintx.cn/hs/divided/refunds/" + detailOrderId);
            // 修改字表状态为已退款
            odMapper.updateDetailOrderState(detailOrderId, 9);
            // 查询子表中是否都是已退款状态,是的话修改主表状态
            Integer ifAllRefund = odMapper.ifAllRefund(order.getId(), 9);
            if (ifAllRefund == null) {// 都是已退款
                odMapper.updateOrderStateById(order.getId(), 9);
            }
        } else {
            // 修改字表状态为退款失败
            odMapper.updateDetailOrderState(detailOrderId, 10);
        }
    }
}
