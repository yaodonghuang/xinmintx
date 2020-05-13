package com.xinmintx.factory.api.mapper;

import com.xinmintx.factory.api.pojo.PoboNotify;
import org.apache.ibatis.annotations.Param;

public interface PoboNotifyMapper {
    int insert(@Param("pn") PoboNotify pn);
}
