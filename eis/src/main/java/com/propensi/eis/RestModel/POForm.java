package com.propensi.eis.RestModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class POForm implements Serializable {
    private static final long serialVersionUID = -6457401473037594897L;
    // untuk PO
    private String number;
    private LocalDate shipment;
    private LocalDate date;
    private String supplierName;
    private String vessel;
    private String poNumSupplier;

    private List<POProductDetailForm> product;

    /**
     * //untuk POProductDetail private String productName; private int quantity;
     * private int freightCost; private int grossWeight; private int netWeight;
     * private String productUnit; private String productDesc; private int
     * pricePerUnit; private int quantityRemaining;
     */

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @return the product
     */
    public List<POProductDetailForm> getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(List<POProductDetailForm> product) {
        this.product = product;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
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

   
}