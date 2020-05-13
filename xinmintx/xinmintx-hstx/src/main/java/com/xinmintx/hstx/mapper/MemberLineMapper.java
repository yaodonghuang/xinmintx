package com.xinmintx.hstx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinmintx.hstx.pojo.po.MemberLine;
import org.apache.ibatis.annotations.Param;

public interface MemberLineMapper extends BaseMapper<MemberLine> {
    public void deleteMemberLineById(@Param("id") Integer id);

    public void insertLine(@Param("id") Integer id, @Param("upperLine") String upperLine);

    public String getUpperLineById(@Param("id") Integer id, @Param("num") Integer num);
}
