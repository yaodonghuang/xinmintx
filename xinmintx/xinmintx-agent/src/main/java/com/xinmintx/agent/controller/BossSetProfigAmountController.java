package com.xinmintx.agent.controller;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.AgentUserProfitamount;
import com.xinmintx.agent.service.BossSetProfigAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/bossSetProfigAmount")
public class BossSetProfigAmountController {

    @Autowired
    private BossSetProfigAmountService bossSetProfigAmountService;

    /**
     * 根据手机号查询
     * @param cellphone
     * @return
     */
    @PostMapping("/queryUser")
    public String queryUser(@RequestParam("cellphone") String cellphone, Model model){
        ResultCode resultCode = new ResultCode();
        resultCode = bossSetProfigAmountService.queryUserByPhone(cellphone);
        model.addAttribute("user",resultCode.getData());
        return "bossSetCardProfitAmount";
    }


    /**
     * boss设置E卡分润
     * @param userId
     * @param cardType
     * @param proportion
     * @param model
     * @return
     */
    @PostMapping("/toUpgradeTheUser")
    @ResponseBody
    public ResultCode toUpgradeTheUser(@RequestParam("userId")Integer userId ,@RequestParam("cardType")Integer cardType,@RequestParam("proportion")Integer proportion,Model model){
        ResultCode resultCode = new ResultCode();
        AgentUserProfitamount agentUserProfitAmountByUserIdAndByCardType = bossSetProfigAmountService.getAgentUserProfitAmountByUserIdAndByCardType(userId, cardType);
        if(agentUserProfitAmountByUserIdAndByCardType != null){
            agentUserProfitAmountByUserIdAndByCardType.setAgentProfitAmount(String.valueOf(proportion));
            bossSetProfigAmountService.updateUserProfitAmount(agentUserProfitAmountByUserIdAndByCardType);
        }else {
            AgentUserProfitamount agentUserProfitamount = new AgentUserProfitamount();
            agentUserProfitamount.setCardType(String.valueOf(cardType));
            agentUserProfitamount.setUserId(userId);
            agentUserProfitamount.setAgentProfitAmount(String.valueOf(proportion));
            agentUserProfitamount.setStarts(1);
            bossSetProfigAmountService.insertUserProfitAmount(agentUserProfitamount);
        }
        resultCode.setCode(200);
        resultCode.setMsg("设置成功");
        return resultCode;
    }

}
