package com.xinmintx.system.service;

import com.xinmintx.system.domain.UnitPhoto;
import java.util.List;

/**
 * 照片Service接口
 * 
 * @author xinmintx
 * @date 2019-11-11
 */
public interface IUnitPhotoService 
{

    int updateUnitPhotoByIds(UnitPhoto unitPhoto, Integer[] photo);

    List<UnitPhoto> selelcUnitPhoteByCompanyId(int companyId);

    void deleteUnitPhotoByCompanyIdWithoutIds(int companyId,Integer[] ids);

}
