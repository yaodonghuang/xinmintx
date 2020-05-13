package com.xinmintx.system.service;

import com.xinmintx.system.domain.Merchant;

import java.util.List;

/**
 * 商家信息Service接口
 *
 * @author xinmintx
 * @date 2019-11-11
 */
public interface IMerchantService {
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
     * 批量删除商家信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerchantByIds(String ids);

    /**
     * 删除商家信息信息
     *
     * @param id 商家信息ID
     * @return 结果
     */
    public int deleteMerchantById(Long id);

    List<Merchant> selectMerchantLists(Merchant merchant);
}
