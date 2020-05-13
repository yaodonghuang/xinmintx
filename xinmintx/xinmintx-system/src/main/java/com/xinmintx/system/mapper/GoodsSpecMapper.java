package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.GoodsSpec;
import com.xinmintx.system.domain.GoodsSpecExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface GoodsSpecMapper {
    long countByExample(GoodsSpecExample example);

    int deleteByExample(GoodsSpecExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpec record);

    int insertSelective(GoodsSpec record);

    List<GoodsSpec> selectByExampleWithRowbounds(GoodsSpecExample example, RowBounds rowBounds);

    List<GoodsSpec> selectByExample(GoodsSpecExample example);

    GoodsSpec selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSpec record, @Param("example") GoodsSpecExample example);

    int updateByExample(@Param("record") GoodsSpec record, @Param("example") GoodsSpecExample example);

    int updateByPrimaryKeySelective(GoodsSpec record);

    int updateByPrimaryKey(GoodsSpec record);
}