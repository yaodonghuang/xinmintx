package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.GoodPtgood;
import com.xinmintx.agent.model.GoodPtgoodExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface GoodPtgoodMapper {
    long countByExample(GoodPtgoodExample example);

    int deleteByExample(GoodPtgoodExample example);

    int deleteByPrimaryKey(Integer ptgoodsId);

    int insert(GoodPtgood record);

    int insertSelective(GoodPtgood record);

    List<GoodPtgood> selectByExampleWithRowbounds(GoodPtgoodExample example, RowBounds rowBounds);

    List<GoodPtgood> selectByExample(GoodPtgoodExample example);

    GoodPtgood selectByPrimaryKey(Integer ptgoodsId);

    int updateByExampleSelective(@Param("record") GoodPtgood record, @Param("example") GoodPtgoodExample example);

    int updateByExample(@Param("record") GoodPtgood record, @Param("example") GoodPtgoodExample example);

    int updateByPrimaryKeySelective(GoodPtgood record);

    int updateByPrimaryKey(GoodPtgood record);

}