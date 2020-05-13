package com.xinmintx.agent.service.impl;

import cfca.sm2rsa.common.PKIException;
import cfca.util.SignatureUtil2;
import com.xinmintx.agent.common.parity.StringUtil;
import com.xinmintx.agent.mapper.WithdrawDepositMapper;
import com.xinmintx.agent.model.DepositInform;
import com.xinmintx.agent.model.UOrder;
import com.xinmintx.agent.model.UOrderExample;
import com.xinmintx.agent.model.WithdrawDeposit;
import com.xinmintx.agent.service.WithdrawCallBackService;
import com.xinmintx.agent.util.MapUtil;
import com.xinmintx.agent.utilAgent.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName:.WithdrawCallBackServiceImpl
 * @author:chf
 * @Date:2019/12/26：9:07
 * @developerKits： win 10     jdk1.8
 */
@Service
public class WithdrawCallBackServiceImpl implements WithdrawCallBackService {
    @Autowired
    private WithdrawDepositMapper withdrawDepositMapper;

    @Override
    public Boolean checkInfo(WithdrawDeposit pn) {
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
            if ("01".equals(pn.getOrderStatus())) {
                WithdrawDeposit withdrawDeposit = new WithdrawDeposit();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
                try {
                    withdrawDeposit.setOrderDate(sdf2.parse(pn.getOrderDate().toString()));
                    withdrawDeposit.setOrderTime(sdf.parse(pn.getOrderTime().toString()));
                    withdrawDeposit.setRequestSn(pn.getRequestSn());
                    withdrawDeposit.setRetCode(pn.getRetCode());
                    withdrawDeposit.setRetMsg(pn.getRetMsg());
                    withdrawDeposit.setMerchantid(pn.getMerchantid());
                    withdrawDeposit.setOrderStatus(pn.getOrderStatus());
                    withdrawDeposit.setTxnAmt(pn.getTxnAmt());
                    withdrawDeposit.setFee(pn.getFee());
                    withdrawDeposit.setSignature(pn.getSignature());
                    withdrawDeposit.setNotifyUrl(pn.getNotifyUrl());
                    withdrawDeposit.setSignType(pn.getSignType());
                    withdrawDepositMapper.insertSelective(withdrawDeposit);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return flag;
    }
}
