package com.xinmintx.agent.mapper;
import com.xinmintx.agent.model.Factory;
import com.xinmintx.agent.model.FactoryExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface FactoryMapper {
    long countByExample(FactoryExample example);

    int deleteByExample(FactoryExample example);

    int deleteByPrimaryKey(Long factoryId);

    int insert(Factory record);

    int insertSelective(Factory record);

    List<Factory> selectByExampleWithRowbounds(FactoryExample example, RowBounds rowBounds);

    List<Factory> selectByExample(FactoryExample example);

    Factory selectByPrimaryKey(Long factoryId);

    int updateByExampleSelective(@Param("record") Factory record, @Param("example") FactoryExample example);

    int updateByExample(@Param("record") Factory record, @Param("example") FactoryExample example);

    int updateByPrimaryKeySelective(Factory record);

    int updateByPrimaryKey(Factory record);
}