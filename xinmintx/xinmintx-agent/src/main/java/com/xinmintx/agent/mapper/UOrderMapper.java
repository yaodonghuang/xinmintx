package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.UOrder;
import com.xinmintx.agent.model.UOrderExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface UOrderMapper {
    long countByExample(UOrderExample example);

    int deleteByExample(UOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UOrder record);

    int insertSelective(UOrder record);

    List<UOrder> selectByExampleWithRowbounds(UOrderExample example, RowBounds rowBounds);

    List<UOrder> selectByExample(UOrderExample example);

    UOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UOrder record, @Param("example") UOrderExample example);

    int updateByExample(@Param("record") UOrder record, @Param("example") UOrderExample example);

    int updateByPrimaryKeySelective(UOrder record);

    int updateByPrimaryKey(UOrder record);
}