package com.propensi.eis.Rest;

import com.propensi.eis.Model.StaffModel;
import com.propensi.eis.Model.UserModel;

import java.sql.Date;

/**
 * untuk add staff
 */
public class ReportModel {

    private int numberOfTransaction;
    private long revenue;
    private String mostProduct;

    /**
     * @return the numberOfTransaction
     */
    public int getNumberOfTransaction() {
        return numberOfTransaction;
    }

    /**
     * @param numberOfTransaction the numberOfTransaction to set
     */
    public void setNumberOfTransaction(int numberOfTransaction) {
        this.numberOfTransaction = numberOfTransaction;
    }

    /**
     * @return the revenue
     */
    public long getRevenue() {
        return revenue;
    }

    /**
     * @param revenue the revenue to set
     */
    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    /**
     * @return the mostProduct
     */
    public String getMostProduct() {
        return mostProduct;
    }

    /**
     * @param mostProduct the mostProduct to set
     */
    public void setMostProduct(String mostProduct) {
        this.mostProduct = mostProduct;
    }
}