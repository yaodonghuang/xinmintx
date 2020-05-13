package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinmintx.hstx.mapper.RegionMapper;
import com.xinmintx.hstx.pojo.po.Region;
import com.xinmintx.hstx.pojo.po.User;
import com.xinmintx.hstx.service.RegoinService;
import com.xinmintx.hstx.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RegionServiceImpl implements RegoinService {

    @Autowired
    private RegionMapper  regionMapper;


    @Override
    public List<List<Region>> selectRegion() {
            List<Region> regions = regionMapper.selectByMap(null);
            for (Region region : regions) {
                String s = region.getPinyin().substring(0, 1).toUpperCase();
                region.setPinyin(s);
            }
            Map<String, List<Region>> listMap = ListUtils.listGroup(regions, "level");
            List<Region> province_list = listMap.get("1");
            List<Region> city_list = listMap.get("2");
            List<Region>  county_list = listMap.get("3");
            List<List<Region>> city = new ArrayList<>();
            city.add(province_list);
            city.add(city_list);
            city.add(county_list);
        return  city;

    }

    @Override
    public List<Region> selectRegionByName(String name) {
        QueryWrapper<Region> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return regionMapper.selectList(queryWrapper);
    }
    @Override
    public List<User> selectHotCity() {
        List<User> users = regionMapper.selectHotCity();
        if (users != null){
            for (User user : users) {
                user.setRegionName(user.getRegionName().split(",")[1]);
            }
        }
        return users;
    }
    @Override
    public List<Region> selectByCityId(String cityName) {
        QueryWrapper<Region> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",cityName);
        List<Region> regions = regionMapper.selectList(queryWrapper);
        Integer id = regions.get(0).getId();
        QueryWrapper<Region> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("pid",id);
        List<Region> regions1 = regionMapper.selectList(objectQueryWrapper);
        return regions1;
    }
}
