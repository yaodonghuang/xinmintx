package com.xinmintx.agent.service.impl;

import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.mapper.BankCardMapper;
import com.xinmintx.agent.mapper.UserMapper;
import com.xinmintx.agent.model.BankCard;
import com.xinmintx.agent.model.BankCardExample;
import com.xinmintx.agent.model.User;
import com.xinmintx.agent.service.BankCardService;
import com.xinmintx.agent.service.ShoppingUnitService;
import com.xinmintx.agent.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BankCardServiceImpl implements BankCardService {
    @Autowired
    private BankCardMapper bankCardMapper;

    @Autowired
    private Jedis jedis;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ShoppingUnitService shoppingUnitService;

    @Override
    public ResultCode addBankCard(BankCard bankCard, User user) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cardNum", bankCard.getBankCard());
        map.put("phone", bankCard.getCellphone());
        map.put("IdCard", bankCard.getCommonOne());
        map.put("userName", bankCard.getCardholderName());
        map.put("mession", jedis.get(user.getId().toString()));
        map.put("smsCode", bankCard.getCode());
        map.put("userId",user.getAccountId());
        ResultCode resultCode1 = shoppingUnitService.tiedCard(map);
        if(200 == resultCode1.getCode()){
            List<BankCard> bankCards = selectBankCard(user.getId());
            if(bankCards.size() == 0){
                bankCardMapper.insertSelective(bankCard);
            }
        }
        Map data1 = (Map) resultCode1.getData();
        resultCode1.setMsg((String)data1.get("retMsg"));
        return resultCode1;
    }

    @Override
    public List<BankCard> selectBankCard(Integer id) {
        BankCardExample bankCardExample = new BankCardExample();
        BankCardExample.Criteria criteria = bankCardExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<BankCard> bankCards = bankCardMapper.selectByExample(bankCardExample);
        return bankCards;
    }


    @Override
    public ResultCode bankCard(BankCard bankCard, User user) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cardNum", bankCard.getBankCard());
        map.put("phone", bankCard.getCellphone());
        map.put("IdCard", bankCard.getCommonOne());
        map.put("userName", bankCard.getCardholderName());
        map.put("mession", jedis.get(user.getId().toString()));
        map.put("smsCode", bankCard.getCode());
        ResultCode resultCode = userAccountService.userAccount(map);
        Map data2 = (Map) resultCode.getData();
        resultCode.setMsg((String)data2.get("retMsg"));
        if(resultCode.getCode() == 200){
            Map data = (Map) resultCode.getData();
            map.put("userId",(String)data.get("userId"));
            user.setAccountStart(1);
            user.setAccountId((String)data.get("userId"));
            userMapper.updateByPrimaryKeySelective(user);
            ResultCode resultCode1 = shoppingUnitService.tiedCard(map);
            if(200 == resultCode1.getCode()){
                List<BankCard> bankCards = selectBankCard(user.getId());
                if(bankCards.size() == 0){
                    bankCardMapper.insertSelective(bankCard);
                }
            }
            Map data1 = (Map) resultCode1.getData();
            resultCode.setMsg((String)data1.get("retMsg"));
        }
        return resultCode;
    }

    @Override
    public BankCard getbank(User user) {
        BankCardExample bankCardExample = new BankCardExample();
        BankCardExample.Criteria criteria = bankCardExample.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        List<BankCard> bankCards = bankCardMapper.selectByExample(bankCardExample);
        if(bankCards.size() > 0){
           return bankCards.get(0);
        }
        return null;
    }
}
