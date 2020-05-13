package com.xinmintx.agent.mapper;
import com.xinmintx.agent.model.UserRequestsn;
import com.xinmintx.agent.model.UserRequestsnExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserRequestsnMapper {
    long countByExample(UserRequestsnExample example);

    int deleteByExample(UserRequestsnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRequestsn record);

    int insertSelective(UserRequestsn record);

    List<UserRequestsn> selectByExampleWithRowbounds(UserRequestsnExample example, RowBounds rowBounds);

    List<UserRequestsn> selectByExample(UserRequestsnExample example);

    UserRequestsn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRequestsn record, @Param("example") UserRequestsnExample example);

    int updateByExample(@Param("record") UserRequestsn record, @Param("example") UserRequestsnExample example);

    int updateByPrimaryKeySelective(UserRequestsn record);

    int updateByPrimaryKey(UserRequestsn record);
}