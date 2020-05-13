package com.xinmintx.community.service.impl;

import com.xinmintx.community.common.ErrorCodeConstants;
import com.xinmintx.community.dto.ComplaintAddDTO;
import com.xinmintx.community.exception.BaseException;
import com.xinmintx.community.mapper.CommunityMapper;
import com.xinmintx.community.mapper.ComplaintMapper;
import com.xinmintx.community.mapper.MerchantMapper;
import com.xinmintx.community.model.Complaint;
import com.xinmintx.community.model.Member;
import com.xinmintx.community.service.ComplaintService;
import com.xinmintx.community.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */

@Slf4j
@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    ComplaintMapper complaintMapper;

    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    CommunityMapper communityMapper;

    @Override
    public void add(ComplaintAddDTO complaintAddDTO,String token) {
        Member mb = communityMapper.getMemberByToken(token);

        if (mb == null) {// token不存在
            throw new BaseException(ErrorCodeConstants.TOKEN_ERR);
        }

        Map merchantMap = merchantMapper.getByPhone(complaintAddDTO.getPhone());
        complaintAddDTO.setMemberId(mb.getId());
        Complaint complaint = BeanUtil.copyProperties(complaintAddDTO, Complaint.class,true);
        complaint.setMerchantId((Integer) merchantMap.get("id"));
        complaintMapper.add(complaint);
    }

}
