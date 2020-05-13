package com.xinmintx.agent.mapper;


import com.xinmintx.agent.model.PoboNotify;
import org.apache.ibatis.annotations.Param;

public interface PoboNotifyMapper {
    int insert(@Param("pn") PoboNotify pn);
}
