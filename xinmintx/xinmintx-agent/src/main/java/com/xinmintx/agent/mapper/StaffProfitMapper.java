package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.StaffProfit;
import com.xinmintx.agent.model.StaffProfitExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface StaffProfitMapper {
    long countByExample(StaffProfitExample example);

    int deleteByExample(StaffProfitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StaffProfit record);

    int insertSelective(StaffProfit record);

    List<StaffProfit> selectByExampleWithRowbounds(StaffProfitExample example, RowBounds rowBounds);

    List<StaffProfit> selectByExample(StaffProfitExample example);

    StaffProfit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StaffProfit record, @Param("example") StaffProfitExample example);

    int updateByExample(@Param("record") StaffProfit record, @Param("example") StaffProfitExample example);

    int updateByPrimaryKeySelective(StaffProfit record);

    int updateByPrimaryKey(StaffProfit record);
}