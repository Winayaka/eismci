package com.propensi.eis.Model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "staff")
public class StaffModel implements Serializable {
    private static final long serialVersionUID = 5897103674704119389L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long staffId;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(max = 50)
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "phone_num", nullable = false)
    private String phoneNum;

    @NotNull
    @Column(name = "gender", nullable = false)
    private Boolean gender;

    @NotNull
    @Size(max = 50)
    @Column(name = "birth_place", nullable = false)
    private String birthplace;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    /**
     * ======================================================== Foreign key
     * ========================================================
     * Foreign key
     * ========================================================
    */
    
    //One-to-One
    @JsonIgnore
    @OneToOne(mappedBy = "staff")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserModel user;

    @OneToOne(mappedBy = "staff")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ShipmentDocumentNotesModel documentNotes;

    //One-to-Many
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ShippingInstructionModel> shippingInstruction;

    /**
     * ======================================================== Getter Setter
     * ========================================================
     */

    /**
     * @return the staffId
     */
    public long getStaffId() {
        return staffId;
    }

    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(long staffId) {
        this.staffId = staffId;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * @param phoneNum the phoneNum to set
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * @return the gender
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * @return the birthplace
     */
    public String getBirthplace() {
        return birthplace;
    }

    /**
     * @param birthplace the birthplace to set
     */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
     * @return the shippingInstruction
     */
    @JsonIgnore
    public List<ShippingInstructionModel> getShippingInstruction() {
        return shippingInstruction;
    }

    /**
     * @param shippingInstruction the shippingInstruction to set
     */
    public void setShippingInstruction(List<ShippingInstructionModel> shippingInstruction) {
        this.shippingInstruction = shippingInstruction;
    }

    /**
     * @return the documentNotes
     */
    @JsonIgnore
    public ShipmentDocumentNotesModel getDocumentNotes() {
        return documentNotes;
    }

    /**
     * @param documentNotes the documentNotes to set
     */
    public void setDocumentNotes(ShipmentDocumentNotesModel documentNotes) {
        this.documentNotes = documentNotes;
    }






}