package com.propensi.eis.RestModel;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ListNotes implements Serializable{

    private Long documentId;
    private List<DocumentNotes> doc;

    /**
     * @return the doc
     */
    public List<DocumentNotes> getDoc() {
        return doc;
    }

    /**
     * @param doc the doc to set
     */
    public void setDoc(List<DocumentNotes> doc) {
        this.doc = doc;
    }

    /**
     * @return the documentId
     */
    public Long getDocumentId() {
        return documentId;
    }

    /**
     * @param documentId the documentId to set
     */
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    
    
}