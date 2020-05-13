package com.xinmintx.merchant.controller;

import com.xinmintx.merchant.common.ResultCode;
import com.xinmintx.merchant.model.MemberAmountLog;
import com.xinmintx.merchant.model.Merchant;
import com.xinmintx.merchant.model.MerchantAmountLog;
import com.xinmintx.merchant.model.PoboNotify;
import com.xinmintx.merchant.service.WalletService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName:.WalletController
 * @author:chf
 * @Date:2020/2/17：12:58
 * @developerKits： win 10     jdk1.8
 */
@RestController
@RequestMapping("/wallet")
@Transactional
public class WalletController {
    @Resource
    private WalletService walletService;


    /**
     * 查询余额冻结资金
     * @param token
     * @return
     */
    @RequestMapping("/queryBalance")
    public ResultCode queryBalance(@RequestHeader String token){
        Merchant wallet = walletService.queryBalance(token);
        ResultCode resultCode = new ResultCode();
        if (wallet!=null){
            resultCode.setCode(200);
            resultCode.setData(wallet);
        }else {
            resultCode.setCode(200);
        }
        return resultCode;
    }

    /**
     * 查询提现记录
     * @param token
     * @return
     */
    @RequestMapping("/queryWithdrawDeposit")
    public ResultCode queryWithdrawDeposit(@RequestHeader String token){
        List<PoboNotify> poboNotifies = walletService.queryWithdrawDeposit(token);
        ResultCode resultCode = new ResultCode();
        if (poboNotifies!=null && poboNotifies.size()>0){
            resultCode.setCode(200);
            resultCode.setData(poboNotifies);
        }else {
            resultCode.setCode(200);
            resultCode.setMsg("商户不存在");
        }
        return resultCode;
    }

    /**
     * 提现
     * @param merchant
     * @return
     */
    @RequestMapping("/cashWithdrawal")
    public ResultCode factoryGetMoney(@RequestBody Merchant merchant, @RequestHeader String token) {
        merchant.setToken(token);
        ResultCode code = walletService.factoryGetMoney(merchant);
        return code;
    }

    /**
     * 查询交易记录
     * @param token
     * @return
     */
    @RequestMapping("/queryTransaction")
    public ResultCode queryTransaction(@RequestHeader String token,Integer type){
        List<MerchantAmountLog> memberAmountLogs = walletService.queryTransaction(token,type);
        ResultCode resultCode = new ResultCode();
        if (memberAmountLogs!=null && memberAmountLogs.size()>0){
            resultCode.setCode(200);
            resultCode.setData(memberAmountLogs);
        }else {
            resultCode.setCode(200);
            resultCode.setMsg("没有交易记录");
        }
        return resultCode;
    }

}
