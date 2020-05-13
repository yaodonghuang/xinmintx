package com.xinmintx.system.service.impl;

import com.xinmintx.system.domain.UnitPhoto;
import com.xinmintx.system.domain.UnitPhotoExample;
import com.xinmintx.system.mapper.UnitPhotoMapper;
import com.xinmintx.system.service.IUnitPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 照片Service业务层处理
 *
 * @author xinmintx
 * @date 2019-11-11
 */
@Service
public class UnitPhotoServiceImpl implements IUnitPhotoService {
    @Autowired
    private UnitPhotoMapper unitPhotoMapper;

    @Override
    public int updateUnitPhotoByIds(UnitPhoto unitPhoto, Integer[] id) {
        List<Integer> ids = Arrays.asList(id);
        UnitPhotoExample example = new UnitPhotoExample();
        UnitPhotoExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return unitPhotoMapper.updateByExampleSelective(unitPhoto, example);
    }

    @Override
    public List<UnitPhoto> selelcUnitPhoteByCompanyId(int companyId) {
        UnitPhotoExample example = new UnitPhotoExample();
        UnitPhotoExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        example.setOrderByClause("type");
        return unitPhotoMapper.selectByExample(example);
    }

    @Override
    public void deleteUnitPhotoByCompanyIdWithoutIds(int companyId,Integer[] ids) {
        UnitPhotoExample example = new UnitPhotoExample();
        UnitPhotoExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andIdNotIn(Arrays.asList(ids));
        unitPhotoMapper.deleteByExample(example);
    }
}
