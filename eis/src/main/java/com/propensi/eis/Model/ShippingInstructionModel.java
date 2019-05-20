package com.propensi.eis.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "shipping_instruction")
public class ShippingInstructionModel implements Serializable {
    private static final long serialVersionUID = 8454160409358353113L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    //@Pattern(regexp = "^[P]{1}.*$", message = "wrong format number")
    @Size(max = 20)
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @NotNull
    @Size(max = 50)
    @Column(name = "Shipment_Status", nullable = false)
    private String shipmentStatus;

    @Column(name = "ETA")
    private LocalDate ETA;

    @NotNull
    @Column(name = "ETD", nullable = false)
    private LocalDate ETD;

    @NotNull
    @Size(max = 50)
    @Column(name = "PoL", nullable = false)
    private String PoL;

    @NotNull
    @Size(max = 50)
    @Column(name = "PoD", nullable = false)
    private String PoD;

    @NotNull
    @Size(max = 50)
    @Column(name = "Final_Destination", nullable = false)
    private String finalDestination;

    @Column(name = "open_date")
    private LocalDate openDate;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_date")
    private LocalDate closeDate;

    @Column(name = "Close_Time")
    private LocalTime closeTime;

    @NotNull
    @Column(name = "liner", nullable = false)
    private String liner;

    @NotNull
    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @NotNull
    @Column(name = "shipment_priority", nullable = false)
    private String shipmentPriority = "Normal";

    @NotNull
    @Column(name = "created_date")
    private LocalDate createDate;

    @Column(name = "finished_date")
    private LocalDate finishedDate;

    @Column(name = "invoice")
    private String invoice;
    
    @Column(name= "si_Document")
    private String siDocument;

    @Column(name= "booking_confirmation")
    private String bookingConfirmation;

   /**
     * ========================================================
     * Foreign key
     * ========================================================
    */    
 
    //Many-to-One
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PO_Id", referencedColumnName = "id")
    @JsonIgnore
    private PurchaseOrderModel purchaseOrder;

    // one-to-one
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vessel", referencedColumnName = "id")
    private VesselModel vessel;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_invoice", referencedColumnName = "id")
    // private InvoiceModel invoice;

    //One-to-Many
    @OneToMany(mappedBy = "shippingInstruction",fetch = FetchType.LAZY)
    private List<ShipmentProductDetailModel> shipmentProductDetail;

    @OneToMany(mappedBy = "shippingInstruction",fetch = FetchType.LAZY)
    private List<ShipmentDocumentModel> shipmentDocument;

    //Many-to-One
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "PI_Id", referencedColumnName = "id")
    @JsonIgnore
    private ProformaInvoiceModel proformaInvoice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Staff_Id", referencedColumnName = "id")
    @JsonIgnore
    private StaffModel staff;

    /**
     * ======================================================== 
     * Setter Getter
     * ========================================================
     */

    /**
     * @return the id
     */
    public long getId() {
      return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
      this.id = id;
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
     * @return the eTA
     */
    public LocalDate getETA() {
      return ETA;
    }

    /**
     * @param eTA the eTA to set
     */
    public void setETA(LocalDate eTA) {
      ETA = eTA;
    }

    /**
     * @return the eTD
     */
    public LocalDate getETD() {
      return ETD;
    }

    /**
     * @param eTD the eTD to set
     */
    public void setETD(LocalDate eTD) {
      ETD = eTD;
    }

    /**
     * @return the poL
     */
    public String getPoL() {
      return PoL;
    }

    /**
     * @param poL the poL to set
     */
    public void setPoL(String poL) {
      PoL = poL;
    }

    /**
     * @return the poD
     */
    public String getPoD() {
      return PoD;
    }

    /**
     * @param poD the poD to set
     */
    public void setPoD(String poD) {
      PoD = poD;
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
     * @return the createDate
     */
    public LocalDate getCreateDate() {
      return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(LocalDate createDate) {
      this.createDate = createDate;
    }

    /**
     * @return the finishedDate
     */
    public LocalDate getFinishedDate() {
      return finishedDate;
    }

    /**
     * @param finishedDate the finishedDate to set
     */
    public void setFinishedDate(LocalDate finishedDate) {
      this.finishedDate = finishedDate;
    }

    /**
     * @return the purchaseOrder
     */
    public PurchaseOrderModel getPurchaseOrder() {
      return purchaseOrder;
    }

    /**
     * @param purchaseOrder the purchaseOrder to set
     */
    public void setPurchaseOrder(PurchaseOrderModel purchaseOrder) {
      this.purchaseOrder = purchaseOrder;
    }

    /**
     * @return the vessel
     */
    public VesselModel getVessel() {
      return vessel;
    }

    /**
     * @param vessel the vessel to set
     */
    public void setVessel(VesselModel vessel) {
      this.vessel = vessel;
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
     * @return the shipmentProductDetail
     */
    public List<ShipmentProductDetailModel> getShipmentProductDetail() {
      return shipmentProductDetail;
    }

    /**
     * @param shipmentProductDetail the shipmentProductDetail to set
     */
    public void setShipmentProductDetail(List<ShipmentProductDetailModel> shipmentProductDetail) {
      this.shipmentProductDetail = shipmentProductDetail;
    }

    /**
     * @return the proformaInvoice
     */
    public ProformaInvoiceModel getProformaInvoice() {
      return proformaInvoice;
    }

    /**
     * @param proformaInvoice the proformaInvoice to set
     */
    public void setProformaInvoice(ProformaInvoiceModel proformaInvoice) {
      this.proformaInvoice = proformaInvoice;
    }

    /**
     * @return the staff
     */
    public StaffModel getStaff() {
      return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(StaffModel staff) {
      this.staff = staff;
    }

  /**
   * @return the shipmentDocument
   */
  public List<ShipmentDocumentModel> getShipmentDocument() {
    return shipmentDocument;
  }

  /**
   * @param shipmentDocument the shipmentDocument to set
   */
  public void setShipmentDocument(List<ShipmentDocumentModel> shipmentDocument) {
    this.shipmentDocument = shipmentDocument;
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