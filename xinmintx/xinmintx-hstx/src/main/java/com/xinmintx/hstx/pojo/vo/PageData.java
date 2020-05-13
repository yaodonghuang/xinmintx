package com.xinmintx.hstx.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/23 0029
 * @time: 上午 9:16
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData {
    private Integer pageIndex;
    private Integer pageSize;
}
