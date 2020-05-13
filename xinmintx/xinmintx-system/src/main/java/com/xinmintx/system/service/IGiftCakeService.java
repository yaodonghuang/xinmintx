package com.xinmintx.system.service;

import com.xinmintx.system.domain.GiftCake;

import java.util.List;

/**
 * 商户生日蛋糕Service接口
 * 
 * @author xinmintx
 * @date 2020-01-19
 */
public interface IGiftCakeService 
{
    /**
     * 查询商户生日蛋糕
     * 
     * @param id 商户生日蛋糕ID
     * @return 商户生日蛋糕
     */
    public GiftCake selectGiftCakeById(Long id);

    /**
     * 查询商户生日蛋糕列表
     * 
     * @param giftCake 商户生日蛋糕
     * @return 商户生日蛋糕集合
     */
    public List<GiftCake> selectGiftCakeList(GiftCake giftCake);

    /**
     * 新增商户生日蛋糕
     * 
     * @param giftCake 商户生日蛋糕
     * @return 结果
     */
    public int insertGiftCake(GiftCake giftCake);

    /**
     * 修改商户生日蛋糕
     * 
     * @param giftCake 商户生日蛋糕
     * @return 结果
     */
    public int updateGiftCake(GiftCake giftCake);

    /**
     * 批量删除商户生日蛋糕
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGiftCakeByIds(String ids);

    /**
     * 删除商户生日蛋糕信息
     * 
     * @param id 商户生日蛋糕ID
     * @return 结果
     */
    public int deleteGiftCakeById(Long id);
}
