package com.xinmintx.hstx.controller;
import com.xinmintx.hstx.common.BaseController;
import com.xinmintx.hstx.pojo.po.DepositSpecification;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.vo.MemberBankcardVo;
import com.xinmintx.hstx.pojo.vo.MemberBindCardVO;
import com.xinmintx.hstx.pojo.po.MemberBankcard;
import com.xinmintx.hstx.pojo.vo.ResultCode;
import com.xinmintx.hstx.service.*;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cashAmount")
public class AechargeController  extends BaseController {

    @Autowired
    private AechargeService aechargeService;

    @Autowired
    private IMemberService IMemberService;

    @Autowired
    private BankCardService bankCardService;

    @Autowired
    private WalletService walletService;


    @Autowired
    private UpToTheAmountService upToTheAmountService;

    /**
     * 充值
     * @param
     * @param money
     * @return
     */
    @PostMapping("/memberAecharge")
    public ResultCode memberAecharge(Integer money){
        ResultCode resultCode = new ResultCode();
        Member member = IMemberService.findMemberByToken(token);
        Map map = aechargeService.unifiedorder(member.getId(),member,money);
        if (map != null && "SUCCESS".equals(map.get("code").toString())) {
            resultCode.setCode(200);
            resultCode.setMsg("SUCCESS");
            resultCode.setData(map);
        } else {
            resultCode.setMsg("FAIL");
            resultCode.setCode(500);
        }
        return resultCode;
    }

    /**
     * 获取起提金额 手续费百分比
     * @return
     */
    @PostMapping("/upToTheAmount")
    public ResultCode upToTheAmount(){
        DepositSpecification depositSpecification = upToTheAmountService.upToTheAmount();
        ResultCode resultCode = new ResultCode();
        resultCode.setCode(200);
        resultCode.setMsg("起提金额");
        resultCode.setData(depositSpecification);
        return resultCode;
    }

    /**
     * 提现
     * @param money 金额
     * @param cardNumbers 卡号
     * @return
     */
    @PostMapping("/withdrawDeposit")
    public ResultCode withdrawDeposit(@RequestParam("money") String money,@RequestParam("cardNumbers")String cardNumbers,@RequestParam("pwd")String pwd){
        ResultCode resultCode = new ResultCode();
        Member member = IMemberService.findMemberByToken(token);
        //交易密码验证
        int memberPwd = bankCardService.payPasswordverify(member,pwd);
        if(memberPwd == 1){
            MemberBankcard bankCard = bankCardService.selectByCardByMemberId(cardNumbers);
            if(bankCard == null){
                resultCode.setCode(500);
                resultCode.setMsg("用户不存在");
                return resultCode;
            }
            resultCode = walletService.factoryGetMoney(bankCard, money, member);
        }else if(memberPwd == 2){
            resultCode.setCode(500);
            resultCode.setMsg("请设置交易密码");
        }else {
            resultCode.setCode(500);
            resultCode.setMsg("密码错误");
        }
        return resultCode;
    }

    /**
     * 获取用户银行卡信息
     * @return
     */
    @PostMapping("/bankCardDisplay")
    public ResultCode bankCardDisplay(){
        ResultCode resultCode = new ResultCode();
        Member member = IMemberService.findMemberByToken(token);
        List<MemberBankcardVo> bankCard = bankCardService.select(member.getId());
        resultCode.setCode(200);
        resultCode.setData(bankCard);
        return resultCode;
    }

    /**
     * 绑定银行卡
     *
     * @param memberBindCardVO 接收银行卡部分信息，用于存储银行卡信息
     * @return ResultCode { code:xxx , msg:xxx , data:[ xxx:xxx , ...]}
     *
     */
    @PostMapping("/memberBindCard")
    public ResultCode memberBindCard(@Valid @RequestBody MemberBindCardVO memberBindCardVO , @Ignore ResultCode resultCode){
         aechargeService.memberBindCard(token, memberBindCardVO);
         return resultCode;
    }
}
