package com.xinmintx.merchant.controller;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.dto.MerchantBindCardDTO;
import com.xinmintx.merchant.service.MerchantExtService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@Slf4j
@RestController
@RequestMapping("merchant")
public class MerchantExtController {

    @Autowired
    MerchantExtService merchantExtService;

    @GetMapping("/getMyCommunity")
    public ResultCode getMyCommunity(@RequestHeader String token, @Ignore ResultCode resultCode){
        log.info("getMyCommunity token is {}",token);
        resultCode.setData(merchantExtService.getMyCommunity(token));
        return resultCode;
    }


    @GetMapping("/getInfo")
    public ResultCode getInfo(@RequestHeader String token, @Ignore ResultCode resultCode){
        resultCode.setData(merchantExtService.getInfo(token));
        return resultCode;
    }

    @DeleteMapping("/rescission/{communityId}")
    public ResultCode rescission(@RequestHeader String token, @PathVariable("communityId") Integer communityId ,@Ignore ResultCode resultCode){
        merchantExtService.rescission(token,communityId);
        return resultCode;
    }

    @GetMapping("/updateShopInfo")
    public ResultCode setInfo(@RequestHeader String token, @RequestParam("shopName") String shopName ,@RequestParam("shopAddress") String  shopAddress ,@Ignore ResultCode resultCode){
        merchantExtService.updateShopInfo(token,shopName,shopAddress);
        return resultCode;
    }

    @GetMapping("/getShopInfo")
    public ResultCode getShopInfo(@RequestHeader String token,@Ignore ResultCode resultCode){
        resultCode.setData(merchantExtService.getShopInfo(token));
        return resultCode;
    }

    @PostMapping("/bindCard")
    public ResultCode bindCard(@RequestHeader String token, @Valid @RequestBody MerchantBindCardDTO merchantBindCardDTO, @Ignore ResultCode resultCode){
        merchantExtService.bindCard(token,merchantBindCardDTO);
        return resultCode;
    }

    @PostMapping("/getBankCardByMerchantId")
    public ResultCode getBankCardByMerchantId(@RequestHeader String token, @Ignore ResultCode resultCode){
        resultCode.setData(merchantExtService.getBankCardByMerchantId(token));
        return resultCode;
    }
}
