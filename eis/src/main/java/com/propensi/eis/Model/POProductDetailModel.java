package com.propensi.eis.Model;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "po_product_detail")
public class POProductDetailModel implements Serializable {
    private static final long serialVersionUID = 1467442787240545479L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
    @Column(name = "container_Quantity", nullable = false)
    private int containerQuantity;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "quantity_remaining", nullable = false)
    private int quantityRemaining;

    /**
     * ======================================================== Foreign key
     * ========================================================
     */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "PO_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private PurchaseOrderModel purchaseOrder;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "container_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ContainerModel container;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ProductModel product;

    @OneToMany(mappedBy = "poProdukDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PIPOTransactionModel> PIPOtransaction;

    /**
     * ======================================================== Setter Getter
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
     * @return the quantityRemaining
     */
    public int getQuantityRemaining() {
        return quantityRemaining;
    }

    /**
     * @param quantityRemaining the quantityRemaining to set
     */
    public void setQuantityRemaining(int quantityRemaining) {
        this.quantityRemaining = quantityRemaining;
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
     * @return the container
     */
    public ContainerModel getContainer() {
        System.out.println("masuk sini gaa");
        return container;
    }

    /**
     * @param container the container to set
     */
    public void setContainer(ContainerModel container) {
        this.container = container;
    }

    /**
     * @return the product
     */
    public ProductModel getProduct() {
        System.out.println("masuk");
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductModel product) {
        this.product = product;
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
     * @return the pIPOtransaction
     */
    @JsonIgnore
    public List<PIPOTransactionModel> getPIPOtransaction() {
        return PIPOtransaction;
    }

    /**
     * @param pIPOtransaction the pIPOtransaction to set
     */
    public void setPIPOtransaction(List<PIPOTransactionModel> pIPOtransaction) {
        PIPOtransaction = pIPOtransaction;
    }

}