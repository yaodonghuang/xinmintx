package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.UserAccountRecordMapper;
import com.xinmintx.agent.model.UserAccountRecord;
import com.xinmintx.agent.service.UserAccountRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:.UserAccountRecordServiceImpl
 * @author:chf
 * @Date:2020/1/16：14:07
 * @developerKits： win 10     jdk1.8
 */
@Service
public class UserAccountRecordServiceImpl implements UserAccountRecordService {

    @Autowired
    private UserAccountRecordMapper accountRecordMapper;

    @Override
    public List<UserAccountRecord> queryDetail(Integer userId) {
        return accountRecordMapper.selectByUserId(userId);
    }
}
