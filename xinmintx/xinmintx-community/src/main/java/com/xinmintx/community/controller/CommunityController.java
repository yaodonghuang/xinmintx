package com.xinmintx.community.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.dto.CommunityAddMerchantDTO;
import com.xinmintx.community.model.Community;
import com.xinmintx.community.service.CommunityService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:.社区Controller
 * @author:chf
 * @Date:2020/2/10：14:27
 * @developerKits： win 10     jdk1.8
 */
@Slf4j
@RestController
@RequestMapping("/community/api")
@Transactional
public class CommunityController {
    @Autowired
    private CommunityService communityService;

    /**
     * 创建社区
     *
     * @param community
     * @param token
     * @return
     */
    @RequestMapping(value = "/creation", method = RequestMethod.POST)
    public ResultCode createCommunity(@RequestBody Community community, @RequestHeader String token) {
        ResultCode resultCode = communityService.createCommunity(community, token);
        return resultCode;
    }

    /**
     * 加入社区接口
     *
     * @param community
     * @param token
     * @return
     */
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ResultCode joinCommunity(@RequestBody Community community, @RequestHeader String token) {
        ResultCode resultCode = communityService.joinCommunity(community, token);
        return resultCode;
    }

    /**
     * 更换社长接口
     *
     * @param json
     * @param token
     * @return
     */
    @RequestMapping(value = "/changePresident", method = RequestMethod.POST)
    public ResultCode changePresident(@RequestBody String json, @RequestHeader String token) {
        JSONObject info = (JSONObject) JSONObject.parseObject(json);
        ResultCode resultCode = communityService.changePresident(info.getLong("communityId"), info.getInteger("memberId"), token);
        return resultCode;
    }

    /**
     * 保存用户地址接口
     *
     * @param regionCode
     * @param token
     * @return
     */
    @RequestMapping(value = "/saveRegion", method = RequestMethod.POST)
    public ResultCode saveRegion(@RequestParam("regionCode") String regionCode, @RequestHeader String token) {
        ResultCode resultCode = communityService.saveRegion(regionCode, token);
        return resultCode;
    }


    /**
     * 根据社区id获取该社区所有供应商
     *
     * @param communityId
     * @param resultCode
     * @return
     */
    @GetMapping("/getByCommunityId")
    public ResultCode getByCommunityId(Integer communityId, @Ignore ResultCode resultCode, @RequestParam(value = "merchantCategory", required = false) String merchantCategory) {
        resultCode.setData(communityService.getByCommunityId(communityId, merchantCategory));
        return resultCode;
    }


    /**
     * 给社区添加供应商
     *
     * @param resultCode
     * @return
     */
    @PostMapping("/addMerchant")
    public ResultCode addMerchant(@RequestParam("communityId") Integer communityId, @RequestParam("merchantId") Integer merchantId, @RequestParam("type") Integer type, @Ignore ResultCode resultCode) {
        CommunityAddMerchantDTO communityAddMerchantDTO = new CommunityAddMerchantDTO();
        communityAddMerchantDTO.setCommunityId(communityId);
        communityAddMerchantDTO.setMerchantId(merchantId);
        List<Integer> types = new ArrayList<>();
        types.add(type);
        communityAddMerchantDTO.setTypes(types);
        communityService.addMerchant(communityAddMerchantDTO);
        return resultCode;
    }

    /**
     * 修改会员昵称
     *
     * @param memberName
     * @param token
     * @return
     */
    @RequestMapping(value = "/changeMemberName", method = RequestMethod.POST)
    public ResultCode changeMemberName(@RequestParam("memberName") String memberName, @RequestParam("id") Long id,
                                       @RequestHeader String token) {
        ResultCode resultCode = communityService.changeMemberName(memberName, id, token);
        return resultCode;
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping("/getCode/{phone}")
    public ResultCode send(@PathVariable String phone) {
        ResultCode code = new ResultCode();
        String sendcode = communityService.sendcode(phone);
        if ("SUCCESS".equals(sendcode)) {
            code.setCode(200);
            code.setMsg("SUCCESS");
        } else {
            code.setCode(500);
            code.setMsg("FAIL");
        }
        return code;
    }

    /**
     * 查询社区附近商户列表接口
     *
     * @param communityId
     * @param merchantCategory
     * @return
     */
    @GetMapping("/getMerchantList")
    public ResultCode getMerchantList(@RequestParam("communityId") Long communityId, @RequestParam(value = "merchantCategory", required = false) String merchantCategory) {
        return communityService.getMerchantList(communityId, merchantCategory);
    }

    /**
     *  商品详情
     * @param communityId
     * @param goodsId
     * @return
     */
    @PostMapping("/goodsDetail")
    public ResultCode  goodsDetail(@RequestParam("communityId")Long communityId ,@RequestParam("goodsId") Long goodsId){
        return communityService.queryGoodsDetail(communityId,goodsId);
    }

    @PostMapping("/signMerchantInfo")
    public ResultCode  getSignMerchantInfo(@RequestParam("communityId")Long communityId ,@RequestHeader String token){
        return communityService.getSignMerchantInfo(communityId, token);
    }

    /**
     * 模糊搜索社区
     * @param token
     * @param name
     * @return
     */
    @PostMapping("/fuzzyQueryCommunity")
    public ResultCode fuzzyQueryCommunity(@RequestHeader String token ,@RequestParam("name")String name){
        return communityService.fuzzyQueryCommunity(token,name);
    }
}
