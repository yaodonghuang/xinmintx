package com.xinmintx.agent.service;

import com.xinmintx.agent.model.User;

import java.util.List;

public interface PartnerService {

    int addPartner(User user);

    List<User> selectPartnerByUserid(int userId);

    User selectPartnerByCellphone(String cellphone);

    User selectPartnerAndShareholder(String cellphone);

    User selectPartner(String cellphone);

}
