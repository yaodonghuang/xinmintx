package com.xinmintx.hstx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinmintx.hstx.mapper.ShippingAddressMapper;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.ShippingAddress;
import com.xinmintx.hstx.pojo.vo.MemberAddressVo;
import com.xinmintx.hstx.service.IMemberService;
import com.xinmintx.hstx.service.ShippingAddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangkang
 */
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
    @Autowired
    private ShippingAddressMapper shippingAddressMapper;
    @Autowired
    private IMemberService IMemberService;

    private Member member;

    /**
     * 添加收货地址
     *
     * @param memberAddressVo
     * @return
     */
    @Override
    public int addShippingAddress(MemberAddressVo memberAddressVo) {
        this.member = IMemberService.findMemberByToken(memberAddressVo.getToken());
        ShippingAddress shippingAdress = getShippingAdress(memberAddressVo);
        List<ShippingAddress> list = getShippingAddressListByMemberId();
        if (list.size() < 1) {
            //没有收货地
            //新增收获地址,设为默认
            shippingAdress.setDefaultAddress(1);
            return shippingAddressMapper.insert(shippingAdress);
        } else {
            //已经有了收货地址
            if (memberAddressVo.getChecked() != null && memberAddressVo.getChecked()) {
                //设置新地址为默认,原本地址改为非默认
                modifyDefaultAddress();
            }
            //保存新地址
            return shippingAddressMapper.insert(shippingAdress);
        }
    }

    private List<ShippingAddress> getShippingAddressListByMemberId() {
        Map<String, Object> map = new HashMap<>();
        map.put("member_id", member.getId());
        return shippingAddressMapper.selectByMap(map);
    }

    /**
     * 查看消费者收货地址
     *
     * @param token
     * @return
     */
    @Override
    public List<MemberAddressVo> selectShippingAddress(String token) {
        Member member = IMemberService.findMemberByToken(token);
        QueryWrapper<ShippingAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", member.getId());
        queryWrapper.orderByDesc("default_address");
        List<ShippingAddress> shippingAddressList = shippingAddressMapper.selectList(queryWrapper);
        List<MemberAddressVo> list = new ArrayList<>();
        for (ShippingAddress shippingAddress : shippingAddressList) {
            MemberAddressVo memberAddressVo = getMemberAddressDTO(shippingAddress);
            list.add(memberAddressVo);
        }
        return list;
    }

    /**
     * 修改消费者收货地址
     *
     * @param id
     * @return
     */
    @Override
    public MemberAddressVo editShippingAddress(int id) {
        ShippingAddress shippingAddress = shippingAddressMapper.selectById(id);
        return getMemberAddressDTO(shippingAddress);
    }

    /**
     * 保存修改的地址
     *
     * @param memberAddressVo
     * @return
     */
    @Override
    public int updateShippingAddress(MemberAddressVo memberAddressVo) {
        this.member = IMemberService.findMemberByToken(memberAddressVo.getToken());
        ShippingAddress shippingAddress = getShippingAdress(memberAddressVo);
        if (shippingAddress.getDefaultAddress() == 0) {
            return shippingAddressMapper.updateById(shippingAddress);
        }
        modifyDefaultAddress();
        return shippingAddressMapper.updateById(shippingAddress);
    }

    /**
     * 删除收货地址
     *
     * @param id
     * @return
     */
    @Override
    public int deleteShippingAddress(int id) {
        return shippingAddressMapper.deleteById(id);
    }

    /**
     * 查询默认收货地址
     *
     * @param token
     * @return
     */
    @Override
    public MemberAddressVo selectDefaultAddress(String token) {
        Member member = IMemberService.findMemberByToken(token);
        Map<String, Object> map = new HashMap<>();
        map.put("member_id", member.getId());
        map.put("default_address", 1);
        List<ShippingAddress> list = shippingAddressMapper.selectByMap(map);
        if (list.size() > 0) {
            ShippingAddress shippingAddress = list.get(0);
            return getMemberAddressDTO(shippingAddress);
        }
        return null;
    }

    /**
     * 根据memberAddressDTO得到ShippingAddress
     *
     * @param memberAddressVo
     * @return
     */
    private ShippingAddress getShippingAdress(MemberAddressVo memberAddressVo) {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(memberAddressVo.getId());
        shippingAddress.setMemberId(member.getId());
        shippingAddress.setName(memberAddressVo.getName());
        shippingAddress.setCellphone(memberAddressVo.getCellphone());
        shippingAddress.setRegion(memberAddressVo.getRegion());
        shippingAddress.setAddress(memberAddressVo.getAddress());
        if (StringUtils.isNotBlank(memberAddressVo.getRegionCode())) {
            shippingAddress.setRegionCode(memberAddressVo.getRegionCode());
        }
        if (memberAddressVo.getChecked() != null && memberAddressVo.getChecked()) {
            shippingAddress.setDefaultAddress(1);
        } else {
            shippingAddress.setDefaultAddress(0);
        }
        return shippingAddress;
    }

    /**
     * 把原本默认地址设为非默认
     *
     * @return
     */
    private int modifyDefaultAddress() {
        Map<String, Object> map = new HashMap<>();
        map.put("member_id", member.getId());
        map.put("default_address", 1);
        List<ShippingAddress> list = shippingAddressMapper.selectByMap(map);
        if (list.size() == 0) {
            return 0;
        }
        ShippingAddress shippingAddress = list.get(0);
        shippingAddress.setDefaultAddress(0);
        return shippingAddressMapper.updateById(shippingAddress);
    }

    /**
     * 根据shippingAddress得到MemberAddressDTO
     *
     * @param shippingAddress
     * @return
     */
    private MemberAddressVo getMemberAddressDTO(ShippingAddress shippingAddress) {
        if (shippingAddress == null) {
            return null;
        }
        MemberAddressVo memberAddressVo = new MemberAddressVo();
        memberAddressVo.setId(shippingAddress.getId());
        memberAddressVo.setName(shippingAddress.getName());
        memberAddressVo.setCellphone(shippingAddress.getCellphone());
        memberAddressVo.setRegion(shippingAddress.getRegion());
        memberAddressVo.setRegionCode(shippingAddress.getRegionCode());
        memberAddressVo.setAddress(shippingAddress.getAddress());
        if (shippingAddress.getDefaultAddress() == 1) {
            memberAddressVo.setChecked(true);
        } else {
            memberAddressVo.setChecked(false);
        }
        return memberAddressVo;
    }
}
