package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.CommodityDetails;
import com.xinmintx.agent.model.CommodityDetailsExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface CommodityDetailsMapper {
    long countByExample(CommodityDetailsExample example);

    int deleteByExample(CommodityDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommodityDetails record);

    int insertSelective(CommodityDetails record);

    List<CommodityDetails> selectByExampleWithRowbounds(CommodityDetailsExample example, RowBounds rowBounds);

    List<CommodityDetails> selectByExample(CommodityDetailsExample example);

    CommodityDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommodityDetails record, @Param("example") CommodityDetailsExample example);

    int updateByExample(@Param("record") CommodityDetails record, @Param("example") CommodityDetailsExample example);

    int updateByPrimaryKeySelective(CommodityDetails record);

    int updateByPrimaryKey(CommodityDetails record);
}