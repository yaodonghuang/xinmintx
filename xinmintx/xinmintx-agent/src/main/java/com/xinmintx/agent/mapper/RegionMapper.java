package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.Region;
import com.xinmintx.agent.model.RegionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RegionMapper {
    long countByExample(RegionExample example);

    int deleteByExample(RegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    List<Region> selectByExampleWithRowbounds(RegionExample example, RowBounds rowBounds);

    List<Region> selectByExample(String example);

    Region selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Region record, @Param("example") RegionExample example);

    int updateByExample(@Param("record") Region record, @Param("example") RegionExample example);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
}