package com.xinmintx.community.mapper;
import com.xinmintx.community.model.CommunityDeputy;
import com.xinmintx.community.model.CommunityDeputyExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommunityDeputyMapper {
    long countByExample(CommunityDeputyExample example);

    int deleteByExample(CommunityDeputyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CommunityDeputy record);

    int insertSelective(CommunityDeputy record);

    List<CommunityDeputy> selectByExampleWithRowbounds(CommunityDeputyExample example, RowBounds rowBounds);

    List<CommunityDeputy> selectByExample(CommunityDeputyExample example);

    CommunityDeputy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CommunityDeputy record, @Param("example") CommunityDeputyExample example);

    int updateByExample(@Param("record") CommunityDeputy record, @Param("example") CommunityDeputyExample example);

    int updateByPrimaryKeySelective(CommunityDeputy record);

    int updateByPrimary(CommunityDeputy record);

    int updateByPrimaryKey(CommunityDeputy record);
}