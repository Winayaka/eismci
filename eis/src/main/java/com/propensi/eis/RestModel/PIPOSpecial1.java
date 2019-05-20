package com.propensi.eis.RestModel;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PIPOSpecial1 implements Serializable{

    private String piNum;
    private String customername;
    private String finalDestination;
    private List<PIPOSpecial2> pipoSpecial2;

    /**
     * @return the piNum
     */
    public String getPiNum() {
        return piNum;
    }

    /**
     * @param piNum the piNum to set
     */
    public void setPiNum(String piNum) {
        this.piNum = piNum;
    }

    /**
     * @return the customername
     */
    public String getCustomername() {
        return customername;
    }

    /**
     * @param customername the customername to set
     */
    public void setCustomername(String customername) {
        this.customername = customername;
    }

    /**
     * @return the pipoSpecial2
     */
    public List<PIPOSpecial2> getPipoSpecial2() {
        return pipoSpecial2;
    }

    /**
     * @param pipoSpecial2 the pipoSpecial2 to set
     */
    public void setPipoSpecial2(List<PIPOSpecial2> pipoSpecial2) {
        this.pipoSpecial2 = pipoSpecial2;
    }

    /**
     * @return the finalDestination
     */
    public String getFinalDestination() {
        return finalDestination;
    }

    /**
     * @param finalDestination the finalDestination to set
     */
    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }
    
}