package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.CommodityTypology;
import com.xinmintx.agent.model.CommodityTypologyExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface CommodityTypologyMapper {
    long countByExample(CommodityTypologyExample example);

    int deleteByExample(CommodityTypologyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommodityTypology record);

    int insertSelective(CommodityTypology record);

    List<CommodityTypology> selectByExampleWithRowbounds(CommodityTypologyExample example, RowBounds rowBounds);

    List<CommodityTypology> selectByExample(CommodityTypologyExample example);

    CommodityTypology selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommodityTypology record, @Param("example") CommodityTypologyExample example);

    int updateByExample(@Param("record") CommodityTypology record, @Param("example") CommodityTypologyExample example);

    int updateByPrimaryKeySelective(CommodityTypology record);

    int updateByPrimaryKey(CommodityTypology record);


    List<CommodityTypology> showType(Integer id);
}