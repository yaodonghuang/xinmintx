package com.xinmintx.merchant.controller;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.dto.MerchantDeliveryAddDTO;
import com.xinmintx.merchant.service.MerchantDeliveryService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@RestController
@RequestMapping("merchantDelivery")
public class MerchantDeliveryController {

    @Autowired
    MerchantDeliveryService merchantDeliveryService;

    @PostMapping("/addOrUpdate")
    public ResultCode addOrUpdate(@RequestHeader String token, @RequestBody List<MerchantDeliveryAddDTO> merchantDeliveryAddDTOS, @Ignore ResultCode resultCode){
        merchantDeliveryService.addOrUpdate(token,merchantDeliveryAddDTOS);
        return resultCode;
    }


//
//    @GetMapping("/getInfo")
//    public ResultCode getInfo(@RequestHeader String token, @Ignore ResultCode resultCode){
//        resultCode.setData(merchantService.getInfo(token));
//        return resultCode;
//    }
//
//    @DeleteMapping("/rescission/{communityId}/{type}")
//    public ResultCode rescission(@RequestHeader String token, @PathVariable("communityId") Integer communityId ,@PathVariable("type") Integer type ,@Ignore ResultCode resultCode){
//        merchantService.rescission(token,communityId,type);
//        return resultCode;
//    }
//
//    @GetMapping("/updateShopInfo")
//    public ResultCode setInfo(@RequestHeader String token, @RequestParam("shopName") String shopName ,@RequestParam("shopAddress") String  shopAddress ,@Ignore ResultCode resultCode){
//        merchantService.updateShopInfo(token,shopName,shopAddress);
//        return resultCode;
//    }
//
//    @GetMapping("/getShopInfo")
//    public ResultCode getShopInfo(@RequestHeader String token,@Ignore ResultCode resultCode){
//        resultCode.setData(merchantService.getShopInfo(token));
//        return resultCode;
//    }
}
