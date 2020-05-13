package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.Commodity;
import com.xinmintx.agent.model.CommodityExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommodityMapper {
    long countByExample(CommodityExample example);

    int deleteByExample(CommodityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    List<Commodity> selectByExampleWithRowbounds(CommodityExample example, RowBounds rowBounds);

    List<Commodity> selectByExample(CommodityExample example);

    Commodity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Commodity record, @Param("example") CommodityExample example);

    int updateByExample(@Param("record") Commodity record, @Param("example") CommodityExample example);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);
}