package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.IntegralAccessMapper;
import com.xinmintx.system.domain.IntegralAccess;
import com.xinmintx.system.service.IIntegralAccessService;
import com.xinmintx.common.core.text.Convert;

/**
 * 积分获取方式Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-11-20
 */
@Service
public class IntegralAccessServiceImpl implements IIntegralAccessService 
{
    @Autowired
    private IntegralAccessMapper integralAccessMapper;

    /**
     * 查询积分获取方式
     * 
     * @param id 积分获取方式ID
     * @return 积分获取方式
     */
    @Override
    public IntegralAccess selectIntegralAccessById(Long id)
    {
        return integralAccessMapper.selectIntegralAccessById(id);
    }

    /**
     * 查询积分获取方式列表
     * 
     * @param integralAccess 积分获取方式
     * @return 积分获取方式
     */
    @Override
    public List<IntegralAccess> selectIntegralAccessList(IntegralAccess integralAccess)
    {
        return integralAccessMapper.selectIntegralAccessList(integralAccess);
    }

    /**
     * 新增积分获取方式
     * 
     * @param integralAccess 积分获取方式
     * @return 结果
     */
    @Override
    public int insertIntegralAccess(IntegralAccess integralAccess)
    {
        return integralAccessMapper.insertIntegralAccess(integralAccess);
    }

    /**
     * 修改积分获取方式
     * 
     * @param integralAccess 积分获取方式
     * @return 结果
     */
    @Override
    public int updateIntegralAccess(IntegralAccess integralAccess)
    {
        return integralAccessMapper.updateIntegralAccess(integralAccess);
    }

    /**
     * 删除积分获取方式对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIntegralAccessByIds(String ids)
    {
        return integralAccessMapper.deleteIntegralAccessByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除积分获取方式信息
     * 
     * @param id 积分获取方式ID
     * @return 结果
     */
    @Override
    public int deleteIntegralAccessById(Long id)
    {
        return integralAccessMapper.deleteIntegralAccessById(id);
    }
}
