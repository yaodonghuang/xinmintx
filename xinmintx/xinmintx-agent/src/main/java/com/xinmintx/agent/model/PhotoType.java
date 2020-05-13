package com.xinmintx.agent.model;

import org.springframework.web.multipart.MultipartFile;

public class PhotoType {
    private MultipartFile idCardQ;
    private MultipartFile idCardH;
    private MultipartFile bankCardQ;
    private MultipartFile bankCardH;
    private MultipartFile merchantDH;
    private MultipartFile store;
    private MultipartFile storeT;
    private MultipartFile busines;

    public MultipartFile getStoreT() {
        return storeT;
    }

    public void setStoreT(MultipartFile storeT) {
        this.storeT = storeT;
    }

    public MultipartFile getIdCardQ() {
        return idCardQ;
    }

    public void setIdCardQ(MultipartFile idCardQ) {
        this.idCardQ = idCardQ;
    }

    public MultipartFile getIdCardH() {
        return idCardH;
    }

    public void setIdCardH(MultipartFile idCardH) {
        this.idCardH = idCardH;
    }

    public MultipartFile getBankCardQ() {
        return bankCardQ;
    }

    public void setBankCardQ(MultipartFile bankCardQ) {
        this.bankCardQ = bankCardQ;
    }

    public MultipartFile getBankCardH() {
        return bankCardH;
    }

    public void setBankCardH(MultipartFile bankCardH) {
        this.bankCardH = bankCardH;
    }

    public MultipartFile getMerchantDH() {
        return merchantDH;
    }

    public void setMerchantDH(MultipartFile merchantDH) {
        this.merchantDH = merchantDH;
    }

    public MultipartFile getStore() {
        return store;
    }

    public void setStore(MultipartFile store) {
        this.store = store;
    }

    public MultipartFile getBusines() {
        return busines;
    }

    public void setBusines(MultipartFile busines) {
        this.busines = busines;
    }
}
