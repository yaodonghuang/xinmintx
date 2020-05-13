package com.xinmintx.hstx.controller;

import com.xinmintx.hstx.util.PinYinUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2020/2/10 0010
 * @time: 下午 14:52
 * @Description:
 */
@RestController
@RequestMapping("/hs/pinyin")
public class PinYinController {

    @GetMapping("getPinYin")
    public String getPinYin(String name) {
        if (StringUtils.isBlank(name)){
            return null;
        }
        return PinYinUtils.toHanYuPinyinUpperCase(name, " ");
    }
}
