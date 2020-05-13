package com.xinmintx.system.mapper;


import com.xinmintx.system.domain.MemberCardOrderDetail;
import com.xinmintx.system.domain.MemberCardOrderDetailExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface MemberCardOrderDetailMapper {
    long countByExample(MemberCardOrderDetailExample example);

    int deleteByExample(MemberCardOrderDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberCardOrderDetail record);

    int insertSelective(MemberCardOrderDetail record);

    List<MemberCardOrderDetail> selectByExampleWithRowbounds(MemberCardOrderDetailExample example, RowBounds rowBounds);

    List<MemberCardOrderDetail> selectByExample(MemberCardOrderDetailExample example);

    MemberCardOrderDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberCardOrderDetail record, @Param("example") MemberCardOrderDetailExample example);

    int updateByExample(@Param("record") MemberCardOrderDetail record, @Param("example") MemberCardOrderDetailExample example);

    int updateByPrimaryKeySelective(MemberCardOrderDetail record);

    int updateByPrimaryKey(MemberCardOrderDetail record);
}