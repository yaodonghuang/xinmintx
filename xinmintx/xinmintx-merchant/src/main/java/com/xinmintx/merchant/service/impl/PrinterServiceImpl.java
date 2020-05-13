package com.xinmintx.merchant.service.impl;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.mapper.CommunityMerchantMapper;
import com.xinmintx.merchant.mapper.MerchantMapper;
import com.xinmintx.merchant.model.GoodsOrder;
import com.xinmintx.merchant.model.Merchant;
import com.xinmintx.merchant.model.PrinterTDO;
import com.xinmintx.merchant.service.PrintService;
import com.xinmintx.merchant.service.PrinterService;
import com.xinmintx.merchant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/4
 * @time: 11:23
 * @Description:
 */
@Service
public class PrinterServiceImpl implements PrinterService {

    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private PrintService printService;


    @Autowired
    private CommunityMerchantMapper communityMerchantMapper;

    /**
     * 打印社区订单（小票打印）
     *
     * @param communityId 社区id   用于获取需要打印订单的社区id
     * @param token 当前登录用户信息  用于获取当前登陆商户的信息，用于获取需要打印订单的商户id
     * @param type 标识  用于需要打印订单的时间轴标识（当前 1 为昨天，其他为今天）
     * @return
     */
    public ResultCode queryCommunityOrder(Integer communityId , String token,Integer type) {
        ResultCode code = new ResultCode();
        List<PrinterTDO> ls = new ArrayList<>();
        Merchant merchant = merchantMapper.queryByToken(token);
        if (merchant != null) {
            if (type.equals(1)) {
                Integer merchantId = merchant.getId();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                long time = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L - 28800L);
                String beginDate = String.valueOf(time - Long.valueOf(86400));
                String endDate = String.valueOf(time);
                List<GoodsOrder> list = merchantMapper.queryOrderList(communityId, merchantId, beginDate, endDate);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        PrinterTDO printerTDO = new PrinterTDO();
                        printerTDO.setReceiveName(list.get(i).getReceiveName());
                        printerTDO.setReceivePhone(list.get(i).getReceivePhone());
                        printerTDO.setReceiveAddress(list.get(i).getReceiveAddress());
                        printerTDO.setCreateTime(list.get(i).getCreateTime());
                        printerTDO.setVoList(merchantMapper.queryOrder(list.get(i).getId()));
                        printerTDO.setReceiveMessage(list.get(i).getReceiveMessage());
                        printerTDO.setTotalPrice(list.get(i).getTotalAmount());
                        printerTDO.setDeputyHelp(list.get(i).getDeputyHelp());
                        printerTDO.setCommunityName(communityMerchantMapper.queryCommunityNameByCommunityId(list.get(i).getCommunityId()));
                        ls.add(printerTDO);
                        //调用打印机打印订单
                        int result = printService.printOrder(printerTDO, merchantId);
                        if (result == 2) {
                            code.setCode(501);
                            code.setMsg("没有打印机");
                            return code;
                        }
                        merchantMapper.updatePrintNumber(list.get(i).getId());
                    }

                    code.setCode(200);
                    code.setMsg("打印成功");
                    return code;
                } else {
                    code.setCode(500);
                    code.setMsg("没有订单");
                    return code;
                }
            } else {
                Integer merchantId = merchant.getId();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                long time = ((long) (DateUtils.getNowDate().getTime() / 1000 / 86400L) * 86400L - 28800L);
                String beginDate = String.valueOf(time);
                String endDate = String.valueOf(time + Long.valueOf(86400));
                List<GoodsOrder> list = merchantMapper.queryOrderList(communityId, merchantId, beginDate, endDate);
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        PrinterTDO printerTDO = new PrinterTDO();
                        printerTDO.setReceiveName(list.get(i).getReceiveName());
                        printerTDO.setReceivePhone(list.get(i).getReceivePhone());
                        printerTDO.setReceiveAddress(list.get(i).getReceiveAddress());
                        printerTDO.setCreateTime(list.get(i).getCreateTime());
                        printerTDO.setVoList(merchantMapper.queryOrder(list.get(i).getId()));
                        printerTDO.setReceiveMessage(list.get(i).getReceiveMessage());
                        printerTDO.setTotalPrice(list.get(i).getTotalAmount());
                        printerTDO.setCommunityName(communityMerchantMapper.queryCommunityNameByCommunityId(list.get(i).getCommunityId()));
                        ls.add(printerTDO);
                    }
                    code.setCode(200);
                    code.setData(ls);
                    return code;
                } else {
                    code.setMsg("没有订单");
                    return code;
                }
            }
        } else {
            code.setMsg("商户不存在");
            return code;
        }
    }

    /**
     * 实时打印
     * @param orderId
     * @return
     */
    @Override
    public ResultCode automaticPrinting(Integer orderId) {
        ResultCode code = new ResultCode();
         GoodsOrder goodsOrder = merchantMapper.selectGoodsOrder(orderId);
         if (goodsOrder!=null){
             PrinterTDO printerTDO = new PrinterTDO();
             printerTDO.setReceiveName(goodsOrder.getReceiveName());
             printerTDO.setReceivePhone(goodsOrder.getReceivePhone());
             printerTDO.setReceiveAddress(goodsOrder.getReceiveAddress());
             printerTDO.setCreateTime(goodsOrder.getCreateTime());
             printerTDO.setVoList(merchantMapper.queryOrder(goodsOrder.getId()));
             printerTDO.setReceiveMessage(goodsOrder.getReceiveMessage());
             printerTDO.setTotalPrice(goodsOrder.getTotalAmount());
             printerTDO.setDeputyHelp(goodsOrder.getDeputyHelp());
             printerTDO.setCommunityName(communityMerchantMapper.queryCommunityNameByCommunityId(goodsOrder.getCommunityId()));
             int merchantId = (int) goodsOrder.getMerchantId();
             int result = printService.printOrder(printerTDO, merchantId);
             if (result == 2) {
                 code.setCode(501);
                 code.setMsg("没有打印机");
                 return code;
             }
             merchantMapper.updatePrintNumber(goodsOrder.getId());
         }
        code.setCode(200);
        code.setMsg("打印成功");
        return code;
    }
}