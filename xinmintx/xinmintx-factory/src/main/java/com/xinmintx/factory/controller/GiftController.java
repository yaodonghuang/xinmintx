package com.xinmintx.factory.controller;

import com.xinmintx.factory.common.ResultCode;
import com.xinmintx.factory.mapper.GiftMapper;
import com.xinmintx.factory.model.Gift;
import com.xinmintx.factory.model.Member;
import com.xinmintx.factory.model.MemberGift;
import com.xinmintx.factory.service.IGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 礼包controller
 */
@RestController
@RequestMapping("/gift")
@Transactional
public class GiftController {
    @Autowired
    private IGiftService giftService;
    @Autowired
    private GiftMapper giftMapper;

    /**
     * 搜索平台礼包
     *
     * @return
     */
    @RequestMapping(value = "/platform", method = RequestMethod.GET)
    public ResultCode getPlatformList(String giftType, Long sourceId, String giftGroup, @RequestHeader("token") String token) {
        ResultCode resultCode = new ResultCode();
        List<Gift> gftList = giftService.getPlatformList(giftType, sourceId, giftGroup, token);
        resultCode.setData(gftList);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 领取礼包(平台+商户通用contoller)
     * Long memberId(会员id) Long giftId(礼包id)
     *
     * @return
     */
    @RequestMapping(value = "/getGift", method = RequestMethod.POST)
    public ResultCode getGift(@RequestHeader("token") String token, Long giftId) {
        ResultCode resultCode = giftService.getGift(token, giftId);
        return resultCode;
    }

    /**
     * 预约生日礼包
     * Long memberId(会员id) Long giftId(礼包id)
     *
     * @return
     */
    @RequestMapping(value = "/getBirthdayGift", method = RequestMethod.POST)
    public ResultCode getBirthdayGift(@RequestHeader("token") String token, @RequestBody MemberGift mg) {
        ResultCode resultCode = giftService.getGift(token, mg.getGiftId());
        if (resultCode != null && resultCode.getCode() == 200) {
            Member mb = giftMapper.getMemberByToken(token);
            mg.setMemberId(Long.valueOf(mb.getId()));
            int result = giftMapper.updateMyGift(mg);
            if (result > 0) {
                resultCode.setCode(200);
                resultCode.setMsg("SUCCESS");
            } else {
                resultCode.setCode(500);
                resultCode.setMsg("FAIL");
            }
        }
        return resultCode;
    }

    /**
     * 搜索我的礼包
     *
     * @return
     */
    @RequestMapping(value = "/myGift", method = RequestMethod.GET)
    public ResultCode getMyGiftList(@RequestHeader("token") String token) {
        ResultCode resultCode = new ResultCode();
        Integer memberId = giftService.getMemberIdByToken(token);
        if (memberId == null) {// token不存在
            resultCode.setCode(500);
            resultCode.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return resultCode;
        }
        List<Gift> myGiftList = giftService.getMyGiftList(memberId);
        resultCode.setData(myGiftList);
        resultCode.setCode(200);
        resultCode.setMsg("SUCCESS");
        return resultCode;
    }

    /**
     * 删除礼包次数
     * Long memberId(会员id) Long giftId(礼包id)
     *
     * @return
     */
    @RequestMapping(value = "/deleteGift", method = RequestMethod.POST)
    public ResultCode deleteGift(@RequestHeader("token") String token, Long giftId) {
        ResultCode resultCode = giftService.deleteGift(token, giftId);
        return resultCode;
    }

    /**
     * 礼包退款接口
     *
     * @param token
     * @param giftId
     * @return
     */
    @RequestMapping(value = "/refundGift", method = RequestMethod.POST)
    public ResultCode refundGift(@RequestHeader("token") String token, Long giftId) {
        ResultCode resultCode = giftService.refundGift(token, giftId);
        return resultCode;
    }

    /**
     * 查询日志接口
     *
     * @param token
     * @param type
     * @return
     */
    @RequestMapping(value = "/giftLog", method = RequestMethod.GET)
    public ResultCode refundGift(@RequestHeader("token") String token, String type) {
        ResultCode resultCode = giftService.giftLogs(token, type);
        return resultCode;
    }

    /**
     * 查询是否领取过平台礼包
     *
     * @param token
     * @param type  礼包类型（1:平台礼包(platform) 2:商户礼包(merchant) 3:分公司礼包(branchOffice) ）
     * @return
     */
    @RequestMapping(value = "/ifGetPlatFormGift", method = RequestMethod.GET)
    public ResultCode ifGetPlatFormGift(@RequestHeader("token") String token, String type) {
        ResultCode resultCode = giftService.ifGetPlatFormGift(token, type);
        return resultCode;
    }

    /**
     * 商户核销礼包
     *
     * @param memberId 会员id
     * @param uuid     礼包唯一标识
     * @return
     */
    @RequestMapping(value = "/writeOff", method = RequestMethod.POST)
    public ResultCode writeOff(@RequestParam("memberId") Integer memberId,
                               @RequestParam("uuid") String uuid,
                               @RequestHeader("token") String token) {
        ResultCode resultCode = giftService.writeOff(memberId, uuid, token);
        return resultCode;
    }

    /**
     * 商户待核销大列表
     *
     * @param token 商户token
     * @param
     * @return
     */
    @RequestMapping(value = "/waitWriteOffList", method = RequestMethod.GET)
    public ResultCode waitWriteOff(@RequestHeader("token") String token) {
        ResultCode resultCode = giftService.waitWriteOff(token);
        return resultCode;
    }

    /**
     * 商户待核销详情列表
     *
     * @param giftId 礼包id
     * @param
     * @return
     */
    @RequestMapping(value = "/waitWriteOffDetailList", method = RequestMethod.GET)
    public ResultCode waitWriteOffDetail(@RequestParam("giftId") Long giftId) {
        ResultCode resultCode = giftService.waitWriteOffDetail(giftId);
        return resultCode;
    }

    /**
     * 商户已核销列表
     *
     * @param token 商户token
     * @param
     * @return
     */
    @RequestMapping(value = "/alreadyWriteOff", method = RequestMethod.GET)
    public ResultCode alreadyWriteOff(@RequestHeader("token") String token) {
        ResultCode resultCode = giftService.alreadyWriteOff(token);
        return resultCode;
    }

    /**
     * 保存日志接口
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/saveLog", method = RequestMethod.POST)
    public ResultCode saveLog(Integer memberId, Long giftId, BigDecimal price, String type,
                              BigDecimal balance, Integer merchantId) {
        ResultCode resultCode = giftService.saveLog(memberId, giftId, price, type, balance, merchantId);
        return resultCode;
    }
}
