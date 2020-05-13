package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.AccountExpirationMapper;
import com.xinmintx.system.domain.AccountExpiration;
import com.xinmintx.system.service.IAccountExpirationService;
import com.xinmintx.common.core.text.Convert;

/**
 * 任务到期时间Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-11-23
 */
@Service
public class AccountExpirationServiceImpl implements IAccountExpirationService 
{
    @Autowired
    private AccountExpirationMapper accountExpirationMapper;

    /**
     * 查询任务到期时间
     * 
     * @param id 任务到期时间ID
     * @return 任务到期时间
     */
    @Override
    public AccountExpiration selectAccountExpirationById(Long id)
    {
        return accountExpirationMapper.selectAccountExpirationById(id);
    }

    /**
     * 查询任务到期时间列表
     * 
     * @param accountExpiration 任务到期时间
     * @return 任务到期时间
     */
    @Override
    public List<AccountExpiration> selectAccountExpirationList(AccountExpiration accountExpiration)
    {
        return accountExpirationMapper.selectAccountExpirationList(accountExpiration);
    }

    /**
     * 新增任务到期时间
     * 
     * @param accountExpiration 任务到期时间
     * @return 结果
     */
    @Override
    public int insertAccountExpiration(AccountExpiration accountExpiration)
    {
        return accountExpirationMapper.insertAccountExpiration(accountExpiration);
    }

    /**
     * 修改任务到期时间
     * 
     * @param accountExpiration 任务到期时间
     * @return 结果
     */
    @Override
    public int updateAccountExpiration(AccountExpiration accountExpiration)
    {
        return accountExpirationMapper.updateAccountExpiration(accountExpiration);
    }

    /**
     * 删除任务到期时间对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAccountExpirationByIds(String ids)
    {
        return accountExpirationMapper.deleteAccountExpirationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务到期时间信息
     * 
     * @param id 任务到期时间ID
     * @return 结果
     */
    @Override
    public int deleteAccountExpirationById(Long id)
    {
        return accountExpirationMapper.deleteAccountExpirationById(id);
    }
}
