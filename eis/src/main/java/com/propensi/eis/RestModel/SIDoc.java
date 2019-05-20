package com.propensi.eis.RestModel;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class SIDoc {

    private String name;
    private String fileName;
    private String status;
    private Long siid;
    private LocalDate createdDate;
    private LocalDate lastChange;
    private List<DocumentNotes> documentNotes;

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
     * @return the siid
     */
    public Long getSiid() {
        return siid;
    }

    /**
     * @param siid the siid to set
     */
    public void setSiid(Long siid) {
        this.siid = siid;
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
     * @return the documentNotes
     */
    public List<DocumentNotes> getDocumentNotes() {
        return documentNotes;
    }

    /**
     * @param documentNotes the documentNotes to set
     */
    public void setDocumentNotes(List<DocumentNotes> documentNotes) {
        this.documentNotes = documentNotes;
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