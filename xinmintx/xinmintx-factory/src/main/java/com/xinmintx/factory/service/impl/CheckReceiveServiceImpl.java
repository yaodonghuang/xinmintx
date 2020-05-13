package com.xinmintx.factory.service.impl;

import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.mapper.AmountLogMapper;
import com.xinmintx.factory.mapper.OrderMapper;
import com.xinmintx.factory.model.GoodsOrder;
import com.xinmintx.factory.model.MemberAmountLog;
import com.xinmintx.factory.model.MerchantAmountLog;
import com.xinmintx.factory.model.UserAccountRecord;
import com.xinmintx.factory.service.CheckReceiveService;
import com.xinmintx.factory.service.ConfirmReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class CheckReceiveServiceImpl implements CheckReceiveService {
    @Resource
    private OrderMapper odMapper;
    @Resource
    private ConfirmReceiptService confirmReceiptService;
    @Resource
    private AmountLogMapper amountLogMapper;

    // 确认收货调第三方接口
    @Override
    public ResultCode checkReceive(Integer orderId) {
        ResultCode rc;
        Map<String, String> paramMap = new HashMap<>();
        GoodsOrder order = odMapper.getOriOrderById(orderId);
        String orderNo = odMapper.getOrderNoById(order.getUOrderId());
        // 获取支付订单号
        if (StringUtils.isNotEmpty(orderNo)) {
            paramMap.put("orderId", orderNo);
            rc = confirmReceiptService.confirmReceipt(paramMap);
            if (rc != null && rc.getCode() == 200 && order.getIfSettlement().intValue() == 0 && "3".equalsIgnoreCase(order.getOrderType())) {
                // 社区菜品订单,计算费用,将内帮办的冻结金额转化为可用金额
                dealMemberInfo(order);
                // 确认收货给商户打钱
                dealMerchantInfo(order);
                // 确认收货将代理商的提货点冻结金额转化为可用金额
                dealUserInfo(order);
            }
            if (rc != null && rc.getCode() == 200) {
                odMapper.settleOrder(orderId);
            }
        } else {
            rc = new ResultCode();
            rc.setCode(500);
            rc.setMsg("订单未支付金额,请确认订单是否支付!");
        }
        return rc;
    }

    // 处理用户金额
    private void dealUserInfo(GoodsOrder order) {
        // 冻结金额转化为可用金额
        if (StringUtils.isNotEmpty(order.getConsigneeInfo())) {
            String id = order.getConsigneeInfo().split(",")[0];
            String cost = order.getConsigneeInfo().split(",")[1];
            String count = order.getConsigneeInfo().split(",")[2];
            BigDecimal finalCost = (new BigDecimal(cost).divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_DOWN));
            BigDecimal cashAmount = amountLogMapper.getUserCashAmount(id);
            cashAmount = cashAmount == null ? BigDecimal.ZERO : cashAmount;
            if (StringUtils.isNotEmpty(cost)) {
                cashAmount = cashAmount.add(finalCost);
            }
            // 更新用户可用金额
            amountLogMapper.updateUserCashAmount(id, cashAmount, finalCost);
            // 保存日志信息
            UserAccountRecord userAccountRecord = new UserAccountRecord();
            userAccountRecord.setUserId(Integer.valueOf(id));
            userAccountRecord.setOrderId(order.getId());
            userAccountRecord.setUserAccountId(amountLogMapper.getUserAccountId(id));
            userAccountRecord.setMoneyChange(finalCost);
            userAccountRecord.setDescription("用户确认收货【进账】提货点费用");
            amountLogMapper.insertAccountRecord(userAccountRecord);
        }
    }

    // 处理商户金额
    private void dealMerchantInfo(GoodsOrder order) {
        amountLogMapper.updateMerchantCash(String.valueOf(order.getTotalAmount()), order.getMerchantId());
        // 保存日志
        List<MerchantAmountLog> merchantLogList = new ArrayList<>();
        BigDecimal cashAmount = amountLogMapper.getMerchantCashAmount(order.getMerchantId());
        cashAmount = cashAmount == null ? BigDecimal.ZERO : cashAmount;
        // 1.可用金额日志
        MerchantAmountLog mcal = new MerchantAmountLog();
        mcal.setMerchantId(Long.valueOf(order.getMerchantId()));
        mcal.setType("2");
        mcal.setPrice(order.getTotalAmount());
        mcal.setBalance(cashAmount);
        mcal.setRemark("菜篮子订单收款");
        BigDecimal freezAmount = amountLogMapper.getMerchantFreezAmount(order.getMerchantId());
        freezAmount = freezAmount == null ? BigDecimal.ZERO : freezAmount;
        // 2.冻结金额日志
        MerchantAmountLog freez = new MerchantAmountLog();
        freez.setMerchantId(Long.valueOf(order.getMerchantId()));
        freez.setType("1");
        freez.setPrice(order.getTotalAmount().multiply(new BigDecimal("-1")));
        freez.setBalance(freezAmount);
        freez.setRemark("用户确认收货,商户冻结金额转换为可用金额(菜篮子订单收款)");
        // 查询平台服务费
        BigDecimal serviceCharge = amountLogMapper.getMerchantServiceCharge(order.getMerchantId());
        serviceCharge = serviceCharge == null ? BigDecimal.ZERO : serviceCharge;
        if (serviceCharge.compareTo(BigDecimal.ZERO) == 1) {
            // 大于0
            amountLogMapper.updateMerchantCashAmount(serviceCharge, order.getMerchantId());
            BigDecimal finalCashAmount = amountLogMapper.getMerchantCashAmount(order.getMerchantId());
            finalCashAmount = finalCashAmount == null ? BigDecimal.ZERO : finalCashAmount;
            // 3.平台手续费日志
            MerchantAmountLog serviceChargeMcal = new MerchantAmountLog();
            serviceChargeMcal.setMerchantId(Long.valueOf(order.getMerchantId()));
            serviceChargeMcal.setType("2");
            serviceChargeMcal.setPrice(serviceCharge.multiply(new BigDecimal("-1")));
            serviceChargeMcal.setBalance(finalCashAmount);
            serviceChargeMcal.setRemark("平台手续费");
            merchantLogList.add(serviceChargeMcal);
        }
        merchantLogList.add(mcal);
        merchantLogList.add(freez);
        amountLogMapper.insertMerchantLogs(merchantLogList);
    }

    // 处理用户金额
    private void dealMemberInfo(GoodsOrder order) {
        if (StringUtils.isNotEmpty(order.getDeputyInfo())) {
            String id = order.getDeputyInfo().split(",")[0];
            String cost = order.getDeputyInfo().split(",")[1];
            String count = order.getDeputyInfo().split(",")[2];
            BigDecimal finalCost = (new BigDecimal(cost).divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_DOWN));
            BigDecimal cashAmount = amountLogMapper.getMemberCashAmount(id);
            cashAmount = cashAmount == null ? BigDecimal.ZERO : cashAmount;
            if (StringUtils.isNotEmpty(cost)) {
                cashAmount = cashAmount.add(finalCost);
            }
            // 更新内帮办可用金额
            amountLogMapper.updateMemberCash(finalCost.toString(), cashAmount, id);
            // 保存日志
            List<MemberAmountLog> memberLogList = new ArrayList<>();
            MemberAmountLog mal = new MemberAmountLog();
            mal.setMemberId(Long.valueOf(id));
            mal.setType("2");
            mal.setPrice(finalCost);
            mal.setBalance(cashAmount);
            mal.setRemark("内帮办配送费");
            MemberAmountLog freez = new MemberAmountLog();
            freez.setMemberId(Long.valueOf(id));
            freez.setType("1");
            freez.setPrice(finalCost.multiply(new BigDecimal("-1")));
            freez.setBalance(cashAmount);
            freez.setRemark("用户确认收货,内帮办配送费:冻结金额转换为可用现金");
            memberLogList.add(mal);
            memberLogList.add(freez);
            amountLogMapper.insertLogs(memberLogList);
        }
    }
}
