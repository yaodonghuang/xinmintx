package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.GoodPtcodeInfoMapper;
import com.xinmintx.hstx.mapper.GoodPtcodeMapper;
import com.xinmintx.hstx.mapper.GoodPtgoodMapper;
import com.xinmintx.hstx.mapper.GoodsSkuMapper;
import com.xinmintx.hstx.pojo.bo.GoodsSkuBo;
import com.xinmintx.hstx.pojo.entity.GoodsOrderType;
import com.xinmintx.hstx.pojo.po.*;
import com.xinmintx.hstx.pojo.vo.GoodsOrderParentInterimVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.pojo.vo.ShopVo;
import com.xinmintx.hstx.service.GoodsService;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.PtOrderService;
import com.xinmintx.hstx.util.DateUtil;
import com.xinmintx.hstx.util.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/18 0018
 * @time: 上午 9:59
 * @Description:
 */
@Service
public class PtOrderServiceImpl implements PtOrderService {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private GoodPtgoodMapper goodPtgoodMapper;
    @Autowired
    private GoodPtcodeMapper goodPtcodeMapper;
    @Autowired
    private GoodPtcodeInfoMapper goodPtcodeInfoMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    /**
     * 发起拼团
     *
     * @param shopVo
     * @return
     */
    @Override
    public ResultCode initiateGroup(ShopVo shopVo) {
        Member member = memberService.findMemberByToken(shopVo.getToken());
        GoodPtgood goodPtgood = goodPtgoodMapper.selectById(shopVo.getPtGoodId());
        GoodsSku goodsSku = goodsSkuMapper.selectById(shopVo.getSkuId());
        GoodsSkuBo goodsSkuBo = goodsService.getGoodsSkuBo(goodsSku, shopVo.getTotal());

        GoodsOrderParentInterimVo orderParentInterimVo = createPtGoodsOrder(member, goodPtgood, goodsSkuBo, GoodsOrderType.GOODS_GROUP_SETTLE);
        //添加团长表信息
        GoodPtcode goodPtcode = addGoodPtCode(goodPtgood, shopVo, member);
        //添加拼团信息表
        addGoodPtcodeInfo(member, goodPtcode, orderParentInterimVo, 1);
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(orderParentInterimVo);
        return resultCode;
    }

    /**
     * 参与拼团
     *
     * @param shopVo
     * @return
     */
    @Override
    public ResultCode joinGroup(ShopVo shopVo) {
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("SUCCESS");
        //获取团长
        GoodPtcode goodPtcode = goodPtcodeMapper.selectById(shopVo.getPtCodeId());
        if (goodPtcode == null) {
            //该团不存在
            resultCode.setMsg("团不存在");
            return resultCode;
        }
        //获取团员列表
        List<GoodPtcodeInfo> goodPtcodeInfos = new LambdaQueryChainWrapper<>(goodPtcodeInfoMapper)
                .eq(GoodPtcodeInfo::getPid, goodPtcode.getId())
                .eq(GoodPtcodeInfo::getIsJoin, 1)
                .list();
        //判断用户是否已经在该团中
        Member member = memberService.findMemberByToken(shopVo.getToken());
        if (goodPtcodeInfos != null && goodPtcodeInfos.size() > 0) {
            if (goodPtcode.getPtnumber() == goodPtcodeInfos.size()) {
                resultCode.setMsg("该团已满");
                return resultCode;
            }
            for (GoodPtcodeInfo goodPtcodeInfo : goodPtcodeInfos) {
                if (member.getId().equals(goodPtcodeInfo.getUid())) {
                    resultCode.setMsg("已经参与该团,请勿重复参加");
                    return resultCode;
                }
            }
        } else {
            resultCode.setMsg("团不存在,或已关闭");
            return resultCode;
        }
        GoodPtgood goodPtgood = goodPtgoodMapper.selectById(goodPtcode.getPtgoodsId());
        GoodsSkuBo goodsSkuBo = goodsService.getGoodsSkuBo(goodsSkuMapper.selectById(shopVo.getSkuId()), shopVo.getTotal());
        int i = DateUtil.compareDateWithNow(goodPtcode.getEndtimeDatetime());
        if (goodPtcode.getStatus() != 1 || i < 0) {
            //该团不可加入,团已关闭
            resultCode.setMsg("团不存在,或已关闭");
            return resultCode;
        }
        GoodsOrderParentInterimVo orderParentInterimVo = createPtGoodsOrder(member, goodPtgood, goodsSkuBo, GoodsOrderType.GOODS_GROUP_SETTLE);
        //添加拼团信息表
        addGoodPtcodeInfo(member, goodPtcode, orderParentInterimVo, 0);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        resultCode.setData(orderParentInterimVo);
        return resultCode;
    }

    /**
     * 创建临时表数据
     *
     * @param member
     * @param goodPtgood
     * @param goodsSkuBo
     * @param orderType
     * @return
     */
    private GoodsOrderParentInterimVo createPtGoodsOrder(Member member, GoodPtgood goodPtgood, GoodsSkuBo goodsSkuBo, Integer orderType) {
        BigDecimal price = goodsSkuBo.getPrice().subtract(goodPtgood.getPtPrice());
        BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(goodsSkuBo.getTotal()));
        GoodsOrderParentInterim parentInterim = new GoodsOrderParentInterim();
        parentInterim.setMemberId(member.getId());
        parentInterim.setTotalPrice(totalPrice);
        parentInterim.setDescription(goodsSkuBo.getGoodsName());
        parentInterim.setOrderType(orderType);
        parentInterim.setStatus(1);
        parentInterim.insert();
        GoodsOrderParentInterimVo goodsOrderParentInterimVo = FieldUtils.fieldTrans(parentInterim, GoodsOrderParentInterimVo.class);
        List<GoodsOrderDetailInterim> details = new ArrayList<>();
        GoodsOrderInterim order = new GoodsOrderInterim();
        order.setOrderId(parentInterim.getId());
        order.setMemberId(member.getId());
        order.setOrderState(1);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setIfPay(0);
        order.setTotalAmount(totalPrice);
        order.setFactoryId(goodsSkuBo.getRelateId());
        order.setOrderType(2);
        order.insert();
        GoodsOrderDetailInterim detail = new GoodsOrderDetailInterim();
        detail.setMemberId(member.getId());
        detail.setOrderId(order.getId());
        detail.setGoodsId(goodsSkuBo.getGoodsId());
        detail.setSkuId(goodsSkuBo.getId());
        detail.setPrice(price);
        detail.setQuantity(goodsSkuBo.getTotal());
        detail.setGoodsName(goodsSkuBo.getGoodsName());
        detail.setGoodsPic(goodsSkuBo.getPhotoUrl());
        detail.setOrderState(1);
        detail.setTotalAmount(totalPrice);
        detail.setLack(0);
        detail.setWeight(goodsSkuBo.getGoodsWeight());
        detail.setSpecName(goodsSkuBo.getSpecName());
        detail.setCreateTime(new Date());
        detail.setUpdateTime(new Date());
        detail.insert();
        details.add(detail);
        goodsOrderParentInterimVo.setDetails(details);
        return goodsOrderParentInterimVo;
    }

    /**
     * 保存团长信息
     *
     * @param goodPtgood
     * @param shopVo
     * @param member
     * @return
     */
    private GoodPtcode addGoodPtCode(GoodPtgood goodPtgood, ShopVo shopVo, Member member) {
        GoodPtcode goodPtcode = new GoodPtcode();
        goodPtcode.setUid(member.getId());
        goodPtcode.setPtgoodsId(goodPtgood.getPtgoodsId());
        goodPtcode.setPtcode(UUID.randomUUID().toString());
        goodPtcode.setPtnumber(goodPtgood.getPtSize());
        long millis = System.currentTimeMillis();
        millis += (goodPtgood.getPtValidhours() * 60 * 60 * 1000);
        Date endDate = new Date(millis);
        goodPtcode.setAddtimeDatetime(new Date());
        goodPtcode.setEndtimeDatetime(endDate);
        if (shopVo.getPtUserId() == null) {
            goodPtcode.setPtType(1);
        } else {
            goodPtcode.setPtType(2);
            goodPtcode.setPtUserId(shopVo.getPtUserId());
        }
        goodPtcodeMapper.insert(goodPtcode);
        return goodPtcode;
    }

    /**
     * 添加团员信息
     *
     * @param member
     * @param goodPtcode
     * @param orderParentInterimVo
     * @param header
     * @return
     */
    private GoodPtcodeInfo addGoodPtcodeInfo(Member member, GoodPtcode goodPtcode, GoodsOrderParentInterimVo orderParentInterimVo, Integer header) {
        GoodsOrderDetailInterim detailInterim = orderParentInterimVo.getDetails().get(0);
        GoodPtcodeInfo goodPtcodeInfo = new GoodPtcodeInfo();
        goodPtcodeInfo.setUid(member.getId());
        goodPtcodeInfo.setSkuId(detailInterim.getSkuId());
        goodPtcodeInfo.setPid(goodPtcode.getId());
        goodPtcodeInfo.setPtcode(goodPtcode.getPtcode());
        goodPtcodeInfo.setPtgoodId(goodPtcode.getPtgoodsId());
        goodPtcodeInfo.setPerPrice(detailInterim.getPrice());
        goodPtcodeInfo.setNumber(detailInterim.getQuantity());
        goodPtcodeInfo.setAddtimeDatetime(goodPtcode.getAddtimeDatetime());
        goodPtcodeInfo.setEndtimeDatetime(goodPtcode.getEndtimeDatetime());
        goodPtcodeInfo.setIsHeader(header);
        goodPtcodeInfo.setLack(0);
        goodPtcodeInfo.setIsJoin(0);
        goodPtcodeInfo.setGoodsOrderId(orderParentInterimVo.getId());
        goodPtcodeInfoMapper.insert(goodPtcodeInfo);
        return goodPtcodeInfo;
    }
}
