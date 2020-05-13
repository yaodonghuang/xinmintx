package com.xinmintx.merchant.service;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.model.PrinterTDO;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by sw
 * @date: 2020/3/5 10:33
 * @Description:  打印机
 */
public interface PrintService {

    int printOrder (PrinterTDO printerTDO, Integer merchantId);

    ResultCode addPrint(String printerName,String token, String sn, String printKey);

    ResultCode selectPrinterStatus(String token);

    ResultCode upPrintingStatus(String token,Integer ifAuto);
}
