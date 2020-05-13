package com.xinmintx.agent.service.impl;
import com.xinmintx.agent.mapper.CommodityInformationSheetMapper;
import com.xinmintx.agent.model.CommodityType;
import com.xinmintx.agent.model.GoodsPhoto;
import com.xinmintx.agent.modelDTO.CommodityAndImage;
import com.xinmintx.agent.service.ProcurementMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcurementMarketServiceImpl implements ProcurementMarketService {

    @Autowired
    private CommodityInformationSheetMapper commodityInformationSheetMapper;
    @Override
    public List<CommodityAndImage> commodityAndImageList(Integer userId) {
        List<CommodityAndImage> commodityAndImages = commodityInformationSheetMapper.commodityAndImageList(userId);
        //截取图片路径
        for (CommodityAndImage commodityAndImage : commodityAndImages) {
            if(commodityAndImage.getOssUrl() !=null){
                String s = commodityAndImage.getOssUrl().split(",")[0];
                GoodsPhoto ossUrl = commodityInformationSheetMapper.selectOssUrl(Integer.parseInt(s));
                commodityAndImage.setOssUrl(ossUrl.getPhotoUrl());
            }
        }
        return commodityAndImages;
    }
    @Override
    public List<CommodityType> selectShoppingType() {

        return commodityInformationSheetMapper.selectShoppingType();
    }
    @Override
    public List<CommodityAndImage> selectByTypeShopping(Integer id) {
        List<CommodityAndImage> commodityAndImages = commodityInformationSheetMapper.selectByTypeShopping(id);
        for (CommodityAndImage commodityAndImage : commodityAndImages) {
            if(commodityAndImage.getOssUrl() !=null){
                String s = commodityAndImage.getOssUrl().split(",")[0];
                GoodsPhoto ossUrl = commodityInformationSheetMapper.selectOssUrl(Integer.parseInt(s));
                commodityAndImage.setOssUrl(ossUrl.getPhotoUrl());
            }
        }
        return commodityAndImages;
    }

    @Override
    public List<CommodityAndImage> selectHotShopping(Integer id) {
        List<CommodityAndImage> commodityAndImages = commodityInformationSheetMapper.selectHotShopping(id);
        for (CommodityAndImage commodityAndImage : commodityAndImages) {
            if(commodityAndImage.getOssUrl() !=null){
                String s = commodityAndImage.getOssUrl().split(",")[0];
                GoodsPhoto ossUrl = commodityInformationSheetMapper.selectOssUrl(Integer.parseInt(s));
                commodityAndImage.setOssUrl(ossUrl.getPhotoUrl());
            }
        }
        return commodityAndImages;
    }
}
