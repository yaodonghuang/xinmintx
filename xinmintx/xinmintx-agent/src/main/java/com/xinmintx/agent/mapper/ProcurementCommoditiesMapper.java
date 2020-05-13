package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.ProcurementCommodities;
import com.xinmintx.agent.model.ProcurementCommoditiesExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ProcurementCommoditiesMapper {
    long countByExample(ProcurementCommoditiesExample example);

    int deleteByExample(ProcurementCommoditiesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProcurementCommodities record);

    int insertSelective(ProcurementCommodities record);

    List<ProcurementCommodities> selectByExampleWithRowbounds(ProcurementCommoditiesExample example, RowBounds rowBounds);

    List<ProcurementCommodities> selectByExample(ProcurementCommoditiesExample example);

    ProcurementCommodities selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProcurementCommodities record, @Param("example") ProcurementCommoditiesExample example);

    int updateByExample(@Param("record") ProcurementCommodities record, @Param("example") ProcurementCommoditiesExample example);

    int updateByPrimaryKeySelective(ProcurementCommodities record);

    int updateByPrimaryKey(ProcurementCommodities record);
}