package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.MemberVo;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.IMemberTreeService;
import com.xinmintx.hstx.service.MemberDataService;
import com.xinmintx.hstx.util.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/24 0024
 * @time: 下午 13:13
 * @Description:
 */
@RestController
@RequestMapping("/api/data")
public class MemberDataController extends BaseController {
    @Autowired
    private MemberDataService memberDataService;

    @PostMapping("/getRelationList")
    public ResultCode getRelationList(){
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("暂无关联新民卡");
        List<MemberVo> relationList = memberDataService.getRelationList(member.getId());
        if (relationList.size() > 0){
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(relationList);
        }
        return resultCode;
    }
}
