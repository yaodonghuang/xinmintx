package com.xinmintx.agent.mapper;

import com.xinmintx.agent.model.GoodsPtUser;
import com.xinmintx.agent.model.GoodsPtUserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface GoodsPtUserMapper {
    long countByExample(GoodsPtUserExample example);

    int deleteByExample(GoodsPtUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsPtUser record);

    int insertSelective(GoodsPtUser record);

    List<GoodsPtUser> selectByExampleWithRowbounds(GoodsPtUserExample example, RowBounds rowBounds);

    List<GoodsPtUser> selectByExample(GoodsPtUserExample example);

    GoodsPtUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsPtUser record, @Param("example") GoodsPtUserExample example);

    int updateByExample(@Param("record") GoodsPtUser record, @Param("example") GoodsPtUserExample example);

    int updateByPrimaryKeySelective(GoodsPtUser record);

    int updateByPrimaryKey(GoodsPtUser record);
}