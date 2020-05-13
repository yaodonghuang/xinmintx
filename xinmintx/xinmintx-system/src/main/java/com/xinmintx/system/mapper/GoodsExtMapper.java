package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.Goods;
import com.xinmintx.system.domain.GoodsBean;
import com.xinmintx.system.domain.GoodsExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsExtMapper {

    List<GoodsExt> selectByCategoryCode(Goods goods);

}
