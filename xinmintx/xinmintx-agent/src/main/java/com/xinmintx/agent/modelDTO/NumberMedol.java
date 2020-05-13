package com.xinmintx.agent.modelDTO;

import lombok.Data;

@Data
public class NumberMedol {
    private Integer id;
    private Integer agentNumber;
    private Integer companyNumber;
    private Integer merchantNumber;
    private Integer partnerNumber;
    private Integer staffNumber;
    private Integer silverNumber;
    //高级合伙人团队人数
    private Integer teamNumber;
    //提货点
    private Integer pickUpNumber;
    //合伙人团队人数
    private Integer agentTeamNumber;
    private double  balance;
    private String  username;
    private String endTime;
    //用户头像
    private String avatar;
}
