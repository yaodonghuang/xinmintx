package com.xinmintx.agent.mapper;


import com.xinmintx.agent.model.BankCard;
import com.xinmintx.agent.model.BankCardExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface BankCardMapper {
    long countByExample(BankCardExample example);

    int deleteByExample(BankCardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BankCard record);

    int insertSelective(BankCard record);

    List<BankCard> selectByExampleWithRowbounds(BankCardExample example, RowBounds rowBounds);

    List<BankCard> selectByExample(BankCardExample example);

    BankCard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BankCard record, @Param("example") BankCardExample example);

    int updateByExample(@Param("record") BankCard record, @Param("example") BankCardExample example);

    int updateByPrimaryKeySelective(BankCard record);

    int updateByPrimaryKey(BankCard record);
}