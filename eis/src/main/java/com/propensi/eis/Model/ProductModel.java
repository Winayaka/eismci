package com.propensi.eis.Model;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "product")
public class ProductModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "gross_Weight", nullable = false)
    private double grossWeight;

    @NotNull
    @Column(name = "net_Weight", nullable = false)
    private double netWeight;

    @NotNull
    @Size(max = 50)
    @Column(name = "product_unit", nullable = false)
    private String productUnit;

    @NotNull
    @Column(name = "product_description", nullable = false)
    private String productDesc;

    @NotNull
    @Column(name = "quantity_left", nullable = false)
    private int quantityLeft = 0;

    /**
     * ======================================================== Foreign key
     * ========================================================
     */

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	  private List<POProductDetailModel> poProductDetail;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	  private List<PIProductDetailModel> piProductDetail;

   

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the grossWeight
     */
    public double getGrossWeight() {
        return grossWeight;
    }

    /**
     * @param grossWeight the grossWeight to set
     */
    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    /**
     * @return the netWeight
     */
    public double getNetWeight() {
        return netWeight;
    }

    /**
     * @param netWeight the netWeight to set
     */
    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    /**
     * @return the productUnit
     */
    public String getProductUnit() {
        return productUnit;
    }

    /**
     * @param productUnit the productUnit to set
     */
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    /**
     * @return the productDesc
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * @param productDesc the productDesc to set
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * @return the quantityLeft
     */
    public int getQuantityLeft() {
        return quantityLeft;
    }

    /**
     * @param quantityLeft the quantityLeft to set
     */
    public void setQuantityLeft(int quantityLeft) {
        this.quantityLeft = quantityLeft;
    }

    /**
     * @return the poProductDetail
     */
    public List<POProductDetailModel> getPoProductDetail() {
        return poProductDetail;
    }

    /**
     * @param poProductDetail the poProductDetail to set
     */
    public void setPoProductDetail(List<POProductDetailModel> poProductDetail) {
        this.poProductDetail = poProductDetail;
    }

    /**
     * @return the piProductDetail
     */
    public List<PIProductDetailModel> getPiProductDetail() {
        return piProductDetail;
    }

    /**
     * @param piProductDetail the piProductDetail to set
     */
    public void setPiProductDetail(List<PIProductDetailModel> piProductDetail) {
        this.piProductDetail = piProductDetail;
    }

}