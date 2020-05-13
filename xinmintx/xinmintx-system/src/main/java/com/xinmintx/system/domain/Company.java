package com.xinmintx.system.domain;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/11/12 0012
 * @time: 上午 9:14
 * @Description:
 */
public class Company extends User {

    private String cardUp;
    private String cardDown;
    private Integer cardUpId;
    private Integer cardDownId;

    public String getCardUp() {
        return cardUp;
    }

    public void setCardUp(String cardUp) {
        this.cardUp = cardUp;
    }

    public String getCardDown() {
        return cardDown;
    }

    public void setCardDown(String cardDown) {
        this.cardDown = cardDown;
    }

    public Integer getCardUpId() {
        return cardUpId;
    }

    public void setCardUpId(Integer cardUpId) {
        this.cardUpId = cardUpId;
    }

    public Integer getCardDownId() {
        return cardDownId;
    }

    public void setCardDownId(Integer cardDownId) {
        this.cardDownId = cardDownId;
    }
}
