package com.xinmintx.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.merchant.mapper.MerchantMapper;
import com.xinmintx.merchant.model.Merchant;
import com.xinmintx.merchant.service.MerchantsLoginService;
import com.xinmintx.merchant.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * @ClassName: com.xinmintx.factory.service.impl.MerchantsLoginServiceImpl
 * @Author:Pikachu
 * @Date: 2020/1/14 16:11
 * @Version: v1.0
 */
@Service
public class MerchantsLoginServiceImpl implements MerchantsLoginService {
    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private Jedis jedis;
    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public Merchant merchantLogin(String phone) {
        String uuid = UUID.randomUUID().toString();
        Merchant merchant = merchantMapper.selectByTel(phone);
        if (merchant != null && merchant.getCellphone() != null) {
            if(StringUtils.isEmpty(merchant.getToken())){
                merchant.setToken(uuid);
                merchantMapper.updateBytel(merchant);
            }
            return merchant;
        } else {
            Merchant merchants = new Merchant();
            merchants.setCellphone(phone);
            merchants.setToken(uuid);
            merchantMapper.addBytel(merchants);
            return merchants;
        }
    }

    @Override
    public String sendcode(String phone) {
        //1 生成6位随机数
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        //存入缓存
        jedis.set(phone, code);
        System.out.println("phone=" + phone + ";msCode=" + code);
        //30分钟过期
        jedis.expire(phone, 1800);
        try {
            SendSmsResponse response = smsUtil.sendSms(phone, "SMS_176440093", "惠商", "{\"code\":\"" + code + "\"}");
            String responseCode = response.getCode();
            System.out.println("msUtil=" + JSON.toJSONString(response));


            if ("OK".equals(responseCode)) {
                return "SUCCESS";
            } else {
                return "FAIL";
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "FAIL";
    }
}
