package com.xinmintx.agent.controller;

import com.xinmintx.agent.model.GoodPtcode;
import com.xinmintx.agent.service.AgentPtBenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("agent/agentPtBenefit")
public class AgentPtBenefitController {

    @Autowired
    private AgentPtBenefitService agentPtBenefitService;

    /**
     * 代理 拼团所得费用
     * @param regimentalId 团长
     * @param ptUserId 中间表
     * @return
     */
    @RequestMapping("/AgentGroupDistribution")
    @ResponseBody
    public String AgentGroupDistribution(@RequestParam("regimentalId") Integer regimentalId, @RequestParam("ptUserId")Integer ptUserId){
        GoodPtcode goodPtcode = agentPtBenefitService.selectgoodPtcode(regimentalId);
        if(goodPtcode != null){
            return agentPtBenefitService.AgentGroupDistribution(regimentalId, ptUserId);
        }
        return "error";
    };


}
