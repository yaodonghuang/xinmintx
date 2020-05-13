package com.xinmintx.agent.model;

public class BankCard {
    private Integer id;

    private String bankCard;

    private Integer start;

    private String cellphone;

    private Integer userId;

    private String bankName;

    private String bankType;

    private String cardholderName;

    private String commonOne;

    private String commonTwo;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType == null ? null : bankType.trim();
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName == null ? null : cardholderName.trim();
    }

    public String getCommonOne() {
        return commonOne;
    }

    public void setCommonOne(String commonOne) {
        this.commonOne = commonOne == null ? null : commonOne.trim();
    }

    public String getCommonTwo() {
        return commonTwo;
    }

    public void setCommonTwo(String commonTwo) {
        this.commonTwo = commonTwo == null ? null : commonTwo.trim();
    }
}