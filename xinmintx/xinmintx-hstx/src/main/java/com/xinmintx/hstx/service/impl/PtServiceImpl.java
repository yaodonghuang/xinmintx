package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinmintx.hstx.mapper.*;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.service.PtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PtServiceImpl implements PtService {

    @Autowired
    private GoodPtgoodMapper goodPtgoodMapper;

    @Autowired
    private GoodsPtUserMapper goodsPtUserMapper;

    @Autowired
    private GoodPtcodeMapper goodPtcodeMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private GoodPtcodeInfoMapper goodPtcodeInfoMapper;

    /**
     * 获取该商品的全部拼团信息
     *
     * @param goodsId   商品id
     * @param groupType 拼团类型
     * @return
     */
    @Override
    public List<HashMap> getPt(Integer goodsId, Integer groupType) {
        List<HashMap> list = new ArrayList<>();
        //获取拼团表
        Map<String, Object> map = new HashMap<>();
        map.put("goods_id", goodsId);
        map.put("group_type", groupType);
        map.put("is_sale", 1);
        List<GoodPtgood> goodPtgoods = goodPtgoodMapper.selectByMap(map);
        //走这里说明该商品没有拼团活动
        if (goodPtgoods == null || goodPtgoods.size() == 0) {
            return null;
        } else {
            //遍历拼团商品表
            for (GoodPtgood goodPtgood : goodPtgoods) {
                //获取团长表
                Map<String, Object> ptMap = new HashMap<>();
                ptMap.put("ptgoods_id", goodPtgood.getPtgoodsId());
                ptMap.put("status", 1);
                List<GoodPtcode> goodPtcodes = goodPtcodeMapper.selectByMap(ptMap);
                //走这里说明有拼团活动,但没人发起拼团
                if (goodPtcodes == null || goodPtcodes.size() == 0) {
                    continue;
                } else {
                    //遍历团长表
                    for (GoodPtcode goodPtcode : goodPtcodes) {
                        HashMap<Object, Object> hashMap = new HashMap<>();
                        //获取团长
                        Integer uid = goodPtcode.getUid();
                        Member member = memberMapper.selectById(uid);
                        hashMap.put("header", member);
                        hashMap.put("id", goodPtcode.getId());
                        //根据团长表id查询拼团成员
                        Map<String, Object> ptCodeMap = new HashMap<>();
                        ptCodeMap.put("pid", goodPtcode.getId());
                        //查询所有已经入团的非团长成员
                        ptCodeMap.put("is_header", 0);
                        ptCodeMap.put("is_join", 1);
                        List<GoodPtcodeInfo> goodPtcodeInfos = goodPtcodeInfoMapper.selectByMap(ptCodeMap);

                        //走这里说明有团长发起拼团,但没人参加拼团
                        if (goodPtcodeInfos == null || goodPtcodeInfos.size() == 0) {
                            hashMap.put("members", new ArrayList<>());
                            hashMap.put("nowTime", System.currentTimeMillis());
                            hashMap.put("endTime", goodPtcode.getEndtimeDatetime().getTime());
                            hashMap.put("position", goodPtcode.getPtnumber());
                            //走这里说明有成员加团
                        } else {
                            List<Object> memberList = new ArrayList<>();
                            for (GoodPtcodeInfo goodPtcodeInfo : goodPtcodeInfos) {
                                //获取成员
                                Integer memberId = goodPtcodeInfo.getUid();
                                Member members = memberMapper.selectById(memberId);
                                memberList.add(members);
                            }
                            hashMap.put("members", memberList);
                            hashMap.put("nowTime", System.currentTimeMillis());
                            hashMap.put("endTime", goodPtcode.getEndtimeDatetime().getTime());
                            hashMap.put("position", goodPtcode.getPtnumber());
                        }
                        list.add(hashMap);
                    }
                }
            }
        }
        return list;

    }


    /**
     * 查询惠商拼团人数
     *
     * @param goodsId 商品id
     * @return goodPtgoods
     */
    @Override
    public List<GoodPtgood> PtGetHsStyle(Integer goodsId) {

        QueryWrapper<GoodPtgood> wrapper = new QueryWrapper<>();
        wrapper.ge("end_time", new Date()).eq("goods_id", goodsId).eq("group_type", 2).eq("is_sale", 1);
        List<GoodPtgood> goodPtgoods = goodPtgoodMapper.selectList(wrapper);
        return goodPtgoods;
    }

    /**
     * 查询代理商拼团人数
     *
     * @param goodsPtUserId 拼团中间表id
     * @return goodPtId
     */
    @Override
    public List<GoodPtgood> PtGetProxyStyle(Integer goodsPtUserId) {
        GoodsPtUser goodsPtUser = goodsPtUserMapper.selectById(goodsPtUserId);
        GoodPtgood goodPtgood = goodPtgoodMapper.selectById(goodsPtUser.getGoodPtId());
        List<GoodPtgood> list = new ArrayList<>();
        list.add(goodPtgood);
        return list;
    }

    /**
     * 获取拼团详情
     *
     * @param id 团长id
     * @return
     */
    @Override
    public HashMap getPtDetail(Integer id) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        GoodPtcode goodPtcode = goodPtcodeMapper.selectById(id);
        if (goodPtcode != null) {
            //获取团长
            Member member = memberMapper.selectById(goodPtcode.getUid());
            hashMap.put("header", member);
            hashMap.put("id", goodPtcode.getId());

            Map<String, Object> map = new HashMap<>();
            map.put("pid", goodPtcode.getId());
            map.put("is_header", 0);
            map.put("is_join", 1);
            List<GoodPtcodeInfo> goodPtcodeInfos = goodPtcodeInfoMapper.selectByMap(map);
            if (goodPtcodeInfos == null || goodPtcodeInfos.size() == 0) {
                hashMap.put("members", new ArrayList<>());
                hashMap.put("nowTime", new Date());
                hashMap.put("endTime", goodPtcode.getEndtimeDatetime());
                hashMap.put("position", goodPtcode.getPtnumber());
            } else {
                List<Object> memberList = new ArrayList<>();
                for (GoodPtcodeInfo goodPtcodeInfo : goodPtcodeInfos) {
                    //获取成员
                    Integer memberId = goodPtcodeInfo.getUid();
                    Member members = memberMapper.selectById(memberId);
                    memberList.add(members);
                }
                hashMap.put("members", memberList);
                hashMap.put("endTime", goodPtcode.getEndtimeDatetime());
                hashMap.put("nowTime", new Date());
                hashMap.put("position", goodPtcode.getPtnumber());
            }
        }
        return hashMap;
    }

}
