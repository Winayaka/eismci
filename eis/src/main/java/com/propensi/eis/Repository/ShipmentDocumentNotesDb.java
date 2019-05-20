package com.propensi.eis.Repository;

import java.util.List;

import com.propensi.eis.Model.ShipmentDocumentNotesModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentDocumentNotesDb extends JpaRepository<ShipmentDocumentNotesModel, Long>{

    List<ShipmentDocumentNotesModel> findByShipmentDocumentId(Long id);
        
}