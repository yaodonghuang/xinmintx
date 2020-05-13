package com.xinmintx.factory.service.impl;

import cfca.sm2rsa.common.PKIException;
import cfca.util.SignatureUtil2;
import com.xinmintx.common.parity.HexConver;
import com.xinmintx.common.parity.StringUtil;
import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.service.InformationChangesService;
import com.xinmintx.factory.util.ConfigInfo;
import com.xinmintx.factory.util.XMLConvertor;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName: com.xinmintx.factory.service.impl.InformationChangesServiceImpl
 * @Author:Pikachu
 * @Date: 2019/12/25 15:44
 * @Version: v1.0
 */
@Service
public class InformationChangesServiceImpl implements InformationChangesService {
    @Override
    public ResultCode informationChanges(Map<String, String> paramMap) {
        // 初始化Map
        initMap(paramMap);
        // 生成post方法
        return pay(paramMap);
    }

    // 维护map初始信息
    private void initMap(Map<String, String> paramMap) {
        paramMap.put("platformId", "00000000035514");// 支付平台统一分配的商户编号
        paramMap.put("version", "2.0");// 版本号
        paramMap.put("signType", "CFCA");// 加密类型
        paramMap.put("md5Key", "309M0O4C9LFKXN3G6KX0THLK");// MD5加密串
        paramMap.put("postUrl", "https://www.56zhifu.com/user/warrantrMerModify.do");
    }
    // 调用支付接口
    private ResultCode pay(Map<String, String> paramMap) {
        ResultCode rc = new ResultCode();
        try {
            HttpClient httpclient = new HttpClient();
            // post赋值
            PostMethod post = initPostValue(paramMap);
            // 请求
            int httpcode = httpclient.executeMethod(post);
            System.out.println("httpcode---->" + httpcode);
            String info = new String(post.getResponseBody(), StandardCharsets.UTF_8);
            System.out.println("返回信息---->" + info);
            // 处理返回结果
            XMLConvertor.dealResult(info, rc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // post对象赋值
    private PostMethod initPostValue(Map<String, String> paramMap) {
        String postUrl = paramMap.get("postUrl");
        PostMethod post = new PostMethod(postUrl);//请求的测试url
        String version = paramMap.get("version"); // 版本号
        String platformId = paramMap.get("platformId");// 支付平台统一分配的商户编号
        String userId = paramMap.get("userId");// 商户编号
        String merBusRegNum = paramMap.get("merBusRegNum");//营业执照注册号
        String credBeginDate = paramMap.get("credBeginDate");//营业执照有效期  起始日期
        String credEndDate = paramMap.get("credEndDate");//营业执照有效期  到期日期
        String credUrl = paramMap.get("credUrl");//营业执照照片
        String merLawPerson = paramMap.get("merLawPerson");//法人姓名 中文转16进制
        String lawPersonCretNo = paramMap.get("lawPersonCretNo");//法人证件号码
        String certBeginDate = paramMap.get("certBeginDate");//法人证有效期
        String certEndDate = paramMap.get("certEndDate");//法人证有效期
        String law1Url = paramMap.get("law1Url");//法人身份证证件正面
        String law2Url = paramMap.get("law2Url");//法人身份证证件反面
        String stockName = paramMap.get("stockName");//高级合伙人姓名 中文转16进制
        String stockCardType = paramMap.get("stockCardType");//高级合伙人证件类型 0-居民身份证(默认),1-护照,2-军官证,C-台湾居民身份证,F-临时身份证,G-警察证,I-港澳身份证,Z-其他
        String stockCardNo = paramMap.get("stockCardNo");//高级合伙人证件号码
        String stockCardDate1 = paramMap.get("stockCardDate1");//高级合伙人证有效期
        String stockCardDate2 = paramMap.get("stockCardDate2");//高级合伙人证有效期
        String storeName = paramMap.get("storeName");//商城名称 中文转16进制
        //String compayEmail = paramMap.get("compayEmail");//公司邮箱
        String sjz1Url = paramMap.get("sjz1Url");//实景照	门头照
        String sjz2Url = paramMap.get("sjz2Url");//实景照	内景照
        String sjz3Url = paramMap.get("sjz3Url");//实景照	法人手持身份证店内照
        String compayType = paramMap.get("compayType");//0-企业法人 1-非法人企业 2-社会团体 3-民办非企业组织 4-个体工商户 5-自然人 6-其他
        String compayStart = paramMap.get("compayStart");//成立时间
        String merAddr = paramMap.get("merAddr");//商户所在地
        String compayTelNo = paramMap.get("compayTelNo");//公司电话
        String merPhone = paramMap.get("merPhone");//手机号码
        String stlBankType = paramMap.get("stlBankType");//结算银行卡类型
        String stlBankCard = paramMap.get("stlBankCard");//结算银行卡号
        String stlBandkCustName = paramMap.get("stlBandkCustName");//结算银行户名 	中文转16进制
        String stlBankRelNo = paramMap.get("stlBankRelNo");//银行联行号
        String stlBankBrchName = paramMap.get("stlBankBrchName");//开户银行网点名称
        String stlBank1Url = paramMap.get("stlBank1Url");//银行卡图片正面
        String stlBank2Url = paramMap.get("stlBank2Url");//银行卡图片反面
        String khdjUrl = paramMap.get("khdjUrl");//开户登记照片	stlBankType=1时(对公账户),必传
        String stlChanFlg = paramMap.get("stlChanFlg");//结算渠道	1-转银行账户 2-转现金账户(默认)
        String orgNo = paramMap.get("orgNo");//组织机构代码	三证不合一时,必传此参数,否者人工审核不通过
        String orgBeginDate = paramMap.get("orgBeginDate");//组织机构有效期	格式yyyyMMdd,三证不合一时,必传此参数,否者人工审核不通过
        String orgEndDate = paramMap.get("orgEndDate");//组织机构有效期	格式yyyyMMdd,三证不合一时,必传此参数,否者人工审核不通过
        String orgUrl = paramMap.get("orgUrl");//组织机构照片
        String swdjNo = paramMap.get("swdjNo");//税务登记证号
        String swdjUrl = paramMap.get("swdjUrl");//税务登记证照片
        String signType = paramMap.get("signType");//签名方式
        // 获取签名
        String signature = getSignature(paramMap);
        // post赋值
        post.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        post.addParameter("platformId", platformId);
        post.addParameter("version", version);
        post.addParameter("userId", userId);
        post.addParameter("merBusRegNum",merBusRegNum);
        post.addParameter("credBeginDate", credBeginDate);
        post.addParameter("credEndDate", credEndDate);
        if (credUrl!=null){
            post.addParameter("credUrl", credUrl);
        }else {
            post.addParameter("orgNo", orgNo);
            post.addParameter("orgBeginDate", orgBeginDate);
            post.addParameter("orgEndDate", orgEndDate);
            post.addParameter("orgUrl", orgUrl);
            post.addParameter("swdjNo", swdjNo);
            post.addParameter("swdjUrl", swdjUrl);
        }
        post.addParameter("merLawPerson", HexConver.Str2Hex(merLawPerson)  );
        post.addParameter("lawPersonCretNo", lawPersonCretNo);
        post.addParameter("certBeginDate", certBeginDate);
        post.addParameter("certEndDate", certEndDate);
        post.addParameter("law1Url", law1Url);
        post.addParameter("law2Url", law2Url);
        post.addParameter("stockName",HexConver.Str2Hex(stockName) );
        post.addParameter("stockCardType",stockCardType);
        post.addParameter("stockCardNo", stockCardNo);
        post.addParameter("stockCardDate1", stockCardDate1);
        post.addParameter("stockCardDate2", stockCardDate2);
        post.addParameter("storeName", HexConver.Str2Hex(storeName));
        //post.addParameter("compayEmail", compayEmail);
        post.addParameter("sjz1Url", sjz1Url);
        post.addParameter("sjz2Url", sjz2Url);
        post.addParameter("sjz3Url", sjz3Url);
        post.addParameter("compayType", compayType);
        post.addParameter("compayStart", compayStart);
        post.addParameter("merAddr", HexConver.Str2Hex(merAddr));
        post.addParameter("compayTelNo", compayTelNo);
        post.addParameter("merPhone", merPhone);
        post.addParameter("stlBankType", stlBankType);
        post.addParameter("stlBankCard", stlBankCard);
        post.addParameter("stlBandkCustName",HexConver.Str2Hex(stlBandkCustName) );
        if (paramMap.get(stlBankType).equals(1)){
            post.addParameter("stlBankRelNo", stlBankRelNo);
            post.addParameter("stlBankBrchName", HexConver.Str2Hex(stlBankBrchName));
            post.addParameter("khdjUrl", khdjUrl);
        }else {
            post.addParameter("stlBank1Url", stlBank1Url);
            post.addParameter("stlBank2Url", stlBank2Url);
        }
        post.addParameter("stlChanFlg", stlChanFlg);
        post.addParameter("signType", signType);
        post.addParameter("signature", signature);
        return post;
    }

    // 获取签名方法
    private String getSignature(Map<String, String> paramMap) {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("platformId", paramMap.get("platformId"));
        map.put("version", paramMap.get("version"));
        map.put("userId",paramMap.get("userId"));
        map.put("merBusRegNum",paramMap.get("merBusRegNum"));
        map.put("credBeginDate", paramMap.get("credBeginDate"));
        map.put("credEndDate", paramMap.get("credEndDate"));
        if (paramMap.get("credUrl")!=null){
            map.put("credUrl", paramMap.get("credUrl"));
        }else {
            map.put("orgNo", paramMap.get("orgNo"));
            map.put("orgBeginDate", paramMap.get("orgBeginDate"));
            map.put("orgEndDate", paramMap.get("orgEndDate"));
            map.put("orgUrl", paramMap.get("orgUrl"));
            map.put("swdjNo", paramMap.get("swdjNo"));
            map.put("swdjUrl", paramMap.get("swdjUrl"));
        }
        map.put("merLawPerson",  HexConver.Str2Hex(paramMap.get("merLawPerson")));
        map.put("lawPersonCretNo", paramMap.get("lawPersonCretNo"));
        map.put("certBeginDate", paramMap.get("certBeginDate"));
        map.put("certEndDate", paramMap.get("certEndDate"));
        map.put("law1Url", paramMap.get("law1Url"));
        map.put("law2Url", paramMap.get("law2Url"));
        map.put("stockName", HexConver.Str2Hex(paramMap.get("stockName")));
        map.put("stockCardType",paramMap.get("stockCardType"));
        map.put("stockCardNo", paramMap.get("stockCardNo"));
        map.put("stockCardDate1", paramMap.get("stockCardDate1"));
        map.put("stockCardDate2", paramMap.get("stockCardDate2"));
        map.put("storeName",HexConver.Str2Hex(paramMap.get("storeName")));
        //map.put("compayEmail", paramMap.get("compayEmail"));
        map.put("sjz1Url", paramMap.get("sjz1Url"));
        map.put("sjz2Url", paramMap.get("sjz2Url"));
        map.put("sjz3Url", paramMap.get("sjz3Url"));
        map.put("compayType", paramMap.get("compayType"));
        map.put("compayStart", paramMap.get("compayStart"));
        map.put("merAddr",HexConver.Str2Hex(paramMap.get("merAddr")));
        map.put("compayTelNo", paramMap.get("compayTelNo"));
        map.put("merPhone", paramMap.get("merPhone"));
        map.put("stlBankType", paramMap.get("stlBankType"));
        map.put("stlBankCard", paramMap.get("stlBankCard"));
        map.put("stlBandkCustName", HexConver.Str2Hex(paramMap.get("stlBandkCustName")));
        if (paramMap.get("stlBankType").equals(1)){
            map.put("stlBankRelNo", paramMap.get("stlBankRelNo"));
            map.put("stlBankBrchName",HexConver.Str2Hex(paramMap.get("stlBankBrchName")));
            map.put("khdjUrl", paramMap.get("khdjUrl"));
        }else {
            map.put("stlBank1Url", paramMap.get("stlBank1Url"));
            map.put("stlBank2Url", paramMap.get("stlBank2Url"));
        }
        map.put("stlChanFlg",paramMap.get("stlChanFlg"));
        map.put("signType", paramMap.get("signType"));
        Map<String, String> params = StringUtil.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        StringUtil.buildPayParams(buf, params, false);
        String payStr = buf.toString();

        String signature;
        System.out.println("payStr:" + payStr);
        String md5Key = paramMap.get("md5Key");
        System.out.println("source+key:==>" + payStr + md5Key);
        try {
            SignatureUtil2 signUtil = new SignatureUtil2();
            byte[] CFCAsignature = signUtil.p7SignMessageDetach("SHA1withRSAEncryption", payStr.getBytes("UTF-8"),
                    ConfigInfo.getPrivateKey(), ConfigInfo.getX509Cert(), ConfigInfo.getSession());
            signature = new String(CFCAsignature, "UTF-8");
            System.out.println("signature:" + signature);
            String str = payStr + "&signature=" + signature;
            System.out.println("发送--加密前参数==" + str);
            return signature;
        } catch (UnsupportedEncodingException | PKIException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 处理返回结果
    private void dealResult(String info) {
        if (info != null && !"".equals(info)) {
            try {
                Document doc = DocumentHelper.parseText(info);
                Element root = doc.getRootElement();
                if (root.attributeValue("retCode").equals("0001")) {
                    System.out.println("success");
                    System.out.println(root.attributeValue("payInfo"));
                } else {
                    System.out.println("fail");
                }
                System.out.println(root.attributeValue("retMsg"));
            } catch (DocumentException e1) {
                e1.printStackTrace();
            }
        }
    }
}
