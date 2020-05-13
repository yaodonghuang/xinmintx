package com.xinmintx.agent.service;

import com.xinmintx.agent.common.ShareholderTeam;
import com.xinmintx.agent.model.User;

import java.util.List;

public interface ShareholderService {

    List<ShareholderTeam> selectShareholder(User user);

    User selectPartner(String cellphone);

    boolean updateUserRole(String cellphone);

    List<ShareholderTeam> selectTeam(Integer id);
}
