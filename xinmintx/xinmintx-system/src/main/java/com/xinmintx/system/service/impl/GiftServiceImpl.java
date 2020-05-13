package com.xinmintx.system.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xinmintx.common.utils.DateUtils;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.system.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xinmintx.system.mapper.GiftMapper;
import com.xinmintx.system.domain.Gift;
import com.xinmintx.system.service.IGiftService;
import com.xinmintx.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 礼包Service业务层处理
 *
 * @author xinmintx
 * @date 2019-12-16
 */
@Transactional
@Service
public class GiftServiceImpl implements IGiftService {
    @Autowired
    private GiftMapper giftMapper;

    /**
     * 查询礼包
     *
     * @param id 礼包ID
     * @return 礼包
     */
    @Override
    public Gift selectGiftById(Long id) {
        return giftMapper.selectGiftById(id);
    }

    /**
     * 查询礼包列表
     *
     * @param gift 礼包
     * @return 礼包
     */
    @Override
    public List<Gift> selectGiftList(Gift gift) {
        getBeginAndEndTime(gift);
        List<Gift> giftList = giftMapper.selectGiftList(gift);
        // 对返回数据进行处理
        dealResultInfo(giftList);
        return giftList;
    }

    /**
     * 新增礼包
     *
     * @param gift 礼包
     * @return 结果
     */
    @Override
    public int insertGift(Gift gift) {
        gift.setCreateTime(DateUtils.getNowDate());
        return giftMapper.insertGift(gift);
    }

    /**
     * 修改礼包
     *
     * @param gift 礼包
     * @return 结果
     */
    @Override
    public int updateGift(Gift gift) {
        gift.setUpdateTime(DateUtils.getNowDate());
        return giftMapper.updateGift(gift);
    }

    /**
     * 删除礼包对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGiftByIds(String ids) {
        return giftMapper.deleteGiftByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除礼包信息
     *
     * @param id 礼包ID
     * @return 结果
     */
    @Override
    public int deleteGiftById(Long id) {
        return giftMapper.deleteGiftById(id);
    }

    @Override
    public List<Member> selectReceiptor(int giftId) {
        List<Member> list = giftMapper.selectReceiptor(giftId);
        return list;
    }

    @Override
    public int delGiftByIds(Long ids) {
        return giftMapper.delGiftByIds(ids);
    }

    // 获取开始和结束的时间
    private static void getBeginAndEndTime(Gift gift) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> dateMap = gift.getParams();
        if (dateMap.get("beginEndDate") != null) {
            String beginEndDate = (String) dateMap.get("beginEndDate");
            if(StringUtils.isNotEmpty(beginEndDate)){
                try {
                    String beginTimeStamp = String.valueOf(sdf.parse(beginEndDate).getTime() / 1000);
                    gift.setBeginTimeStamp(beginTimeStamp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        if (dateMap.get("endEndDate") != null) {
            String endEndDate = (String) dateMap.get("endEndDate");
            if(StringUtils.isNotEmpty(endEndDate)){
                try {
                    String endTimeStamp = String.valueOf(sdf.parse(endEndDate).getTime() / 1000);
                    gift.setEndTimeStamp(endTimeStamp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 返回数据处理
    private static void dealResultInfo(List<Gift> giftList) {
        if (giftList != null && giftList.size() > 0) {
            giftList.forEach(gft -> {
                if ("platform".equalsIgnoreCase(gft.getGiftType())) {
                    gft.setGiftType("平台类型");
                } else if ("merchant".equalsIgnoreCase(gft.getGiftType())) {
                    gft.setGiftType("商户类型");
                } else if ("branchOffice".equalsIgnoreCase(gft.getGiftType())) {
                    gft.setGiftType("分公司类型");
                }
                if ("cashCoupon".equalsIgnoreCase(gft.getGiftGroup())) {
                    gft.setGiftGroup("代金券");
                } else if ("giftPackage".equalsIgnoreCase(gft.getGiftGroup())) {
                    gft.setGiftGroup("礼包");
                } else if ("birthGiftPackage".equalsIgnoreCase(gft.getGiftGroup())) {
                    gft.setGiftGroup("生日礼包");
                }
                if ("0".equalsIgnoreCase(gft.getGiftState())) {
                    gft.setGiftState("启用");
                } else if ("1".equalsIgnoreCase(gft.getGiftState())) {
                    gft.setGiftState("禁用");
                }
            });
        }
    }
}
