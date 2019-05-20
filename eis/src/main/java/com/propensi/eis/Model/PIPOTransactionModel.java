package com.propensi.eis.Model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pi_po_transaction")
public class PIPOTransactionModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    /**
     * ======================================================== Foreign key
     * ========================================================
     */

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "pi_produk_detail_Id", referencedColumnName = "id")
    @JsonIgnore
    private PIProductDetailModel piProdukDetail;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "po_produk_detail_id", referencedColumnName = "id")
    @JsonIgnore
    private POProductDetailModel poProdukDetail;

    @OneToMany(mappedBy = "PIPOTransaction", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<ShipmentProductDetailModel> shippingDetail;

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
     * @return PIProductDetailModel return the piProdukDetail
     */
    public PIProductDetailModel getPiProdukDetail() {
        return piProdukDetail;
    }

    /**
     * @param piProdukDetail the piProdukDetail to set
     */
    public void setPiProdukDetail(PIProductDetailModel piProdukDetail) {
        this.piProdukDetail = piProdukDetail;
    }

    /**
     * @return POProductDetailModel return the poProdukDetail
     */
    public POProductDetailModel getPoProdukDetail() {
        return poProdukDetail;
    }

    /**
     * @param poProdukDetail the poProdukDetail to set
     */
    public void setPoProdukDetail(POProductDetailModel poProdukDetail) {
        this.poProdukDetail = poProdukDetail;
    }

    /**
     * @return List<ShipmentProductDetailModel> return the shippingDetail
     */
    public List<ShipmentProductDetailModel> getShippingDetail() {
        return shippingDetail;
    }

    /**
     * @param shippingDetail the shippingDetail to set
     */
    public void setShippingDetail(List<ShipmentProductDetailModel> shippingDetail) {
        this.shippingDetail = shippingDetail;
    }

}