package com.xinmintx.system.service.impl;

import com.xinmintx.common.core.text.Convert;
import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.system.domain.Factory;
import com.xinmintx.system.mapper.FactoryMapper;
import com.xinmintx.system.service.IFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 厂家信息Service业务层处理
 *
 * @author xinmintx
 * @date 2019-11-29
 */
@Transactional
@Service
public class FactoryServiceImpl implements IFactoryService {
    @Autowired
    private FactoryMapper factoryMapper;

    /**
     * 查询厂家信息
     *
     * @param factoryId 厂家信息ID
     * @return 厂家信息
     */
    @Override
    public Factory selectFactoryById(Long factoryId) {
        return factoryMapper.selectFactoryById(factoryId);
    }

    /**
     * 查询厂家信息列表
     *
     * @param factory 厂家信息
     * @return 厂家信息
     */
    @Override
    public List<Factory> selectFactoryList(Factory factory) {
        return factoryMapper.selectFactoryList(factory);
    }

    /**
     * 新增厂家信息
     *
     * @param factory 厂家信息
     * @return 结果
     */
    @Override
    public int insertFactory(Factory factory) {
        if (factory == null) {
            return 0;
        }
        if (StringUtils.isNotEmpty(factory.getName()) && StringUtils.isNotEmpty(factory.getPhone())) {
            // 验证厂家账号是否已经存在,存在则不创建,跳出方法
            Integer ifExistsFactory = factoryMapper.ifExistsFactory(factory.getName(), factory.getPhone());
            if (ifExistsFactory != null && ifExistsFactory == 1) {
                return 0;
            }
        } else {// 厂家名称和手机号必填,没有的话不创建
            return 0;
        }
        int result;
        if(StringUtils.isEmpty(factory.getSalt())){
            factory.setSalt(StringUtils.randomSalt());
            factory.setPassword(StringUtils.encryptPassword(factory.getName(), factory.getPassword(), factory.getSalt()));
        }
        factory.setCreateTime(DateUtils.getNowDate());
        String code = StringUtils.getCode(9, "");
        Boolean flag = true;
        while (flag) {
            // 查询code是否唯一
            Integer ifExists = factoryMapper.ifExistsCode(code);
            if (ifExists == null) {// 不存在
                flag = false;
            } else {// 存在继续生成
                code = StringUtils.getCode(9, "");
            }
        }
        factory.setFactoryCode(code);
        result = factoryMapper.insertFactory(factory);
        return result;
    }

    /**
     * 修改厂家信息
     *
     * @param factory 厂家信息
     * @return 结果
     */
    @Override
    public int updateFactory(Factory factory) {
        factory.setUpdateTime(DateUtils.getNowDate());
        return factoryMapper.updateFactory(factory);
    }

    /**
     * 删除厂家信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFactoryByIds(String ids) {
        return factoryMapper.deleteFactoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除厂家信息信息
     *
     * @param factoryId 厂家信息ID
     * @return 结果
     */
    @Override
    public int deleteFactoryById(Long factoryId) {
        return factoryMapper.deleteFactoryById(factoryId);
    }

    @Override
    public List<Factory> selectFactory(String name) {
        List<Factory> list = factoryMapper.selectFactory(name);
        if (list.size()>0){
            return list;
        }
        return null;
    }
}
