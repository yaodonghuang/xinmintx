package com.xinmintx.community.controller;

import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.service.CommunityMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: com.xinmintx.community.controller.CommunityMemberController
 * @Author:Pikachu
 * @Date: 2020/2/10 15:59
 * @Version: v1.0
 */
@RestController
@RequestMapping("community/member")
public class CommunityMemberController {
    @Autowired
    private CommunityMemberService communityMemberService;

    /**
     * 根据用户位置信息查询附近社区
     * @param token
     * @return
     */
    @PostMapping("/queryCommunity")
    public ResultCode queryCommunity (@RequestHeader("token")String token){
        return communityMemberService.queryCommunity(token);
    }

    /**
     * 查询已加入社区
     * @param token
     * @return
     */
    @PostMapping("/myCommunity")
    public ResultCode myCommunity (@RequestHeader("token")String token){
        return communityMemberService.myCommunity(token);
    }


    /**
     * 修改社区公告
     * @param token
     * @param notice
     * @param id
     * @return
     */
    @PostMapping("/upNotice")
    public ResultCode upNotice(@RequestHeader("token")String token,@RequestParam("icon")String icon,@RequestParam("notice")String notice, @RequestParam("id")Long id ){
        return communityMemberService.upNotice(token,icon,notice,id);
    }

    /**
     * 根据社区Id查询
     * @param token
     * @param id
     * @return
     */
    @PostMapping("/queryCommunityById")
    public ResultCode queryCommunity(@RequestHeader("token")String token,@RequestParam("id")Integer id){
        return communityMemberService.queryCommunityById(token,id);
    }

    /**
     * 查询社长
     * @param token
     * @param id
     * @return
     */
    @PostMapping("/queryProprieter")
    public ResultCode queryProprieter(@RequestHeader("token")String token,@RequestParam("id")Integer id){
        return communityMemberService.queryProprieter(token,id);
    }

    /**
     * 修改帮办代提
     * @param communityId
     * @param token
     * @param deputyHelp
     * @return
     */
    @PostMapping("/upAssist")
    public ResultCode upAssist(@RequestParam("communityId")Integer communityId,@RequestHeader("token")String token,@RequestParam("deputyHelp")Integer deputyHelp  ){
        return communityMemberService.upAssist(communityId,token,deputyHelp);
    }

    /**
     * 获取社区用户列表
     * @param communityId
     * @return
     */
    @PostMapping("/getCommunityMemberList")
    public ResultCode getCommunityMemberList(@RequestParam("communityId")Integer communityId){
        return communityMemberService.getCommunityMemberList(communityId);
    }

    /**
     *  退出社区接口
     * @param communityId
     * @param token
     * @return
     */
    @PostMapping("/outOfCommunity")
    public ResultCode outOfCommunity(@RequestParam("communityId")Integer communityId, @RequestHeader("token") String token){
        return communityMemberService.outOfCommunity(communityId, token);
    }

}
