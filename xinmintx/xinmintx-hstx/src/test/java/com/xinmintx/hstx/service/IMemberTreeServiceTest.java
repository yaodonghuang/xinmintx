package com.xinmintx.hstx.service;

import com.xinmintx.hstx.pojo.po.Member;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.List;

import static com.xinmintx.hstx.util.FieldUtils.getAllFields;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/26 0026
 * @time: 下午 15:55
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class IMemberTreeServiceTest {

    @Autowired
    private IMemberTreeService service;

    @Test
    void getMemberId() {
        List<Integer> memberId = service.getTenMemberId(71);
        System.out.println(memberId);
    }
    @Test
    void getMemberIdS() {
        List<Field> allFields = getAllFields(Member.class);
    }
}