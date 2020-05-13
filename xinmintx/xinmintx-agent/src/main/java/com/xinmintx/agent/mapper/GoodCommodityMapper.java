package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface GoodCommodityMapper {

    @Select("SELECT * FROM commodity a WHERE  a.commodity_name=#{name}")
    Commodity selectGoodCommodity(String name);

    @Select("SELECT b.commodity_typology_id  FROM goods_order a LEFT JOIN goods_order_detail b on a.id = b.order_id WHERE a.u_order_id = #{id}")
    int getCommodityTypology(Integer id);
}
