package com.propensi.eis.RestModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class PIForm implements Serializable {
    private static final long serialVersionUID = -7223373703329725892L;

    private String number;
    private LocalDate date;
    private String countryOfOrigin;
    private String portOfLoading;
    private String portOfDestination;
    private LocalDate deliveryTerm;
    private String paymentTerm;
    private String termAndCondition;
    private String finalDestination;
    private LocalDate etaDate;
    private LocalDate etdDate;

    // //PaymentMethod
    // private String paymentName;
    // private String paymentNote;
    // private String paymentStatus;
    // private boolean paymentLcStatus;

    // Foreign column
    private long customerID;
    private List<PIDetailProductForm> productDetailList;

    /**
     * @return String return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return LocalDate return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return String return the countryOfOrigin
     */
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * @param countryOfOrigin the countryOfOrigin to set
     */
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    /**
     * @return String return the portOfLoading
     */
    public String getPortOfLoading() {
        return portOfLoading;
    }

    /**
     * @param portOfLoading the portOfLoading to set
     */
    public void setPortOfLoading(String portOfLoading) {
        this.portOfLoading = portOfLoading;
    }

    /**
     * @return String return the portOfDestination
     */
    public String getPortOfDestination() {
        return portOfDestination;
    }

    /**
     * @param portOfDestination the portOfDestination to set
     */
    public void setPortOfDestination(String portOfDestination) {
        this.portOfDestination = portOfDestination;
    }

    /**
     * @return LocalDate return the deliveryTerm
     */
    public LocalDate getDeliveryTerm() {
        return deliveryTerm;
    }

    /**
     * @param deliveryTerm the deliveryTerm to set
     */
    public void setDeliveryTerm(LocalDate deliveryTerm) {
        this.deliveryTerm = deliveryTerm;
    }

    /**
     * @return String return the paymentTerm
     */
    public String getPaymentTerm() {
        return paymentTerm;
    }

    /**
     * @param paymentTerm the paymentTerm to set
     */
    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    /**
     * @return String return the termAndCondition
     */
    public String getTermAndCondition() {
        return termAndCondition;
    }

    /**
     * @param termAndCondition the termAndCondition to set
     */
    public void setTermAndCondition(String termAndCondition) {
        this.termAndCondition = termAndCondition;
    }

    /**
     * @return String return the finalDestination
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

    /**
     * @return LocalDate return the etaDate
     */
    public LocalDate getEtaDate() {
        return etaDate;
    }

    /**
     * @param etaDate the etaDate to set
     */
    public void setEtaDate(LocalDate etaDate) {
        this.etaDate = etaDate;
    }

    /**
     * @return LocalDate return the etdDate
     */
    public LocalDate getEtdDate() {
        return etdDate;
    }

    /**
     * @param etdDate the etdDate to set
     */
    public void setEtdDate(LocalDate etdDate) {
        this.etdDate = etdDate;
    }

    /**
     * @return long return the customerID
     */
    public long getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    /**
     * @return List<PIDetailProductForm> return the productDetailList
     */
    public List<PIDetailProductForm> getProductDetailList() {
        return productDetailList;
    }

    /**
     * @param productDetailList the productDetailList to set
     */
    public void setProductDetailList(List<PIDetailProductForm> productDetailList) {
        this.productDetailList = productDetailList;
    }

    // /**
    // * @return the paymentName
    // */
    // public String getPaymentName() {
    // return paymentName;
    // }

    // /**
    // * @param paymentName the paymentName to set
    // */
    // public void setPaymentName(String paymentName) {
    // this.paymentName = paymentName;
    // }

    // /**
    // * @return the paymentNote
    // */
    // public String getPaymentNote() {
    // return paymentNote;
    // }

    // /**
    // * @param paymentNote the paymentNote to set
    // */
    // public void setPaymentNote(String paymentNote) {
    // this.paymentNote = paymentNote;
    // }

    // /**
    // * @return the paymentStatus
    // */
    // public String getPaymentStatus() {
    // return paymentStatus;
    // }

    // /**
    // * @param paymentStatus the paymentStatus to set
    // */
    // public void setPaymentStatus(String paymentStatus) {
    // this.paymentStatus = paymentStatus;
    // }

    // /**
    // * @return the paymentLcStatus
    // */
    // public boolean getPaymentLcStatus() {
    // return paymentLcStatus;
    // }

    // /**
    // * @param paymentLcStatus the paymentLcStatus to set
    // */
    // public void setPaymentLcStatus(boolean paymentLcStatus) {
    // this.paymentLcStatus = paymentLcStatus;
    // }

}