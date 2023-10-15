package com.amdck.instaboost.international.model;


public class ProductPurchaseResponse {
    private String purchaseTimeMillis;
    private int purchaseState;
    private int consumptionState;
    private String developerPayload;
    private String orderId;
    private int purchaseType;
    private int acknowledgementState;
    private String kind;
    private String regionCode;


    public String getPurchaseTimeMillis() {
        return purchaseTimeMillis;
    }

    public void setPurchaseTimeMillis(String purchaseTimeMillis) {
        this.purchaseTimeMillis = purchaseTimeMillis;
    }

    public int getPurchaseState() {
        return purchaseState;
    }

    public void setPurchaseState(int purchaseState) {
        this.purchaseState = purchaseState;
    }

    public int getConsumptionState() {
        return consumptionState;
    }

    public void setConsumptionState(int consumptionState) {
        this.consumptionState = consumptionState;
    }

    public String getDeveloperPayload() {
        return developerPayload;
    }

    public void setDeveloperPayload(String developerPayload) {
        this.developerPayload = developerPayload;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(int purchaseType) {
        this.purchaseType = purchaseType;
    }

    public int getAcknowledgementState() {
        return acknowledgementState;
    }

    public void setAcknowledgementState(int acknowledgementState) {
        this.acknowledgementState = acknowledgementState;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}


