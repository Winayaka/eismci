package com.propensi.eis.RestModel;

import java.time.LocalDate;

import com.propensi.eis.Model.ContainerModel;
import com.propensi.eis.Model.ProductModel;

import lombok.Data;

@Data
public class POProductDetailForm {
    //untuk Container
    //private long containerId;
    private String containerName;

    //untuk poprodukdetail
    //private Long idProduct;
    private long id;
    private String productName;
    private int quantity;
    private int freightCost;
    private int pricePerUnit;
    private int quantityRemaining;
    private int containerQuantity;
    private LocalDate date;

    //untuk update
    private ProductModel product;
    private ContainerModel container;
    
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
     * @return the containerQuantity
     */
    public int getcontainerQuantity() {
        return containerQuantity;
    }

    /**
     * @param containerQuantity the containerQuantity to set
     */
    public void setcontainerQuantity(int containerQuantity) {
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

   

}