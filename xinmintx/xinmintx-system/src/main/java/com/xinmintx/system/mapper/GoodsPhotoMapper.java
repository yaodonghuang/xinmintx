package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.GoodsPhoto;
import com.xinmintx.system.domain.GoodsPhotoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface GoodsPhotoMapper {
    long countByExample(GoodsPhotoExample example);

    int deleteByExample(GoodsPhotoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsPhoto record);

    int insertSelective(GoodsPhoto record);

    List<GoodsPhoto> selectByExampleWithRowbounds(GoodsPhotoExample example, RowBounds rowBounds);

    List<GoodsPhoto> selectByExample(GoodsPhotoExample example);

    GoodsPhoto selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsPhoto record, @Param("example") GoodsPhotoExample example);

    int updateByExample(@Param("record") GoodsPhoto record, @Param("example") GoodsPhotoExample example);

    int updateByPrimaryKeySelective(GoodsPhoto record);

    int updateByPrimaryKey(GoodsPhoto record);

    String selectByKey(int parseInt);
}