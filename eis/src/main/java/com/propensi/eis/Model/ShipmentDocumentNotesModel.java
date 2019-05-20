package com.propensi.eis.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "shipment_document_notes")
public class ShipmentDocumentNotesModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "created_time    ")
    private LocalTime createdTime;    

    @Column(name = "content")
    private String content;

	@Column(name = "sender")
	private String sender;
     /**
     * ========================================================
     * Fereign Key
     * ========================================================
    */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private CustomerModel customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_staff", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private StaffModel staff;

    //Many-to-One
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "sd_id", referencedColumnName = "id")
    @JsonIgnore
    private ShipmentDocumentModel shipmentDocument;

    /**
     * ========================================================
     * Setter Getter
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
	 * @return the createdDate
	 */
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdTime
	 */
	public LocalTime getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the customer
	 */
	public CustomerModel getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	/**
	 * @return the staff
	 */
	public StaffModel getStaff() {
		return staff;
	}

	/**
	 * @param staff the staff to set
	 */
	public void setStaff(StaffModel staff) {
		this.staff = staff;
	}

	/**
	 * @return the shipmentDocument
	 */
	public ShipmentDocumentModel getShipmentDocument() {
		return shipmentDocument;
	}

	/**
	 * @param shipmentDocument the shipmentDocument to set
	 */
	public void setShipmentDocument(ShipmentDocumentModel shipmentDocument) {
		this.shipmentDocument = shipmentDocument;
	}

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}


}