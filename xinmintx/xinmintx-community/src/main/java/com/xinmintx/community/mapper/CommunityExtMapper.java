package com.xinmintx.community.mapper;
import com.xinmintx.community.model.CommunityExt;
import com.xinmintx.community.model.CommunityExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommunityExtMapper {
    long countByExample(CommunityExample example);

    int deleteByExample(CommunityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommunityExt record);

    int insertSelective(CommunityExt record);

    List<CommunityExt> selectByExampleWithRowbounds(CommunityExample example, RowBounds rowBounds);

    List<CommunityExt> selectByExample(CommunityExample example);

    CommunityExt selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommunityExt record, @Param("example") CommunityExample example);

    int updateByExample(@Param("record") CommunityExt record, @Param("example") CommunityExample example);

    int updateByPrimaryKeySelective(CommunityExt record);

    int updateByPrimaryKey(CommunityExt record);

}