package com.xinmintx.agent.service;

import com.xinmintx.agent.model.UserAccountRecord;

import java.util.List;

public interface UserAccountRecordService {
    List<UserAccountRecord> queryDetail(Integer userId);
}
