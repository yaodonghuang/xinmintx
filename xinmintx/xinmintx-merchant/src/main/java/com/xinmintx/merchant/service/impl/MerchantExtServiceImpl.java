package com.xinmintx.merchant.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.xinmintx.merchant.common.ErrorCodeConstants;
import com.xinmintx.merchant.dto.DeliveryVo;
import com.xinmintx.merchant.dto.MerchantBindCardDTO;
import com.xinmintx.merchant.exception.BaseException;
import com.xinmintx.merchant.mapper.CommunityMerchantMapper;
import com.xinmintx.merchant.mapper.MerchantBankCardMapper;
import com.xinmintx.merchant.mapper.MerchantDeliveryMapper;
import com.xinmintx.merchant.mapper.MerchantExtMapper;
import com.xinmintx.merchant.model.MerchantBankCard;
import com.xinmintx.merchant.service.MerchantExtService;
import com.xinmintx.merchant.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */

@Slf4j
@Service
public class MerchantExtServiceImpl implements MerchantExtService {

    @Resource
    MerchantExtMapper merchantExtMapper;

    @Resource
    CommunityMerchantMapper communityMerchantMapper;

    @Resource
    MerchantDeliveryMapper merchantDeliveryMapper;

    @Resource
    MerchantBankCardMapper merchantBankCardMapper;

    @Override
    public List<Map> getMyCommunity(String token) {
        Integer id = merchantExtMapper.getIdByToken(token);

        if (id == null) {
            throw new BaseException(ErrorCodeConstants.MERCHANT_NOT_FOUNT_ERR);
        }
        List<Map> communitys = communityMerchantMapper.getCommunitysByMerchantId(id);
        if (CollectionUtil.isEmpty(communitys)) {
            return communitys;
        }
        communitys.forEach(com ->{
            if(com != null){
                Integer communityId = Integer.parseInt(com.get("communityId")+"");
                List<DeliveryVo> deliveryVos = merchantDeliveryMapper.getByMerchantIdAndCommunityId(id, communityId);
                if(CollectionUtil.isEmpty(deliveryVos)){
                    deliveryVos = new ArrayList<>();
                    deliveryVos.add(new DeliveryVo());
                }
                com.put("deliver", deliveryVos);
            }
        });
        return communitys;
    }



    @Override
    public Map getByPhone(String phone) {
        return merchantExtMapper.getByPhone(phone);
    }

    @Override
    public Map getInfo(String token) {
        return merchantExtMapper.getInfo(token) ;
    }

    @Override
    public void rescission(String token, Integer communityId) {
        Integer merchantId = merchantExtMapper.getIdByToken(token);

        if (merchantId == null) {
            throw new BaseException(ErrorCodeConstants.MERCHANT_NOT_FOUNT_ERR);
        }

        communityMerchantMapper.deleteByCommunityIdAndMerchantId(communityId,merchantId) ;
    }

    @Override
    public Map getShopInfo(String token) {
        return merchantExtMapper.getShopInfo(token);
    }

    @Override
    public void updateShopInfo(String token, String shopName, String shopAddress) {
        merchantExtMapper.updateShopInfo(token,shopName,shopAddress);
    }

    @Override
    public void bindCard(String token,MerchantBindCardDTO merchantBindCardDTO) {

        Integer merchantId = merchantExtMapper.getIdByToken(token);

        if (merchantId == null) {
            throw new BaseException(ErrorCodeConstants.MERCHANT_NOT_FOUNT_ERR);
        }
        //该卡已被绑定
        if(merchantBankCardMapper.getByCardNum(merchantBindCardDTO.getCardNum()) >= 1){
            throw new BaseException(ErrorCodeConstants.REPEAT_BIND_CARD_ERR);
        }
        merchantBindCardDTO.setMerchantId(merchantId);
        MerchantBankCard merchantBankCard = BeanUtil.copyProperties(merchantBindCardDTO,MerchantBankCard.class,true);
        merchantBankCardMapper.insert(merchantBankCard);
    }

    @Override
    public List<MerchantBankCard> getBankCardByMerchantId(String token) {
        Integer merchantId = merchantExtMapper.getIdByToken(token);

        if (merchantId == null) {
            throw new BaseException(ErrorCodeConstants.MERCHANT_NOT_FOUNT_ERR);
        }
        List<MerchantBankCard> merchantBankCards = merchantBankCardMapper.queryByMerchantId(merchantId);

        return merchantBankCards;
    }


//    private List<SupplierBo> getDis(List<SupplierBo> supplierBos,String lonlat) {
//        StringBuffer origins = new StringBuffer();
//        for (SupplierBo shopLittleBO : supplierBos) {
//            origins.append(shopLittleBO.getLon()).append(",").append(shopLittleBO.getLat()).append("|");
//        }
//        origins.deleteCharAt(origins.length() - 1);
//
//        Map<Integer, Integer> distanceMap = AmapUtil.getDistanceList(origins.toString(), lonlat, null, null);
//
//        for (int i = 0; i < supplierBos.size(); i++) {
//            //设置距离
//            double div = NumberUtil.div(distanceMap.get(i + 1).floatValue(), 1000, 2);
//            log.info("{}-->米:{} 公里:{}",supplierBos.get(i).getName(),distanceMap.get(i + 1).floatValue(),div);
//            supplierBos.get(i).setDistance(div);
//        }
//
//        //排序
//        supplierBos.sort(Comparator.comparing(SupplierBo::getDistance));
//
//        return supplierBos;
//    }
}
