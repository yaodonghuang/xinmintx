package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.Profit;
import com.xinmintx.agent.model.ProfitExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ProfitMapper {
    long countByExample(ProfitExample example);

    int deleteByExample(ProfitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Profit record);

    int insertSelective(Profit record);

    List<Profit> selectByExampleWithRowbounds(ProfitExample example, RowBounds rowBounds);

    List<Profit> selectByExample(ProfitExample example);

    Profit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Profit record, @Param("example") ProfitExample example);

    int updateByExample(@Param("record") Profit record, @Param("example") ProfitExample example);

    int updateByPrimaryKeySelective(Profit record);

    int updateByPrimaryKey(Profit record);
}