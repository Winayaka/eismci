package com.propensi.eis.RestModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class SIOutputCustomer implements Serializable{

    private String pod;
    private String pol;
    private LocalDate eta;
    private LocalDate etd;
    private String shipmentStatus;
    private String finalDestination;
    private String staffName;
    private String invoice;
    private String paymentStatus;
    private Long shipmentId;
    private List<PIPOTransFormOut> produkDetail;

    /**
     * @return the pod
     */
    public String getPod() {
        return pod;
    }

    /**
     * @param pod the pod to set
     */
    public void setPod(String pod) {
        this.pod = pod;
    }

    /**
     * @return the pol
     */
    public String getPol() {
        return pol;
    }

    /**
     * @param pol the pol to set
     */
    public void setPol(String pol) {
        this.pol = pol;
    }

    /**
     * @return the eta
     */
    public LocalDate getEta() {
        return eta;
    }

    /**
     * @param eta the eta to set
     */
    public void setEta(LocalDate eta) {
        this.eta = eta;
    }

    /**
     * @return the etd
     */
    public LocalDate getEtd() {
        return etd;
    }

    /**
     * @param etd the etd to set
     */
    public void setEtd(LocalDate etd) {
        this.etd = etd;
    }

    /**
     * @return the shipmentStatus
     */
    public String getShipmentStatus() {
        return shipmentStatus;
    }

    /**
     * @param shipmentStatus the shipmentStatus to set
     */
    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
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
     * @return the invoice
     */
    public String getInvoice() {
        return invoice;
    }

    /**
     * @param invoice the invoice to set
     */
    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    /**
     * @return the paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * @return the shipmentId
     */
    public Long getShipmentId() {
        return shipmentId;
    }

    /**
     * @param shipmentId the shipmentId to set
     */
    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    /**
     * @return the produkDetail
     */
    public List<PIPOTransFormOut> getProdukDetail() {
        return produkDetail;
    }

    /**
     * @param produkDetail the produkDetail to set
     */
    public void setProdukDetail(List<PIPOTransFormOut> produkDetail) {
        this.produkDetail = produkDetail;
    }

    
}