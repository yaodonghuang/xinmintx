package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.common.parity.StringUtil;
import com.xinmintx.agent.mapper.UOrderMapper;
import com.xinmintx.agent.model.PayNotify;
import com.xinmintx.agent.model.UOrder;
import com.xinmintx.agent.model.UOrderExample;
import com.xinmintx.agent.service.TopUpInformService;
import com.xinmintx.agent.util.MapUtil;
import com.xinmintx.agent.utilAgent.ConfigInfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName:.TopUpInformServiceImpl
 * @author:chf
 * @Date:2019/12/25：10:05
 * @developerKits： win 10     jdk1.8
 */
@Service
public class TopUpInformServiceImpl implements TopUpInformService {
    @Autowired
    private UOrderMapper orderMapper;

    @Override
    public Boolean topUpInform(PayNotify pn) {
        Boolean flag = false;
        // 签名验证,转换成map
        Map<String, String> map = MapUtil.objectToMap(pn);
        Map<String, String> params = StringUtil.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        StringUtil.buildPayParams(buf, params, false);
        String payStr = buf.toString();
//        String signature = null;
//        try {
//            SignatureUtil2 signUtil = new SignatureUtil2();
//            byte[] CFCAsignature = signUtil.p7SignMessageDetach("SHA1withRSAEncryption", payStr.getBytes("UTF-8"),
//                    ConfigInfo.getPrivateKey(), ConfigInfo.getX509Cert(), ConfigInfo.getSession());
//            signature = new String(CFCAsignature, "UTF-8");
//        } catch (UnsupportedEncodingException | PKIException e) {
//            e.printStackTrace();
//        }
            flag = true;
            if ("1".equals(pn.getStatus())) {
                flag = true;
                UOrderExample example = new UOrderExample();
                UOrder uOrder = new UOrder();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                try {
                    uOrder.setPayTime(sdf.parse(pn.getCompleteDate()));
                    uOrder.setCompleteDate(sdf.parse(pn.getCompleteDate()));
                    uOrder.setSettleDate(sdf.parse(pn.getSettleDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                uOrder.setPayStatus("1");
//                        uOrder.setTransactionId(mchPayNotify.getTransaction_id());
                uOrder.setPayTime(new Date());
                uOrder.setPayType("1");
                // 第三方数据
                uOrder.setVersionId(pn.getVersionId());
                uOrder.setMerchantId(pn.getMerchantId());
                uOrder.setOrderId(pn.getOrderId());
                uOrder.setStatus(Integer.parseInt(pn.getStatus()));
                uOrder.setNotifyTyp(Integer.parseInt(pn.getNotifyTyp()));
                uOrder.setPayOrdNo(pn.getPayOrdNo());
                uOrder.setOrderAmt(BigDecimal.valueOf(Double.parseDouble(pn.getOrderAmt())));
                uOrder.setNotifyUrl(pn.getNotifyUrl());
                uOrder.setSignType(pn.getSignType());
                uOrder.setSignature(pn.getSignature());
                uOrder.setAttach(pn.getAttach());
                try {
                    orderMapper.insertSelective(uOrder);

                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        return flag;
    }
}
