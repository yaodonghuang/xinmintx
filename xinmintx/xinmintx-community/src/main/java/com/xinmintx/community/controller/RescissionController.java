package com.xinmintx.community.controller;

import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.dto.RescissionAddDTO;
import com.xinmintx.community.service.RescissionService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@RestController
@RequestMapping("community/rescission")
public class RescissionController {

    @Autowired
    RescissionService rescissionService;

    @PostMapping("/votingRescission")
    public ResultCode votingRescission(@Valid @RequestBody RescissionAddDTO rescissionAddDTO, @RequestHeader String token, @Ignore ResultCode resultCode){
        rescissionService.votingRescission(rescissionAddDTO,token);
        return resultCode;
    }

}
