package com.xinmintx.community.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.xinmintx.community.common.ErrorCodeConstants;
import com.xinmintx.community.dto.RescissionAddDTO;
import com.xinmintx.community.exception.BaseException;
import com.xinmintx.community.mapper.*;
import com.xinmintx.community.model.CommunityVotingRescissionNotice;
import com.xinmintx.community.model.Member;
import com.xinmintx.community.model.Rescission;
import com.xinmintx.community.service.RescissionService;
import com.xinmintx.community.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.util.Map;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */

@Slf4j
@Service
public class RescissionServiceImpl implements RescissionService {

    @Autowired
    RescissionMapper rescissionMapper;

    @Autowired
    CommunityMapper communityMapper;

    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    CommunityMerchantMapper communityMerchantMapper;

    @Autowired
    CommunityVotingRescissionNoticeMapper communityVotingRescissionNoticeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void votingRescission(RescissionAddDTO rescissionAddDTO , String token) {
        Member mb = communityMapper.getMemberByToken(token);
        // token不存在
        if (mb == null) {
            throw new BaseException(ErrorCodeConstants.TOKEN_ERR);
        }

        Map merchant = merchantMapper.getByPhone(rescissionAddDTO.getPhone());
        Integer merchantId = (Integer) merchant.get("id");
        Integer createId = communityMapper.getPresidentByCommunityId(rescissionAddDTO.getCommunityId());
        if (createId != null && createId.intValue() == mb.getId().intValue()) {
            if(communityVotingRescissionNoticeMapper.isVotingByCommunityIdAndType(rescissionAddDTO.getCommunityId(),rescissionAddDTO.getType())){
                throw new BaseException(ErrorCodeConstants.VOTING_RESCISSION_ERR);
            }

            CommunityVotingRescissionNotice communityVotingRescissionNotice = BeanUtil.copyProperties(rescissionAddDTO,CommunityVotingRescissionNotice.class,true);
            communityVotingRescissionNotice.setCreateUser(createId);
            communityVotingRescissionNotice.setMerchantId(merchantId);

            communityVotingRescissionNoticeMapper.insert(communityVotingRescissionNotice);
            return;
        }


        rescissionAddDTO.setMemberId(mb.getId());

        if(rescissionMapper.existByMemberId(rescissionAddDTO.getMemberId())){
            throw new BaseException(ErrorCodeConstants.VOTINGED_ERR);
        }

        Map<String,Object> map = rescissionMapper.getByCommunityIdAndMerchantIdAndType(rescissionAddDTO.getCommunityId(),merchantId,rescissionAddDTO.getType());

        Double proportion = (Double)map.get("proportion");
        if (proportion != null && proportion.doubleValue() >= 0.70) {
            throw new BaseException(ErrorCodeConstants.VOTING_ENDED_ERR);
        }

        Integer total = communityMapper.getCountByCommunityId(rescissionAddDTO.getCommunityId());
        Integer votinged = Integer.parseInt(map.get("votinged")+"");

        if (votinged == null) {
            votinged = 0;
        }
        ++votinged;
        double prop = NumberUtil.div((double)votinged , (double)total,2, RoundingMode.DOWN) ;
        if(prop >= 0.70){
            communityMerchantMapper.deleteByCommunityIdAndMerchantIdAndType(rescissionAddDTO.getCommunityId(),merchantId,rescissionAddDTO.getType());
            communityVotingRescissionNoticeMapper.deleteByCommunityIdAndMerchantIdAndType(rescissionAddDTO.getCommunityId(), merchantId, rescissionAddDTO.getType());
        }else{
            Rescission rescission = BeanUtil.copyProperties(rescissionAddDTO, Rescission.class,true);
            rescission.setProportion(prop);
            rescission.setMerchantId(merchantId);
            rescissionMapper.add(rescission);
        }

    }

}
