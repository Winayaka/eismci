package com.propensi.eis.RestModel;

import java.io.Serializable;

import com.propensi.eis.Model.ContainerModel;
import com.propensi.eis.Model.ProductModel;

public class PIDetailProductForm implements Serializable {
    private static final long serialVersionUID = 2184710604846491439L;

    private long id;
    private String productName;
    private int quantity;
    private int freightCost;
    private int pricePerUnit;
    private int quantitySent;
    private int containerQuantity;
    private String containerName;

    // For update PI
    private ProductModel product;
    private ContainerModel container;

    /**
     * @return Object return the product
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
     * @return Object return the container
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
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
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
     * @return the containerName
     */
    public String getContainerName() {
        return containerName;
    }

    /**
     * @param containerName the containerName to set
     */
    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

}