package com.xinmintx.agent.model;

public class DepositSpecification {
    private Integer id;

    private Long depositSum;

    private Double serviceCharge;

    private String reservedOne;

    private String reservedTwo;

    private String reservedThree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDepositSum() {
        return depositSum;
    }

    public void setDepositSum(Long depositSum) {
        this.depositSum = depositSum;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getReservedOne() {
        return reservedOne;
    }

    public void setReservedOne(String reservedOne) {
        this.reservedOne = reservedOne == null ? null : reservedOne.trim();
    }

    public String getReservedTwo() {
        return reservedTwo;
    }

    public void setReservedTwo(String reservedTwo) {
        this.reservedTwo = reservedTwo == null ? null : reservedTwo.trim();
    }

    public String getReservedThree() {
        return reservedThree;
    }

    public void setReservedThree(String reservedThree) {
        this.reservedThree = reservedThree == null ? null : reservedThree.trim();
    }
}