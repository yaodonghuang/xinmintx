package com.xinmintx.agent.controller;

import com.xinmintx.agent.mchpay.MchPayNotify;
import com.xinmintx.agent.mchpay.WechatResult;
import com.xinmintx.agent.service.WechatNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qiuyue on 7/4/16.
 */
@RestController
//@RequestMapping(value = "/api")
public class WechatNotifyController {

    @Autowired
    private WechatNotifyService wechatNotifyService;

    /**
     * 微信支付
     * @param mchPayNotify
     * @return
     */
    @RequestMapping(value = "/wechatPayNotify", method = RequestMethod.POST, produces = "application/xml")
    public ResponseEntity<WechatResult> wechatNotify(@RequestBody MchPayNotify mchPayNotify) {
        WechatResult wechatResult = null;
        try {
            wechatResult = wechatNotifyService.wechatNotify(mchPayNotify);
        } catch (Exception e) {
            e.printStackTrace();
            new ResponseEntity<WechatResult>(new WechatResult(WechatResult.FAIL), HttpStatus.OK);
        }
        return new ResponseEntity<WechatResult>(wechatResult, HttpStatus.OK);
    }

    /**
     * 商品支付回调
     * @param mchPayNotify
     * @return
     */
    @RequestMapping(value = "/wechatMerchantPayNotify", method = RequestMethod.POST, produces = "application/xml")
    public ResponseEntity<WechatResult> wechatMerchantPayNotify(MchPayNotify mchPayNotify){
        WechatResult wechatResult = null;
        try {
            wechatResult = wechatNotifyService.wechatMerchantPayNotify(mchPayNotify);
        } catch (Exception e) {
            e.printStackTrace();
            new ResponseEntity<WechatResult>(new WechatResult(WechatResult.FAIL), HttpStatus.OK);
        }
        return new ResponseEntity<WechatResult>(wechatResult, HttpStatus.OK);
    }
}