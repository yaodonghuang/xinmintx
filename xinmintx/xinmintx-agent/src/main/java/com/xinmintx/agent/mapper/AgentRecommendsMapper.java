package com.xinmintx.agent.mapper;
import com.xinmintx.agent.model.AgentRecommends;
import com.xinmintx.agent.model.AgentRecommendsExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface AgentRecommendsMapper {
    long countByExample(AgentRecommendsExample example);

    int deleteByExample(AgentRecommendsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AgentRecommends record);

    int insertSelective(AgentRecommends record);

    List<AgentRecommends> selectByExampleWithRowbounds(AgentRecommendsExample example, RowBounds rowBounds);

    List<AgentRecommends> selectByExample(AgentRecommendsExample example);

    AgentRecommends selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AgentRecommends record, @Param("example") AgentRecommendsExample example);

    int updateByExample(@Param("record") AgentRecommends record, @Param("example") AgentRecommendsExample example);

    int updateByPrimaryKeySelective(AgentRecommends record);

    int updateByPrimaryKey(AgentRecommends record);
}