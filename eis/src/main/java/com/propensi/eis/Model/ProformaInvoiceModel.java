package com.propensi.eis.Model;

import java.io.Serializable;
import java.time.LocalDate;
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
// import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "proforma_invoice")
public class ProformaInvoiceModel implements Serializable {

    private static final long serialVersionUID = 9044870275567531389L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "number", unique = true, nullable = false)
    private String number;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Size(max = 50)
    @Column(name = "country_of_origin", nullable = false)
    private String countryOfOrigin;

    @NotNull
    @Size(max = 50)
    @Column(name = "port_of_loading", nullable = false)
    private String portOfLoading;

    @NotNull
    @Size(max = 50)
    @Column(name = "port_of_destination", nullable = false)
    private String portOfDestination;

    @Size(max = 50)
    @Column(name = "final_destination", nullable = false)
    private String finalDestination;

    @NotNull
    @Column(name = "delivery_term", nullable = false)
    private LocalDate deliveryTerm;

    @NotNull
    @Column(name = "payment_term", nullable = false)
    private String paymentTerm;

    @Size(max = 255)
    @Column(name = "term_and_condition")
    private String termAndCondition;

    @Column(name = "eta_date", nullable = false)
    private LocalDate etaDate;

    @Column(name = "etd_date", nullable = false)
    private LocalDate etdDate;

    @Column(name = "complete_status", nullable = false)
    private Boolean completed;

    @Column(name = "complete_date")
    private LocalDate completeDate;

    /**
     * ======================================================== Foreign key
     * ========================================================
     */

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "payment_method_id", referencedColumnName = "id", nullable
    // = false)
    // @OnDelete(action = OnDeleteAction.NO_ACTION)
    // private PaymentMethodModel paymentMethod;

    @OneToMany(mappedBy = "proformaInvoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PIProductDetailModel> detailProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private CustomerModel customer;

    @OneToMany(mappedBy = "proformaInvoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ShippingInstructionModel> shippingInstruction;

    /**
     * ======================================================== Setter Getter
     * ========================================================
     */

    /**
     * @return long return the id
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
     * @param finalDestination the finalDestination to set
     */
    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }

    /**
     * @return String return the finalDestination
     */
    public String getFinalDestination() {
        return finalDestination;
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
     * @return List<PIProductDetailModel> return the detailProduct
     */
    public List<PIProductDetailModel> getDetailProduct() {
        return detailProduct;
    }

    /**
     * @param detailProduct the detailProduct to set
     */
    public void setDetailProduct(List<PIProductDetailModel> detailProduct) {
        this.detailProduct = detailProduct;
    }

    /**
     * @return CustomerModel return the customer
     */
    public CustomerModel getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    /**
     * @return List<ShippingInstructionModel> return the shippingInstruction
     */
    public List<ShippingInstructionModel> getShippingInstruction() {
        return shippingInstruction;
    }

    /**
     * @param shippingInstruction the shippingInstruction to set
     */
    public void setShippingInstruction(List<ShippingInstructionModel> shippingInstruction) {
        this.shippingInstruction = shippingInstruction;
    }

    /**
     * @return the completed
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    /**
     * @return the completeDate
     */
    public LocalDate getCompleteDate() {
        return completeDate;
    }

    /**
     * @param completeDate the completeDate to set
     */
    public void setCompleteDate(LocalDate completeDate) {
        this.completeDate = completeDate;
    }
}