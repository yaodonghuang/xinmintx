package com.xinmintx.merchant.controller;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.service.PrintService;
import com.xinmintx.merchant.service.PrinterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/4
 * @time: 14:18
 * @Description:
 */

@Slf4j
@RestController
@RequestMapping("/merchants")
public class PrinterController {

    @Autowired
    PrinterService printerService;
    @Autowired
    private PrintService printService;

    @RequestMapping("/printer")
    public ResultCode queryCommunityOrder(@RequestParam("communityID") Integer communityID, @RequestParam("type") Integer type, @RequestHeader String token) {
        return printerService.queryCommunityOrder(communityID, token, type);
    }

    /**
     * 商户添加打印机
     *
     * @param sn       打印机号码
     * @param printKey 打印机key
     * @param token    商户token
     * @return ResultCode
     */
    @RequestMapping("/addPrinter")
    public ResultCode addPrinter(@RequestParam("printerName") String printerName,@RequestParam("sn") String sn, @RequestParam("printKey") String printKey, @RequestHeader String token) {
        return printService.addPrint(printerName,token,sn,printKey);
    }

    /**
     * 查询打印机状态
     * @param token
     * @return
     */
    @PostMapping("/queryPrinterStatus")
    public ResultCode queryPrinterStatus(@RequestHeader String token){
        return printService.selectPrinterStatus(token);
    }

    /**
     * 按订单号打印单个订单
     * @param orderId
     * @return
     */
    @PostMapping("/automaticPrinting")
    public ResultCode automaticPrinting(@RequestParam("orderId")Integer orderId){
        return printerService.automaticPrinting(orderId);
    }

    /**
     * 修改商家自动打印状态
     * @param token
     * @param ifAuto
     * @return
     */
    @PostMapping("/upPrintingStatus")
    public ResultCode  upPrintingStatus (@RequestHeader String token,@RequestParam("ifAuto")Integer ifAuto){
        return printService.upPrintingStatus(token,ifAuto);
    }
}
