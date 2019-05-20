package com.propensi.eis.RestModel;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PIPOSpecial2 implements Serializable{

    private String productName;
    private String Container;
    private String Price;
    private List<PIPOSpecial3> pipoSpecial3;

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
     * @return the Container
     */
    public String getContainer() {
        return Container;
    }

    /**
     * @param Container the Container to set
     */
    public void setContainer(String Container) {
        this.Container = Container;
    }

    /**
     * @return the Price
     */
    public String getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(String Price) {
        this.Price = Price;
    }

    /**
     * @return the pipoSpecial3
     */
    public List<PIPOSpecial3> getPipoSpecial3() {
        return pipoSpecial3;
    }

    /**
     * @param pipoSpecial3 the pipoSpecial3 to set
     */
    public void setPipoSpecial3(List<PIPOSpecial3> pipoSpecial3) {
        this.pipoSpecial3 = pipoSpecial3;
    }
    
}
