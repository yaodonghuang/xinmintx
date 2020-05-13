package com.xinmintx.factory.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.factory.exception.BaseRunException;
import com.xinmintx.factory.exception.errorCodeStants.ErrorCodeConStants;
import com.xinmintx.factory.mapper.VenderMapper;
import com.xinmintx.factory.mapper.po.GoodsTypeMapper;
import com.xinmintx.factory.model.Factory;
import com.xinmintx.factory.model.po.GoodsType;
import com.xinmintx.factory.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/18
 * @time: 14:33
 * @Description:
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private VenderMapper venderMapper;

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    //过滤登录信息
    public Factory verifyToken(String token) {
        if (null == token || "".equals(token.trim())) {
            throw new BaseRunException(ErrorCodeConStants.SYSTEM_ABNORMAL_TOKEN);
        }
        Factory factory = venderMapper.queryByToken(token);
        if (null == factory || 0 == factory.getFactoryId()) {
            throw new BaseRunException(ErrorCodeConStants.SYSTEM_ABNORMAL_TOKEN);
        }
        return factory;
    }

    @Override
    public List<GoodsType> queryGoodsType(String token) {
        List<GoodsType> goodsTypeList = new LambdaQueryChainWrapper<>(goodsTypeMapper).list();
        if (null == goodsTypeList || 0 == goodsTypeList.size()){
            throw new BaseRunException(ErrorCodeConStants.SYSTEM_OBJECT_ALREADY_NONENTITY);
        }
        return goodsTypeList;
    }
}
