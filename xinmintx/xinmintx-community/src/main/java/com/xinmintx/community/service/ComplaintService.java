package com.xinmintx.community.service;

import com.xinmintx.community.dto.ComplaintAddDTO;
import com.xinmintx.community.dto.RescissionAddDTO;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */

public interface ComplaintService {
    void add(ComplaintAddDTO complaintAddDTO,String token);

}
