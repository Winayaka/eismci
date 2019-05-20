package com.propensi.eis.RestModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class SIOutput implements Serializable{

    private Long ShipmentId;
    private String piNum;
    private String siNum;
    private String liner;
    private LocalDate eta;
    private LocalDate etd;
    private String pod;
    private String pol;
    private String shipmentStatus;
    private String finalDestination;
    private LocalDate openDate;
    private LocalTime openTime;
    private LocalDate closeDate;
    private LocalTime closeTime;
    private String invoiceNum;
    private String vesselName;
    private String connectingVessel;
    private String paymentStatus;
    private String staff;
    private Long piid;
    private Long poid;
    private List<PIPOTransFormOut> produkDetail;

    //tambahan
    private String siDocument;
    private String bookingConfirmation;

    
    /**
     * @return the shipmentId
     */
    public Long getShipmentId() {
        return ShipmentId;
    }

    /**
     * @param shipmentId the shipmentId to set
     */
    public void setShipmentId(Long shipmentId) {
        ShipmentId = shipmentId;
    }

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
     * @return the siNum
     */
    public String getSiNum() {
        return siNum;
    }

    /**
     * @param siNum the siNum to set
     */
    public void setSiNum(String siNum) {
        this.siNum = siNum;
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
     * @return the staff
     */
    public String getStaff() {
        return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(String staff) {
        this.staff = staff;
    }

    /**
     * @return the piid
     */
    public Long getPiid() {
        return piid;
    }

    /**
     * @param piid the piid to set
     */
    public void setPiid(Long piid) {
        this.piid = piid;
    }

    /**
     * @return the poid
     */
    public Long getPoid() {
        return poid;
    }

    /**
     * @param poid the poid to set
     */
    public void setPoid(Long poid) {
        this.poid = poid;
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