package com.xinmintx.hstx.service.impl;

import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.mapper.LikenumListMapper;
import com.xinmintx.hstx.pojo.po.LikenumList;
import com.xinmintx.hstx.service.IntegralAccessService;
import com.xinmintx.hstx.service.LikeNumListService;
import com.xinmintx.hstx.service.MemberIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LikeNumListServiceImpl implements LikeNumListService {
    @Autowired
    private LikenumListMapper likenumListMapper;

    @Autowired
    private IntegralAccessService integralAccessService;

    @Autowired
    private MemberIntegralService memberIntegralService;


    /**
     * 添加点赞
     *
     * @param memberCheckInId 打卡表id
     * @param memberName      点赞人名字
     * @return 点赞是否成功状态
     */
    @Override
    public ResultCode putLike(int memberCheckInId, String memberName, int id) {
        //先去查询是否已经对该条文案点过赞
        Map<String,Object> map = new HashMap<>();
        map.put("likenum_int_id",memberCheckInId);
        map.put("likenum_name",memberName);
        List<LikenumList> likenumLists = likenumListMapper.selectByMap(map);

        ResultCode resultCode = new ResultCode();

        //走这里说明还没点过赞
        if (likenumLists == null || likenumLists.size() == 0) {
            LikenumList likenumList = new LikenumList();
            likenumList.setLikenumIntId(memberCheckInId);
            likenumList.setLikenumName(memberName);
            likenumList.setCreateTime(new Date());
            likenumListMapper.insert(likenumList);
            resultCode.setCode(200);
            resultCode.setMsg("点赞成功");
            //查询点赞的积分
            double integeral = integralAccessService.getIntegeral(3);
            //插入积分
            memberIntegralService.insertIntegral(id, integeral);
            return resultCode;
        }
        //走这里说明点过赞了
        resultCode.setCode(500);
        resultCode.setMsg("已经点过赞了");
        return resultCode;
    }

    /**
     * 查询点赞数并判断当前用户是否点过赞
     *
     * @param memberCheckInId 打卡id
     * @param memberName      当前人的名字
     * @return 点赞个数
     */
    @Override
    public List<Object> countLike(int memberCheckInId, String memberName) {
        List<Object> list = new ArrayList<>();
        //根据打卡表id查询点赞的个数
        Map<String,Object> map = new HashMap<>();
        map.put("likenum_int_id",memberCheckInId);
        List<LikenumList> likenumLists = likenumListMapper.selectByMap(map);

        //查询当前点赞用户是否已经点过赞
        map = new HashMap<>();
        map.put("likenum_name",memberName);
        map.put("likenum_int_id",memberCheckInId);
        List<LikenumList> likenumList = likenumListMapper.selectByMap(map);
        //走这里说明还没点过赞
        if (likenumList == null || likenumList.size() == 0) {
            list.add(false);
            list.add(likenumLists.size());
            //走这里说明点过赞了
        } else {
            list.add(true);
            list.add(likenumLists.size());
        }
        return list;
    }


    /**
     * 查询点赞最近点赞人
     *
     * @param memberCheckInId 打卡表id
     * @return 点赞人名字
     */
    @Override
    public String likeName(int memberCheckInId) {
        Map<String,Object> map = new HashMap<>();
        map.put("likenum_int_id",memberCheckInId);
        List<LikenumList> likenumLists = likenumListMapper.selectByMap(map);
        if (likenumLists == null || likenumLists.size() == 0) {
            return null;
        }
        return likenumLists.get(likenumLists.size() - 1).getLikenumName();
    }

}
