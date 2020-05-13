package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.UnitPhoto;
import com.xinmintx.agent.model.UnitPhotoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UnitPhotoMapper {
    long countByExample(UnitPhotoExample example);

    int deleteByExample(UnitPhotoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UnitPhoto record);

    int insertSelective(UnitPhoto record);

    List<UnitPhoto> selectByExampleWithRowbounds(UnitPhotoExample example, RowBounds rowBounds);

    List<UnitPhoto> selectByExample(UnitPhotoExample example);

    UnitPhoto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UnitPhoto record, @Param("example") UnitPhotoExample example);

    int updateByExample(@Param("record") UnitPhoto record, @Param("example") UnitPhotoExample example);

    int updateByPrimaryKeySelective(UnitPhoto record);

    int updateByPrimaryKey(UnitPhoto record);
}