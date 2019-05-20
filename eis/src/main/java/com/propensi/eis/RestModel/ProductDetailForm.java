package com.propensi.eis.RestModel;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductDetailForm implements Serializable {
    private String productName;
    private String poNum;
    private int quantity;

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
     * @return the poNum
     */
    public String getPoNum() {
        return poNum;
    }

    /**
     * @param poNum the poNum to set
     */
    public void setPoNum(String poNum) {
        this.poNum = poNum;
    }

    
   
  
}