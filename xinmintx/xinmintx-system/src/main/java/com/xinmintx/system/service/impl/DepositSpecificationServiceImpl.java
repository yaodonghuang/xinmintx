package com.xinmintx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.DepositSpecificationMapper;
import com.xinmintx.system.domain.DepositSpecification;
import com.xinmintx.system.service.IDepositSpecificationService;
import com.xinmintx.common.core.text.Convert;

/**
 * 提现规格Service业务层处理
 * 
 * @author xinmintx
 * @date 2020-01-07
 */
@Service
public class DepositSpecificationServiceImpl implements IDepositSpecificationService
{
    @Autowired
    private DepositSpecificationMapper depositSpecificationMapper;

    /**
     * 查询提现规格
     * 
     * @param id 提现规格ID
     * @return 提现规格
     */
    @Override
    public DepositSpecification selectDepositSpecificationById(Long id)
    {
        DepositSpecification depositSpecification = depositSpecificationMapper.selectDepositSpecificationById(id);
        depositSpecification.setServiceCharge(depositSpecification.getServiceCharge()*100);
        return depositSpecification;
    }

    /**
     * 查询提现规格列表
     * 
     * @param depositSpecification 提现规格
     * @return 提现规格
     */
    @Override
    public List<DepositSpecification> selectDepositSpecificationList(DepositSpecification depositSpecification)
    {
        List<DepositSpecification> depositSpecifications = depositSpecificationMapper.selectDepositSpecificationList(depositSpecification);
        for (int i = 0; i <depositSpecifications.size() ; i++) {
            depositSpecifications.get(i).setServiceCharge(depositSpecifications.get(i).getServiceCharge()*100);
        }
        return depositSpecifications;
    }

    /**
     * 新增提现规格
     * 
     * @param depositSpecification 提现规格
     * @return 结果
     */
    @Override
    public int insertDepositSpecification(DepositSpecification depositSpecification)
    {
        depositSpecification.setServiceCharge(depositSpecification.getServiceCharge()/100);
        return depositSpecificationMapper.insertDepositSpecification(depositSpecification);
    }

    /**
     * 修改提现规格
     * 
     * @param depositSpecification 提现规格
     * @return 结果
     */
    @Override
    public int updateDepositSpecification(DepositSpecification depositSpecification)
    {
        depositSpecification.setServiceCharge(depositSpecification.getServiceCharge()/100);
        return depositSpecificationMapper.updateDepositSpecification(depositSpecification);
    }

    /**
     * 删除提现规格对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDepositSpecificationByIds(String ids)
    {
        return depositSpecificationMapper.deleteDepositSpecificationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除提现规格信息
     * 
     * @param id 提现规格ID
     * @return 结果
     */
    @Override
    public int deleteDepositSpecificationById(Long id)
    {
        return depositSpecificationMapper.deleteDepositSpecificationById(id);
    }
}
