package com.xinmintx.community.service.impl;

import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.mapper.CommunityMapper;
import com.xinmintx.community.mapper.CommunityVoteMapper;
import com.xinmintx.community.model.CommunityVote;
import com.xinmintx.community.model.Member;
import com.xinmintx.community.service.CommunityVoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * 社区投票实现类
 *
 * @author hyd
 */
@Service
public class CommunityVoteServiceImpl implements CommunityVoteService {
    @Resource
    private CommunityMapper communityMapper;
    @Resource
    private CommunityVoteMapper communityVoteMapper;

    /**
     * 发起投票接口
     *
     * @param communityVote
     * @param token
     * @return
     */
    @Override
    public ResultCode beginVote(CommunityVote communityVote, String token) {
        ResultCode rc = new ResultCode();
        rc.setCode(200);
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
        // 1.判断会员是否是该社区的
        Integer ifBelongToCommunity = communityVoteMapper.ifBelongToCommunity(communityVote.getCommunityId(), mb.getId());
        if (ifBelongToCommunity == null) {
            // 会员不是该社区的,不能发起投票
            rc.setCode(500);
            rc.setMsg("您不是该社区的,不能发起投票!");
            return rc;
        }
        // 2.判断是否是创始人,是创始人则不能发起投票
        Integer ifPresident = communityMapper.ifPresident(communityVote.getCommunityId(), Long.valueOf(mb.getId()));
        if (ifPresident != null && ifPresident == 1) {
            // 是社区社长,不能发起投票
            rc.setCode(500);
            rc.setMsg("您是社区社长,不能发起投票!");
            return rc;
        }
        // 3.验证是否正在投票过程中
        Integer ifComplete = communityVoteMapper.ifComplete(communityVote.getCommunityId(), Byte.valueOf("0"));
        if (ifComplete != null && ifComplete == 1) {
            // 投票中
            rc.setCode(500);
            rc.setMsg("投票中,不能发起新投票!");
            return rc;
        }
        // 4.查询是否发起过投票,发起过则不能再次发起
        Integer ifBeginVote = communityVoteMapper.ifBeginVote(communityVote.getCommunityId(), mb.getId());
        if (ifBeginVote != null && ifBeginVote == 1) {
            rc.setCode(500);
            rc.setMsg("您已经发起过投票,不能再次发起!");
            return rc;
        }
        // 5.没发起过则删除该社区所有历史投票信息(community_vote),变更community_member表中if_vote = 0,重新开启投票
        communityVoteMapper.deleteAllVoteInfo(communityVote.getCommunityId());
        communityVoteMapper.updateMemberVoteInfo(communityVote.getCommunityId(), null, Byte.valueOf("0"));
        // 6.开启投票
        communityVote.setOriginatorId(Long.valueOf(mb.getId()));
        communityVote.setAgreeNum(1);
        communityVote.setRefuseNum(0);
        communityVote.setIfComplete(Byte.valueOf("0"));
        communityVoteMapper.insert(communityVote);
        // 7.更新该会员community_member表中if_vote = 1
        communityVoteMapper.updateMemberVoteInfo(communityVote.getCommunityId(), mb.getId(), Byte.valueOf("1"));
        rc.setMsg("SUCCESS");
        return rc;
    }

    /**
     * 用户获取投票信息
     *
     * @param communityId
     * @param token
     * @return
     */
    @Override
    public ResultCode voteInfo(Long communityId, String token) {
        ResultCode rc = new ResultCode();
        rc.setCode(200);
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
        if (communityId == null) {
            // 社区id为空
            rc.setCode(500);
            rc.setMsg("社区id为空");
            return rc;
        }
        Integer ifBelongToCommunity = communityVoteMapper.ifBelongToCommunity(communityId, mb.getId());
        if (ifBelongToCommunity == null) {
            // 会员不是该社区的,不能发起投票
            rc.setCode(500);
            rc.setMsg("您不是该社区的,不能查看投票信息!");
            return rc;
        }
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
            rc.setData(infoMap);
            rc.setMsg("SUCCESS");
        }
        return rc;
    }

    /**
     * 用户投票接口
     *
     * @param communityVote
     * @param token
     * @return
     */
    @Override
    public ResultCode memberVote(CommunityVote communityVote, String token) {
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
        if (communityVote == null || communityVote.getCommunityId() == null || communityVote.getId() == null) {
            // 社区id为空
            rc.setCode(500);
            rc.setMsg("社区id和投票id不能为空");
            return rc;
        }
        Integer ifPresident = communityMapper.ifPresident(communityVote.getCommunityId(), Long.valueOf(mb.getId()));
        if (ifPresident != null && ifPresident == 1) {
            // 是社区社长,不能发起投票
            rc.setCode(500);
            rc.setMsg("您是社区社长,不能发起投票!");
            return rc;
        }
        // 1.更新投票表community_vote中的同意或者拒绝数量 + 1,社区会员表中会员标记已投票:if_vote = 1
        int result = communityVoteMapper.updateNumOfVote(communityVote);
        if (result > 0) {
            communityVoteMapper.updateMemberVoteInfo(communityVote.getCommunityId(), mb.getId(), Byte.valueOf("1"));
            rc.setCode(200);
            rc.setMsg("SUCCESS");
        } else {
            rc.setCode(500);
            rc.setMsg("投票失败!");
            return rc;
        }
        // 2.判断投票人数是否过半,如果过半则更新community_vote为完成投票:if_complete = 1
        Integer totalCount = communityVoteMapper.getCountByCommunityId(communityVote.getCommunityId(), null);
        if (totalCount != null && totalCount > 0) {
            Integer voteNum = communityVoteMapper.getAgreeNum(communityVote.getId());
            if (voteNum != null && voteNum > 0) {
                // 比较投票人数是否过半
                Boolean flag = compareValue(totalCount, voteNum);
                if (flag) {
                    // 同意数量过半
                    communityVote.setIfComplete(Byte.valueOf("1"));
                    communityVote.setAgreeNum(null);
                    communityVote.setRefuseNum(null);
                    communityVoteMapper.updateNumOfVote(communityVote);
                    // 3.变更社长为投票发起人:community表中create_id变更为community_vote表中的originator_id
                    communityVoteMapper.updatePresident(communityVote.getCommunityId());
                    // 先修改所有社区会员标记，然后标记该会员是社长
                    Long memberId = communityVoteMapper.getMemberIdByCommunityId(communityVote.getCommunityId());
                    communityMapper.updatePresentToMember(communityVote.getCommunityId(), null, 0);
                    communityMapper.updatePresentToMember(communityVote.getCommunityId(), memberId, 1);
                }
            }
        }
        return rc;
    }

    /**
     * 比较大小
     *
     * @param totalCount
     * @param voteNum
     * @return
     */
    private static Boolean compareValue(Integer totalCount, Integer voteNum) {
        Boolean flag = false;
        BigDecimal tCount = new BigDecimal(String.valueOf(totalCount));
        BigDecimal vCount = new BigDecimal(String.valueOf(voteNum));
        tCount = tCount.divide(new BigDecimal("2"), 1, RoundingMode.HALF_UP);
        if (vCount.compareTo(tCount) == 1) {
            // 大于
            flag = true;
        }
        return flag;
    }
}
