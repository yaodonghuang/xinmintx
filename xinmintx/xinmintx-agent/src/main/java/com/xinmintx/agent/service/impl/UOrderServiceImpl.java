package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.configuration.pay.PayConfig;
import com.xinmintx.agent.mapper.*;
import com.xinmintx.agent.mchpay.*;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.UOrderService;
import com.xinmintx.agent.service.WechatOrderService;
import com.xinmintx.agent.service.WechatPayMchService;
import com.xinmintx.agent.util.MapUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bao on 7/4/16.
 */
@Service
@Slf4j
public class UOrderServiceImpl implements UOrderService {


    @Autowired
    public UOrderMapper uOrderMapper;

    @Autowired
    private WechatOrderService wechatOrderService;

    @Resource
    private WechatPayMchService wechatPayMchService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Value("${wechat.key}")
    private String wechatKey;

    @Value("${wechat.appid}")
    private String wechatAppid;

    @Value("${wechat.mch_id}")
    private String wechatMchId;

    @Autowired
    private UOrderService uOrderService;

    private static final String ALIPAY = "ALIPAY";

    private static final String WXPAY = "WXPAY";

    @Override
    public UOrder findByTradeNo(String outTradeNo) {
        UOrderExample example = new UOrderExample();
        UOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOutTradeNoEqualTo(outTradeNo);
        List<UOrder> uorderList = uOrderMapper.selectByExample(example);
        if (uorderList != null && uorderList.size() > 0) {
            return uorderList.get(0);
        }
        return null;
    }

    @Override
    public UOrder findByUorderNo(String orderNo) {
        UOrderExample example = new UOrderExample();
        UOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(orderNo);
        List<UOrder> uorderList = uOrderMapper.selectByExample(example);
        if (uorderList != null && uorderList.size() > 0) {
            return uorderList.get(0);
        }
        return null;
    }

    @Override
    public int updateUorder(UOrder order) {
        return uOrderMapper.updateByPrimaryKey(order);
    }

    /**
     *
     * @param totalFee 金额(分)
     * @param body 订单信息
     * @param user 创建人的对象
     * @param userId 被创建人的主键
     * @param roleId 需要创建的角色(1,合伙人;2,分公司;3,代理;4,黄金商户;5,普通商户;6,新民金卡)
     * @return
     * @throws RuntimeException
     */
    @Transactional
    @Override
    public Map<String, Object> unifiedorder(Long totalFee, String body, User user, int userId, String roleId) throws RuntimeException {
        Map<String, Object> map = new HashMap<String, Object>();
        String out_trade_no = DigestUtils.md5Hex(System.currentTimeMillis() + "");
//        Long total_fee = (long)1;
        UOrder uOrder = new UOrder();
        uOrder.setCreateTime(new Date());
        uOrder.setOutTradeNo(out_trade_no);
        uOrder.setTotalFee(totalFee);
        uOrder.setPayType("1");
        uOrder.setOrderNo(System.currentTimeMillis() + "");
        uOrder.setPayStatus("0");
        uOrder.setGoodsDesc(body);
        uOrder.setUserId(user.getId());
        Map<String, Object> wechatPayConfig = new HashMap<>();
        wechatPayConfig.put("appid", wechatAppid);
        wechatPayConfig.put("mch_id", wechatMchId);
        wechatPayConfig.put("appid_key", wechatKey);
        wechatPayConfig.put("openid", user.getOpenid());
        WXUnifiedOrder wxUnifiedOrder = wechatOrderService.createNativeWechatOrder(uOrder,
                wechatPayConfig, PayConfig.WECHAT_NOTIFY_URL);
        WXUnifiedOrderResult wxUnifiedOrderResult = wechatPayMchService.payUnifiedorder(wxUnifiedOrder,
                wechatPayConfig.get("appid_key").toString());
        if (wxUnifiedOrderResult == null) {
            map.put("code", "FAIL");
            map.put("msg", "WeChat orders failed");
            map.put("sub_code", PayConfig.SYSTEMERROR);
            return map;
        }
        log.info("=======WeChatResult========" + JSON.toJSONString(wxUnifiedOrderResult));
        if (wxUnifiedOrderResult.getReturn_code().equals(ReturnCode.SUCCESS.toString())
                && wxUnifiedOrderResult.getResult_code().equals(ReturnCode.SUCCESS.toString())) {
            uOrder.setPrepayId(wxUnifiedOrderResult.getPrepay_id());
            try {
                int i = uOrderMapper.insert(uOrder);
                if ("1".equals(roleId) || "2".equals(roleId) || "3".equals(roleId)) {
                    User user1 = new User();
                    user1.setOrderId(uOrder.getId());
                    user1.setId(userId);
                    userMapper.updateByPrimaryKeySelective(user1);
                } else if ("4".equals(roleId) || "5".equals(roleId)) {
                    Merchant merchant = new Merchant();
                    merchant.setOrderId(uOrder.getId());
                    merchant.setId(userId);
                    merchantMapper.updateByPrimaryKeySelective(merchant);
                } else {
                    Member member = new Member();
                    member.setOrderId(uOrder.getId());
                    member.setId(userId);
                    memberMapper.updateByPrimaryKeySelective(member);
                }
                if (i < 1) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
            Map<String, String> params = new HashMap<>();
            long millis = System.currentTimeMillis();
            params.put("appId", wechatAppid);
            params.put("timeStamp", String.valueOf(millis / 1000));
            params.put("nonceStr", String.valueOf(millis));
            params.put("package", "prepay_id=" + wxUnifiedOrderResult.getPrepay_id());
            params.put("signType", "MD5");

            String paySign = generateSign(params, wechatKey);//生成二次签名

            map.put("code", "SUCCESS");
            map.put("msg", "WeChat orders a success");
            map.put("package", params.get("package"));
            map.put("paySign", paySign);
            map.put("timeStamp", params.get("timeStamp"));
            map.put("nonceStr", params.get("nonceStr"));
            map.put("appId", params.get("appId"));
            map.put("out_trade_no", out_trade_no);
            map.put("real_fee", totalFee);
            return map;
        } else {
            map.put("code", "FAIL");
            map.put("msg", "WeChat orders failed，【" + wxUnifiedOrderResult.getErr_code_des() + "】");
            map.put("sub_code", PayConfig.SYSTEMERROR);
            return map;
        }

    }

    @Override
    public Map<String, String> closeorder(Map<String, String> param) {
        return null;
    }

    @Override
    public Map<String, String> queryOrder(Map<String, String> param) {
        Map<String, String> map = new HashMap<String, String>();
        if (param != null) {
            UOrderExample example = new UOrderExample();
            UOrderExample.Criteria criteria = example.createCriteria();
            String out_trade_no = param.get("out_trade_no");
            if (null != out_trade_no && !"".equals(out_trade_no)) {
                criteria.andOutTradeNoEqualTo(out_trade_no);
            }
            List<UOrder> list = uOrderMapper.selectByExample(example);
            UOrder UOrder = null;
            if (list != null && list.size() > 0) {
                String payType = list.get(0).getPayType();
                String payStatus = list.get(0).getPayStatus();
                UOrder = list.get(0);
                if (payStatus != null && "1".equals(payStatus)) {
                    map.put("code", "SUCCESS");
                    map.put("msg", "OK");
                    map.put("trade_state", "paySuccess");
                    map.put("total_fee", UOrder.getTotalFee() + "");
                    return map;
                }
                MchOrderQuery mchOrderquery = new MchOrderQuery();
                mchOrderquery.setAppid(wechatAppid);
                mchOrderquery.setMch_id(wechatMchId);
                mchOrderquery.setNonce_str(String.valueOf(System.currentTimeMillis()));
                mchOrderquery.setOut_trade_no(out_trade_no);
                MchOrderInfoResult result = wechatPayMchService.payOrderQuery(mchOrderquery, wechatKey);
                if (result != null && "SUCCESS".equals(result.getTrade_state()) && "SUCCESS".equals(result.getResult_code())) {
                    map.put("code", "SUCCESS");
                    map.put("trade_state", result.getTrade_state());
                    map.put("total_fee", result.getTotal_fee() + "");
                    map.put("trade_state_desc", result.getTrade_state_desc());
                    map.put("out_trade_no", out_trade_no);
                    map.put("transaction_id", result.getTransaction_id());
                    map.put("time_end", result.getTime_end());

                    UOrder.setPayTime(new Date(Long.valueOf(result.getTime_end())));
                    UOrder.setPayStatus("1");
                    UOrder.setTransactionId(result.getTransaction_id());
                    uOrderService.updateUorder(UOrder);
                    return map;
                } else {
                    map.put("code", "FAIL");
                    map.put("trade_state", result.getTrade_state());
                    map.put("msg", result.getTrade_state_desc());
                    map.put("sub_code", "PAYTRADE_ERROR");
                    return map;
                }
            } else {
                map.put("code", "FAIL");
                map.put("msg", "query nothing order");
                map.put("sub_code", "NO_ORDER");
                return map;
            }
        }
        return map;
    }

    @Override
    public int addUorder(UOrder uOrder) {
        return uOrderMapper.insert(uOrder);
    }

    public String generateSign(Map<String, String> map, String paternerKey) {
        Map<String, String> tmap = MapUtil.order(map);
        if (tmap.containsKey("sign")) {
            tmap.remove("sign");
        }
        log.info(JSON.toJSONString(tmap));
        String str = MapUtil.mapJoin(tmap, false, false);
        return DigestUtils.md5Hex(str + "&key=" + paternerKey).toUpperCase();
    }
}