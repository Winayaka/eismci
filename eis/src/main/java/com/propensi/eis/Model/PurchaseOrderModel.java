package com.propensi.eis.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "PurchaseOrder")
public class PurchaseOrderModel implements Serializable {
    private static final long serialVersionUID = 7555271471837914113L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 225)
    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @NotNull
    @Size(max = 225)
    @Column(name = "supplier", nullable = false)
    private String namaSupplier;

    @NotNull
    @Size(max = 225)
    @Column(name = "vessel", nullable = false)
    private String vessel;

    @Column(name = "shipment")
    private LocalDate shipment; //ETA

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "po_supplier", unique = true)
    private String poNumSupplier;

    /**
     * ========================================================
     * Foreign Key
     * ========================================================
    */
    
    @OneToMany(mappedBy = "purchaseOrder",fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<POProductDetailModel> purchaseOrder;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShippingInstructionModel> shippingInstruction;

     /**
     * ========================================================
     */

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
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
     * @return the namaSupplier
     */
    public String getNamaSupplier() {
        return namaSupplier;
    }

    /**
     * @param namaSupplier the namaSupplier to set
     */
    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    /**
     * @return the vessel
     */
    public String getVessel() {
        return vessel;
    }

    /**
     * @param vessel the vessel to set
     */
    public void setVessel(String vessel) {
        this.vessel = vessel;
    }



    /**
     * @return the poNumSupplier
     */
    public String getPoNumSupplier() {
        return poNumSupplier;
    }

    /**
     * @param poNumSupplier the poNumSupplier to set
     */
    public void setPoNumSupplier(String poNumSupplier) {
        this.poNumSupplier = poNumSupplier;
    }

    /**
     * @return the purchaseOrder
     */
    public List<POProductDetailModel> getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * @param purchaseOrder the purchaseOrder to set
     */
    public void setPurchaseOrder(List<POProductDetailModel> purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    /**
     * @return the shippingInstruction
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
     * @return the shipment
     */
    public LocalDate getShipment() {
        return shipment;
    }

    /**
     * @param shipment the shipment to set
     */
    public void setShipment(LocalDate shipment) {
        this.shipment = shipment;
    }

    /**
     * @return the date
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

   


   
}