package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.CommodityType;
import com.xinmintx.agent.model.CommodityTypeExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface CommodityTypeMapper {
    long countByExample(CommodityTypeExample example);

    int deleteByExample(CommodityTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommodityType record);

    int insertSelective(CommodityType record);

    List<CommodityType> selectByExampleWithRowbounds(CommodityTypeExample example, RowBounds rowBounds);

    List<CommodityType> selectByExample(CommodityTypeExample example);

    CommodityType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommodityType record, @Param("example") CommodityTypeExample example);

    int updateByExample(@Param("record") CommodityType record, @Param("example") CommodityTypeExample example);

    int updateByPrimaryKeySelective(CommodityType record);

    int updateByPrimaryKey(CommodityType record);
}