package com.xinmintx.agent.service;

import com.xinmintx.agent.common.ResultCode;

import com.xinmintx.agent.model.AgentUserProfitamount;


public interface BossSetProfigAmountService {
    ResultCode queryUserByPhone(String cellphone);

    void updateUserProfitAmount(AgentUserProfitamount agentUserProfitamount);


    void insertUserProfitAmount(AgentUserProfitamount agentUserProfitamount);

    /**
     * @author: create by hjh
     * @time: 2020/3/13 11:02
     * @Description: 根据 用户id 和 卡的类型查询是否存在
     * @param userId 用户 id
     * @param cardType 卡的类型
     * @return: com.xinmintx.agent.model.AgentUserProfitamount
     */
    AgentUserProfitamount getAgentUserProfitAmountByUserIdAndByCardType(Integer userId , Integer cardType);

    /**
     * @author: create by hjh
     * @time: 2020/3/13 17:40
     * @Description: 根据 用户id 和 卡的类型 卡的状态 查询是否存在
     * @param userId 用户 id
     * @param cardType 卡的类型
     * @param starts 卡的状态
     * @return: com.xinmintx.agent.model.AgentUserProfitamount
     */
    AgentUserProfitamount getAgentUserProfitAmountByUserIdAndByCardType(Integer userId , Integer cardType ,Integer starts);

}
