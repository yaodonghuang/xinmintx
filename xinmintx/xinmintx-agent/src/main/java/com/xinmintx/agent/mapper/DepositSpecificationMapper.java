package com.xinmintx.agent.mapper;
import com.xinmintx.agent.model.DepositSpecification;
import com.xinmintx.agent.model.DepositSpecificationExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface DepositSpecificationMapper {
    long countByExample(DepositSpecificationExample example);

    int deleteByExample(DepositSpecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DepositSpecification record);

    int insertSelective(DepositSpecification record);

    List<DepositSpecification> selectByExampleWithRowbounds(DepositSpecificationExample example, RowBounds rowBounds);

    List<DepositSpecification> selectByExample(DepositSpecificationExample example);

    DepositSpecification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DepositSpecification record, @Param("example") DepositSpecificationExample example);

    int updateByExample(@Param("record") DepositSpecification record, @Param("example") DepositSpecificationExample example);

    int updateByPrimaryKeySelective(DepositSpecification record);

    int updateByPrimaryKey(DepositSpecification record);
}