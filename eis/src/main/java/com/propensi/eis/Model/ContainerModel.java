package com.propensi.eis.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "container")
public class ContainerModel implements Serializable {

	private static final long serialVersionUID = -1252515652177617447L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@NotNull
	@Size(max = 50)
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "quantity", nullable = false)
	private int quantity;

    //One-to-Many
	@OneToMany(mappedBy = "container", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
    private List<PIProductDetailModel> PIDetail;

	//One-to-Many
	@OneToMany(mappedBy = "container", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<POProductDetailModel> PODetail;
	
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
	 * @return the pIDetail
	 */
	@JsonIgnore
	public List<PIProductDetailModel> getPIDetail() {
		return PIDetail;
	}

	/**
	 * @param pIDetail the pIDetail to set
	 */
	public void setPIDetail(List<PIProductDetailModel> pIDetail) {
		PIDetail = pIDetail;
	}

	// public void setPIDetail(PIProductDetailModel pIDetail) {
	// PIDetail = pIDetail;
	// }

	/**
	 * @return the pODetail
	 */
	@JsonIgnore
	public List<POProductDetailModel> getPODetail() {
		return PODetail;
	}

	/**
	 * @param pODetail the pODetail to set
	 */
	public void setPODetail(List<POProductDetailModel> pODetail) {
		PODetail = pODetail;
	}

    

	
    

}