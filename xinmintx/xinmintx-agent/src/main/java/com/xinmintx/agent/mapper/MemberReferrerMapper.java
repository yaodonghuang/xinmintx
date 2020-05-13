package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.MemberReferrer;
import com.xinmintx.agent.model.MemberReferrerExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface MemberReferrerMapper {
    long countByExample(MemberReferrerExample example);

    int deleteByExample(MemberReferrerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberReferrer record);

    int insertSelective(MemberReferrer record);

    List<MemberReferrer> selectByExampleWithRowbounds(MemberReferrerExample example, RowBounds rowBounds);

    List<MemberReferrer> selectByExample(MemberReferrerExample example);

    MemberReferrer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberReferrer record, @Param("example") MemberReferrerExample example);

    int updateByExample(@Param("record") MemberReferrer record, @Param("example") MemberReferrerExample example);

    int updateByPrimaryKeySelective(MemberReferrer record);

    int updateByPrimaryKey(MemberReferrer record);
}