package com.xinmintx.community.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.community.common.ErrorCodeConstants;
import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.dto.CommunityAddMerchantDTO;
import com.xinmintx.community.exception.BaseException;
import com.xinmintx.community.mapper.CommunityMapper;
import com.xinmintx.community.mapper.CommunityMerchantMapper;
import com.xinmintx.community.mapper.CommunityVoteMapper;
import com.xinmintx.community.model.*;
import com.xinmintx.community.service.CommunityService;
import com.xinmintx.community.util.BeanUtil;
import com.xinmintx.community.util.SmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName:.CommunityServiceImpl
 * @author:chf
 * @Date:2020/2/10：15:38
 * @developerKits： win 10     jdk1.8
 */
@Service
public class CommunityServiceImpl implements CommunityService {
    private static final Logger log = LoggerFactory.getLogger(CommunityServiceImpl.class);

    @Resource
    private CommunityMapper communityMapper;
    @Autowired
    private Jedis jedis;
    @Autowired
    private SmsUtil smsUtil;
    @Resource
    private CommunityMerchantMapper communityMerchantMapper;
    @Resource
    private CommunityVoteMapper communityVoteMapper;

    /**
     * 创建社区
     *
     * @param community 社区实体
     * @param token     创建人token
     * @return
     */
    @Override
    public ResultCode createCommunity(Community community, String token) {
        ResultCode rc = new ResultCode();
        if (StringUtils.isEmpty(token)) {
            rc.setCode(500);
            rc.setMsg("请先登录账号");
            return rc;
        }
        Member mb = communityMapper.getMemberByToken(token);
        if (mb == null) {// token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        Integer memberId = mb.getId();
        log.info("user token:" + token + ",memberId:" + memberId);
        if (community != null) {
            community.setCreateId(Long.valueOf(memberId));
            community.setRegionCode(mb.getRegionCode());
        } else {
            rc.setCode(500);
            rc.setMsg("请填写社区信息");
            return rc;
        }
        // 根据区域和社区名称判断是否已经存在该名字的社区,存在则报错
        Integer ifExists = communityMapper.ifExistsCommunity(community);
        if (ifExists == null) {// 不存在可以创建
            communityMapper.insert(community);// 创建社区
            // 保存社区会员信息
            Integer rank = 1;
            communityMapper.insertCommunityProprieter(community.getId(), community.getCreateId(), rank);
            rc.setCode(200);
            rc.setMsg("SUCCESS");
        } else {
            rc.setCode(500);
            rc.setMsg("该社区已经存在,请更换名称");
            return rc;
        }
        return rc;
    }

    /**
     * 加入社区接口
     *
     * @param community
     * @param token
     * @return
     */
    @Override
    public ResultCode joinCommunity(Community community, String token) {
        ResultCode rc = new ResultCode();
        if (StringUtils.isEmpty(token)) {
            rc.setCode(500);
            rc.setMsg("请先登录账号");
            return rc;
        }
        Member mb = communityMapper.getMemberByToken(token);
        if (mb == null) {// token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        Integer memberId = mb.getId();
        log.info("user token:" + token + ",memberId:" + memberId);
        if (community != null && community.getId() != null) {
            Long communityId = community.getId();// 获取社区id
            // 判断该用户是否已经加入过该社区,没加入过则加入
            Integer ifJoin = communityMapper.ifJoinCommunity(communityId, Long.valueOf(memberId));
            if (ifJoin == null) {// 没加入过
                // 保存社区会员信息
                communityMapper.insertCommunityMemberInfo(communityId, Long.valueOf(memberId));
                rc.setCode(200);
                rc.setMsg("SUCCESS");
            } else {
                rc.setCode(500);
                rc.setMsg("已经加入过该社团!");
            }
        } else {
            rc.setCode(500);
            rc.setMsg("选择的社区异常");
            return rc;
        }
        return rc;
    }

    /**
     * 更换社长接口
     *
     * @param memberId    会员id
     * @param token       登录用户的token
     * @param communityId 社区主键id
     * @return
     */
    @Override
    public ResultCode changePresident(Long communityId, Integer memberId, String token) {
        ResultCode rc = new ResultCode();
        if (StringUtils.isEmpty(token)) {
            rc.setCode(500);
            rc.setMsg("请先登录账号");
            return rc;
        }
        Member mb = communityMapper.getMemberByToken(token);
        if (mb == null) {// token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        if (memberId == null) {
            rc.setCode(500);
            rc.setMsg("请选择要将社长变更为哪个会员!");
            return rc;
        }
        if (communityId == null) {
            rc.setCode(500);
            rc.setMsg("社区id为空");
            return rc;
        }
        // 判断操作的用户是否是社区创始人
        Integer ifPresident = communityMapper.ifPresident(communityId, Long.valueOf(mb.getId()));
        if (ifPresident != null && ifPresident.intValue() == 1) {// 是
            updatePresident(communityMapper, memberId, communityId, rc);
        } else {// 否
            rc.setCode(500);
            rc.setMsg("您不是社区创始人,无法更换社长!");
        }
        return rc;
    }

    /**
     * 保存用户地址接口
     *
     * @param regionCode
     * @param token
     * @return
     */
    @Override
    public ResultCode saveRegion(String regionCode, String token) {
        ResultCode rc = new ResultCode();
        if (StringUtils.isEmpty(token)) {
            rc.setCode(500);
            rc.setMsg("请先登录账号");
            return rc;
        }
        Member mb = communityMapper.getMemberByToken(token);
        if (mb == null) {// token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        log.info("user token:" + token);
        communityMapper.updateMemberRegion(Long.valueOf(mb.getId()), regionCode);
        rc.setCode(200);
        rc.setMsg("SUCCESS");
        return rc;
    }

    /**
     * 修改会员昵称
     *
     * @param memberName
     * @param token
     * @return
     */
    @Override
    public ResultCode changeMemberName(String memberName, Long id, String token) {
        ResultCode rc = new ResultCode();
        if (StringUtils.isEmpty(token)) {
            rc.setCode(500);
            rc.setMsg("请先登录账号");
            return rc;
        }
        Member mb = communityMapper.getMemberByToken(token);
        if (mb == null) {// token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        if (StringUtils.isEmpty(memberName)) {
            rc.setCode(500);
            rc.setMsg("请输入昵称");
            return rc;
        }
        if (id == null) {
            rc.setCode(500);
            rc.setMsg("社团id为空");
            return rc;
        }
        log.info("user token:" + token + ",input memberName:" + memberName);
        communityMapper.updateMemberName(memberName, id, mb.getId());
        rc.setCode(200);
        rc.setMsg("SUCCESS");
        return rc;
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @Override
    public String sendcode(String phone) {
        //1 生成6位随机数
        final String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        //存入缓存
        jedis.set(phone, code);
        log.info("phone=" + phone + ";msCode=" + code);
        //30分钟过期
        jedis.expire(phone, 1800);
        try {
            SendSmsResponse response = smsUtil.sendSms(phone, "SMS_176440093", "惠商", "{\"code\":\"" + code + "\"}");
            String responseCode = response.getCode();
            if ("OK".equals(responseCode)) {
                return "SUCCESS";
            } else {
                return "FAIL";
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return "FAIL";
    }

    /**
     * 查询附近商户列表
     *
     * @param communityId
     * @param merchantCategory
     * @return
     */
    @Override
    public ResultCode getMerchantList(Long communityId, String merchantCategory) {
        ResultCode rc = new ResultCode();
        List<Merchant> finalList = new ArrayList<>();
        String addressCode = communityMapper.getAddressCodeById(communityId);
        if (StringUtils.isNotEmpty(addressCode)) {
            // 区域code不为空，查询附近区域code相同的商户
            List<Merchant> merchantList = communityMapper.getMerchantList(addressCode);
            if (merchantList != null && merchantList.size() > 0) {
                for (Merchant m : merchantList) {
                    String mcd = m.getMerchantCategoryDetail();
                    if (StringUtils.isEmpty(merchantCategory)) {
                        finalList.add(m);
                        continue;
                    }
                    if (StringUtils.isNotEmpty(mcd)) {
                        String[] str = mcd.split(",");
                        if (str != null && str.length > 0) {
                            for (int i = 0; i < str.length; i++) {
                                if (StringUtils.isNotEmpty(merchantCategory) && merchantCategory.equalsIgnoreCase(str[i])) {
                                    finalList.add(m);
                                }
                            }
                        }
                    }
                }
            }
        }
        rc.setCode(200);
        rc.setMsg("SUCCESS");
        rc.setData(finalList);
        return rc;
    }

    @Override
    public ResultCode queryGoodsDetail(Long communityId, Long goodsId) {
        ResultCode code = new ResultCode();
        GoodsDetail goodsDetail = new GoodsDetail();
        MerchantGoods merchantGoods = communityMapper.queryMerchantGoods(goodsId);
        if (merchantGoods != null) {
            goodsDetail.setId(merchantGoods.getId());
            goodsDetail.setBigdecimal(merchantGoods.getBigdecimal());
            goodsDetail.setCommunityPrice(merchantGoods.getCommunityPrice());
            goodsDetail.setName(merchantGoods.getName());
            goodsDetail.setOnlinePrice(merchantGoods.getOnlinePrice());
            goodsDetail.setDescription(merchantGoods.getDescription());
            Community community = communityMapper.queryAddress(communityId);
            goodsDetail.setConsigneeAddress(community.getConsigneeAddress());
            goodsDetail.setCommunityName(community.getName());
            goodsDetail.setIcon(community.getIcon());
            goodsDetail.setPicList(communityMapper.queryMerchantGoodsPic(goodsId));
            goodsDetail.setList(communityMapper.queryGoodsOrder(goodsId));
            code.setCode(200);
            code.setData(goodsDetail);
            return code;
        } else {
            code.setMsg("商品不存在");
            return code;
        }
    }

    /**
     * 查询签约的商户信息
     *
     * @param communityId
     * @param token
     * @return
     */
    @Override
    public ResultCode getSignMerchantInfo(Long communityId, String token) {
        ResultCode rc = new ResultCode();
        if (StringUtils.isEmpty(token)) {
            rc.setCode(500);
            rc.setMsg("请先登录账号");
            return rc;
        }
        Member mb = communityMapper.getMemberByToken(token);
        if (mb == null) {// token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        Integer ifBelongToCommunity = communityVoteMapper.ifBelongToCommunity(communityId, mb.getId());
        if (ifBelongToCommunity == null) {
            // 会员不是该社区的,不能发起投票
            rc.setCode(500);
            rc.setMsg("您不是该社区的会员，不能查询绑定商户信息!");
            return rc;
        }
        List<CommunityMerchant> merchantInfoList = communityMapper.getMerchantInfoList(communityId);
        if (merchantInfoList != null && merchantInfoList.size() > 0) {
            merchantInfoList.forEach(merchantInfo -> {
                if (merchantInfo.getType() == 1) {
                    merchantInfo.setTypeName("水果");
                    merchantInfo.setIfSign("true");
                } else if (merchantInfo.getType() == 2) {
                    merchantInfo.setTypeName("水产");
                    merchantInfo.setIfSign("true");
                } else if (merchantInfo.getType() == 3) {
                    merchantInfo.setTypeName("蔬菜");
                    merchantInfo.setIfSign("true");
                } else if (merchantInfo.getType() == 4) {
                    merchantInfo.setTypeName("肉类");
                    merchantInfo.setIfSign("true");
                } else if (merchantInfo.getType() == 5) {
                    merchantInfo.setTypeName("粮油调味");
                    merchantInfo.setIfSign("true");
                }
            });
            // 处理未签约的数据
            List<Integer> typeList = new ArrayList<>();
            merchantInfoList.forEach(merchantInfo -> {
                typeList.add(merchantInfo.getType());
            });
            if (typeList != null && typeList.size() > 0) {
                if (!typeList.contains(1)) {
                    CommunityMerchant communityMerchant = new CommunityMerchant();
                    communityMerchant.setTypeName("水果");
                    communityMerchant.setIfSign("false");
                    communityMerchant.setType(1);
                    merchantInfoList.add(communityMerchant);
                }
                if (!typeList.contains(2)) {
                    CommunityMerchant communityMerchant2 = new CommunityMerchant();
                    communityMerchant2.setTypeName("水产");
                    communityMerchant2.setIfSign("false");
                    communityMerchant2.setType(2);
                    merchantInfoList.add(communityMerchant2);
                }
                if (!typeList.contains(3)) {
                    CommunityMerchant communityMerchant3 = new CommunityMerchant();
                    communityMerchant3.setTypeName("蔬菜");
                    communityMerchant3.setIfSign("false");
                    communityMerchant3.setType(3);
                    merchantInfoList.add(communityMerchant3);
                }
                if (!typeList.contains(4)) {
                    CommunityMerchant communityMerchant4 = new CommunityMerchant();
                    communityMerchant4.setTypeName("肉类");
                    communityMerchant4.setIfSign("false");
                    communityMerchant4.setType(4);
                    merchantInfoList.add(communityMerchant4);
                }
                if (!typeList.contains(5)) {
                    CommunityMerchant communityMerchant5 = new CommunityMerchant();
                    communityMerchant5.setTypeName("粮油调味");
                    communityMerchant5.setIfSign("false");
                    communityMerchant5.setType(5);
                    merchantInfoList.add(communityMerchant5);
                }
            }
        } else {
            // 未签约
            merchantInfoList = new ArrayList<>();
            CommunityMerchant communityMerchant = new CommunityMerchant();
            communityMerchant.setTypeName("水果");
            communityMerchant.setIfSign("false");
            communityMerchant.setType(1);

            CommunityMerchant communityMerchant2 = new CommunityMerchant();
            communityMerchant2.setTypeName("水产");
            communityMerchant2.setIfSign("false");
            communityMerchant2.setType(2);

            CommunityMerchant communityMerchant3 = new CommunityMerchant();
            communityMerchant3.setTypeName("蔬菜");
            communityMerchant3.setIfSign("false");
            communityMerchant3.setType(3);

            CommunityMerchant communityMerchant4 = new CommunityMerchant();
            communityMerchant4.setTypeName("肉类");
            communityMerchant4.setIfSign("false");
            communityMerchant4.setType(4);

            CommunityMerchant communityMerchant5 = new CommunityMerchant();
            communityMerchant5.setTypeName("粮油调味");
            communityMerchant5.setIfSign("false");
            communityMerchant5.setType(5);

            merchantInfoList.add(communityMerchant);
            merchantInfoList.add(communityMerchant2);
            merchantInfoList.add(communityMerchant3);
            merchantInfoList.add(communityMerchant4);
            merchantInfoList.add(communityMerchant5);
        }
        rc.setCode(200);
        rc.setMsg("SUCCESS");
        Collections.sort(merchantInfoList);
        rc.setData(merchantInfoList);
        return rc;
    }

    @Override
    public ResultCode fuzzyQueryCommunity(String token, String name) {
        ResultCode code = new ResultCode();
        Member member = communityMapper.getMemberByToken(token);
        if (member!=null){
            List<Community> list = communityMapper.fuzzyQueryCommunity(member.getRegionCode(),name);
            if(list.size()>0&&list!=null){
                code.setCode(200);
                code.setData(list);
                return code;
            }else {
                code.setCode(500);
                code.setMsg("社区不存在");
                return code;
            }
        }else {
            code.setCode(500);
            code.setMsg("用户信息查询错误");
            return code;
        }
    }

    @Override
    public List<Map<String, Object>> getByCommunityId(Integer communityId, String merchantCategory) {
        List<Map<String, Object>> finalListMap = new ArrayList<>();
        List<Map<String, Object>> listMap = communityMapper.getByCommunityId(communityId);
        if (listMap != null && listMap.size() > 0) {
            for (Map<String, Object> map : listMap) {
                if (StringUtils.isEmpty(merchantCategory)) {
                    finalListMap.add(map);
                    continue;
                }
                if (merchantCategory.equalsIgnoreCase(String.valueOf(map.get("type")))) {
                    finalListMap.add(map);
                }
            }
        }
        return finalListMap;
    }

    @Override
    public void addMerchant(CommunityAddMerchantDTO communityAddMerchantDTO) {
        Integer merchantId = communityAddMerchantDTO.getMerchantId();

        if (merchantId == null) {
            throw new BaseException(ErrorCodeConstants.MERCHANT_NOT_EXIST_ERR);
        }
        for (int i = 0; i < communityAddMerchantDTO.getTypes().size(); i++) {
            int type = communityAddMerchantDTO.getTypes().get(i);
            if (communityMerchantMapper.isExistByCommunityAndType(communityAddMerchantDTO.getCommunityId(), type)) {
                ErrorCodeConstants communityTypeExistErr = ErrorCodeConstants.COMMUNITY_TYPE_EXIST_ERR;
                communityTypeExistErr.setMsg(StrUtil.format(communityTypeExistErr.getMsg(), type));
                throw new BaseException(communityTypeExistErr);
            }
        }

        for (int i = 0; i < communityAddMerchantDTO.getTypes().size(); i++) {
            int type = communityAddMerchantDTO.getTypes().get(i);
            CommunityMerchant communityMerchant = BeanUtil.copyProperties(communityAddMerchantDTO, CommunityMerchant.class, true);
            communityMerchant.setMerchantId(merchantId);
            communityMerchant.setType(type);
            communityMerchantMapper.add(communityMerchant);
        }

    }

    // 变更社长逻辑
    private static void updatePresident(CommunityMapper communityMapper, Integer memberId, Long communityId, ResultCode rc) {
        // 判断输入的姓名和手机号是否能匹配到会员
        Long id = communityMapper.ifMember(memberId);
        if (id != null) {
            //能匹配到则变更社长
            communityMapper.updatePredisent(communityId, id);
            // 判断该用户是否已经加入过该社区,没加入过则加入
            Integer ifJoin = communityMapper.ifJoinCommunity(communityId, id);
            if (ifJoin == null) {
                // 没加入过,保存社区会员信息
                communityMapper.insertCommunityMemberInfo(communityId, id);
            }
            // 先修改所有社区会员标记，然后标记该会员是社长
            communityMapper.updatePresentToMember(communityId, null, 0);
            communityMapper.updatePresentToMember(communityId, id, 1);
            rc.setCode(200);
            rc.setMsg("SUCCESS");
        } else {// 不能
            rc.setCode(500);
            rc.setMsg("无法查询到该会员,请核对信息!");
        }
    }
}
