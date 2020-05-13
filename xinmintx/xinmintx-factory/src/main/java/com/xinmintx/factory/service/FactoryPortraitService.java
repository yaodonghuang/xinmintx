package com.xinmintx.factory.service;

import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.model.Factory;
import com.xinmintx.factory.model.po.FactoryName;

public interface FactoryPortraitService {

    /**
     * @author: create by hjh
     * @time: 2020/3/18 11:36
     * @Description: 返回商户头像信息
     * @param token 获取登录信息
     * @return: com.xinmintx.factory.model.po.FactoryName
     */
    FactoryName queryFactoryPortraitByFactoryId(String token);

    ResultCode addFactoryImge(String token, FactoryName factoryName);
}
