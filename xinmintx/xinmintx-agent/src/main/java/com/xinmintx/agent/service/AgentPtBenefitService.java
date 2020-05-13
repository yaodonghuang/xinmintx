package com.xinmintx.agent.service;
import com.xinmintx.agent.model.GoodPtcode;
public interface AgentPtBenefitService {

    public String AgentGroupDistribution(Integer regimentalId,Integer ptUserId);

    GoodPtcode selectgoodPtcode(Integer regimentalId);
}
