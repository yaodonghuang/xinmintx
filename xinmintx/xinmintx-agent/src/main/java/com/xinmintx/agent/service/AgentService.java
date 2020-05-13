package com.xinmintx.agent.service;

import com.xinmintx.agent.common.ShareholderTeam;
import com.xinmintx.agent.model.User;

import java.util.List;

public interface AgentService {

    int addAgent(User user);

    List<User> selectAgentsByUserid(int userId);


    List<ShareholderTeam> partnerTeamList(Integer id);
}

