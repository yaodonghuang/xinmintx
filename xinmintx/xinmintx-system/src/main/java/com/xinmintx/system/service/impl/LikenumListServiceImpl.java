package com.xinmintx.system.service.impl;

import java.util.List;
import com.xinmintx.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.LikenumListMapper;
import com.xinmintx.system.domain.LikenumList;
import com.xinmintx.system.service.ILikenumListService;
import com.xinmintx.common.core.text.Convert;

/**
 * 点赞列Service业务层处理
 * 
 * @author xinmintx
 * @date 2019-11-29
 */
@Service
public class LikenumListServiceImpl implements ILikenumListService 
{
    @Autowired
    private LikenumListMapper likenumListMapper;

    /**
     * 查询点赞列
     * 
     * @param id 点赞列ID
     * @return 点赞列
     */
    @Override
    public LikenumList selectLikenumListById(Long id)
    {
        return likenumListMapper.selectLikenumListById(id);
    }

    /**
     * 查询点赞列列表
     * 
     * @param likenumList 点赞列
     * @return 点赞列
     */
    @Override
    public List<LikenumList> selectLikenumListList(LikenumList likenumList)
    {
        return likenumListMapper.selectLikenumListList(likenumList);
    }

    /**
     * 新增点赞列
     * 
     * @param likenumList 点赞列
     * @return 结果
     */
    @Override
    public int insertLikenumList(LikenumList likenumList)
    {
        likenumList.setCreateTime(DateUtils.getNowDate());
        return likenumListMapper.insertLikenumList(likenumList);
    }

    /**
     * 修改点赞列
     * 
     * @param likenumList 点赞列
     * @return 结果
     */
    @Override
    public int updateLikenumList(LikenumList likenumList)
    {
        likenumList.setUpdateTime(DateUtils.getNowDate());
        return likenumListMapper.updateLikenumList(likenumList);
    }

    /**
     * 删除点赞列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLikenumListByIds(String ids)
    {
        return likenumListMapper.deleteLikenumListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除点赞列信息
     * 
     * @param id 点赞列ID
     * @return 结果
     */
    @Override
    public int deleteLikenumListById(Long id)
    {
        return likenumListMapper.deleteLikenumListById(id);
    }
}
