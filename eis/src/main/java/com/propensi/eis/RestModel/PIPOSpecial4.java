package com.propensi.eis.RestModel;

import java.io.Serializable;

import lombok.Data;

@Data
public class PIPOSpecial4 implements Serializable{

    private String noSI;
    private String staffName;
    private String Quantity;
    private String invoiceNum;

    /**
     * @return the noSI
     */
    public String getNoSI() {
        return noSI;
    }

    /**
     * @param noSI the noSI to set
     */
    public void setNoSI(String noSI) {
        this.noSI = noSI;
    }

    /**
     * @return the staffName
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * @param staffName the staffName to set
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return Quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    /**
     * @return the invoiceNum
     */
    public String getInvoiceNum() {
        return invoiceNum;
    }

    /**
     * @param invoiceNum the invoiceNum to set
     */
    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }
    
}