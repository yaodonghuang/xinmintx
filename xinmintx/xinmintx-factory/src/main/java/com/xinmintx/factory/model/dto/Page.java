package com.xinmintx.factory.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by hjh
 * @date: 2020/3/17
 * @time: 16:13
 * @Description:
 */
@Data
public class Page<T> implements Serializable {
//    private Integer startNumberOfPages;//起始页数
    private Integer numberOfPages;//当前页数
    private Integer totPages;//总页数
    private Integer pagination;//页内数
    private T data;

    public Page(Integer numberOfPages, Integer pagination) {
        this.numberOfPages = numberOfPages;
        this.pagination = pagination;
    }
}
