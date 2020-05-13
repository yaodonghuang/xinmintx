package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.WithdrawDeposit;
import com.xinmintx.agent.model.WithdrawDepositExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface WithdrawDepositMapper {
    long countByExample(WithdrawDepositExample example);

    int deleteByExample(WithdrawDepositExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WithdrawDeposit record);

    int insertSelective(WithdrawDeposit record);

    List<WithdrawDeposit> selectByExampleWithRowbounds(WithdrawDepositExample example, RowBounds rowBounds);

    List<WithdrawDeposit> selectByExample(WithdrawDepositExample example);

    WithdrawDeposit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WithdrawDeposit record, @Param("example") WithdrawDepositExample example);

    int updateByExample(@Param("record") WithdrawDeposit record, @Param("example") WithdrawDepositExample example);

    int updateByPrimaryKeySelective(WithdrawDeposit record);

    int updateByPrimaryKey(WithdrawDeposit record);
}