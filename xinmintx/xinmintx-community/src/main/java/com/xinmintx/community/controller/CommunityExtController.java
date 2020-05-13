package com.xinmintx.community.controller;

import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.model.CommunityDeputy;
import com.xinmintx.community.model.CommunityExt;
import com.xinmintx.community.service.CommunityExtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName:.社区Controller
 * @author:chf
 * @Date:2020/2/10：14:27
 * @developerKits： win 10     jdk1.8
 */
@Controller
@RequestMapping("community/api")
public class CommunityExtController {

    @Resource
    private CommunityExtService communityExtService;

    /**
     * 添加社区提货点
     * @return
     */
    @RequestMapping("/pickupLocation")
    @ResponseBody
    public ResultCode addPickupLocation(@RequestBody CommunityExt communityExt){
        int i = communityExtService.addPickupLocation(communityExt);
        ResultCode resultCode = new ResultCode();
        if (i!=0){
            resultCode.setCode(200);
            resultCode.setMsg("保存成功");
        }else {
            resultCode.setMsg("提货点已存在");
            resultCode.setCode(200);
        }
        return resultCode;
    }

    /**
     * 修改提货点
     * @param communityExt
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultCode updatePickupLocation(@RequestBody CommunityExt communityExt){
        int i = communityExtService.updatePickupLocation(communityExt);
        ResultCode resultCode = new ResultCode();
        if (i!=0){
            resultCode.setCode(200);
            resultCode.setMsg("修改成功");
        }else {
            resultCode.setMsg("修改失败");
            resultCode.setCode(200);
        }
        resultCode.setData(i);
        return resultCode;
    }

    /**
     * 添加社区帮办
     * @return
     */
    @PostMapping("/assistInManaging")
    @ResponseBody
    public ResultCode addAssistInManaging(@RequestBody CommunityDeputy communityDeputies){
        int i = communityExtService.addAssistInManaging(communityDeputies);
        ResultCode resultCode = new ResultCode();
        if (i==1){
            resultCode.setCode(200);
            resultCode.setMsg("添加成功");
        }
        if (i==0){
            resultCode.setCode(200);
            resultCode.setMsg("添加失败或帮点已存在");
        }
        if (i==2){
            resultCode.setCode(200);
            resultCode.setMsg("请先注册会员");
        }
        return resultCode;
    }

    /**
     * 修改帮点
     * @param communityDeputies
     * @return
     */
    @PostMapping("/updateAssistInManaging")
    @ResponseBody
    public ResultCode updateAssistInManaging(@RequestBody CommunityDeputy communityDeputies){
        int i = communityExtService.update(communityDeputies);
        ResultCode resultCode = new ResultCode();
        if (i!=0){
            resultCode.setCode(200);
            resultCode.setMsg("修改成功");
        }else {
            resultCode.setCode(200);
            resultCode.setMsg("修改失败或不是会员");
        }
        resultCode.setData(i);
        return resultCode;
    }

    /**
     * 查询帮点
     * @param id
     * @return
     */
    @PostMapping("/queryAssistInManaging")
    @ResponseBody
    public ResultCode queryAssistInManaging(Integer id){
        CommunityDeputy communityDeputy = communityExtService.queryAssistInManaging(id);
        ResultCode resultCode = new ResultCode();
        if (communityDeputy!=null){
            resultCode.setData(communityDeputy);
            resultCode.setCode(200);
        }else {
            resultCode.setCode(200);
            resultCode.setData(null);
        }
        return resultCode;
    }

    /**
     * 查询提货点
     * @param id
     * @return
     */
    @PostMapping("/queryPickupLocation")
    @ResponseBody
    public ResultCode queryPickupLocation(Integer id){
        CommunityExt communityExt = communityExtService.queryPickupLocation(id);
        ResultCode resultCode = new ResultCode();
        if (communityExt!=null){
            resultCode.setData(communityExt);
            resultCode.setCode(200);
        }else {
            resultCode.setCode(200);
            resultCode.setData(null);
        }
        return resultCode;
    }

}
