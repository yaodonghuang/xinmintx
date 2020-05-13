package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.Merchant;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 商家信息Mapper接口
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
public interface MerchantMapper 
{
    /**
     * 查询商家信息
     * 
     * @param id 商家信息ID
     * @return 商家信息
     */
    public Merchant selectMerchantById(Long id);

    /**
     * 查询商家信息列表
     * 
     * @param merchant 商家信息
     * @return 商家信息集合
     */
    public List<Merchant> selectMerchantList(Merchant merchant);

    /**
     * 新增商家信息
     * 
     * @param merchant 商家信息
     * @return 结果
     */
    public int insertMerchant(Merchant merchant);

    /**
     * 修改商家信息
     * 
     * @param merchant 商家信息
     * @return 结果
     */
    public int updateMerchant(Merchant merchant);

    /**
     * 删除商家信息
     * 
     * @param id 商家信息ID
     * @return 结果
     */
    public int deleteMerchantById(Long id);

    /**
     * 批量删除商家信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerchantByIds(String[] ids);

    /**
     * 修改商户归属
     * @param code
     * @param id
     */
    @Update("update merchant set merchant_branch_office_id=#{id} where region_code=#{code}")
    void updateMerchantByRegionCode(@Param("code") String code,@Param("id") Long id);

    /**
     * 删除原本分公司下属的商户
     * @param id
     */
    @Update("update merchant set merchant_branch_office_id=1 where merchant_branch_office_id=#{id}")
    void updateMerchantById(@Param("id") Long id);

    List<Merchant> selectMerchant();


    List<Merchant> selectMerchants(Merchant merchant);

}
