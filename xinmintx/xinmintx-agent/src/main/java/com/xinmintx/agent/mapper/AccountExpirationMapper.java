package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.AccountExpiration;
import com.xinmintx.agent.model.AccountExpirationExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface AccountExpirationMapper {
    long countByExample(AccountExpirationExample example);

    int deleteByExample(AccountExpirationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountExpiration record);

    int insertSelective(AccountExpiration record);

    List<AccountExpiration> selectByExampleWithRowbounds(AccountExpirationExample example, RowBounds rowBounds);

    List<AccountExpiration> selectByExample(AccountExpirationExample example);

    AccountExpiration selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountExpiration record, @Param("example") AccountExpirationExample example);

    int updateByExample(@Param("record") AccountExpiration record, @Param("example") AccountExpirationExample example);

    int updateByPrimaryKeySelective(AccountExpiration record);

    int updateByPrimaryKey(AccountExpiration record);
}