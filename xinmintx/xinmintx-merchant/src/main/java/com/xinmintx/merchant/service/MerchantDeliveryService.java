package com.xinmintx.merchant.service;


import com.xinmintx.merchant.dto.MerchantDeliveryAddDTO;

import java.util.List;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */

public interface MerchantDeliveryService {
    void addOrUpdate(String token, List<MerchantDeliveryAddDTO> merchantDeliveryAddDTOS);

}
