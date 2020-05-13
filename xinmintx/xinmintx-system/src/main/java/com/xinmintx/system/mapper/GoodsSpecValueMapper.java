package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.GoodsSpecValue;
import com.xinmintx.system.domain.GoodsSpecValueExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface GoodsSpecValueMapper {
    long countByExample(GoodsSpecValueExample example);

    int deleteByExample(GoodsSpecValueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpecValue record);

    int insertSelective(GoodsSpecValue record);

    List<GoodsSpecValue> selectByExampleWithRowbounds(GoodsSpecValueExample example, RowBounds rowBounds);

    List<GoodsSpecValue> selectByExample(GoodsSpecValueExample example);

    GoodsSpecValue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSpecValue record, @Param("example") GoodsSpecValueExample example);

    int updateByExample(@Param("record") GoodsSpecValue record, @Param("example") GoodsSpecValueExample example);

    int updateByPrimaryKeySelective(GoodsSpecValue record);

    int updateByPrimaryKey(GoodsSpecValue record);

    GoodsSpecValue selectById(String specId);

    String selectValue (Integer id);
}