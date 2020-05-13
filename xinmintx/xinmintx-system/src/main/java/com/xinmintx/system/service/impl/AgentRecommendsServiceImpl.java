package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.AgentRecommendsMapper;
import com.xinmintx.system.domain.AgentRecommends;
import com.xinmintx.system.service.IAgentRecommendsService;
import com.xinmintx.common.core.text.Convert;

/**
 * 代理升级规则Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-02-05
 */
@Service
public class AgentRecommendsServiceImpl implements IAgentRecommendsService 
{
    @Autowired
    private AgentRecommendsMapper agentRecommendsMapper;

    /**
     * 查询代理升级规则
     * 
     * @param id 代理升级规则ID
     * @return 代理升级规则
     */
    @Override
    public AgentRecommends selectAgentRecommendsById(Long id)
    {
        return agentRecommendsMapper.selectAgentRecommendsById(id);
    }

    /**
     * 查询代理升级规则列表
     * 
     * @param agentRecommends 代理升级规则
     * @return 代理升级规则
     */
    @Override
    public List<AgentRecommends> selectAgentRecommendsList(AgentRecommends agentRecommends)
    {
        return agentRecommendsMapper.selectAgentRecommendsList(agentRecommends);
    }

    /**
     * 新增代理升级规则
     * 
     * @param agentRecommends 代理升级规则
     * @return 结果
     */
    @Override
    public int insertAgentRecommends(AgentRecommends agentRecommends)
    {
        return agentRecommendsMapper.insertAgentRecommends(agentRecommends);
    }

    /**
     * 修改代理升级规则
     * 
     * @param agentRecommends 代理升级规则
     * @return 结果
     */
    @Override
    public int updateAgentRecommends(AgentRecommends agentRecommends)
    {
        return agentRecommendsMapper.updateAgentRecommends(agentRecommends);
    }

    /**
     * 删除代理升级规则对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAgentRecommendsByIds(String ids)
    {
        return agentRecommendsMapper.deleteAgentRecommendsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除代理升级规则信息
     * 
     * @param id 代理升级规则ID
     * @return 结果
     */
    @Override
    public int deleteAgentRecommendsById(Long id)
    {
        return agentRecommendsMapper.deleteAgentRecommendsById(id);
    }
}
