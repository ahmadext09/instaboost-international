package com.amdck.instaboost.international.model;

import java.util.Objects;

public class PurchaseData {
    private String packageName;
    private String productId;
    private String purchaseToken;


    public PurchaseData() {
        // Initialize fields or leave them null by default
    }

    // Parameterized constructor
    public PurchaseData(String packageName, String productId, String purchaseToken) {
        this.packageName = packageName;
        this.productId = productId;
        this.purchaseToken = purchaseToken;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPurchaseToken() {
        return purchaseToken;
    }

    public void setPurchaseToken(String purchaseToken) {
        this.purchaseToken = purchaseToken;
    }

    // You can also manually override the equals, hashCode, and toString methods if needed.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseData that = (PurchaseData) o;
        return packageName.equals(that.packageName) &&
                productId.equals(that.productId) &&
                purchaseToken.equals(that.purchaseToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageName, productId, purchaseToken);
    }

    @Override
    public String toString() {
        return "PurchaseData{" +
                "packageName='" + packageName + '\'' +
                ", productId='" + productId + '\'' +
                ", purchaseToken='" + purchaseToken + '\'' +
                '}';
    }
}


