package com.xinmintx.agent.service;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.model.BankCard;
import com.xinmintx.agent.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BankCardService {
    ResultCode addBankCard(BankCard bankCard, User user);

    List<BankCard> selectBankCard(Integer id);

    ResultCode bankCard(BankCard bankCard, User user);

    BankCard getbank(User user);
}
