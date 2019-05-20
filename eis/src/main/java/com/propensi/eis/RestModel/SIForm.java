package com.propensi.eis.RestModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class SIForm implements Serializable {
    private static final long serialVersionUID = 6817836953505456514L;

    private long proformaInvoiceId;
    private String number;
    private long purchaseOrderId;
    private String invoice;
    private long staff;
    private String vesselName;
    private String connectingVessel;
    private String liner;
    private LocalTime openTime;
    private LocalDate openDate;
    private LocalDate closeDate;
    private LocalTime closeTime;
    private String poL;
    private String poD;
    private LocalDate eTA;
    private LocalDate etdep;
    private String finalDestination;
    private String status;
    private String paymentStatus;
    private String shipmentPriority;
    private List<PIPOTransFormOut> product;

    //tambahan untuk update
    private String siDocument;
    private String bookingConfirmation;

    /**
     * @return the proformaInvoice
     */
    public long getProformaInvoiceId() {
        return proformaInvoiceId;
    }

    /**
     * @param proformaInvoice the proformaInvoice to set
     */
    public void setProformaInvoiceId(long proformaInvoice) {
        this.proformaInvoiceId = proformaInvoice;
    }

    /**
     * @return the number
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
     * @return the purchaseOrder
     */
    public long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    /**
     * @param purchaseOrder the purchaseOrder to set
     */
    public void setPurchaseOrderId(long purchaseOrder) {
        this.purchaseOrderId = purchaseOrder;
    }

    /**
     * @return the staff
     */
    public long getStaff() {
        return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(long staff) {
        this.staff = staff;
    }

    /**
     * @return the vesselName
     */
    public String getVesselName() {
        return vesselName;
    }

    /**
     * @param vesselName the vesselName to set
     */
    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    /**
     * @return the connectingVessel
     */
    public String getConnectingVessel() {
        return connectingVessel;
    }

    /**
     * @param connectingVessel the connectingVessel to set
     */
    public void setConnectingVessel(String connectingVessel) {
        this.connectingVessel = connectingVessel;
    }

    /**
     * @return the liner
     */
    public String getLiner() {
        return liner;
    }

    /**
     * @param liner the liner to set
     */
    public void setLiner(String liner) {
        this.liner = liner;
    }


    /**
     * @return the poL
     */
    public String getPoL() {
        return poL;
    }

    /**
     * @param poL the poL to set
     */
    public void setPoL(String poL) {
        this.poL = poL;
    }

    /**
     * @return the poD
     */
    public String getPoD() {
        return poD;
    }

    /**
     * @param poD the poD to set
     */
    public void setPoD(String poD) {
        this.poD = poD;
    }

    /**
     * @return the eTA
     */
    public LocalDate getETA() {
        return eTA;
    }

    /**
     * @param eTA the eTA to set
     */
    public void setETA(LocalDate eTA) {
        this.eTA = eTA;
    }

    /**
     * @return the etdep
     */
    public LocalDate getEtdep() {
        return etdep;
    }

    /**
     * @param etdep the etdep to set
     */
    public void setEtdep(LocalDate etdep) {
        this.etdep = etdep;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * @return the eTA
     */
    public LocalDate geteTA() {
        return eTA;
    }

    /**
     * @param eTA the eTA to set
     */
    public void seteTA(LocalDate eTA) {
        this.eTA = eTA;
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
     * @return the shipmentPriority
     */
    public String getShipmentPriority() {
        return shipmentPriority;
    }

    /**
     * @param shipmentPriority the shipmentPriority to set
     */
    public void setShipmentPriority(String shipmentPriority) {
        this.shipmentPriority = shipmentPriority;
    }

    /**
     * @return the openTime
     */
    public LocalTime getOpenTime() {
        return openTime;
    }

    /**
     * @param openTime the openTime to set
     */
    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    /**
     * @return the openDate
     */
    public LocalDate getOpenDate() {
        return openDate;
    }

    /**
     * @param openDate the openDate to set
     */
    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    /**
     * @return the closeDate
     */
    public LocalDate getCloseDate() {
        return closeDate;
    }

    /**
     * @param closeDate the closeDate to set
     */
    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * @return the closeTime
     */
    public LocalTime getCloseTime() {
        return closeTime;
    }

    /**
     * @param closeTime the closeTime to set
     */
    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * @return the product
     */
    public List<PIPOTransFormOut> getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(List<PIPOTransFormOut> product) {
        this.product = product;
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
     * @return the siDocument
     */
    public String getSiDocument() {
        return siDocument;
    }

    /**
     * @param siDocument the siDocument to set
     */
    public void setSiDocument(String siDocument) {
        this.siDocument = siDocument;
    }

    /**
     * @return the bookingConfirmation
     */
    public String getBookingConfirmation() {
        return bookingConfirmation;
    }

    /**
     * @param bookingConfirmation the bookingConfirmation to set
     */
    public void setBookingConfirmation(String bookingConfirmation) {
        this.bookingConfirmation = bookingConfirmation;
    }

}
