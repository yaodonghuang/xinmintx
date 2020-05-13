package com.xinmintx.factory.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.factory.mapper.VenderMapper;
import com.xinmintx.factory.model.Factory;
import com.xinmintx.factory.service.VenderLoginService;
import com.xinmintx.factory.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import java.util.UUID;

/**
 * @ClassName: com.xinmintx.api.service.impl.VenderLoginServiceImpl
 * @Author:Pikachu
 * @Date: 2019/12/3 9:58
 * @Version: v1.0
 */
@Transactional
@Service
public class VenderLoginServiceImpl implements VenderLoginService {

    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private Jedis jedis;
    @Autowired
    private VenderMapper venderMapper;

    @Override
    public Factory venderLogin(String phone) {
        String uuid = UUID.randomUUID().toString();
        Factory vender = venderMapper.selectByTel(phone);
        if (vender != null && vender.getPhone() != null) {
            if(StringUtils.isEmpty(vender.getToken())){
                vender.setToken(uuid);
                venderMapper.updateBytel(vender);
            }
            return vender;
        } else {
            Factory factory = new Factory();
            factory.setPhone(phone);
            factory.setPassword("123456");
            factory.setToken(uuid);
            factory.setSalt(StringUtils.randomSalt());
            venderMapper.addBytel(factory);
            return factory;
        }
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
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
