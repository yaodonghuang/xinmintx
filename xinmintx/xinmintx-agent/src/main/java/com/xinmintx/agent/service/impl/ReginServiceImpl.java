package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.RegionMapper;
import com.xinmintx.agent.model.Region;
import com.xinmintx.agent.model.RegionExample;
import com.xinmintx.agent.service.ReginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReginServiceImpl implements ReginService {
    @Autowired
    private RegionMapper regionMapper;
    @Override
    public Region selectRegin(String reginName) {
        RegionExample regionExample = new RegionExample();
        RegionExample.Criteria criteria = regionExample.createCriteria();
        criteria.andNameEqualTo(reginName);
        criteria.andRegionalstateEqualTo(0);
        criteria.andLevelEqualTo(2);
        List<Region> regions = regionMapper.selectByExample(reginName);
        return regions.get(0);
    }

    @Override
    public void updateReginStart(Region region) {
        region.setRegionalstate(1);
        regionMapper.updateByPrimaryKey(region);
    }
}
