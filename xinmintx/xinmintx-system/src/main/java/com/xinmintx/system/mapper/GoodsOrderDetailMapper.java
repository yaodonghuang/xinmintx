package com.xinmintx.system.mapper;


import com.xinmintx.system.domain.GoodsOrderDetail;
import com.xinmintx.system.domain.GoodsOrderDetailExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface GoodsOrderDetailMapper {
    long countByExample(GoodsOrderDetailExample example);

    int deleteByExample(GoodsOrderDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsOrderDetail record);

    int insertSelective(GoodsOrderDetail record);

    List<GoodsOrderDetail> selectByExampleWithRowbounds(GoodsOrderDetailExample example, RowBounds rowBounds);

    List<GoodsOrderDetail> selectByExample(GoodsOrderDetailExample example);

    GoodsOrderDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsOrderDetail record, @Param("example") GoodsOrderDetailExample example);

    int updateByExample(@Param("record") GoodsOrderDetail record, @Param("example") GoodsOrderDetailExample example);

    int updateByPrimaryKeySelective(GoodsOrderDetail record);

    int updateByPrimaryKey(GoodsOrderDetail record);
}