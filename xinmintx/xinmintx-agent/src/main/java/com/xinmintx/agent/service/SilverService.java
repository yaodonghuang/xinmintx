package com.xinmintx.agent.service;


import com.xinmintx.agent.model.Member;

import java.util.List;

public interface SilverService {
    public int addSilver(Member member);

    List<Member> selectSilverByUserid(int userId);
}
