package com.xinmintx.system.mapper;

import com.xinmintx.system.domain.LikenumList;
import java.util.List;

/**
 * 点赞列Mapper接口
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
public interface LikenumListMapper 
{
    /**
     * 查询点赞列
     * 
     * @param id 点赞列ID
     * @return 点赞列
     */
    public LikenumList selectLikenumListById(Long id);

    /**
     * 查询点赞列列表
     * 
     * @param likenumList 点赞列
     * @return 点赞列集合
     */
    public List<LikenumList> selectLikenumListList(LikenumList likenumList);

    /**
     * 新增点赞列
     * 
     * @param likenumList 点赞列
     * @return 结果
     */
    public int insertLikenumList(LikenumList likenumList);

    /**
     * 修改点赞列
     * 
     * @param likenumList 点赞列
     * @return 结果
     */
    public int updateLikenumList(LikenumList likenumList);

    /**
     * 删除点赞列
     * 
     * @param id 点赞列ID
     * @return 结果
     */
    public int deleteLikenumListById(Long id);

    /**
     * 批量删除点赞列
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLikenumListByIds(String[] ids);
}
