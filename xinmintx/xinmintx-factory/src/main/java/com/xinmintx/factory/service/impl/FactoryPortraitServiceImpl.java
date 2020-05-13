package com.xinmintx.factory.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.exception.BaseRunException;
import com.xinmintx.factory.exception.errorCodeStants.ErrorCodeConStants;
import com.xinmintx.factory.mapper.VenderMapper;
import com.xinmintx.factory.mapper.po.FactoryNameMapper;
import com.xinmintx.factory.model.Factory;
import com.xinmintx.factory.model.po.FactoryName;
import com.xinmintx.factory.service.FactoryPortraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryPortraitServiceImpl  implements FactoryPortraitService {

    @Autowired
    private FactoryNameMapper factoryNameMapper;
    @Autowired
    private VenderMapper venderMapper;

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
    public ResultCode addFactoryImge(String token, FactoryName factoryName) {
        ResultCode resultCode = new ResultCode();
        Factory factory = verifyToken(token);
        FactoryName one = new LambdaQueryChainWrapper<>(factoryNameMapper)
                .eq(FactoryName::getFactoryId, factory.getFactoryId())
                .one();
        if (one != null) {
            one.setFactoryName(factoryName.getFactoryName());
            one.setFactoryPortrait(factoryName.getFactoryPortrait());
            boolean flag = one.updateById();
            if (flag) {
                resultCode.setCode(200);
                resultCode.setMsg("修改成功");
            } else {
                resultCode.setCode(500);
                resultCode.setMsg("修改失败");
            }
        } else {
            factoryName.setFactoryId(factory.getFactoryId().intValue());
            boolean flag = factoryName.insert();
            if (flag) {
                resultCode.setCode(200);
                resultCode.setMsg("添加成功");
            } else {
                resultCode.setCode(200);
                resultCode.setMsg("添加失败");
            }
        }
        return resultCode;
    }

    @Override
    public FactoryName queryFactoryPortraitByFactoryId(String token) {
        Factory factory = verifyToken(token);
        FactoryName factoryName = new LambdaQueryChainWrapper<>(factoryNameMapper)
                .eq(FactoryName::getFactoryId, factory.getFactoryId())
                .one();
        if (null == factoryName) {
            throw new BaseRunException(ErrorCodeConStants.SYSTEM_OBJECT_ALREADY_NONENTITY);
        }
        return factoryName;
    }
}
