package com.xinmintx.hstx.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.xinmintx.hstx.mapper.MemberLineMapper;
import com.xinmintx.hstx.mapper.MemberTreeMapper;
import com.xinmintx.hstx.pojo.po.Member;
import com.xinmintx.hstx.pojo.po.MemberTree;
import com.xinmintx.hstx.service.IMemberTreeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * YaoDongHuang
 */
@Transactional
@Service
public class MemberTreeServiceImpl implements IMemberTreeService {

    @Autowired
    private MemberTreeMapper memberTreeMapper;

    @Autowired
    private MemberLineMapper memberLineMapper;

    /**
     * @param id       会员卡ID  不能为空
     * @param treeCode 会员树编码  treeCode不传会自动获取一个code进行绑定,如果传了(请保证传过来的code存在)
     *                 就绑定到传过来的code中
     */
    @Override
    public void setTreeNumForMember(int id, String treeCode) {
        // 查询会员树表中是否存在数据,存在则不需要操作,不存在则插入初始化数据列表
        Integer result = memberTreeMapper.ifExistsData();
        if (result == null || result == 0) {
            initData(memberTreeMapper);
        }
        // 查询该用户是否已经对应过treeCode
        Integer ifExistsMember = memberTreeMapper.ifExistsMember(id);
        // 处理数据
        dealData(ifExistsMember, id, treeCode, memberTreeMapper, memberLineMapper);
    }

    /**
     * @param id 会员ID
     * @return 返回矩阵中的上层两个member的IdList, 如果没有则返回null
     */
    @Override
    public List<Integer> getMemberId(int id) {
        // 根据id判断返回路线到哪里,获取具体id
        int getDeleteId = id;
        String upperLine = memberLineMapper.getUpperLineById(id, 1);
        if (StringUtils.isNotEmpty(upperLine)) {
            String strArray[] = upperLine.split(",");
            id = Integer.valueOf(strArray[1]);
        }
        // 树前两层不进行递归
        Long getTreeId = memberTreeMapper.getTreeIdByMemberId(getDeleteId);
        if (getTreeId == null || getTreeId <= 4) {
            return null;
        }
        List<Integer> idList = new ArrayList<Integer>();
        // 获取上层树
        MemberTree m1 = memberTreeMapper.getUpperMemberId(id);
        if (m1 == null) {
            // 删除树,从头开始计算
            memberLineMapper.deleteMemberLineById(getDeleteId);
            return getMemberId(getDeleteId);
        }
        if (m1 != null && m1.getMemberId() == null) {
            // 删除线路表中的数据,从头开始匹配
            if (m1.getTreeId() == 1) {
                // 删除树,从头开始计算
                memberLineMapper.deleteMemberLineById(getDeleteId);
                return getMemberId(getDeleteId);
            } else {
                return null;
            }
        } else if (m1 != null && m1.getMemberId() != null) {
            MemberTree m2 = memberTreeMapper.getUpperMemberId(m1.getMemberId());
            if (m2 == null) {
                // 删除树,从头开始计算
                memberLineMapper.deleteMemberLineById(getDeleteId);
                return getMemberId(getDeleteId);
            }
            if (m2 != null && m2.getMemberId() == null) {
                return null;
            }
            idList.add(m1.getMemberId());
            idList.add(m2.getMemberId());
            return idList;
        }
        return null;
    }

    /**
     * 会员id上层十个会员id的list
     *
     * @param id 会员ID
     * @return 有数据返回id列表, 没数据返回空列表
     */
    @Override
    public List<Integer> getTenMemberId(int id) {
        // 查询当前第几层
        Integer getfloor = 10;
        List<Integer> idList = new ArrayList<>();
        String treeCode = memberTreeMapper.getTreeCodeByMemberId(id);
        if (StringUtils.isNotEmpty(treeCode)) {
            int abc = (int) treeCode.substring(0, 1).toCharArray()[0];
            int floor = abc - 64;// 当前层数
            int count = floor - 1;// 推几次
            int plus = getfloor - count;//第一层追加分润的次数
            Boolean ifTop = false;
            Integer topId = null;
            for (int i = 0; i < count; i++) {
                // 获取上层树
                MemberTree m = memberTreeMapper.getUpperMemberId(id);
                if (m != null && m.getMemberId() != null) {
                    id = m.getMemberId();
                    idList.add(m.getMemberId());
                    if (m.getTreeId().intValue() == 1) {
                        ifTop = true;
                        topId = m.getMemberId();
                        break;
                    }
                } else {
                    break;
                }
            }
            if (idList != null && idList.size() > 0 && ifTop) {
                for (int i = 0; i < plus; i++) {
                    if (topId != null) {
                        idList.add(topId);
                    }
                }
            }
        }
        return idList;
    }

    /**
     * 用于保存会员线路信息,分润成功之后调用该方法进行保存
     *
     * @param id     会员ID
     * @param idList 上层分润的idList
     */
    @Override
    public void saveMemberLine(int id, List<Integer> idList) {
        if (idList != null && idList.size() > 0) {
            String upperLine = String.valueOf(idList.get(0)) + "," + String.valueOf(idList.get(1));
            memberLineMapper.insertLine(id, upperLine);
        }
    }

    /**
     * 根据会员id查询下游所有的账号数量
     *
     * @param id 会员ID
     * @return
     */
    @Override
    public Integer getNextMemberQty(int id) {
        Integer count = 0;
        List<MemberTree> mtList = memberTreeMapper.getNextMemberQty(id);
        List<MemberTree> nextMtList = new ArrayList<MemberTree>();
        if (mtList != null && mtList.size() > 0) {
            for (MemberTree mt : mtList) {
                if (mt.getMemberId() != null) {
                    count++;
                    nextMtList.add(mt);
                }
            }
            // 查询下游
            if (nextMtList != null && nextMtList.size() > 0) {
                for (MemberTree nextMt : nextMtList) {
                    // 下游递归查询
                    Integer nextCount = getNextMemberQty(nextMt.getMemberId());
                    count = count + nextCount;
                }
            }
        }
        return count;
    }


    // 数据处理
    private static void dealData(Integer ifExistsMember, int id, String treeCode,
                                 MemberTreeMapper memberTreeMapper, MemberLineMapper memberLineMapper) {
        if (ifExistsMember != null && ifExistsMember == 1 && StringUtils.isNotEmpty(treeCode)) {
            // 先判断treeCode是否存在,存在才执行下面的流程
            Integer ifExistsTreeCode = memberTreeMapper.ifExistsTreeCode(treeCode);
            if (ifExistsTreeCode == null) {
                // 1.存在且treeCode不为空,先解绑原有数据
                memberTreeMapper.updateMemberIdToNull(id);
                // 2.删除线路表中的数据
                memberLineMapper.deleteMemberLineById(id);
                // 3.后更新新数据
                memberTreeMapper.setTreeNoForMember(id, treeCode);
                memberTreeMapper.updateMemberTreeCode(treeCode, id);// 保存会员表中的树code
            }
        } else if ((ifExistsMember == null || ifExistsMember == 0) && StringUtils.isNotEmpty(treeCode)) {
            // 不存在且treeCode不为空,则更新
            Integer ifExistsTreeCode = memberTreeMapper.ifExistsTreeCode(treeCode);
            if (ifExistsTreeCode == null) {
                memberTreeMapper.setTreeNoForMember(id, treeCode);
                memberTreeMapper.updateMemberTreeCode(treeCode, id);// 保存会员表中的树code
            }
        } else if ((ifExistsMember == null || ifExistsMember == 0) && StringUtils.isEmpty(treeCode)) {
            // 不存在且treeCode为空默认保存9层开始的treeCode,获取code更新
            treeCode = getTreeCode(memberTreeMapper, 6);
            memberTreeMapper.setTreeNoForMember(id, treeCode);
            memberTreeMapper.updateMemberTreeCode(treeCode, id);// 保存会员表中的树code
        }
    }

    // 生成初始化数据
    private static List<MemberTree> getCodeList() {
        List<MemberTree> mtList = new ArrayList<MemberTree>();
        String code = "";
        int num = 1;
        int parentId = 1;
        for (int i = 0; i < 10; i++) {
            // 第一层生成字母A-J
            String abc = String.valueOf((char) (65 + i));
            for (int j = 0; j < num; j++) {
                // 第二层遍历生成树
                StringBuffer zero = new StringBuffer();
                for (int k = 0; k < 5 - String.valueOf(j).length(); k++) {
                    // 第三层补充缺位的0
                    zero.append("0");
                }
                if (String.valueOf(j).length() != String.valueOf(j + 1).length()) {
                    zero.deleteCharAt(0);
                }
                code = abc + zero + (j + 1);
                MemberTree mt = new MemberTree();
                mt.setTreeCode(code);
                // 获取上下层级关系id
                if (i > 0) {// 第二层开始赋值
                    mt.setParentTreeId((long) parentId);
                    if (i == 1) {
                        if (j != 0 && j % 2 == 0) {
                            ++parentId;
                        }
                    } else {
                        if (j != 0 && j % 3 == 2) {
                            ++parentId;
                        }
                    }
                }
                mtList.add(mt);
            }
            num = num * 3;
        }
        return mtList;
    }

    // 获取第九层开始未使用过的code(I开头),如果都使用了,则从后面层数递推
    private static String getTreeCode(MemberTreeMapper memberTreeMapper, Integer num) {
        Boolean ifExists = true;
        int i = 0;
        num = num - 1;
        String treeCode = "";
        while (ifExists) {
            treeCode = memberTreeMapper.getTreeCode(String.valueOf((char) (65 + num + i)));
            if (StringUtils.isEmpty(treeCode)) {// I层都被使用,从下一层去找
                i++;
                if (i > 17) {
                    break;// 遍历到Z跳出循环
                }
            } else {
                ifExists = false;
            }
        }
        return treeCode;
    }

    // 初始化数据
    private static void initData(MemberTreeMapper memberTreeMapper) {
        // 获取A-J所有code
        List<MemberTree> mtList = getCodeList();
        // 遍历list获取mtList
        if (mtList != null && mtList.size() > 0) {
            // 批量插入数据
            memberTreeMapper.insert(mtList);
            // 创建第二层到第五层的120个用户并且绑定Code
            initMemberAccount(5, mtList, memberTreeMapper);
        }
    }

    // 生成预留的n(5)层会员账号
    private static void initMemberAccount(int floor, List<MemberTree> mtList, MemberTreeMapper memberTreeMapper) {
        int count = 0;// 需要创建的用户账号数
        Integer num = 1;
        for (int i = 0; i < floor; i++) {
            for (int j = 0; j < num; j++) {
                count++;
            }
            num = num * 3;
        }
        count = count - 1;
        List<Member> memberList = new ArrayList<>();
        for (int i = 0; i < count; i++) {// 生成会员实体
            memberList.add(initMember(i, mtList));
        }
        if (memberList != null && memberList.size() > 0) {
            // 批量插入会员账号信息
            memberTreeMapper.insertMemberList(memberList);
            // 根据会员表的treeCode更新会员id到矩阵中
            memberTreeMapper.updateMemberIdToMemberTree();
        }
    }

    private static Member initMember(int i, List<MemberTree> mtList) {
        Member mb = new Member();
        mb.setName("占位会员" + (i + 1));
        mb.setGender(1);
        mb.setIdcard("");
        mb.setMemberType(6);
        mb.setIsReview(1);
        mb.setCreateTime(new Date());
        mb.setUpdateUser("");
        mb.setUpdateTime(new Date());
        mb.setAvatarUrl("");
        mb.setNewCurrency(new BigDecimal("0"));
        mb.setNewBeans(new BigDecimal("0"));
        mb.setUserId(0);
        mb.setFreezeBeans(new BigDecimal("0"));
        mb.setFreezeCurrency(new BigDecimal("0"));
        mb.setGiftStart(0);
        mb.setBirthGiftCount(0);
        mb.setTreeCode(mtList.get(i + 1).getTreeCode());
        return mb;
    }

}
