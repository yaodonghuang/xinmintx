package com.xinmintx.community.mapper;

import com.xinmintx.community.model.Complaint;
import org.apache.ibatis.annotations.Param;

public interface ComplaintMapper {
    void add(@Param("complaint") Complaint complaint);

}
