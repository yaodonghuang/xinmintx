package com.xinmintx.agent.controller;

import com.xinmintx.agent.annotation.DisableAuth;
import com.xinmintx.agent.common.ResultCode;
import com.xinmintx.agent.mapper.DepositSpecificationMapper;
import com.xinmintx.agent.mapper.UserAccountMapper;
import com.xinmintx.agent.mapper.UserRequestsnMapper;
import com.xinmintx.agent.model.*;
import com.xinmintx.agent.service.BankCardService;
import com.xinmintx.agent.service.NoteCodeService;
import com.xinmintx.agent.service.QueryTiedCardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/agent/bankCard")
@Slf4j
public class BankCardController {

    @Autowired
    private BankCardService bankCardService;


    @Autowired
    private QueryTiedCardService queryTiedCardService;

    @Autowired
    private UserRequestsnMapper userRequestsnMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private com.xinmintx.agent.service.PaymentOnBehalfOf paymentOnBehalfOf;

    @Autowired
    private Jedis jedis;

    @Autowired
    private DepositSpecificationMapper depositSpecificationMapper;

    @Autowired
    private NoteCodeService noteCodeService;

    /**
     * 绑定银行卡
     *
     * @param bankCard
     * @param request
     * @param
     * @return
     */
    @PostMapping("/addBankCard")
    @ResponseBody
    public ResultCode addBankCard(BankCard bankCard, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        bankCard.setUserId(user.getId()); //银行卡 设置用户id
        String accountId = user.getAccountId();
        //如果开户成功
        if (StringUtils.isNotBlank(accountId)) {
            return bankCardService.addBankCard(bankCard, user);
        } else {
            return bankCardService.bankCard(bankCard, user);
        }
    }

    /**
     * 查看用户是会否绑定银行卡
     *
     * @param request
     * @return
     */
    @PostMapping("/selectBankCard")
    @ResponseBody
    public boolean selectBankCard(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<BankCard> bankCards = bankCardService.selectBankCard(user.getId());
        return bankCards.size() > 0;
    }


    /**
     * 跳转提现页面
     *
     * @return
     */
    @GetMapping("/jumpDeposit")
    public String jumpDeposit() {
        return "balance";
    }

    /**
     * 跳转绑定银行卡
     *
     * @return
     */
    @GetMapping("/jumpCard")
    public String jumpCard() {
        return "card";
    }

    /**
     * 短信
     *
     * @return
     */
    @PostMapping("/note")
    @ResponseBody
    public void jumpCard(@RequestParam("phone") String phone, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String s = user.getId().toString();
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        Map note = (Map) noteCodeService.note(phone).getData();
        if ("0001".equals(note.get("retCode"))) {
            resultCode.setCode(200);
            jedis.set(s, (String) note.get("mession"));
        }
    }


    /**
     * 提现
     *
     * @param price
     * @param request
     * @return
     */
    @RequestMapping("/withdrawDeposit")
    @ResponseBody
    public ResultCode withdrawDeposit(String price, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(500);
        resultCode.setMsg("审核中");
        DepositSpecificationExample depositSpecificationExample = new DepositSpecificationExample();
        List<DepositSpecification> depositSpecifications = depositSpecificationMapper.selectByExample(depositSpecificationExample);
        DepositSpecification depositSpecification = depositSpecifications.get(0);
        if (Double.parseDouble(price) < depositSpecification.getDepositSum()) {
            resultCode.setMsg("低于起提金额");
            return resultCode;
        }
        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria criteria = userAccountExample.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        List<UserAccount> userAccounts = userAccountMapper.selectByExample(userAccountExample);
        if (userAccounts.size() > 0) {
            UserAccount userAccount = userAccounts.get(0);
            //手续费判断
            double chargePrice = Double.parseDouble(price) * depositSpecification.getServiceCharge();
            if (chargePrice < 1) {
                chargePrice = 1;
            }
            double money = Double.parseDouble(price) + chargePrice;//手续费加用户所提金额
            if (userAccount.getMoney() < money) {
                resultCode.setMsg("余额不足");
                return resultCode;
            }
            List<BankCard> bankCards = bankCardService.selectBankCard(user.getId());
            if (bankCards.size() > 0) {
                String requestSN = UUID.randomUUID().toString().replace("-", "");
                //添加中间表
                UserRequestsnExample userRequestsnExample = new UserRequestsnExample();
                UserRequestsnExample.Criteria criteria1 = userRequestsnExample.createCriteria();
                criteria1.andUserIdEqualTo(user.getId());
                List<UserRequestsn> userRequestsns = userRequestsnMapper.selectByExample(userRequestsnExample);
                if (userRequestsns.size() > 0) {
                    userRequestsns.get(0).setRequestsn(requestSN);
                    userRequestsnMapper.updateByPrimaryKey(userRequestsns.get(0));
                } else {
                    UserRequestsn userRequestsn = new UserRequestsn();
                    userRequestsn.setUserId(user.getId());
                    userRequestsn.setRequestsn(requestSN);
                    userRequestsnMapper.insertSelective(userRequestsn);
                }
                HashMap<String, String> map = new HashMap<>();
                map.put("txnAmt", new BigDecimal(price).toString());
                map.put("acctNo", bankCards.get(0).getBankCard());
                map.put("recvAccName", bankCards.get(0).getCardholderName());
                //map.put("flag","1");
                map.put("notifyUrl", "http://agent2.xinmintx.cn/agent/bankCard/poboNotify");
                map.put("requestSN", requestSN);
                log.info("发起请求之前");
                ResultCode resultCode1 = paymentOnBehalfOf.paymentOnBehalfOf(map);
                if (200 == resultCode1.getCode()) {
                    System.out.println(resultCode1);
                    resultCode.setMsg("提现成功");
                }
                return resultCode;
            }
        }
        return resultCode;
    }

    /**
     * 提现回调
     *
     * @param pn
     * @param
     */
    @DisableAuth
    @RequestMapping("/poboNotify")
    @ResponseBody
    public ResultCode paymentOnBehalfOfNotify(PoboNotify pn) {
        return paymentOnBehalfOf.paymentOnBehalfOfNotify(pn);
    }

    /**
     * 获取余额
     *
     * @return
     */
    @RequestMapping("/getPrice")
    @ResponseBody
    public double getPrice(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria criteria = userAccountExample.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        List<UserAccount> userAccounts = userAccountMapper.selectByExample(userAccountExample);
        if (userAccounts.size() > 0) {
            return userAccounts.get(0).getMoney();
        }
        return 0;
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping("/getKh")
    @ResponseBody
    public BankCard getKh(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        BankCard bank = bankCardService.getbank(user);
        HashMap hashMap = new HashMap();
        if (bank != null ) {
            if(StringUtils.isNotBlank(user.getAccountId())){
                hashMap.put("userId",user.getAccountId());
            }
            ResultCode resultCode = queryTiedCardService.queryTiedCard(hashMap);
            bank.setBankName(resultCode.getMsg());
            bank.setCardholderName(replaceNameX(bank.getCardholderName()));
            bank.setBankCard(bank.getBankCard().substring((bank.getBankCard().length()) - 4));
        }
        return bank;
    }

    /**
     * 名字处理
     * @param str
     * @return
     */
    public  String replaceNameX(String str){
        String reg = ".{1}";
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        int i = 0;
        while(m.find()){
            i++;
            if(i==1)
                continue;
            m.appendReplacement(sb, "*");
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
