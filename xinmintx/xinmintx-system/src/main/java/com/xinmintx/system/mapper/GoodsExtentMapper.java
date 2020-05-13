package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.GoodsBean;
import com.xinmintx.system.domain.GoodsExtent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsExtentMapper {
    GoodsExtent selectGoodsById(Long id);

    List<GoodsBean> selectType(@Param("id") Integer id, @Param("typeId") Integer typeId);

    Integer selectTypeId(Integer id);
}
