package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.RoleShare;
import com.xinmintx.agent.model.RoleShareExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RoleShareMapper {
    long countByExample(RoleShareExample example);

    int deleteByExample(RoleShareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleShare record);

    int insertSelective(RoleShare record);

    List<RoleShare> selectByExampleWithRowbounds(RoleShareExample example, RowBounds rowBounds);

    List<RoleShare> selectByExample(RoleShareExample example);

    RoleShare selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleShare record, @Param("example") RoleShareExample example);

    int updateByExample(@Param("record") RoleShare record, @Param("example") RoleShareExample example);

    int updateByPrimaryKeySelective(RoleShare record);

    int updateByPrimaryKey(RoleShare record);
}