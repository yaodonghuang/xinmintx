package com.xinmintx.system.mapper;

import org.apache.ibatis.annotations.Param;

public interface MemberLineMapper {
    public void deleteMemberLineById(@Param("id") Integer id);

    public void insertLine(@Param("id") Integer id,@Param("upperLine") String upperLine);

    public String getUpperLineById(@Param("id") Integer id,@Param("num") Integer num);
}
