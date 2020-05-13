package com.xinmintx.factory.service.impl;

import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.mapper.OrderMapper;
import com.xinmintx.factory.model.Factory;
import com.xinmintx.factory.model.GoodsOrder;
import com.xinmintx.factory.model.GoodsOrderDetail;
import com.xinmintx.factory.model.PoboNotify;
import com.xinmintx.factory.service.IFactoryInfoService;
import com.xinmintx.factory.service.PaymentOnBehalfOf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 工厂功能实现类
 */
@Transactional
@Service
public class FactoryInfoServiceImpl implements IFactoryInfoService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PaymentOnBehalfOf paymentOnBehalfOf;

    /**
     * 扫描快递单号,变更订单状态
     *
     * @param detailId      订单详情id
     * @param courierNumber 快递单号
     * @return
     */
    @Override
    public int updateOrderStateAndcNum(Long detailId, String courierNumber) {
        // 判断该单号必须是[待发货]才能进行操作
        String orderState = orderMapper.getSendStateById(detailId);
        if (StringUtils.isEmpty(orderState)) {// 订单详情查询不到状态
            return 0;
        } else {
            // 待发货状态才能进行操作
            if (orderState.equalsIgnoreCase("2")) {
                int result = orderMapper.updateOrderStateAndcNum(detailId, courierNumber, "3");
                if (result > 0) {// 判断订单号下所有商品状态
                    Integer getOrderId = orderMapper.getOrderIdByDetailId(detailId);
                    // 根据订单id更新发货时间
                    orderMapper.updateSendDate(getOrderId);
                    Integer ifExists = orderMapper.ifExistsWaitSendPro(getOrderId);
                    if (ifExists == null) {// 不存在待发货商品,反向更新订单状态
                        orderMapper.updateOrderState(getOrderId);
                    }
                    // 修改主表订单号
                    orderMapper.updateOrderCourierNumber(getOrderId, courierNumber);
                }
            }
        }
        return 1;
    }

    /**
     * 根据订单id查询退款信息
     *
     * @param orderId
     * @return
     */
    @Override
    public GoodsOrder getOrderInfoById(Integer orderId) {
        return orderMapper.getOrderById(orderId);
    }

    /**
     * 更新支付状态为已退款
     *
     * @param orderId
     * @return
     */
    @Override
    public int updatePayStateById(Integer orderId, Integer ifPay) {
        return orderMapper.updatePayStateById(orderId, ifPay);
    }

    /**
     * 工厂提现
     *
     * @param ft
     * @return
     */
    @Override
    public ResultCode factoryGetMoney(Factory ft) {
        ResultCode rc = new ResultCode();
        // 输入的提现金额
        BigDecimal inputCashAmount = StringUtils.isNotEmpty(ft.getInputCashAmount()) ? new BigDecimal(ft.getInputCashAmount()) : BigDecimal.ZERO;
        // 根据工厂id查询出工厂可以使用的提现金额
        Factory getFc = orderMapper.getFactoryCashByToken(ft.getToken());
        if(getFc == null){
            rc.setCode(500);
            rc.setMsg("token查询不到工厂,请重新登陆!");
            return rc;
        }
        // 可用金额扣除手续费(1元)之后作比较
        BigDecimal cashAmount = getFc.getCashAmount().subtract(new BigDecimal("1"));
        // 输入的金额小于可用金额才可以提现
        if (cashAmount != null && cashAmount.compareTo(BigDecimal.ZERO) == 1
                && inputCashAmount.compareTo(BigDecimal.ZERO) == 1
                && inputCashAmount.compareTo(cashAmount) <= 0) {
            rc = dealCash(getFc, inputCashAmount);// 处理提现逻辑
        } else {
            rc.setCode(500);
            rc.setMsg("可提现金额不足(大于1元才能提现)");
        }
        return rc;
    }

    // 处理提现逻辑
    private ResultCode dealCash(Factory getFc, BigDecimal inputCashAmount) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("requestSN", UUID.randomUUID().toString().replace("-",""));
        paramMap.put("notifyUrl", "http://factory-api.xinmintx.cn/merchants/poboNotify");
        paramMap.put("txnAmt", String.valueOf(inputCashAmount));
        paramMap.put("recvAccName", getFc.getName());
        paramMap.put("acctNo", getFc.getBankCard());
        // 厂家提现表保存相关信息
        orderMapper.insertFactoryCashInfo(getFc.getFactoryId(), paramMap.get("requestSN"));
        ResultCode rc = paymentOnBehalfOf.paymentOnBehalfOf(paramMap);// 调用代付接口提现
        return rc;
    }

    /**
     * 查询厂家余额及冻结余额
     *
     * @param token
     * @return
     */
    @Override
    public ResultCode getFactoryCashInfo(String token) {
        ResultCode rc = new ResultCode();
        Factory fc = orderMapper.getFactoryCashByToken(token);
        if (fc != null) {
            rc.setCode(200);
            rc.setData(fc);
        } else {
            rc.setCode(500);
            rc.setMsg("厂家不存在,请重试或者重新登陆!");
        }
        return rc;
    }

    /**
     * 查询厂家提现日志接口
     *
     * @param token
     * @return
     */
    @Override
    public ResultCode getFactoryCashLog(String token) {
        ResultCode rc = new ResultCode();
        Factory fc = orderMapper.getFactoryCashByToken(token);
        if (fc != null) {// 厂家不为空,根据厂家id查询厂家提现日志信息
            List<PoboNotify> logList = orderMapper.getCashLog(fc.getFactoryId());
            rc.setCode(200);
            rc.setData(logList);
        } else {
            rc.setCode(500);
            rc.setMsg("厂家不存在,请重试或者重新登陆!");
        }
        return rc;
    }

    @Override
    public int upReturnInformation(Integer orderId ,String refundInformation,String returnMessage) {
        return orderMapper.upReturnInformation(orderId,refundInformation,returnMessage);
    }

    @Override
    public List<GoodsOrderDetail> selectOrder(Integer orderId) {
        return orderMapper.selectOrder(orderId);
    }

    @Override
    public int upReturnsSingleNumber(Long orderId, String courierNumber) {
        return orderMapper.upReturnsSingleNumber(orderId,courierNumber);
    }

}
