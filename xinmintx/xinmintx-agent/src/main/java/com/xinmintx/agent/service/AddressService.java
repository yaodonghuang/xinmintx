package com.xinmintx.agent.service;

import com.xinmintx.agent.model.ShippingAddress;

import java.util.List;

public interface AddressService {

    ShippingAddress shippingAddress(Integer id);

    List<ShippingAddress> selectAddres(Integer id);

    int saveAddress(ShippingAddress shippingAddress);

    int updateDefaultAddress(Integer id);

    ShippingAddress selectAddress(Integer id);

    int updateAddress(ShippingAddress shippingAddress);
}
