package com.propensi.eis.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer") // relasi ke proforma invoice dan user
public class CustomerModel implements Serializable {
	private static final long serialVersionUID = -1897762486866854669L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(max = 50)
	@Column(name = "title", nullable = false) // company atau individu (mrs/mr)
	private String title;

	@NotNull
	@Size(max = 50)
	@Column(name = "cust_name", nullable = false)
	private String custName;

	@NotNull
	@Size(max = 50)
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotNull
	@Size(max = 50)
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@NotNull
	@Size(max = 50)
	@Column(name = "email", nullable = false)
	private String email;

	@NotNull
	@Size(max = 20)
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@NotNull
	@Size(max = 50)
	@Column(name = "street", nullable = false)
	private String street;

	@NotNull
	@Size(max = 20)
	@Column(name = "postal_code", nullable = false)
	private String postalCode;

	@NotNull
	@Size(max = 20)
	@Column(name = "address_number", nullable = false)
	private String addressNumber;

	@NotNull
	@Size(max = 50)
	@Column(name = "country", nullable = false)
	private String country;

	@NotNull
	@Size(max = 50)
	@Column(name = "region", nullable = false)
	private String region;

	@NotNull
	@Size(max = 50)
	@Column(name = "city", nullable = false)
	private String city;

	@NotNull
	@Size(max = 255)
	@Column(name = "description", nullable = false)
	private String description;

	/**
	 * ======================================================== Foreign key
	 * ========================================================
	 */

	// One-to-One
	@OneToOne(mappedBy = "customer", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserModel user;

	@OneToOne(mappedBy = "customer", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private ShipmentDocumentNotesModel documentNotes;

	// One-to-Many
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")

	private List<ProformaInvoiceModel> listPI;

	/**
	 * ======================================================== Setter Getter
	 * ========================================================
	 */

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddressNumber() {
		return this.addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the user
	 */
	public UserModel getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserModel user) {
		this.user = user;
	}

	/**
	 * @return the pI
	 */
	public List<ProformaInvoiceModel> getPI() {
		return listPI;
	}

	/**
	 * @param pI the pI to set
	 */
	public void setPI(List<ProformaInvoiceModel> pI) {
		listPI = pI;
	}

}
