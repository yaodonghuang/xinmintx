package com.xinmintx.merchant.mapper;

import com.xinmintx.merchant.model.PoboNotify;

import java.util.List;

public interface PoboNotifyMapper {
    PoboNotify selectList(String requestSn);

    void insert(PoboNotify pn);
}
