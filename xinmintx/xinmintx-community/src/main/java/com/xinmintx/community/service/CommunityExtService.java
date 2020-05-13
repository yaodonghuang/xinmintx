package com.xinmintx.community.service;

import com.xinmintx.community.model.CommunityExt;
import com.xinmintx.community.model.CommunityDeputy;

import java.util.List;

public interface CommunityExtService {
    /**
     * 添加社区提货点
     * @param communityExt
     * @return
     */
    int addPickupLocation(CommunityExt communityExt);

    /**
     * 修改社区提货点
     * @param communityExt
     * @return
     */
    int updatePickupLocation(CommunityExt communityExt);

    /**
     * 添加帮点
     * @param communityDeputies
     * @return
     */
    int addAssistInManaging(CommunityDeputy communityDeputies);


    /**
     * 修改帮点
     * @param communityDeputies
     * @return
     */
    int update(CommunityDeputy communityDeputies);

    /**
     * 查询帮点
     * @param id
     * @return
     */
    CommunityDeputy queryAssistInManaging(Integer id);

    /**
     * 查询提货点
     * @param id
     * @return
     */
    CommunityExt queryPickupLocation(Integer id);
}
