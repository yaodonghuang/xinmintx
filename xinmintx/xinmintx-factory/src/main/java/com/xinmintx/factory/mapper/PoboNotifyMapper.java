package com.xinmintx.factory.mapper;

import com.xinmintx.factory.model.PoboNotify;
import org.apache.ibatis.annotations.Param;

public interface PoboNotifyMapper {
    int insert(@Param("pn") PoboNotify pn);
}
