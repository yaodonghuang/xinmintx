//package com.xinmintx.community.service.impl;
//
//import com.xinmintx.community.dto.CommunityTypeAddDTO;
//import com.xinmintx.community.dto.CommunityTypeUpdateDTO;
//import com.xinmintx.community.mapper.CommunityShopTypeMapper;
//import com.xinmintx.community.mapper.CommunityTypeMapper;
//import com.xinmintx.community.model.CommunityType;
//import com.xinmintx.community.service.CommunityTypeService;
//import com.xinmintx.community.util.BeanUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @Author zhangliang
// * @Date 2020/2/10
// */
//
//@Slf4j
//@Service
//public class CommunityTypeServiceImpl implements CommunityTypeService {
//
//    @Autowired
//    CommunityTypeMapper communityTypeMapper;
//
//    @Autowired
//    CommunityShopTypeMapper communityShopTypeMapper;
//
//    @Override
//    public void add(CommunityTypeAddDTO communityTypeAddDTO) {
//        CommunityType communityType = BeanUtil.copyProperties(communityTypeAddDTO, CommunityType.class, true);
//        communityTypeMapper.add(communityType);
//    }
//
//    @Override
//    public void updateById(CommunityTypeUpdateDTO communityTypeUpdateDTO) {
//        CommunityType communityType = BeanUtil.copyProperties(communityTypeUpdateDTO, CommunityType.class, false);
//        communityTypeMapper.updateById(communityType);
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        if(communityShopTypeMapper.hasRelationShop(id)){
//            throw new RuntimeException("该类型有关联的供应商无法删除");
//        }
//        communityTypeMapper.deleteById(id);
//    }
//
//    @Override
//    public List<CommunityType> getAll() {
//        return communityTypeMapper.getAll();
//    }
//}
