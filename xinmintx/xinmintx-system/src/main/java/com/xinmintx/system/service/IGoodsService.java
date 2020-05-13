package com.xinmintx.system.service;

import com.xinmintx.system.domain.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 商品Service接口
 * 
 * @author xinmintx
 * @date 2019-12-11
 */
public interface IGoodsService 
{
    /**
     * 查询商品
     * 
     * @param id 商品ID
     * @return 商品
     */
    public Goods selectGoodsById(Long id);

    /**
     * 查询商品列表
     * 
     * @param goods 商品
     * @return 商品集合
     */
    public List<GoodsExt> selectGoodsList(Goods goods);

    /**
     * 新增商品
     * 
     * @param goodsBean 商品
     * @return 结果
     */
    public int insertGoods(GoodsBean goodsBean);

    /**
     * 修改商品
     * 
     * @param goods 商品
     * @return 结果
     */
    public int updateGoods(GoodsBean goods);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsByIds(String ids);

    /**
     * 删除商品信息
     * 
     * @param id 商品ID
     * @return 结果
     */
    public int deleteGoodsById(Long id);

    /**
     * 查询一级分类
     * @return
     */
    List<GoodsType> selectParent();

    /**
     * 查询二级分类
     * @param parentCode
     * @return
     */
    List<GoodsType> selectByParentId(Integer parentCode);

    /**
     * 修改商品查询
     * @param id
     * @return
     */
    GoodsExtent selectGoods(Long id);

    /**
     * 查询三级分类
     * @param threeType
     * @return
     */
    List<GoodsType> getThreeType(Integer threeType);

    /**
     * 查询规格
     * @param id
     * @param typeId
     * @return
     */
    List<GoodsBean> getType(Integer id, Integer typeId);

    boolean addSku(Spec spec);

    List<SpecSelect> specSelect(Integer id);

    List<GoodsSkuBean> querySku(String goodsId);

    GoodsSku selectMaxSpec(Integer id);

    GoodsSku selectMaxSpecValue(Integer id);

    int insertParameter(Integer id,String[] attributeName, String[] attributeValue);

    String selectParameter(String id);

    int deleteSku(Integer id);
}
