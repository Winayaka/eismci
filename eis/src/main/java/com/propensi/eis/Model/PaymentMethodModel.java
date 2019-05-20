package com.propensi.eis.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "payment_method")
public class PaymentMethodModel implements Serializable {
	private static final long serialVersionUID = -6951174902153323457L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(max = 50)
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Size(max = 255)
	@Column(name = "note", nullable = false)
	private String note;

	@NotNull
	@Size(max = 50)
	@Column(name = "status", nullable = false)
	private String status;

	@NotNull
	@Column(name = "lc_status", nullable = false)
	private boolean lcStatus;

	/**
	 * ======================================================== Foreign key
	 * ========================================================
	 */

	// @OneToOne(mappedBy = "paymentMethod", fetch = FetchType.LAZY, cascade =
	// CascadeType.ALL)
	// private ProformaInvoiceModel PI;

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
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the lcStatus
	 */
	public boolean getLcStatus() {
		return lcStatus;
	}

	/**
	 * @param lcStatus the lcStatus to set
	 */
	public void setLcStatus(boolean lcStatus) {
		this.lcStatus = lcStatus;
	}

	// /**
	// * @return the pI
	// */
	// public ProformaInvoiceModel getPI() {
	// return PI;
	// }

	// /**
	// * @param pI the pI to set
	// */
	// public void setPI(ProformaInvoiceModel pI) {
	// PI = pI;
	// }

}