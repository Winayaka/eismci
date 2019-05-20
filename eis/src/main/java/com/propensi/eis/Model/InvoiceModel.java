package com.propensi.eis.Model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "invoice")
public class InvoiceModel implements Serializable {

    private static final long serialVersionUID = 657517938670988058L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "number", unique = true)
    private String number;

    @Column(name = "date")
    private LocalDate date;

    /**
     * ======================================================== Foreign key
     * ========================================================
     */

    // //One-to-One
    // @OneToOne(mappedBy = "invoice")
    // @JsonIgnore
    // private ShippingInstructionModel SIId;

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

    // /**
    //  * @return the sIId
    //  */
    // @JsonIgnore
    // public ShippingInstructionModel getSIId() {
    //     return SIId;
    // }

    // /**
    //  * @param sIId the sIId to set
    //  */
    // public void setSIId(ShippingInstructionModel sIId) {
    //     SIId = sIId;
    // }

}