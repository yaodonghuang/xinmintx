package com.xinmintx.agent.service;

import com.xinmintx.agent.model.User;

import java.util.List;

public interface CompanyService {
    int addCompany(User user, Integer[] photo);

    List<User> selectCompanyByUserid(int userId);
}
