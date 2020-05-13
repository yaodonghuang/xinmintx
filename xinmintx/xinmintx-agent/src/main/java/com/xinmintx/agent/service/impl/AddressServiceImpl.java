package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.mapper.AddressMapper;
import com.xinmintx.agent.model.ShippingAddress;
import com.xinmintx.agent.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public ShippingAddress shippingAddress(Integer id) {
        return addressMapper.shippingAddress(id);
    }

    @Override
    public List<ShippingAddress> selectAddres(Integer id) {
        return addressMapper.selectAddres(id);
    }

    @Override
    public int saveAddress(ShippingAddress shippingAddress) {

        return addressMapper.saveAddress(shippingAddress);
    }

    @Override
    public int updateDefaultAddress(Integer id) {
        ShippingAddress shippingAddress = addressMapper.shippingAddress(id);
        int i = 0;
        if (shippingAddress!=null){
            shippingAddress.setDefaultAddress(0);
            i = addressMapper.updateAddress(shippingAddress);
        }
        return i;
    }

    @Override
    public ShippingAddress selectAddress(Integer id) {
        return addressMapper.selectAddress(id);
    }

    @Override
    public int updateAddress(ShippingAddress shippingAddress) {
        return addressMapper.updateAddress(shippingAddress);
    }
}
