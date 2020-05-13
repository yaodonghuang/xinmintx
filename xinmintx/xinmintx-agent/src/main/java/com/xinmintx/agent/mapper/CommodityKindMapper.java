package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.CommodityKind;
import com.xinmintx.agent.model.CommodityKindExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface CommodityKindMapper {
    long countByExample(CommodityKindExample example);

    int deleteByExample(CommodityKindExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommodityKind record);

    int insertSelective(CommodityKind record);

    List<CommodityKind> selectByExampleWithRowbounds(CommodityKindExample example, RowBounds rowBounds);

    List<CommodityKind> selectByExample(CommodityKindExample example);

    CommodityKind selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommodityKind record, @Param("example") CommodityKindExample example);

    int updateByExample(@Param("record") CommodityKind record, @Param("example") CommodityKindExample example);

    int updateByPrimaryKeySelective(CommodityKind record);

    int updateByPrimaryKey(CommodityKind record);

    List<CommodityKind> selectType(Integer id);
}