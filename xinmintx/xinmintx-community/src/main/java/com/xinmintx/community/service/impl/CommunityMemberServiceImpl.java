package com.xinmintx.community.service.impl;

import com.xinmintx.common.utils.StringUtils;
import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.mapper.CommunityMapper;
import com.xinmintx.community.model.Community;
import com.xinmintx.community.model.Member;
import com.xinmintx.community.model.MemberExt;
import com.xinmintx.community.service.CommunityMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: com.xinmintx.community.service.impl.CommunityMemberServiceImpl
 * @Author:Pikachu
 * @Date: 2020/2/10 16:23
 * @Version: v1.0
 */
@Service
public class CommunityMemberServiceImpl implements CommunityMemberService {
    private static final Logger log = LoggerFactory.getLogger(CommunityMemberServiceImpl.class);
    @Resource
    private CommunityMapper communityMapper;

    @Override
    public ResultCode queryCommunity(String token) {
        ResultCode code = new ResultCode();
        Member member = communityMapper.getMemberByToken(token);
        if(null!=member){
            Integer memberId = member.getId();
            String regionCode = member.getRegionCode();
            if (StringUtils.isNotEmpty(regionCode)) {
                List<Community> list = communityMapper.queryCommunity(regionCode, memberId);
                if (list != null && list.size() > 0) {
                    code.setCode(200);
                    code.setData(list);
                    return code;
                } else {
                    code.setMsg("附近没有社区");
                    return code;
                }
            } else {
                code.setMsg("用户没有位置信息");
                return code;
            }
        }else {
            code.setMsg("用户不存在 ");
            return code;
        }
    }

    @Override
    public ResultCode myCommunity(String token) {
        ResultCode code = new ResultCode();
        Member member = communityMapper.getMemberByToken(token);
        if (member != null) {
            Integer memberId = member.getId();
            List<Community> list = communityMapper.myCommunity(memberId);
            if (list != null && list.size() > 0) {
                code.setCode(200);
                code.setData(list);
                return code;
            } else {
                code.setMsg("没有加入社区");
                return code;
            }
        } else {
            code.setMsg("用户不存在");
            return code;
        }
    }

    @Override
    public ResultCode upNotice(String token, String icon, String notice, Long id) {
        ResultCode code = new ResultCode();
        Member member = communityMapper.getMemberByToken(token);
        if (member != null) {
            Integer memberId = member.getId();
            if (icon != null && icon.length() > 0) {
                if (StringUtils.isNotEmpty(icon)) {
                    communityMapper.upIcon(icon, memberId, id);
                    code.setCode(200);
                    code.setMsg("SUCCESS");
                    return code;
                }
            } else {
                int i = communityMapper.upNotice(notice, memberId, id);
                if (i > 0) {
                    code.setCode(200);
                    code.setMsg("公告修改成功");
                    return code;
                } else {
                    code.setMsg("修改失败，只有社区创始人才能修改");
                    return code;
                }
            }
        } else {
            code.setMsg("用户不存在");
            return code;
        }
        return null;
    }

    @Override
    public ResultCode queryCommunityById(String token, Integer id) {
        ResultCode code = new ResultCode();
        Member member = communityMapper.getMemberByToken(token);
        if (member != null) {
            Integer memberId = member.getId();
            Community community = communityMapper.queryCommunityById(memberId, id);
            if (community != null) {
                Long createId = community.getCreateId();
                Community proprieterName = communityMapper.queryProprieterName(createId, id);
                if(proprieterName == null){
                    // 查询不到社区
                    code.setCode(500);
                    code.setMsg("会员昵称和手机号查询不存在！");
                    log.info("会员id:" + createId + ",社区id：" + id);
                    return code;
                }
                if (StringUtils.isNotEmpty(proprieterName.getMemberName())&&StringUtils.isNotEmpty(proprieterName.getCellphone())){
                        community.setProprieterName(proprieterName.getMemberName());
                        community.setCellphone(proprieterName.getCellphone());
                }else {
                   Member proprieter  = communityMapper.queryMemberInfo(createId.intValue());
                   if(proprieter == null){
                       // 查询不到会员
                       code.setCode(500);
                       code.setMsg("会员不存在！");
                       return code;
                   }
                    if (StringUtils.isNotEmpty(proprieter.getName())){
                        community.setProprieterName(proprieter.getName());
                    }
                    if (StringUtils.isNotEmpty(proprieter.getCellphone())){
                        community.setCellphone(proprieter.getCellphone());
                    }
                }
                code.setCode(200);
                code.setData(community);
                return code;
            } else {
                code.setMsg("用户不存在");
                return code;
            }
        } else {
            code.setMsg("不是该社区成员");
            return code;
        }

    }

    @Override
    public ResultCode queryProprieter(String token, Integer id) {
        ResultCode code = new ResultCode();
        Member member = communityMapper.getMemberByToken(token);
        if (member != null) {
            Integer memberId = member.getId();
            Integer i = communityMapper.queryProprieter(memberId, id);
            if (i != null) {
                code.setCode(200);
                code.setMsg("true");
                return code;
            } else {
                code.setCode(200);
                code.setMsg("false");
                return code;
            }
        } else {
            code.setMsg("用户不存在");
            return code;
        }
    }

    @Override
    public ResultCode upAssist(Integer communityId, String token, Integer deputyHelp) {
        ResultCode code = new ResultCode();
        Member member = communityMapper.getMemberByToken(token);
        if (member != null) {
            Integer memberId = member.getId();
            int i = communityMapper.upAssist(communityId, memberId, deputyHelp);
            if (i > 0) {
                code.setCode(200);
                code.setMsg("修改成功");
                return code;
            } else {
                code.setCode(200);
                code.setMsg("修改失败");
                return code;
            }
        } else {
            code.setMsg("用户不存在");
            return code;
        }
    }

    @Override
    public ResultCode getCommunityMemberList(Integer communityId) {
        ResultCode code = new ResultCode();
        List<MemberExt> list = communityMapper.getCommunityMemberList(communityId);
        if (list != null && list.size() > 0) {
            for (int i = 0;i<list.size();i++){
                if (!StringUtils.isNotEmpty(list.get(i).getMemberName())){
                    String name = communityMapper.queryMember(list.get(i).getMemberId());
                    if (StringUtils.isNotEmpty(name)){
                        list.get(i).setMemberName(name);
                    }
                }
            }
            code.setCode(200);
            code.setData(list);
            return code;
        } else {
            code.setCode(200);
            code.setMsg("暂无用户信息");
            return code;
        }
    }

    /**
     * 退出社区接口
     *
     * @param communityId
     * @param token
     * @return
     */
    @Override
    public ResultCode outOfCommunity(Integer communityId, String token) {
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
        Integer ifPresident = communityMapper.ifPresident(Long.valueOf(communityId), Long.valueOf(mb.getId()));
        if (ifPresident != null && ifPresident == 1) {
            // 是社区社长,不能发起投票
            rc.setCode(500);
            rc.setMsg("您是社区社长,不能退出社区!");
            return rc;
        }
        int result = communityMapper.outOfCommunity(communityId, mb.getId());
        if (result > 0) {
            rc.setCode(200);
            rc.setMsg("SUCCESS");
        } else {
            rc.setCode(500);
            rc.setMsg("您未加入该社区!");
        }
        return rc;
    }
}
