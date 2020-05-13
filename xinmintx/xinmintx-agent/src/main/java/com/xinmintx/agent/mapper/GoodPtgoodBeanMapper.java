package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.GoodPtgood;
import com.xinmintx.agent.model.GoodPtgoodExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface GoodPtgoodBeanMapper {

    GoodPtgood selectGoodPt(@Param("people") Integer people, @Param("shopingId") Integer shopingId);
}