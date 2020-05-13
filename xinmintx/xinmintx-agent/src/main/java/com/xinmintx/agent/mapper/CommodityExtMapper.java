package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.CommodityExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface CommodityExtMapper {

    List<CommodityExt> selectGoods(@Param("name") String name);

    CommodityExt getGoodById(@Param("id") Integer id);

    String selectUrl(@Param("id") Integer id);

    String getParameter(Integer id);

    List<CommodityExt> getGoodType(@Param("id") Integer id);

}
