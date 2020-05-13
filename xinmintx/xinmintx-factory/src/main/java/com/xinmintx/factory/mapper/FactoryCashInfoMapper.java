package com.xinmintx.factory.mapper;

import com.xinmintx.factory.model.FactoryCashInfo;
import com.xinmintx.factory.model.FactoryCashInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface FactoryCashInfoMapper {
    long countByExample(FactoryCashInfoExample example);

    int deleteByExample(FactoryCashInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FactoryCashInfo record);

    int insertSelective(FactoryCashInfo record);

    List<FactoryCashInfo> selectByExampleWithRowbounds(FactoryCashInfoExample example, RowBounds rowBounds);

    List<FactoryCashInfo> selectByExample(FactoryCashInfoExample example);

    FactoryCashInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FactoryCashInfo record, @Param("example") FactoryCashInfoExample example);

    int updateByExample(@Param("record") FactoryCashInfo record, @Param("example") FactoryCashInfoExample example);

    int updateByPrimaryKeySelective(FactoryCashInfo record);

    int updateByPrimaryKey(FactoryCashInfo record);
}