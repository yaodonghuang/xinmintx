package com.xinmintx.web.controller.system;

import com.xinmintx.system.service.IMerchantWxPublicPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @ClassName: com.xinmintx.web.controller.system.WeChatPayController
 * @Author:Pikachu
 * @Date: 2019/12/10 16:49
 * @Version: v1.0
 */
@Controller
@RequestMapping("/system/wechatpay")
public class WeChatPayController {
    @Autowired
    private IMerchantWxPublicPay iMerchantWxPublicPay;
    @RequestMapping("/payment")
    public void  Payment(@RequestParam("paramMap")Map<String, String> paramMap){
        iMerchantWxPublicPay.weChatPay(paramMap);
    }

}
