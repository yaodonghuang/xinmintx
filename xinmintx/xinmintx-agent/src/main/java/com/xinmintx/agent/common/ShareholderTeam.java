package com.xinmintx.agent.common;

import com.xinmintx.agent.model.User;
import lombok.Data;

@Data
public class ShareholderTeam implements Comparable<ShareholderTeam>{
    private User user;  //团队对象
    private Integer directNum; //直接推荐人数
    private Integer teamNum; //我的团队人数

    private String directName; //推荐人名字
    private Integer agencyNum; //代理数量
    private Integer merchantNum; //商户
    private Integer partnerNum; //合伙人
    private String avatar; //头像

    @Override
    public int compareTo(ShareholderTeam shareholderTeam) {
        return shareholderTeam.getTeamNum().compareTo(this.teamNum);
    }
}
