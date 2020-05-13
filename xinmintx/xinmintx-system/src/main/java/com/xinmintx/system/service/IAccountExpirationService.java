package com.xinmintx.system.service;

import com.xinmintx.system.domain.AccountExpiration;
import java.util.List;

/**
 * 任务到期时间Service接口
 * 
 * @author xinmintx
 * @date 2019-11-23
 */
public interface IAccountExpirationService 
{
    /**
     * 查询任务到期时间
     * 
     * @param id 任务到期时间ID
     * @return 任务到期时间
     */
    public AccountExpiration selectAccountExpirationById(Long id);

    /**
     * 查询任务到期时间列表
     * 
     * @param accountExpiration 任务到期时间
     * @return 任务到期时间集合
     */
    public List<AccountExpiration> selectAccountExpirationList(AccountExpiration accountExpiration);

    /**
     * 新增任务到期时间
     * 
     * @param accountExpiration 任务到期时间
     * @return 结果
     */
    public int insertAccountExpiration(AccountExpiration accountExpiration);

    /**
     * 修改任务到期时间
     * 
     * @param accountExpiration 任务到期时间
     * @return 结果
     */
    public int updateAccountExpiration(AccountExpiration accountExpiration);

    /**
     * 批量删除任务到期时间
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccountExpirationByIds(String ids);

    /**
     * 删除任务到期时间信息
     * 
     * @param id 任务到期时间ID
     * @return 结果
     */
    public int deleteAccountExpirationById(Long id);
}
