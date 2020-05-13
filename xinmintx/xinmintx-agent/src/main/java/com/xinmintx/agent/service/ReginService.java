package com.xinmintx.agent.service;

import com.xinmintx.agent.model.Region;

public interface ReginService {
    public Region selectRegin(String reginName);

    void updateReginStart(Region region);
}
