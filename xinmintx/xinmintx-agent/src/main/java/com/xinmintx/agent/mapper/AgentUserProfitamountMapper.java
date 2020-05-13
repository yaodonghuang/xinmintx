package com.xinmintx.agent.mapper;
import com.xinmintx.agent.model.AgentUserProfitamount;
import com.xinmintx.agent.model.AgentUserProfitamountExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface AgentUserProfitamountMapper {
    long countByExample(AgentUserProfitamountExample example);

    int deleteByExample(AgentUserProfitamountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AgentUserProfitamount record);

    int insertSelective(AgentUserProfitamount record);

    List<AgentUserProfitamount> selectByExampleWithRowbounds(AgentUserProfitamountExample example, RowBounds rowBounds);

    List<AgentUserProfitamount> selectByExample(AgentUserProfitamountExample example);

    AgentUserProfitamount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AgentUserProfitamount record, @Param("example") AgentUserProfitamountExample example);

    int updateByExample(@Param("record") AgentUserProfitamount record, @Param("example") AgentUserProfitamountExample example);

    int updateByPrimaryKeySelective(AgentUserProfitamount record);

    int updateByPrimaryKey(AgentUserProfitamount record);
}