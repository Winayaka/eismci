package com.propensi.eis.RestModel;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PIPOSpecial3 implements Serializable {

    private String poNum;
    private String poSupplierNum;
    private String supplierName;
    private String poPrice;
    private String containerQuantityPO;
    private List<PIPOSpecial4> pipoSpecial4;

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

    /**
     * @return the poSupplierNum
     */
    public String getPoSupplierNum() {
        return poSupplierNum;
    }

    /**
     * @param poSupplierNum the poSupplierNum to set
     */
    public void setPoSupplierNum(String poSupplierNum) {
        this.poSupplierNum = poSupplierNum;
    }

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return the poPrice
     */
    public String getPoPrice() {
        return poPrice;
    }

    /**
     * @param poPrice the poPrice to set
     */
    public void setPoPrice(String poPrice) {
        this.poPrice = poPrice;
    }

    /**
     * @return the containerQuantityPO
     */
    public String getContainerQuantityPO() {
        return containerQuantityPO;
    }

    /**
     * @param containerQuantityPO the containerQuantityPO to set
     */
    public void setContainerQuantityPO(String containerQuantityPO) {
        this.containerQuantityPO = containerQuantityPO;
    }
    
    /**
     * @return the pipoSpecial4
     */
    public List<PIPOSpecial4> getPipoSpecial4() {
        return pipoSpecial4;
    }

    /**
     * @param pipoSpecial4 the pipoSpecial4 to set
     */
    public void setPipoSpecial4(List<PIPOSpecial4> pipoSpecial4) {
        this.pipoSpecial4 = pipoSpecial4;
    }

    
    
}