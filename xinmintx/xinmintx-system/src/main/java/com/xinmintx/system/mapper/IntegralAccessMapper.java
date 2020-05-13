package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.IntegralAccess;
import java.util.List;

/**
 * 积分获取方式Mapper接口
 * 
 * @author xinmintx
 * @date 2019-11-20
 */
public interface IntegralAccessMapper 
{
    /**
     * 查询积分获取方式
     * 
     * @param id 积分获取方式ID
     * @return 积分获取方式
     */
    public IntegralAccess selectIntegralAccessById(Long id);

    /**
     * 查询积分获取方式列表
     * 
     * @param integralAccess 积分获取方式
     * @return 积分获取方式集合
     */
    public List<IntegralAccess> selectIntegralAccessList(IntegralAccess integralAccess);

    /**
     * 新增积分获取方式
     * 
     * @param integralAccess 积分获取方式
     * @return 结果
     */
    public int insertIntegralAccess(IntegralAccess integralAccess);

    /**
     * 修改积分获取方式
     * 
     * @param integralAccess 积分获取方式
     * @return 结果
     */
    public int updateIntegralAccess(IntegralAccess integralAccess);

    /**
     * 删除积分获取方式
     * 
     * @param id 积分获取方式ID
     * @return 结果
     */
    public int deleteIntegralAccessById(Long id);

    /**
     * 批量删除积分获取方式
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteIntegralAccessByIds(String[] ids);
}
