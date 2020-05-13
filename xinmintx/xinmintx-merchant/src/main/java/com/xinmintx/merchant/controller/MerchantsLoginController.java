package com.xinmintx.merchant.controller;


import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.mapper.MerchantMapper;
import com.xinmintx.merchant.model.Merchant;
import com.xinmintx.merchant.service.MerchantsLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: com.xinmintx.factory.controller.MerchantsLoginController
 * @Author:Pikachu
 * @Date: 2020/1/14 15:59
 * @Version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsLoginController {
    @Autowired
    private Jedis jedis;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private MerchantsLoginService merchantsLoginService;
    /**
     * 登陆
     * @param phone
     * @param code
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public ResultCode login(@RequestParam("phone") String phone, @RequestParam("code") String code, HttpServletRequest request) {
        ResultCode<Merchant> resultCode = new ResultCode<Merchant>();
        Integer result = merchantMapper.selectIphone(phone);
        if(result == null){
            resultCode.setCode(800);
            resultCode.setMsg("手机号未注册");
            resultCode.setMsg("FAIL");
            return resultCode;
        }
        String code1 = jedis.get(phone);
        if (code.equals(code1)) {
            Merchant merchant = merchantsLoginService.merchantLogin(phone);
            request.getSession().setAttribute("merchant", merchant);
            //登陆成功删除redis缓存
            jedis.del(phone);
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(merchant);
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("验证码错误");
            resultCode.setMsg("FAIL");
        }
        return resultCode;
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping("/getCode/{phone}")
    public ResultCode send(@PathVariable String phone) {
        ResultCode code = new ResultCode();
        String sendcode = merchantsLoginService.sendcode(phone);
        if ("SUCCESS".equals(sendcode)) {
            code.setCode(200);
            code.setMsg("SUCCESS");
        } else {
            code.setCode(500);
            code.setMsg("FAIL");
        }

        return code;
    }
    /**
     * 是否存在token
     * @param token
     * @return
     */
    @RequestMapping("/getToken")
    public ResultCode getToken(@RequestHeader("token")String token){
        Merchant merchant = merchantMapper.queryByToken(token);
        ResultCode resultCode = new ResultCode<>();
        if (merchant != null) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(merchant);
        } else {
            resultCode.setCode(500);
            resultCode.setMsg("FAIL");
        }
        return resultCode;
    }

}
