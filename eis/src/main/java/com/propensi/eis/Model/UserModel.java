package com.propensi.eis.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = "user")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 3358344709517334834L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "username", nullable = false)
    private String username;

    @JsonIgnore
    @NotNull
    @Size(max = 50)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Size(max = 50)
    @Column(name = "role", nullable = false)
    private String role;

    @NotNull
    @Column(name = "ActiveStatus", nullable = false)
    private Boolean activeStatus;

    /**
     * ======================================================== Foreign key
     * ========================================================
     */

    // One-to-One
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StaffModel staff;

    // One-to-One
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CustomerModel customer;

    /**
     * ======================================================== Getter Setter
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the activeStatus
     */
    public Boolean getActiveStatus() {
        return activeStatus;
    }

    /**
     * @param activeStatus the activeStatus to set
     */
    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    /**
     * @return the staff
     */
    @JsonIgnore
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
     * @return the customer
     */
    @JsonIgnore
    public CustomerModel getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

}