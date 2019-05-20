package com.propensi.eis.RestModel;

import com.propensi.eis.Model.ProductModel;

import lombok.Data;

@Data
public class PIPOTransFormOut {

    private Long PIPOId;
    private int quantity;
    private String PONum;
    private String PINum;
    private String productName;
    private Long productId;
    private double grossWeight;
    private double netWeight;
    private String productUnit;
    private String productDesc;
    private int quantityLeft;
    private long siDetailId;

    /**
     * @return the pIPOId
     */
    public Long getPIPOId() {
        return PIPOId;
    }

    /**
     * @param pIPOId the pIPOId to set
     */
    public void setPIPOId(Long pIPOId) {
        PIPOId = pIPOId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the pONum
     */
    public String getPONum() {
        return PONum;
    }

    /**
     * @param pONum the pONum to set
     */
    public void setPONum(String pONum) {
        PONum = pONum;
    }

    /**
     * @return the pINum
     */
    public String getPINum() {
        return PINum;
    }

    /**
     * @param pINum the pINum to set
     */
    public void setPINum(String pINum) {
        PINum = pINum;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productId
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return the grossWeight
     */
    public double getGrossWeight() {
        return grossWeight;
    }

    /**
     * @param grossWeight the grossWeight to set
     */
    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    /**
     * @return the netWeight
     */
    public double getNetWeight() {
        return netWeight;
    }

    /**
     * @param netWeight the netWeight to set
     */
    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    /**
     * @return the productUnit
     */
    public String getproductUnit() {
        return productUnit;
    }

    /**
     * @param productUnit the productUnit to set
     */
    public void setproductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    /**
     * @return the productDesc
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * @param productDesc the productDesc to set
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * @return the quantityLeft
     */
    public int getQuantityLeft() {
        return quantityLeft;
    }

    /**
     * @param quantityLeft the quantityLeft to set
     */
    public void setQuantityLeft(int quantityLeft) {
        this.quantityLeft = quantityLeft;
    }

    /**
     * @return the siDetailId
     */
    public long getSiDetailId() {
        return siDetailId;
    }

    /**
     * @param siDetailId the siDetailId to set
     */
    public void setSiDetailId(long siDetailId) {
        this.siDetailId = siDetailId;
    }

    


}