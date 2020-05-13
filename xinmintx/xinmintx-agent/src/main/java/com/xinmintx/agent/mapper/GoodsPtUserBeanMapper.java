package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.GoodsPtUser;
import com.xinmintx.agent.model.GoodsPtUserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface GoodsPtUserBeanMapper {

    GoodsPtUser selectPtUser(@Param("userId") int userId,@Param("ptgoodsId") Integer ptgoodsId,@Param("shopingId") Integer shopingId);
}