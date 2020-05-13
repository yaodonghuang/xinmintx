package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.exception.BaseException;
import com.xinmintx.agent.exception.errorCodeStants.ErrorCodeConStants;
import com.xinmintx.agent.mapper.AgentUserProfitamountMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.model.AgentUserProfitamount;
import com.xinmintx.agent.model.AgentUserProfitamountExample;
import com.xinmintx.agent.model.UserExample;
import com.xinmintx.agent.service.BossSetProfigAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BossSetProfigAmountServiceImpl implements BossSetProfigAmountService {

    @Autowired
    private AgentUserProfitamountMapper agentUserProfitamountMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public ResultCode queryUserByPhone(String cellphone) {
        ResultCode resultCode = new ResultCode();
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andCellphoneEqualTo(cellphone);
        List<User> user = userMapper.selectByExample(example);
        if (user != null && user.size()>0){
            resultCode.setCode(200);
            resultCode.setMsg("查询成功");
            resultCode.setData(user.get(0));
        }else {
            resultCode.setCode(200);
            resultCode.setMsg("没有信息");
        }
        return resultCode;
    }
    @Override
    public AgentUserProfitamount getAgentUserProfitAmountByUserIdAndByCardType(Integer userId, Integer cardType) {
        return getAgentUserProfitamount( userId,  cardType,  -1);
    }

    @Override
    public AgentUserProfitamount getAgentUserProfitAmountByUserIdAndByCardType(Integer userId, Integer cardType, Integer starts) {
        return getAgentUserProfitamount( userId,  cardType,  starts);
    }

    private AgentUserProfitamount getAgentUserProfitamount(Integer userId, Integer cardType, Integer starts){
        //参数过滤
        if ( null == userId || 0 == userId || null == cardType || 0 == cardType){
            throw new BaseException(ErrorCodeConStants.PARAM_ALREADY_NONENTITY);
        }
        AgentUserProfitamountExample agentUserProfitamountExample = new AgentUserProfitamountExample();
        AgentUserProfitamountExample.Criteria criteria = agentUserProfitamountExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        if (-1 == starts){
            criteria.andStartsEqualTo(1);
        }else {
            criteria.andStartsEqualTo(starts);
        }
        criteria.andCardTypeEqualTo(String.valueOf(cardType));
        try {
            List<AgentUserProfitamount> agentUserProfitamounts = agentUserProfitamountMapper.selectByExample(agentUserProfitamountExample);
            if (agentUserProfitamounts==null || agentUserProfitamounts.size()==0){
                return null;
            }
            return agentUserProfitamounts.get(0);
        } catch (BaseException e) {
            throw new BaseException(ErrorCodeConStants.ABNORMAL_SQL);
        }
    }

    @Override
    public void updateUserProfitAmount(AgentUserProfitamount agentUserProfitamount) {
        agentUserProfitamountMapper.updateByPrimaryKey(agentUserProfitamount);
    }

    @Override
    public void insertUserProfitAmount(AgentUserProfitamount agentUserProfitamount) {
        agentUserProfitamountMapper.insertSelective(agentUserProfitamount);
    }
}
