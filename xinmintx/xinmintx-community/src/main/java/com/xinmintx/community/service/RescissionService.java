package com.xinmintx.community.service;

import com.xinmintx.community.dto.RescissionAddDTO;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */

public interface RescissionService {
    void votingRescission(RescissionAddDTO rescissionAddDTO, String token);

}
