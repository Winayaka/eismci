package com.propensi.eis.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "shipment_document")
public class ShipmentDocumentModel implements Serializable {
    private static final long serialVersionUID = 5321920275570654905L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @Transient
    @Column(name = "link")
    private MultipartFile link;

    @Column(name = "last_change")
    private LocalDate lastChange;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "status")
    private String status;

    @Column(name="file_name")
    private String fileName;

    /**
     * ======================================================== Fereign Key
     * ========================================================
     */

    //many-to-One
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "si_id", referencedColumnName = "id")
    @JsonIgnore
    private ShippingInstructionModel shippingInstruction;

    //one-to-many
    @OneToMany(mappedBy = "shipmentDocument",fetch = FetchType.LAZY)
    private List<ShipmentDocumentNotesModel> documentNotes;

    /**
     * ========================================================= Setter Getter
     * =========================================================
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
     * @return the link
     */
    public MultipartFile getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(MultipartFile link) {
        this.link = link;
    }

    /**
     * @return the shippingInstruction
     */
    public ShippingInstructionModel getShippingInstruction() {
        return shippingInstruction;
    }

    /**
     * @param shippingInstruction the shippingInstruction to set
     */
    public void setShippingInstruction(ShippingInstructionModel shippingInstruction) {
        this.shippingInstruction = shippingInstruction;
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
     * @return the documentNotes
     */
    public List<ShipmentDocumentNotesModel> getDocumentNotes() {
        return documentNotes;
    }

    /**
     * @param documentNotes the documentNotes to set
     */
    public void setDocumentNotes(List<ShipmentDocumentNotesModel> documentNotes) {
        this.documentNotes = documentNotes;
    }

    /**
     * @return the lastChange
     */
    public LocalDate getLastChange() {
        return lastChange;
    }

    /**
     * @param lastChange the lastChange to set
     */
    public void setLastChange(LocalDate lastChange) {
        this.lastChange = lastChange;
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
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



    
}