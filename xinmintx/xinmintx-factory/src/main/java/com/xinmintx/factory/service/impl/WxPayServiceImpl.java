package com.xinmintx.factory.service.impl;

import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.common.property.AppProperties;
import com.xinmintx.factory.mapper.OrderMapper;
import com.xinmintx.factory.model.GoodsOrder;
import com.xinmintx.factory.model.goodsOrderRefund;
import com.xinmintx.factory.service.IFactoryInfoService;
import com.xinmintx.factory.service.IWxPayService;
import com.xinmintx.factory.util.HttpUtil;
import com.xinmintx.factory.util.PaymentUtils;
import com.xinmintx.factory.util.RandomUtil;
import com.xinmintx.factory.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 **/
@Slf4j
@Transactional
@Service
public class WxPayServiceImpl implements IWxPayService {

    @Resource
    private AppProperties appProperties;

    @Autowired
    private IFactoryInfoService iFactoryInfoService;
    @Autowired
    private OrderMapper odMapper;
    /**
     * 微信支付通知接口 回调数据示例
     *
     * <xml>
     * <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
     * <attach><![CDATA[支付测试]]></attach>
     * <bank_type><![CDATA[CFT]]></bank_type>
     * <fee_type><![CDATA[CNY]]></fee_type>
     * <is_subscribe><![CDATA[Y]]></is_subscribe>
     * <mch_id><![CDATA[10000100]]></mch_id>
     * <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>
     * <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>
     * <out_trade_no><![CDATA[1409811653]]></out_trade_no>
     * <result_code><![CDATA[SUCCESS]]></result_code>
     * <return_code><![CDATA[SUCCESS]]></return_code>
     * <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>
     * <sub_mch_id><![CDATA[10000100]]></sub_mch_id>
     * <time_end><![CDATA[20140903131540]]></time_end>
     * <total_fee>1</total_fee><coupon_fee><![CDATA[10]]></coupon_fee>
     * <trade_type><![CDATA[JSAPI]]></trade_type>
     * <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>
     * </xml>
     *
     * <p>
     * 收到微信通知后应返回微信已经收到通知，不然微信会回调多次 可能会出现保存多次订单流水的现象
     * <p>
     * 返回给微信通知示例如下
     *
     * <xml>
     * <return_code><![CDATA[SUCCESS]]></return_code>
     * <return_msg><![CDATA[OK]]></return_msg>
     * </xml>
     *
     * @param request
     * @param response
     */
//    public void notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String wxRetXml = PaymentUtils.getRequestData(request);
//        Map<String, String> wxRetMap = PaymentUtils.xmlToMap(wxRetXml);
//        Assert.notNull(wxRetMap, ExceptionMessage.XML_DATA_INCORRECTNESS.getMessage());
//
//        // 当返回的return_code为SUCCESS则回调成功
//        if ("SUCCESS".equalsIgnoreCase(wxRetMap.get("return_code"))) {
//            // 通知微信收到回调
//            String resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
//            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//            out.write(resXml.getBytes());
//            out.flush();
//            out.close();
//
//            // TODO 保存订单流水 具体细节待实现
//            orderBillService.save(new OrderBill());
//
//            log.info("notify success");
//        } else {
//            log.error("notify failed");
//        }
//    }

    /**
     * 微信退款
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    public ResultCode wxRefund(Integer orderId) throws Exception {
        ResultCode rc = new ResultCode();
        GoodsOrder order = iFactoryInfoService.getOrderInfoById(orderId);

        if (order == null || order.getIfPay() == null || order.getIfPay() == 0) {// 订单未付款
            rc.setCode(500);
            rc.setMsg("NOT PAY");
            return rc;
        }
        String transactionId = odMapper.getTransactionId(order.getUOrderId());
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal getMoney = odMapper.getTotalAmount(orderId);
        if(getMoney != null){
            totalAmount = getMoney;// 查询字表退款金额
        }
        String nonceStr = RandomUtil.randomStr(32);
        String out_refund_no = RandomUtil.randomStr(15);

        SortedMap<String, Object> params = new TreeMap<>();
        // 公众账号ID
        params.put("appid", appProperties.getWx().getApp_id());
        // 商户号
        params.put("mch_id", appProperties.getWx().getMch_id());
        // 随机字符串
        params.put("nonce_str", nonceStr);
        // 微信订单号
        params.put("transaction_id", transactionId);
        // 订单金额
        params.put("total_fee", StringUtil.getPrettyNumber(order.getTotalAmount()));
        // 商户退款单号
        params.put("out_refund_no", out_refund_no);
        // 退款金额
        params.put("refund_fee", StringUtil.getPrettyNumber(totalAmount));
        // 退款结果通知url
        params.put("notify_url", appProperties.getWx().getRefund_notify_url());
        // 签名
        params.put("sign", PaymentUtils.sign(params, appProperties.getWx().getApi_secret()));
        String data = PaymentUtils.mapToXml(params);

        // 微信退款需要证书
        CloseableHttpClient httpClient = HttpUtil.sslHttpsClient(appProperties.getWx().getCertificate_path(), appProperties.getWx().getApi_key());

        // 向微信发起退款
        String responseXml = HttpUtil.sendSslXmlPost(appProperties.getWx().getRefund_url(), data, null, httpClient);

        Map<String, String> responseMap = PaymentUtils.xmlToMap(responseXml);
        // return_code为微信返回的状态码，SUCCESS表示申请退款成功，return_msg 如非空，为错误原因 签名失败 参数格式校验错误
        if ("SUCCESS".equalsIgnoreCase(responseMap.get("return_code"))) {
            rc.setCode(200);
            rc.setMsg("SUCCESS");
            if ("FAIL".equalsIgnoreCase(responseMap.get("result_code"))) {
                rc.setCode(500);
                rc.setMsg("errorMsg:" + responseMap.get("err_code_des"));
                return rc;
            } else if ("SUCCESS".equalsIgnoreCase(responseMap.get("result_code"))) {
                // 成功则将退款返回信息保存下来
                insertRefundInfo(responseMap, odMapper);
            }
            // 修改订单状态为退款保存退款订单等操作
            iFactoryInfoService.updatePayStateById(orderId, 3);
            return rc;
        }
        rc.setCode(500);
        rc.setMsg("returnCode:" + responseMap.get("return_code") + ",returnMsg:" + responseMap.get("return_msg"));
        return rc;
    }

    // 保存退款返回信息
    private static void insertRefundInfo(Map<String, String> responseMap, OrderMapper odMapper) {
        goodsOrderRefund gor = new goodsOrderRefund();
        gor.setTransactionId(responseMap.get("transaction_id"));
        gor.setNonceStr(responseMap.get("nonce_str"));
        gor.setSign(responseMap.get("sign"));
        gor.setReturnMsg(responseMap.get("return_msg"));
        gor.setMchId(responseMap.get("mch_id"));
        gor.setRefundId(responseMap.get("refund_id"));
        if (responseMap.get("cash_fee") != null) {
            gor.setCashFee(Integer.valueOf(responseMap.get("cash_fee")));
        }
        gor.setOutTradeNo(responseMap.get("out_trade_no"));
        if (responseMap.get("coupon_refund_fee") != null) {
            gor.setCouponRefundFee(Integer.valueOf(responseMap.get("coupon_refund_fee")));
        }
        gor.setRefundChannel(responseMap.get("refund_channel"));
        gor.setAppid(responseMap.get("appid"));
        if (responseMap.get("refund_fee") != null) {
            gor.setRefundFee(Integer.valueOf(responseMap.get("refund_fee")));
        }
        if (responseMap.get("total_fee") != null) {
            gor.setTotalFee(Integer.valueOf(responseMap.get("total_fee")));
        }
        gor.setResultCode(responseMap.get("result_code"));
        if (responseMap.get("coupon_refund_count") != null) {
            gor.setCouponRefundCount(Integer.valueOf(responseMap.get("coupon_refund_count")));
        }
        if (responseMap.get("cash_refund_fee") != null) {
            gor.setCashRefundFee(Integer.valueOf(responseMap.get("cash_refund_fee")));
        }
        gor.setReturnCode(responseMap.get("return_code"));
        gor.setCreateTime(new Date());
        odMapper.insertRefundInfo(gor);
    }
}
