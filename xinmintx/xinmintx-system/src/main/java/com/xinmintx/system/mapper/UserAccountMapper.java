package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.UserAccount;
import com.xinmintx.system.domain.UserAccountExample;
import com.xinmintx.system.domain.UserInformation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserAccountMapper {
    long countByExample(UserAccountExample example);

    int deleteByExample(UserAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    List<UserAccount> selectByExampleWithRowbounds(UserAccountExample example, RowBounds rowBounds);

    List<UserAccount> selectByExample(UserAccountExample example);

    UserAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    UserAccount selectByUserId(int intValue);

    List<UserInformation> selectById();
}