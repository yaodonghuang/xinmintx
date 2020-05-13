package com.xinmintx.hstx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xinmintx.hstx.mapper.MemberBankcardMapper;
import com.xinmintx.hstx.mapper.MemberMapper;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberBankcard;
import com.xinmintx.hstx.pojo.vo.MemberBankcardVo;
import com.xinmintx.hstx.service.BankCardService;
import com.xinmintx.hstx.util.ListUtils;
import com.xinmintx.hstx.util.httpclient.HttpClientUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankCardServiceImpl implements BankCardService {

    @Autowired
    private MemberBankcardMapper memberBankcardMapper;


    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<MemberBankcardVo> select(int id) {
        List<MemberBankcard> bankCard = new LambdaQueryChainWrapper<>(memberBankcardMapper)
                .eq(MemberBankcard::getMemberId, id).list();
        List<MemberBankcardVo> memberBankcardVos = ListUtils.listTrans(bankCard, MemberBankcardVo.class);
        int size = bankCard.size();
        for (int i = 0;i < size;i++){
            String s = HttpClientUtil.doGet("http://a3.work/open/demo3/index.php?bank=" + memberBankcardVos.get(i).getShortName());
            Object parse = JSON.parse(s);
            memberBankcardVos.get(i).setBankCardMsg(parse);
        }
        return memberBankcardVos;
    }

    @Override
    public MemberBankcard selectByCardByMemberId(String cardNumbers) {
        MemberBankcard bankCard = new LambdaQueryChainWrapper<>(memberBankcardMapper)
                .eq(MemberBankcard::getBankCard,cardNumbers).one();
        return bankCard;
    }

    @Override
    public int payPasswordverify(Member member, String pwd) {
        Member one = new LambdaQueryChainWrapper<>(memberMapper)
                .eq(Member::getId, member.getId()).one();
        //密码进行MD5加密
        String Md5pwd = DigestUtils.md5Hex(pwd);
        //获取原密码
        String payPassword = one.getPayPassword();
        //如果没有密码
        if(payPassword == null || payPassword == ""){
            return 2;
        }
        //密码验证
        if(Md5pwd.equals(payPassword)){
            return 1;
        }
        return 3;
    }
}
