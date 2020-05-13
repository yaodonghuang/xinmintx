package com.xinmintx.community.service.impl;

import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.mapper.CommunityMapper;
import com.xinmintx.community.mapper.CommunityOrderMapper;
import com.xinmintx.community.mapper.CommunityVoteMapper;
import com.xinmintx.community.model.Community;
import com.xinmintx.community.model.CommunityMessage;
import com.xinmintx.community.model.GoodsOrder;
import com.xinmintx.community.model.Member;
import com.xinmintx.community.service.CommunityMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author hyd
 */
@Service
public class CommunityMessageServiceImpl implements CommunityMessageService {
    private static final Logger log = LoggerFactory.getLogger(CommunityMessageServiceImpl.class);

    @Resource
    private CommunityMapper communityMapper;
    @Resource
    private CommunityOrderMapper communityOrderMapper;
    @Resource
    private CommunityVoteMapper communityVoteMapper;

    /**
     * 获取消息列表
     *
     * @param communityId 社区id
     * @param token
     * @return
     */
    @Override
    public ResultCode messageList(Long communityId, String token) {
        ResultCode rc = new ResultCode();
        if (StringUtils.isEmpty(token)) {
            rc.setCode(500);
            rc.setMsg("请先登录账号");
            return rc;
        }
        Member mb = communityMapper.getMemberByToken(token);
        if (mb == null) {
            // token不存在
            rc.setCode(500);
            rc.setMsg("token不存在,或者用户未登陆,请重新登陆");
            return rc;
        }
        log.info("user token:" + token + ",communityId:" + communityId);
        // 根据用户id和社区id查询该用户的所有订单
        List<CommunityMessage> msgList = new ArrayList<>();
        // 获取订单信息
        getOrderInfo(communityId, mb, msgList);
        // 获取投票信息
        getVoteInfo(communityId, mb, msgList);
        //是否设置提货点状态
        getConsigneeAddress(communityId,mb,msgList);
        //是否设置社区帮办
        queryCommunityDeputy(communityId,mb,msgList);
        //是否设置供货商
        queryCommunityMerchant(communityId,mb,msgList);
        rc.setCode(200);
        rc.setMsg("SUCCESS");
        rc.setData(msgList);
        return rc;
    }

    /**
     * 获取投票信息
     *
     * @param communityId
     * @param mb
     * @param msgList
     */
    private void getVoteInfo(Long communityId, Member mb, List<CommunityMessage> msgList) {
        // 判断用户是否已经投票,没投票的才显示投票信息
        Map<String, Object> infoMap = communityVoteMapper.getVoteInfo(communityId);
        if (infoMap != null && infoMap.size() > 0) {
            Integer ifMemberVote = communityVoteMapper.ifMemberVote(communityId, mb.getId(), Byte.valueOf("1"));
            if (ifMemberVote != null && ifMemberVote == 1) {
                // 投过票,标记投过
                infoMap.put("ifVote", "true");
            } else {
                // 没投过
                infoMap.put("ifVote", "false");
            }
            CommunityMessage cm = new CommunityMessage();
            cm.setType("2");
            cm.setCreateTime((Date) infoMap.get("createTime"));
            cm.setState((String) infoMap.get("ifVote"));
            cm.setName((String) infoMap.get("name"));
            log.info("vote begin name:" + cm.getName());
            msgList.add(cm);
        }
    }

    /**
     * 获取订单信息
     *
     * @param communityId
     * @param mb
     * @param msgList
     */
    private void getOrderInfo(Long communityId, Member mb, List<CommunityMessage> msgList) {
        List<GoodsOrder> orderList = communityOrderMapper.getOrderList(communityId, mb.getId());
        if (orderList != null && orderList.size() > 0) {
            for (GoodsOrder go : orderList) {
                CommunityMessage cm = new CommunityMessage();
                cm.setCreateTime(go.getCreateTime());
                cm.setState(String.valueOf(go.getOrderState()));
                cm.setType("1");
                // 字表信息
                if (go.getDetailList() != null && go.getDetailList().size() > 0) {
                    StringBuffer sb = new StringBuffer();
                    go.getDetailList().forEach(god -> {
                        if (StringUtils.isEmpty(cm.getPic())) {
                            cm.setPic(god.getGoodsPic());
                        }
                        if (StringUtils.isEmpty(cm.getName())) {
                            cm.setName(god.getGoodsName());
                        }
                        sb.append(god.getGoodsName()).append(":").append(god.getFormate()).append("*").append(god.getQuantity()).append(",");
                    });
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    cm.setFormat(sb.toString());
                }
                msgList.add(cm);
            }
        }
    }

    /**
     * 获取提货点状态
     * @param communityId
     * @param mb
     * @param msgList
     */
    private void getConsigneeAddress (Long communityId,Member mb , List<CommunityMessage> msgList){
        Community community =communityMapper.queryCommunityInfo( communityId);
        if (community!=null){
            if (community.getCreateId().equals(Long.valueOf(mb.getId()))){
                if (community.getConsigneeAddress()==null){
                    CommunityMessage cm = new CommunityMessage();
                    cm.setCreateTime(new Date());
                    cm.setType("3");
                    if (StringUtils.isNotEmpty(community.getIcon())){
                        cm.setPic(community.getIcon());
                    }
                    cm.setPromptMessage("未设置提货点，请先设置提货点");
                    msgList.add(cm);
                }
            }
        }
    }

    /**
     * 是否设置社区帮办
     * @param communityId
     * @param mb
     * @param msgList
     */
    private void queryCommunityDeputy(Long communityId,Member mb,List<CommunityMessage> msgList){
        Community community =communityMapper.queryCommunityInfo( communityId);
        if (community!=null){
            if (community.getCreateId().equals(Long.valueOf(mb.getId()))){
                int type = 0;
                int s = communityMapper.queryCommunityDeputy(communityId,type);
                if (s==0){
                    CommunityMessage communityMessage = new CommunityMessage();
                    communityMessage.setType("5");
                    communityMessage.setCreateTime(new Date());
                    if (StringUtils.isNotEmpty(community.getIcon())){
                        communityMessage.setPic(community.getIcon());
                    }
                    communityMessage.setPromptMessage("未设社区帮办，请先设置社区帮办");
                    msgList.add(communityMessage);
                }
            }
        }
    }

    /**
     * 是否设置供货商
     * @param communityId
     * @param mb
     * @param msgList
     */
    private void queryCommunityMerchant(Long communityId,Member mb,List<CommunityMessage> msgList){
        Community community =communityMapper.queryCommunityInfo( communityId);
        int type;
        if (community!=null){
            if (community.getCreateId().equals(Long.valueOf(mb.getId()))){
                    type = 1;
                    int fruits  = communityMapper.queryCommunityMerchant(communityId,type);
                    type = 2;
                    int aquaticProduct = communityMapper.queryCommunityMerchant(communityId,type);
                    type = 3;
                    int vegetables = communityMapper.queryCommunityMerchant(communityId,type);
                    type = 4;
                    int meat = communityMapper.queryCommunityMerchant(communityId,type);
                    type = 5;
                    int relish = communityMapper.queryCommunityMerchant(communityId,type);
                CommunityMessage communityMessage = new CommunityMessage();
                    StringBuffer sb = new StringBuffer();
                    if (fruits==0){
                        if (aquaticProduct==0||vegetables==0||meat==0||relish==0){
                            sb.append("水果/");
                        }else {
                            sb.append("水果");
                        }
                    }
                    if (aquaticProduct==0) {
                        if (vegetables == 0 || meat == 0 || relish == 0) {
                            sb.append("水产/");
                        } else {
                            sb.append("水产");
                        }
                    }
                    if (vegetables==0){
                        if (meat==0||relish==0){
                            sb.append("蔬菜/");
                        }else {
                            sb.append("蔬菜");
                        }
                    }
                if (meat==0){
                    if (relish==0){
                        sb.append("肉类/");
                    }else {
                        sb.append("肉类");
                    }
                }
                if (relish==0){
                    sb.append("粮油调味");
                }
                if (fruits==0||aquaticProduct==0||vegetables==0||meat==0||relish==0){
                    communityMessage.setCreateTime(new Date());
                    communityMessage.setType("4");
                    if (StringUtils.isNotEmpty(community.getIcon())){
                        communityMessage.setPic(community.getIcon());
                    }
                    communityMessage.setPromptMessage("您的社区还有"+sb+"供货商暂未设置");
                    msgList.add(communityMessage);
                }

            }
        }
    }
}
