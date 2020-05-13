package com.xinmintx.business.mapper;

import com.xinmintx.business.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BusinessMapper {
    @Select("select * from goods")
    List<Goods> selectGoods();
}
