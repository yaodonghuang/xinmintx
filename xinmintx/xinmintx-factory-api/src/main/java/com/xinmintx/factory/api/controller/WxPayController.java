package com.xinmintx.factory.api.controller;

import com.xinmintx.factory.api.common.ResultCode;
import com.xinmintx.factory.api.service.IWeChatRefund;
import com.xinmintx.factory.api.service.IWxPayService;
import com.xinmintx.factory.api.service.PaymentService;
import com.xinmintx.factory.api.service.ReimburseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信支付
 *
 **/
@Slf4j
@RestController
@RequestMapping("/api/wx")
@Transactional
public class WxPayController {
    @Resource
    private ReimburseService reimburseService;
    @Resource
    private PaymentService paymentService;
    @Resource
    private IWeChatRefund iWeChatRefund;
    @Resource
    private IWxPayService wxPayService;

    @PostMapping("/refund")
    public ResultCode appRefund(@RequestParam("orderId") Integer orderId) throws Exception {
        return wxPayService.wxRefund(orderId);
    }

    /**
     * 担保支付
     * @param paramMap
     */
    @PostMapping("/payment")
    public ResultCode payment(@RequestParam Map<String,String> paramMap){
        return paymentService.payment(paramMap);
    }

    /**
     * 测试担保支付
     * @param paramMap
     */
    @PostMapping("/testpayment")
    public ResultCode testPayment(@RequestParam Map<String,String> paramMap){
        paramMap.put("orderId","1577434922223");// 订单号
        paramMap.put("orderAmt","1");// 订单金额
        paramMap.put("openid",paramMap.get("openid"));
        paramMap.put("prdDesc","15151");// 描述
        paramMap.put("retUrl","http://hsapi.xinmintx.cn/wechatMemberPayNotify");// 回调地址
        return paymentService.payment(paramMap);
    }

    /**
     * 退款
     * @param paramMap
     * @return
     */
    @PostMapping("/reimburse")
    public ResultCode reimburse(@RequestParam Map<String,String> paramMap){
        return reimburseService.reimburse(paramMap);
    }
}
