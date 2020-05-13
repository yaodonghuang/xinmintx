package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.configuration.pay.BaseAPI;
import com.xinmintx.agent.mchpay.*;
import com.xinmintx.agent.service.WechatPayMchService;
import com.xinmintx.agent.util.MapUtil;
import com.xinmintx.agent.util.XMLConverUtil;
import com.xinmintx.agent.util.httpclient.HttpClientUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * 微信支付 基于V3.X 版本
 */
@Service(value = "wechatPayMchService")
@Slf4j
public class WechatPayMchServiceImpl extends BaseAPI implements WechatPayMchService {

    /**
     * 统一下单
     */
    @Override
    public WXUnifiedOrderResult payUnifiedorder(WXUnifiedOrder unifiedorder, String key) {
        HttpUriRequest httpUriRequest = createRequest(unifiedorder, key, MCH_UNIFIE_DORDER);
        return HttpClientUtils.executeXmlResult(httpUriRequest, WXUnifiedOrderResult.class);
    }

    /**
     * 刷卡支付 提交被扫支付API
     */
    @Override
    public MicroPayResult payMicropay(MicroPay micropay, String key) {
        HttpUriRequest httpUriRequest = createRequestForYuyin(micropay, key, MCH_MICRO_PAY);
        return HttpClientUtils.executeXmlResult(httpUriRequest, MicroPayResult.class);
    }

    /**
     * 查询订单
     */
    @Override
    public MchOrderInfoResult payOrderQuery(MchOrderQuery mchOrderquery, String key) {
        HttpUriRequest httpUriRequest = createRequest(mchOrderquery, key, MCH_ORDER_QUERY);
        return HttpClientUtils.executeXmlResult(httpUriRequest, MchOrderInfoResult.class);
    }

    /**
     * 关闭订单
     */
    @Override
    public MchBaseResult payCloseOrder(CloseOrder closeorder, String key) {
        HttpUriRequest httpUriRequest = createRequest(closeorder, key, MCH_CLOSE_ORDER);
        return HttpClientUtils.executeXmlResult(httpUriRequest, MchBaseResult.class);
    }

    /**
     * 查询退款
     * <p>
     * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款 20 分钟内到账，银行卡支付的退款3 个工作日后重新查询退款状态。
     *
     * @param refundquery
     * @param key         商户支付密钥
     * @return
     */
    @Override
    public RefundQueryResult payRefundQuery(RefundQuery refundquery, String key) {
        HttpUriRequest httpUriRequest = createRequest(refundquery, key, MCH_REFUND_QUERYD);
        return HttpClientUtils.executeXmlResult(httpUriRequest, RefundQueryResult.class);
    }

    /***
     * 构造微信支付请求
     *
     * @param requestParams
     * @param key
     * @param uri
     * @return
     */
    private HttpUriRequest createRequest(MchBaseRequest requestParams, String key, String uri) {
        if (requestParams == null || key == null) {
            throw new IllegalArgumentException("requestParams and key is can't empty !");
        }
        Map<String, String> map = MapUtil.objectToMap(requestParams);
        if (key != null) {
            String sign = generateSign(map, key);
            requestParams.setSign(sign);
        }
        String unifiedorderXML = XMLConverUtil.convertToXML(requestParams);
        log.info(unifiedorderXML);
        HttpUriRequest httpUriRequest = RequestBuilder.post().setHeader(xmlHeader).setUri(BASE_MCH_URI + uri)
                .setEntity(new StringEntity(unifiedorderXML, Charset.forName("utf-8"))).build();
        return httpUriRequest;
    }

    /***
     * 构造微信支付请求
     *
     * @param requestParams
     * @param key
     * @param uri
     * @return
     */
    private HttpUriRequest createRequestForYuyin(MicroPay requestParams, String key, String uri) {
        if (requestParams == null || key == null) {
            throw new IllegalArgumentException("requestParams and key is can't empty !");
        }
        Map<String, String> map = MapUtil.objectToMap(requestParams);
        if (key != null) {
            String sign = generateSign(map, key);
            requestParams.setSign(sign);
        }
        String unifiedorderXML = XMLConverUtil.convertToXML(requestParams);
        log.info(unifiedorderXML);
        HttpUriRequest httpUriRequest = RequestBuilder.post().setHeader(xmlHeader).setUri(BASE_MCH_URI + uri)
                .setEntity(new StringEntity(unifiedorderXML, Charset.forName("utf-8"))).build();
        log.info("result return");

        return httpUriRequest;
    }


    /**
     * 生成sign MD5 加密 toUpperCase
     *
     * @param map
     * @param paternerKey
     * @return
     */
    public String generateSign(Map<String, String> map, String paternerKey) {
        Map<String, String> tmap = MapUtil.order(map);
        if (tmap.containsKey("sign")) {
            tmap.remove("sign");
        }
        log.info(JSON.toJSONString(tmap));
        String str = MapUtil.mapJoin(tmap, false, false);
        return DigestUtils.md5Hex(str + "&key=" + paternerKey).toUpperCase();
    }

    /**
     * 微信退款
     */
    @Override
    public SecapiPayRefundResult secapiPayRefund(SecapiPayRefund secapiPayRefund, String key, byte[] keyStoreP12) {

        HttpUriRequest httpUriRequest = createRequest(secapiPayRefund, key, MCH_REFUND);
        //keystore的 byte数组
        return HttpClientUtils.keyStoreExecuteXmlResult(secapiPayRefund.getMch_id(), httpUriRequest, keyStoreP12, SecapiPayRefundResult.class);
    }
}
