package com.xinmintx.agent.mapper;
import com.xinmintx.agent.model.UserAccountRecord;
import com.xinmintx.agent.model.UserAccountRecordExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserAccountRecordMapper {
    long countByExample(UserAccountRecordExample example);

    int deleteByExample(UserAccountRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAccountRecord record);

    int insertSelective(UserAccountRecord record);

    List<UserAccountRecord> selectByExampleWithRowbounds(UserAccountRecordExample example, RowBounds rowBounds);

    List<UserAccountRecord> selectByExample(UserAccountRecordExample example);

    UserAccountRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAccountRecord record, @Param("example") UserAccountRecordExample example);

    int updateByExample(@Param("record") UserAccountRecord record, @Param("example") UserAccountRecordExample example);

    int updateByPrimaryKeySelective(UserAccountRecord record);

    int updateByPrimaryKey(UserAccountRecord record);

    List<UserAccountRecord> selectByUserId(Integer userId);
}