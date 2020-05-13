package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.DepositSpecification;
import java.util.List;

/**
 * 提现规格Mapper接口
 * 
 * @author xinmintx
 * @date 2020-01-07
 */
public interface DepositSpecificationMapper 
{
    /**
     * 查询提现规格
     * 
     * @param id 提现规格ID
     * @return 提现规格
     */
    public DepositSpecification selectDepositSpecificationById(Long id);

    /**
     * 查询提现规格列表
     * 
     * @param depositSpecification 提现规格
     * @return 提现规格集合
     */
    public List<DepositSpecification> selectDepositSpecificationList(DepositSpecification depositSpecification);

    /**
     * 新增提现规格
     * 
     * @param depositSpecification 提现规格
     * @return 结果
     */
    public int insertDepositSpecification(DepositSpecification depositSpecification);

    /**
     * 修改提现规格
     * 
     * @param depositSpecification 提现规格
     * @return 结果
     */
    public int updateDepositSpecification(DepositSpecification depositSpecification);

    /**
     * 删除提现规格
     * 
     * @param id 提现规格ID
     * @return 结果
     */
    public int deleteDepositSpecificationById(Long id);

    /**
     * 批量删除提现规格
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDepositSpecificationByIds(String[] ids);
}
