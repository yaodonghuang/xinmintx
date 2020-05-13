package com.xinmintx.factory.api.service;

public interface StatisticsService {
    long queryOrderSize(String token, String beginDate, String endDate);
    Double queryTurnover(String token, String beginDate, String endDate);
}
