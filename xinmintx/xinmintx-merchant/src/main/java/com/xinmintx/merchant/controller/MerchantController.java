package com.xinmintx.merchant.controller;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.model.MerchantGoods;
import com.xinmintx.merchant.model.PoboNotify;
import com.xinmintx.merchant.service.IMerchantService;
import com.xinmintx.merchant.util.File2OSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 商家controller
 */
@RestController
@RequestMapping("/gift")
@Transactional
public class MerchantController {
    @Autowired
    private IMerchantService merchantService;
    @Autowired
    private File2OSSUtils file2OSSUtils;

    /**
     * 商户核销礼包
     *
     * @param memberId 会员id
     * @param uuid     礼包唯一标识
     * @return
     */
    @RequestMapping(value = "/writeOff", method = RequestMethod.POST)
    public ResultCode writeOff(@RequestParam("memberId") Integer memberId,
                               @RequestParam("uuid") String uuid,
                               @RequestHeader("token") String token) {
        ResultCode resultCode = merchantService.writeOff(memberId, uuid, token);
        return resultCode;
    }

    /**
     * 商户待核销大列表
     *
     * @param token 商户token
     * @param
     * @return
     */
    @RequestMapping(value = "/waitWriteOffList", method = RequestMethod.GET)
    public ResultCode waitWriteOff(@RequestHeader("token") String token) {
        ResultCode resultCode = merchantService.waitWriteOff(token);
        return resultCode;
    }

    /**
     * 商户待核销详情列表
     *
     * @param giftId 礼包id
     * @param
     * @return
     */
    @RequestMapping(value = "/waitWriteOffDetailList", method = RequestMethod.GET)
    public ResultCode waitWriteOffDetail(@RequestParam("giftId") Long giftId) {
        ResultCode resultCode = merchantService.waitWriteOffDetail(giftId);
        return resultCode;
    }

    /**
     * 商户已核销列表
     *
     * @param token 商户token
     * @param
     * @return
     */
    @RequestMapping(value = "/alreadyWriteOff", method = RequestMethod.GET)
    public ResultCode alreadyWriteOff(@RequestHeader("token") String token) {
        ResultCode resultCode = merchantService.alreadyWriteOff(token);
        return resultCode;
    }

    // 批量上传图片
    @PostMapping("/imageListUpload")
    public ResultCode imageListUpload(@RequestParam("files") MultipartFile[] files) {
        ResultCode rc = new ResultCode();
        rc.setCode(200);
        rc.setMsg("SUCCESS");
        if (files != null && files.length > 0) {
            List<String> urlList = new ArrayList<>();
            for (MultipartFile multipartFile : files) {
                String fileUrl = file2OSSUtils.fileUploadOSS(multipartFile);
                urlList.add(fileUrl);
            }
            rc.setData(urlList);
        }
        return rc;
    }

    /**
     * 添加商品
     * @param merchantGoods
     * @return
     */
    @PostMapping("/createGoods")
    public ResultCode createGoods(@RequestBody MerchantGoods merchantGoods,@RequestHeader("token") String token){
       return merchantService.createGoods(merchantGoods,token);
    }

    /**
     * 根据菜品ID查询详情
     * @param goodsId
     * @return
     */
    @PostMapping("/queryMerchantGoods")
    public ResultCode queryMerchantGoods(@RequestParam("goodsId")Long goodsId){
        return merchantService.queryMerchantGoods(goodsId);
    }

    /**
     * 修改菜品
     * @param merchantGoods
     * @return
     */
    @PostMapping("/upMerchantGoods")
    public ResultCode upMerchantGoods(@RequestBody MerchantGoods merchantGoods){
        return merchantService.upMerchantGoods(merchantGoods);
}

    /**
     * 查询菜品列表
     * @param token
     * @return
     */
    @PostMapping("/queryMerchantGoodsList")
    public ResultCode queryMerchantGoodsList(@RequestHeader("token") String token){
        return merchantService.queryMerchantGoodsList(token);
}

    /**
     * 修改上下架状态
     * @param goodsId
     * @param state
     * @return
     */
    @PostMapping("/upGoodsState")
    public ResultCode upGoodsState(@RequestParam("goodsId")Long goodsId,@RequestParam("state")Integer state){
        return merchantService.upGoodsState(goodsId,state);
}

    /**
     * 根据上下架状态获取商品列表
     * @param token
     * @param state
     * @return
     */
    @PostMapping("/queryGoodsListByState")
    public ResultCode queryGoodsListByState(@RequestHeader("token") String token,@RequestParam("state")Integer state){
        return merchantService.queryGoodsListByState(token,state);
}

    /**
     * 删除商品
     * @param goodsId
     * @return
     */
    @PostMapping("/delGoods")
    public ResultCode delGoods(@RequestParam("goodsId")Long goodsId){
        return merchantService.delGoods(goodsId);
}

    /**
     * 获取订单详情
      * @param communityId
     * @param token
     * @return
     */
    @PostMapping("/getCommunityOrderList")
    public ResultCode getCommunityOrderList(@RequestParam("communityId")Integer communityId,@RequestHeader("token")String token,@RequestParam("type")Integer type){
        return merchantService.getCommunityOrderList(communityId,token,type);
    }

    /**
     * 获取社区订单总量
     * @param token
     * @return
     */
    @PostMapping("/getCommunityOrder")
    public ResultCode getCommunityOrder(@RequestHeader("token")String token,@RequestParam("type")Integer type){
        return merchantService.getCommunityOrder(token,type);
    }

    /**
     * 接受订单接口
     * @param token
     * @param communityId
     * @param acceptOrder
     * @return
     */
    @PostMapping("/upacceptOrder")
    public ResultCode upacceptOrder(@RequestHeader("token")String token,@RequestParam("communityId")Integer communityId,@RequestParam("acceptOrder")Integer acceptOrder){
        return merchantService.upacceptOrder(token,communityId,acceptOrder);
    }

    /**
     * 修改待配货状态
     * @param orderId
     * @param orderStatu
     * @return
     */
    @PostMapping("/upOrderStatu")
    public ResultCode upOrderStatu(@RequestParam("orderId")Integer orderId,@RequestParam("orderStatu") Integer orderStatu ){
        return merchantService.upOrderStatu(orderId,orderStatu);
    }

    /**
     * 历史订单总量
     * @param token
     * @param times
     * @return
     */
    @PostMapping("/orderHistory")
    public ResultCode orderHistory(@RequestHeader("token")String token,@RequestParam("times")String times){
        return merchantService.orderHistory(token,times);
    }

    /**
     * 历史订单详情
     * @param token
     * @param times
     * @param communityId
     * @return
     */
    @PostMapping("/historicalOrderDetails")
    public ResultCode historicalOrderDetails(@RequestHeader("token")String token,@RequestParam("times")String times,@RequestParam("communityId")Integer communityId){
        return merchantService.historicalOrderDetails(token,times,communityId);
    }

    /**
     * 查询银行卡
     * @param bank
     * @return
     */
    @PostMapping("/queryBank")
    public ResultCode queryBank(@RequestParam("bank")String bank){
        return merchantService.queryBank(bank);
    }

    /**
     * 商户拒单接口
     * @param communityId
     * @param token
     * @return
     */
    @PostMapping("/refuseOrder")
    public ResultCode refuseOrder(@RequestParam("communityId")Integer communityId,@RequestHeader("token")String token){
        return merchantService.refuseOrder(communityId, token);
    }

    /**
     * 商户提现回调接口
     * @param pn
     */
    @PostMapping("/poboNotify")
    public ResultCode paymentOnBehalfOfNotify(PoboNotify pn){
        return merchantService.paymentOnBehalfOfNotify(pn);
    }

    /**
     * 商品详情接口
     * @param communityId
     * @param goodsId
     * @return
     */
    @PostMapping("/goodsDetail")
    public ResultCode  goodsDetail(@RequestParam("communityId")Long communityId ,@RequestParam("goodsId") Long goodsId){
        return merchantService.queryGoodsDetail(communityId,goodsId);

    }

    /**
     * 今日总计接口
     * @param token
     * @return
     */
    @PostMapping("/totalOrder")
    public ResultCode totalOrder(@RequestHeader("token")String token){
        return merchantService.totalOrder(token);
    }

    /**
     * 根据订单状态查询社区订单详情
     * @param communityId
     * @param token
     * @param type
     * @param orderStatus
     * @return
     */
    @PostMapping("/getOrderListByStatus")
    public ResultCode getOrderListByStatus (@RequestParam("communityId")Integer communityId,@RequestHeader("token")String token,@RequestParam("type")Integer type,@RequestParam("orderStatus")Integer orderStatus) {
        return merchantService.getOrderListByStatus(communityId,token,type,orderStatus);
    }
    @PostMapping("/queryMerchantAuditStatus")
    public ResultCode queryMerchantAuditStatus(@RequestHeader("token")String token){
        return merchantService.queryMerchantAuditStatus(token);
    }

}
