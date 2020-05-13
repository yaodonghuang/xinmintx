package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.vo.ResultCode;

import java.util.List;

public interface LikeNumListService {
    ResultCode putLike (int memberCheckInId, String memberName,int id);

    List<Object> countLike (int memberCheckInId, String memberName);

    String likeName (int memberCheckInId);

}
