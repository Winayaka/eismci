package com.propensi.eis.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pi_product_detail")
public class PIProductDetailModel implements Serializable {

    private static final long serialVersionUID = -6032969958263186797L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @NotNull
    @Column(name = "freight_cost", nullable = false)
    private int freightCost;

    @NotNull
    @Column(name = "price_per_unit", nullable = false)
    private int pricePerUnit;

    @NotNull
    @Column(name = "quantity_sent", nullable = false)
    private int quantitySent;

    @NotNull
    @Column(name = "container_quantity", nullable = false)
    private int containerQuantity;

    /**
     * ======================================================== Foreign key
     * ========================================================
     */

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PI_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private ProformaInvoiceModel proformaInvoice;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "container_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ContainerModel container;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ProductModel product;


    //@OneToMany(mappedBy = "piProdukDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonIgnore
    // private List<PIPOTransactionModel> PIPOTransaction;
  
    @OneToMany(mappedBy = "piProdukDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
	  private List<PIPOTransactionModel> PIPOTransaction;

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
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the freightCost
     */
    public int getFreightCost() {
        return freightCost;
    }

    /**
     * @param freightCost the freightCost to set
     */
    public void setFreightCost(int freightCost) {
        this.freightCost = freightCost;
    }

    /**
     * @return the pricePerUnit
     */
    public int getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     * @param pricePerUnit the pricePerUnit to set
     */
    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * @return the quantitySent
     */
    public int getQuantitySent() {
        return quantitySent;
    }

    /**
     * @param quantitySent the quantitySent to set
     */
    public void setQuantitySent(int quantitySent) {
        this.quantitySent = quantitySent;
    }

    /**
     * @return the containerQuantity
     */
    public int getContainerQuantity() {
        return containerQuantity;
    }

    /**
     * @param containerQuantity the containerQuantity to set
     */
    public void setContainerQuantity(int containerQuantity) {
        this.containerQuantity = containerQuantity;
    }

    /**
     * @return the container
     */
    public ContainerModel getContainer() {
        return container;
    }

    /**
     * @param container the container to set
     */
    public void setContainer(ContainerModel container) {
        this.container = container;
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
     * @return the product
     */
    public ProductModel getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductModel product) {
        this.product = product;
    }

    /**
     * @return the pIPOTransaction
     */
    public List<PIPOTransactionModel> getPIPOTransaction() {
        return PIPOTransaction;
    }

    /**
     * @param pIPOTransaction the pIPOTransaction to set
     */
    public void setPIPOTransaction(List<PIPOTransactionModel> pIPOTransaction) {
        PIPOTransaction = pIPOTransaction;
    }

}
