package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.Region;
import com.xinmintx.hstx.pojo.po.User;

import java.util.List;
import java.util.Map;

public interface RegoinService {
    List<List<Region>> selectRegion();

    List<Region> selectRegionByName(String name);

    List<User> selectHotCity();

    public List<Region> selectByCityId(String cityName);
}
