package com.xinmintx.merchant.service;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.model.PrinterTDO;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/4
 * @time: 11:23
 * @Description:
 */
public interface PrinterService {

    ResultCode queryCommunityOrder(Integer CommunityID, String token,Integer type);

    ResultCode  automaticPrinting(Integer orderId);
}
