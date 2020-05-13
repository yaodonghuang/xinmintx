package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.AgentRecommends;
import java.util.List;

/**
 * 代理升级规则Mapper接口
 * 
 * @author xinmintx
 * @date 2020-02-05
 */
public interface AgentRecommendsMapper 
{
    /**
     * 查询代理升级规则
     * 
     * @param id 代理升级规则ID
     * @return 代理升级规则
     */
    public AgentRecommends selectAgentRecommendsById(Long id);

    /**
     * 查询代理升级规则列表
     * 
     * @param agentRecommends 代理升级规则
     * @return 代理升级规则集合
     */
    public List<AgentRecommends> selectAgentRecommendsList(AgentRecommends agentRecommends);

    /**
     * 新增代理升级规则
     * 
     * @param agentRecommends 代理升级规则
     * @return 结果
     */
    public int insertAgentRecommends(AgentRecommends agentRecommends);

    /**
     * 修改代理升级规则
     * 
     * @param agentRecommends 代理升级规则
     * @return 结果
     */
    public int updateAgentRecommends(AgentRecommends agentRecommends);

    /**
     * 删除代理升级规则
     * 
     * @param id 代理升级规则ID
     * @return 结果
     */
    public int deleteAgentRecommendsById(Long id);

    /**
     * 批量删除代理升级规则
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAgentRecommendsByIds(String[] ids);
}
