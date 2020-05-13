package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.DepositInform;
import com.xinmintx.agent.model.PayNotify;
import com.xinmintx.agent.model.WithdrawDeposit;
import com.xinmintx.agent.service.*;
import com.xinmintx.agent.util.XMLConverUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 微信支付
 *
 **/
@Slf4j
@RestController
@RequestMapping("/api/wx")
public class WxPayController {
    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private NoteCodeService noteCodeService;

    @Autowired
    private QueryOpenService queryOpenService;

    @Autowired
    private IDCardVerifyService idCardVerifyService;

    @Autowired
    private RelieveBindService bindService;

    @Autowired
    private SetPwdService setPwdService;

    @Autowired
    private QueryTiedCardService tiedCardService;

    @Autowired
    private TopUpInformService topUpInformService;

    @Autowired
    private RechargeService rechargeService;

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private WithdrawCallBackService withdrawCallBackService;

    @Autowired
    private ShoppingUnitService shoppingUnitService;

    @Autowired
    private QueryOrderService queryOrderService;

    /**
     * 开户
     * @param paramMap
     */
    @PostMapping("/regist")
    public ResultCode appPayment(@RequestParam Map<String,String> paramMap){
         return userAccountService.userAccount(paramMap);
    }

    /**
     *短信
     * @param mobile
     */
    @PostMapping("/note")
    public ResultCode note(@RequestParam String mobile){
        return noteCodeService.note(mobile);
    }

    /**
     * 绑卡
     * @param paramMap
     */
    @PostMapping("/tiedCard")
    public ResultCode tiedCard(@RequestParam Map<String,String> paramMap){
        ResultCode resultCode = new ResultCode();
        resultCode.setData(shoppingUnitService.tiedCard(paramMap));
        return resultCode;
    }

    /**
     * 商户查询开户审核接口
     * @param merBusRegNum
     */
    @PostMapping("/queryOpen")
    public ResultCode queryOpen(@RequestParam String merBusRegNum){
        ResultCode resultCode = new ResultCode();
        resultCode.setData(queryOpenService.queryOpen(merBusRegNum));
        return resultCode;
    }

    /**
     * 用户上传身份证
     * @param paramMap
     */
    @PostMapping("/IDCardVerify")
    public ResultCode IDCardVerify(@RequestParam Map<String,String> paramMap){
        ResultCode resultCode = new ResultCode();
        resultCode.setData(idCardVerifyService.IDCardVerify(paramMap));
        return resultCode;
    }

    /**
     * 解除绑卡
     * @param paramMap
     */
    @PostMapping("/relieveBind")
    public ResultCode relieveBind(@RequestParam Map<String,String> paramMap){
        ResultCode resultCode = new ResultCode();
        resultCode.setData(bindService.relieveBind(paramMap));
        return resultCode;
    }

    /**
     *设置密码
     * @param paramMap
     */
    @PostMapping("/setPwd")
    public ResultCode setPwd(@RequestParam Map<String,String> paramMap){
        ResultCode resultCode = new ResultCode();
        resultCode.setData(setPwdService.setPwd(paramMap));
        return resultCode;
    }

    /**
     * 查询绑卡
     * @param paramMap
     */
    @PostMapping("/queryTiedCard")
    public ResultCode queryTiedCard(@RequestParam Map<String,String> paramMap){

        return tiedCardService.queryTiedCard(paramMap);
    }

    /**
     * 充值
     * @param paramMap
     * @return
     */
    @PostMapping("/recharge")
    public ResultCode recharge(@RequestParam Map<String,String> paramMap){
        return rechargeService.recharge(paramMap);
    }

    /**
     * 支付充值回调
     * @param pn
     */
    @PostMapping("/topUpCallBack")
    public Boolean topUpCallBack(@RequestParam PayNotify pn){
        return topUpInformService.topUpInform(pn);
    }

//    /**
//     * 充值查询
//     * @param paramMap
//     */
//    @PostMapping("/topUpInform")
//    public void topUpInform(@RequestParam Map<String,String> paramMap){
//        topUpInformService.topUpInform(paramMap);
//    }

    /**
     * purchase_price
     * @param paramMap
     * @return
     */
    @PostMapping("/withdraw")
    public ResultCode withdraw(@RequestParam Map<String,String> paramMap){
        return  withdrawService.withdraw(paramMap);
    }

    /**
     * 提现回调
     * @param withdrawDeposit
     */
    @PostMapping("/withdrawCallBack")
    public Boolean withdrawCallBack(@RequestParam WithdrawDeposit withdrawDeposit){
        return withdrawCallBackService.checkInfo(withdrawDeposit);
    }

    /**
     * 订单查询
     * @param paramMap
     * @return
     */
    @PostMapping("/queryOrder")
    public ResultCode queryOrder(@RequestParam Map<String,String> paramMap){
        return queryOrderService.queryOrder(paramMap);
    }



}
