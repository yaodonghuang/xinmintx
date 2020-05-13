package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.GoodsSku;
import com.xinmintx.system.domain.GoodsSkuExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface GoodsSkuMapper {
    long countByExample(GoodsSkuExample example);

    int deleteByExample(GoodsSkuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSku record);

    int insertSelective(GoodsSku record);

    List<GoodsSku> selectByExampleWithRowbounds(GoodsSkuExample example, RowBounds rowBounds);

    List<GoodsSku> selectByExample(GoodsSkuExample example);

    GoodsSku selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSku record, @Param("example") GoodsSkuExample example);

    int updateByExample(@Param("record") GoodsSku record, @Param("example") GoodsSkuExample example);

    int updateByPrimaryKeySelective(GoodsSku record);

    int updateByPrimaryKey(GoodsSku record);

    void deleteById(Integer id);

    String selectSpecValueId(Integer id);
}