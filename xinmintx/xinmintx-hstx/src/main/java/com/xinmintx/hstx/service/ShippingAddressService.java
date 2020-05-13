package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.vo.MemberAddressVo;

import java.util.List;

/**
 * @author wangkang
 */
public interface ShippingAddressService {
    /**
     * 消费者添加收货地址
     * @param memberAddressVo
     * @return
     */
    int addShippingAddress(MemberAddressVo memberAddressVo);

    /**
     * 查看消费者收货地址
     * @param token
     * @return
     */
    List<MemberAddressVo> selectShippingAddress(String token);

    /**
     * 修改消费者收货地址
     * @param id
     * @return
     */
    MemberAddressVo editShippingAddress(int id);

    /**
     * 保存修改的地址
     * @param memberAddressVo
     * @return
     */
    int updateShippingAddress(MemberAddressVo memberAddressVo);

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    int deleteShippingAddress(int id);

    /**
     * 查询默认收货地址
     * @param token
     * @return
     */
    MemberAddressVo selectDefaultAddress(String token);
}
