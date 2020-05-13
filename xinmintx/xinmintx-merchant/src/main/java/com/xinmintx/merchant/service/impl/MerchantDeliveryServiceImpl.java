package com.xinmintx.merchant.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.xinmintx.merchant.common.ErrorCodeConstants;
import com.xinmintx.merchant.dto.MerchantDeliveryAddDTO;
import com.xinmintx.merchant.exception.BaseException;
import com.xinmintx.merchant.mapper.MerchantDeliveryMapper;
import com.xinmintx.merchant.mapper.MerchantExtMapper;
import com.xinmintx.merchant.model.MerchantDelivery;
import com.xinmintx.merchant.service.MerchantDeliveryService;
import com.xinmintx.merchant.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */

@Slf4j
@Service
public class MerchantDeliveryServiceImpl implements MerchantDeliveryService {

    @Autowired
    MerchantDeliveryMapper merchatDeliveryMapper;
    @Autowired
    MerchantExtMapper merchantExtMapper;

    @Override
    public void addOrUpdate(String token, List<MerchantDeliveryAddDTO> merchantDeliveryAddDTOS) {

        if (CollectionUtil.isEmpty(merchantDeliveryAddDTOS)) {
            return;
        }

        merchantDeliveryAddDTOS.forEach(merchantDeliveryAddDTO -> {
            if (merchantDeliveryAddDTO.getDeliveryId() == null) {
                Integer id = merchantExtMapper.getIdByToken(token);

                if (id == null) {// token不存在
                    throw new BaseException(ErrorCodeConstants.TOKEN_ERR);
                }

                merchantDeliveryAddDTO.setMerchantId(id);
                MerchantDelivery merchantDelivery = BeanUtil.copyProperties(merchantDeliveryAddDTO, MerchantDelivery.class, true);
                merchatDeliveryMapper.add(merchantDelivery);
            }else{
                MerchantDelivery merchantDelivery = BeanUtil.copyProperties(merchantDeliveryAddDTO, MerchantDelivery.class, false);
                merchantDelivery.setId(merchantDeliveryAddDTO.getDeliveryId());
                merchatDeliveryMapper.updateById(merchantDelivery);
            }
        });

    }
}
