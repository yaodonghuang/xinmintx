package com.xinmintx.factory.api.mapper;

import org.apache.ibatis.annotations.Param;

public interface StatisticsMapper {
    long queryOrderSize(@Param("id") long id, @Param("beginDate") String beginDate, @Param("endDate") String endDate);
    Double queryTurnover(@Param("id") long id, @Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("orderStatu") long orderStatu);
}
