package com.propensi.eis.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shipment_product_detail")
public class ShipmentProductDetailModel implements Serializable {
    private static final long serialVersionUID = 4336454737950925262L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private int quantity;

    /**
     * ======================================================== Foreign key
     * ========================================================
     */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_si", referencedColumnName = "id")
    @JsonIgnore
    private ShippingInstructionModel shippingInstruction;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pi_po_transaction", referencedColumnName = "id")
    @JsonIgnore
    private PIPOTransactionModel PIPOTransaction;

    /**
     * ======================================================== Setter Getter
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
     * @return the quantity
     */
    public int getquantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the shippingInstruction
     */
    public ShippingInstructionModel getShippingInstruction() {
        return shippingInstruction;
    }

    /**
     * @param shippingInstruction the shippingInstruction to set
     */
    public void setShippingInstruction(ShippingInstructionModel shippingInstruction) {
        this.shippingInstruction = shippingInstruction;
    }

    /**
     * @return the pIPOTransaction
     */
    @JsonIgnore
    public PIPOTransactionModel getPIPOTransaction() {
        return PIPOTransaction;
    }

    /**
     * @param pIPOTransaction the pIPOTransaction to set
     */
    public void setPIPOTransaction(PIPOTransactionModel pIPOTransaction) {
        PIPOTransaction = pIPOTransaction;
    }

}