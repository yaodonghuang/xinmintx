package com.xinmintx.community.controller;

import com.xinmintx.community.common.ResultCode;
import com.xinmintx.community.dto.ComplaintAddDTO;
import com.xinmintx.community.service.ComplaintService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Author zhangliang
 * @Date 2020/2/10
 */
@RestController
@RequestMapping("community/complaint")
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;

    @PostMapping("/add")
    public ResultCode add(@Valid @RequestBody ComplaintAddDTO complaintAddDTO,  @RequestHeader String token, @Ignore ResultCode resultCode){
        complaintService.add(complaintAddDTO,token);
        return resultCode;
    }

}
