package com.xinmintx.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xinmintx.common.constant.UserConstants;
import com.xinmintx.common.core.domain.Ztree;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.system.domain.GoodsTypeBean;
import com.xinmintx.system.domain.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.GoodsTypeMapper;
import com.xinmintx.system.domain.GoodsType;
import com.xinmintx.system.service.IGoodsTypeService;
import com.xinmintx.common.core.text.Convert;

/**
 * 商品分类Service业务层处理
 *
 * @author xinmintx
 * @date 2019-12-12
 */
@Service
public class GoodsTypeServiceImpl implements IGoodsTypeService
{
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    /**
     * 查询商品分类
     *
     * @param id 商品分类ID
     * @return 商品分类
     */
    @Override
    public GoodsType selectGoodsTypeById(Long id)
    {
        return goodsTypeMapper.selectGoodsTypeById(id);
    }

    /**
     * 查询商品分类列表
     *
     * @param goodsType 商品分类
     * @return 商品分类
     */
    @Override
    public List<GoodsType> selectGoodsTypeList(GoodsType goodsType)
    {
        return goodsTypeMapper.selectGoodsTypeList(goodsType);
    }

    /**
     * 新增商品分类
     *
     * @param goodsType 商品分类
     * @return 结果
     */
    @Override
    public int insertGoodsType(GoodsType goodsType) {
        goodsType.setLevel(goodsType.getLevel() + 1);
        return goodsTypeMapper.insertGoodsType(goodsType);
    }

    /**
     * 修改商品分类
     *
     * @param goodsType 商品分类
     * @return 结果
     */
    @Override
    public int updateGoodsType(GoodsType goodsType)
    {

        return goodsTypeMapper.updateGoodsType(goodsType);
    }

    /**
     * 删除商品分类对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGoodsTypeByIds(String ids)
    {
        return goodsTypeMapper.deleteGoodsTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品分类信息
     *
     * @param id 商品分类ID
     * @return 结果
     */
    @Override
    public int deleteGoodsTypeById(Long id)
    {
        return goodsTypeMapper.deleteGoodsTypeById(id);
    }

    @Override
    public List<Ztree> menuTreeData() {
        List<GoodsType> goodsTypes = goodsTypeMapper.selectTreeData();
        List<Ztree> ztrees = initZtree(goodsTypes);
        return ztrees;
    }

    /**
     * 对象转菜单树
     *
     * @param goodsTypes 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<GoodsType> goodsTypes)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (GoodsType goodsType : goodsTypes){
            Ztree ztree = new Ztree();
            ztree.setId(goodsType.getId());
            ztree.setpId(goodsType.getParentId());
            ztree.setName(goodsType.getTypeName());
            ztree.setTitle(goodsType.getTypeName());
            if (goodsType.getLevel().equals(2L)) {
                ztree.setChecked(true);
            }else{
                ztree.setChecked(false);
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    @Override
    public String checkMenuNameUnique(GoodsType goodsType) {
        Long id = StringUtils.isNull(goodsType.getId()) ? -1L : goodsType.getId();
        GoodsType info = goodsTypeMapper.checkMenuNameUnique(goodsType.getTypeName(), goodsType.getParentId());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue())
        {
            return UserConstants.MENU_NAME_NOT_UNIQUE;
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }

    @Override
    public int selectCountMenuByParentId(Long parentId) {
        return goodsTypeMapper.selectCountMenuByParentId(parentId);
    }
}
