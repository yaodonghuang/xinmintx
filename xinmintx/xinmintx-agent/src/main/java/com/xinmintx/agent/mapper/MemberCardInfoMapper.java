package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.MemberCardInfo;
import com.xinmintx.agent.model.MemberCardInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface MemberCardInfoMapper {
    long countByExample(MemberCardInfoExample example);

    int deleteByExample(MemberCardInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberCardInfo record);

    int insertSelective(MemberCardInfo record);

    List<MemberCardInfo> selectByExampleWithRowbounds(MemberCardInfoExample example, RowBounds rowBounds);

    List<MemberCardInfo> selectByExample(MemberCardInfoExample example);

    MemberCardInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberCardInfo record, @Param("example") MemberCardInfoExample example);

    int updateByExample(@Param("record") MemberCardInfo record, @Param("example") MemberCardInfoExample example);

    int updateByPrimaryKeySelective(MemberCardInfo record);

    int updateByPrimaryKey(MemberCardInfo record);
}